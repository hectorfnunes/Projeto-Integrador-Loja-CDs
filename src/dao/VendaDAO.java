
package dao;

import conexao.Conexao;
import modelo.VendaHeader;
import modelo.VendaItem;
import modelo.CD;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class VendaDAO {
    private CDDAO cdDAO = new CDDAO();

    public void inserir(VendaHeader venda) throws SQLException {
        Connection conn = null;
        PreparedStatement psHeader = null;
        PreparedStatement psItem = null;
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            LocalDateTime dataHora = venda.getDataHora().truncatedTo(ChronoUnit.MINUTES);

            String sqlHeader = "INSERT INTO vendas_header (data_hora, id_cliente, id_funcionario, total) VALUES (?, ?, ?, ?)";
            psHeader = conn.prepareStatement(sqlHeader, Statement.RETURN_GENERATED_KEYS);
            psHeader.setTimestamp(1, Timestamp.valueOf(dataHora));
            if (venda.getIdCliente() != null) {
                psHeader.setInt(2, venda.getIdCliente());
            } else {
                psHeader.setNull(2, Types.INTEGER);
            }
            psHeader.setInt(3, venda.getIdFuncionario());
            psHeader.setBigDecimal(4, venda.getTotal());
            psHeader.executeUpdate();

            ResultSet rs = psHeader.getGeneratedKeys();
            if (rs.next()) {
                venda.setId(rs.getInt(1));
            }

            String sqlItem = "INSERT INTO vendas_itens (id_venda, id_cd, quantidade, subtotal) VALUES (?, ?, ?, ?)";
            psItem = conn.prepareStatement(sqlItem);
            for (VendaItem item : venda.getItens()) {
                psItem.setInt(1, venda.getId());
                psItem.setInt(2, item.getIdCd());
                psItem.setInt(3, item.getQuantidade());
                psItem.setBigDecimal(4, item.getSubtotal());
                psItem.executeUpdate();

                CD cd = cdDAO.buscarPorId(item.getIdCd(), conn);
                if (cd != null) {
                    cd.setEstoque(cd.getEstoque() - item.getQuantidade());
                    cdDAO.atualizar(cd, conn);
                }
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (psHeader != null) psHeader.close();
            if (psItem != null) psItem.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public List<VendaHeader> listarPorData(String data) throws SQLException {
        List<VendaHeader> vendas = new ArrayList<>();
        String sql = "SELECT vh.id, vh.data_hora, vh.total, vh.qnt_itens " +
                     "FROM vendas_header vh ";
        if (!data.isEmpty()) {
            sql += "WHERE DATE(vh.data_hora) = ? ";
        }
        sql += "ORDER BY vh.data_hora DESC";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (!data.isEmpty()) {
                ps.setDate(1, Date.valueOf(data));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VendaHeader venda = new VendaHeader();
                    venda.setId(rs.getInt("id"));
                    venda.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                    venda.setTotal(rs.getBigDecimal("total"));
                    venda.setQntItens(rs.getInt("qnt_itens"));
                    vendas.add(venda);
                }
            }
        }
        return vendas;
    }

    public VendaHeader buscarPorId(int id) throws SQLException {
        VendaHeader venda = null;
        String sqlHeader = "SELECT * FROM vendas_header WHERE id = ?";
        String sqlItens = "SELECT * FROM vendas_itens WHERE id_venda = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement psHeader = conn.prepareStatement(sqlHeader);
             PreparedStatement psItens = conn.prepareStatement(sqlItens)) {
            psHeader.setInt(1, id);
            try (ResultSet rsHeader = psHeader.executeQuery()) {
                if (rsHeader.next()) {
                    venda = new VendaHeader();
                    venda.setId(rsHeader.getInt("id"));
                    venda.setDataHora(rsHeader.getTimestamp("data_hora").toLocalDateTime());
                    venda.setIdCliente(rsHeader.getObject("id_cliente") != null ? rsHeader.getInt("id_cliente") : null);
                    venda.setIdFuncionario(rsHeader.getInt("id_funcionario"));
                    venda.setTotal(rsHeader.getBigDecimal("total"));
                }
            }
            if (venda != null) {
                psItens.setInt(1, id);
                try (ResultSet rsItens = psItens.executeQuery()) {
                    while (rsItens.next()) {
                        VendaItem item = new VendaItem();
                        item.setId(rsItens.getInt("id"));
                        item.setIdVenda(rsItens.getInt("id_venda"));
                        item.setIdCd(rsItens.getInt("id_cd"));
                        item.setQuantidade(rsItens.getInt("quantidade"));
                        item.setSubtotal(rsItens.getBigDecimal("subtotal"));
                        venda.addItem(item);
                    }
                }
            }
        }
        return venda;
    }

    public void atualizar(VendaHeader venda) throws SQLException {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            
            VendaHeader vendaAtual = buscarPorId(venda.getId());
            for (VendaItem item : vendaAtual.getItens()) {
                CD cd = cdDAO.buscarPorId(item.getIdCd(), conn);
                if (cd != null) {
                    cd.setEstoque(cd.getEstoque() + item.getQuantidade());
                    cdDAO.atualizar(cd, conn);
                }
            }

            LocalDateTime dataHora = venda.getDataHora().truncatedTo(ChronoUnit.MINUTES);

            String sqlHeader = "UPDATE vendas_header SET data_hora=?, id_cliente=?, id_funcionario=?, total=? WHERE id=?";
            try (PreparedStatement psHeader = conn.prepareStatement(sqlHeader)) {
                psHeader.setTimestamp(1, Timestamp.valueOf(dataHora));
                if (venda.getIdCliente() != null) {
                    psHeader.setInt(2, venda.getIdCliente());
                } else {
                    psHeader.setNull(2, Types.INTEGER);
                }
                psHeader.setInt(3, venda.getIdFuncionario());
                psHeader.setBigDecimal(4, venda.getTotal());
                psHeader.setInt(5, venda.getId());
                psHeader.executeUpdate();
            }

            // Deletar itens antigos (estoque já restaurado acima)
            String sqlDeleteItens = "DELETE FROM vendas_itens WHERE id_venda=?";
            try (PreparedStatement psDelete = conn.prepareStatement(sqlDeleteItens)) {
                psDelete.setInt(1, venda.getId());
                psDelete.executeUpdate();
            }

            String sqlItem = "INSERT INTO vendas_itens (id_venda, id_cd, quantidade, subtotal) VALUES (?, ?, ?, ?)";
            try (PreparedStatement psItem = conn.prepareStatement(sqlItem)) {
                for (VendaItem item : venda.getItens()) {
                    psItem.setInt(1, venda.getId());
                    psItem.setInt(2, item.getIdCd());
                    psItem.setInt(3, item.getQuantidade());
                    psItem.setBigDecimal(4, item.getSubtotal());
                    psItem.executeUpdate();

                    CD cd = cdDAO.buscarPorId(item.getIdCd(), conn);
                    if (cd != null) {
                        cd.setEstoque(cd.getEstoque() - item.getQuantidade());
                        cdDAO.atualizar(cd, conn);
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public void deletar(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            VendaHeader venda = buscarPorId(id);
            if (venda != null) {
                for (VendaItem item : venda.getItens()) {
                    CD cd = cdDAO.buscarPorId(item.getIdCd(), conn);
                    if (cd != null) {
                        cd.setEstoque(cd.getEstoque() + item.getQuantidade());
                        cdDAO.atualizar(cd, conn);
                    }
                }
            }

            String sql = "DELETE FROM vendas_header WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public BigDecimal calcularTotal(List<VendaItem> itens, Integer idCliente) {
        BigDecimal total = BigDecimal.ZERO;
        for (VendaItem item : itens) {
            total = total.add(item.getSubtotal());
        }
        if (idCliente != null) {
            total = total.multiply(new BigDecimal("0.9"));
        }
        return total;
    }
}