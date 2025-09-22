package dao;

import conexao.Conexao;
import modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public void inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, cargo, salario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getCargo());
            ps.setDouble(4, funcionario.getSalario());
            ps.executeUpdate();
        }
    }

    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setCargo(rs.getString("cargo"));
                func.setSalario(rs.getDouble("salario"));
                funcionarios.add(func);
            }
        }
        return funcionarios;
    }
    public List<Funcionario> listarPorFiltro(String filtro) throws SQLException {
    List<Funcionario> funcionarios = new ArrayList<>();
    String sql = "SELECT * FROM funcionarios WHERE nome LIKE ? OR cargo LIKE ?";
    try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
        String like = "%" + filtro + "%";
        ps.setString(1, like);
        ps.setString(2, like);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setCargo(rs.getString("cargo"));
                func.setSalario(rs.getDouble("salario"));
                funcionarios.add(func);
            }
        }
    }
    return funcionarios;
}

    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionarios SET nome=?, cpf=?, cargo=?, salario=? WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getCargo());
            ps.setDouble(4, funcionario.getSalario());
            ps.setInt(5, funcionario.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public Funcionario buscarPorId(int id) throws SQLException {
    Funcionario func = null;
    String sql = "SELECT * FROM funcionarios WHERE id = ?";
    try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                func = new Funcionario();
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setCargo(rs.getString("cargo"));
                func.setSalario(rs.getDouble("salario"));
            }
        }
    }
    return func;
}
}