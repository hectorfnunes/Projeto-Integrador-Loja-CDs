package dao;

import conexao.Conexao;
import modelo.CD;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CDDAO {
    public void inserir(CD cd) throws SQLException {
        String sql = "INSERT INTO cds (titulo, artista, genero, ano, preco, estoque) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cd.getTitulo());
            ps.setString(2, cd.getArtista());
            ps.setString(3, cd.getGenero());
            ps.setInt(4, cd.getAno());
            ps.setBigDecimal(5, cd.getPreco());
            ps.setInt(6, cd.getEstoque());
            ps.executeUpdate();
        }
    }

    public List<CD> listar(String filtro) throws SQLException {
    List<CD> cds = new ArrayList<>();
    String sql = "SELECT * FROM cds";
    if (!filtro.isEmpty()) {
        sql += " WHERE titulo LIKE ? OR artista LIKE ?";
    }
    try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
        if (!filtro.isEmpty()) {
            ps.setString(1, "%" + filtro + "%");
            ps.setString(2, "%" + filtro + "%");
        }
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CD cd = new CD();
                cd.setId(rs.getInt("id"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setArtista(rs.getString("artista"));
                cd.setGenero(rs.getString("genero"));
                cd.setAno(rs.getInt("ano"));
                cd.setPreco(rs.getBigDecimal("preco"));
                cd.setEstoque(rs.getInt("estoque"));
                cds.add(cd);
            }
        }
    }
    return cds;
}

    
    public void atualizar(CD cd) throws SQLException {
        String sql = "UPDATE cds SET titulo=?, artista=?, genero=?, ano=?, preco=?, estoque=? WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cd.getTitulo());
            ps.setString(2, cd.getArtista());
            ps.setString(3, cd.getGenero());
            ps.setInt(4, cd.getAno());
            ps.setBigDecimal(5, cd.getPreco());
            ps.setInt(6, cd.getEstoque());
            ps.setInt(7, cd.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM cds WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public CD buscarPorId(int id) throws SQLException {
    CD cd = null;
    String sql = "SELECT * FROM cds WHERE id = ?";
    try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                cd = new CD();
                cd.setId(rs.getInt("id"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setArtista(rs.getString("artista"));
                cd.setGenero(rs.getString("genero"));
                cd.setAno(rs.getInt("ano"));
                cd.setPreco(rs.getBigDecimal("preco"));
                cd.setEstoque(rs.getInt("estoque"));
            }
        }
    }
    return cd;
}
    
    // --- Novos métodos sobrecarregados que usam a Connection passada (não fecham a Connection) ---
    public void atualizar(CD cd, Connection conn) throws SQLException {
        String sql = "UPDATE cds SET titulo=?, artista=?, genero=?, ano=?, preco=?, estoque=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cd.getTitulo());
            ps.setString(2, cd.getArtista());
            ps.setString(3, cd.getGenero());
            ps.setInt(4, cd.getAno());
            ps.setBigDecimal(5, cd.getPreco());
            ps.setInt(6, cd.getEstoque());
            ps.setInt(7, cd.getId());
            ps.executeUpdate();
        }
    }

    public CD buscarPorId(int id, Connection conn) throws SQLException {
        CD cd = null;
        String sql = "SELECT * FROM cds WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cd = new CD();
                    cd.setId(rs.getInt("id"));
                    cd.setTitulo(rs.getString("titulo"));
                    cd.setArtista(rs.getString("artista"));
                    cd.setGenero(rs.getString("genero"));
                    cd.setAno(rs.getInt("ano"));
                    cd.setPreco(rs.getBigDecimal("preco"));
                    cd.setEstoque(rs.getInt("estoque"));
                }
            }
        }
        return cd;
    }
    
}
