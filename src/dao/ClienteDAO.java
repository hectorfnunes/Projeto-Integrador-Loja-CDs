package dao;

import conexao.Conexao;
import modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf, email, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.executeUpdate();
        }
    }
    public List<Cliente> listar() throws SQLException {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM clientes";
    try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            clientes.add(cliente);
        }
    }
    return clientes;
}
    
    public List<Cliente> listarPorNome(String nome) throws SQLException {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
    try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + nome + "%");
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        }
    }
    return clientes;
}

    
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome=?, cpf=?, email=?, telefone=? WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.setInt(5, cliente.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public Cliente buscarPorId(int id) throws SQLException {
    Cliente cliente = null;
    String sql = "SELECT * FROM clientes WHERE id = ?";
    try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
            }
        }
    }
    return cliente;
}
}