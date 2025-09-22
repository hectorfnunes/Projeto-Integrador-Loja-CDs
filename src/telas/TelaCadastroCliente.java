
package telas;

import dao.ClienteDAO;
import modelo.Cliente;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TelaCadastroCliente extends javax.swing.JFrame {
    private ClienteDAO dao = new ClienteDAO();

    
    public TelaCadastroCliente() {
        initComponents();
        if (!UserSession.isGerente()) {
    btnDeletar.setEnabled(false);
}
        tblLista.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblLista.getColumnModel().getColumn(1).setPreferredWidth(180); 
        tblLista.getColumnModel().getColumn(2).setPreferredWidth(120); 
        tblLista.getColumnModel().getColumn(3).setPreferredWidth(220); 
        tblLista.getColumnModel().getColumn(4).setPreferredWidth(120);
        atualizarTabela("");
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyReleased(java.awt.event.KeyEvent evt) {
        atualizarTabela(txtPesquisa.getText());
    }
});
        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int row = tblLista.getSelectedRow();
            if (row >= 0) {
                int id = (int) tblLista.getValueAt(row, 0);
                try {
                    Cliente cliente = dao.buscarPorId(id);
                    if (cliente != null) {
                        txtNome.setText(cliente.getNome());
                        txtCpf.setText(cliente.getCpf());
                        txtEmail.setText(cliente.getEmail());
                        txtTelefone.setText(cliente.getTelefone());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(TelaCadastroCliente.this, "Erro ao carregar cliente: " + ex.getMessage());
                }
            }
        }
    }
});
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRetornar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnRetornar.setText("Retornar");
        btnRetornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetornarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cadastro de Clientes");

        jLabel2.setText("Nome:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Email:");

        jLabel5.setText("Telefone:");

        btnSalvar.setText("Cadastrar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        tblLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Email", "Telefone"
            }
        ));
        jScrollPane1.setViewportView(tblLista);

        jLabel6.setText("Listagem");

        jLabel7.setText("Pesquisar por nome:");

        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDeletar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtTelefone))
                        .addGap(36, 36, 36))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRetornar)
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jLabel6)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnRetornar))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnAtualizar)
                    .addComponent(btnDeletar))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String msgErro = "";
    if (txtNome.getText().trim().isEmpty()) {
        msgErro += "Nome não pode estar vazio. ";
    }
    if (!Pattern.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", txtCpf.getText().trim())) {
        msgErro += "Formato do CPF inválido (deve ser XXX.XXX.XXX-XX). ";
    }
    if (!txtEmail.getText().trim().contains("@")) {
        msgErro += "Formato do email inválido (deve conter @). ";
    }
    if (!Pattern.matches("\\d+", txtTelefone.getText().trim())) {
        msgErro += "Formato do telefone inválido (deve ser numérico). ";
    }
    if (!msgErro.isEmpty()) {
        JOptionPane.showMessageDialog(this, msgErro);
        return;
    }
    
    try {
        Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtEmail.getText(), txtTelefone.getText());
        dao.inserir(cliente);
        JOptionPane.showMessageDialog(this, "Cliente salvo!");
        limparCampos();
        atualizarTabela(txtPesquisa.getText()); 
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
    }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        int row = tblLista.getSelectedRow();
    if (row >= 0) {
        String msgErro = "";
        if (txtNome.getText().trim().isEmpty()) {
            msgErro += "Nome não pode estar vazio. ";
        }
        if (!Pattern.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", txtCpf.getText().trim())) {
            msgErro += "Formato do CPF inválido (deve ser XXX.XXX.XXX-XX). ";
        }
        if (!txtEmail.getText().trim().contains("@")) {
            msgErro += "Formato do email inválido (deve conter @). ";
        }
        if (!Pattern.matches("\\d+", txtTelefone.getText().trim())) {
            msgErro += "Formato do telefone inválido (deve ser numérico). ";
        }
        if (!msgErro.isEmpty()) {
            JOptionPane.showMessageDialog(this, msgErro);
            return;
        }
        
        int option = JOptionPane.showConfirmDialog(this, "Deseja atualizar este cliente?", "Confirmação de Atualização", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                Cliente cliente = new Cliente();
                cliente.setId((int) tblLista.getValueAt(row, 0));
                cliente.setNome(txtNome.getText());
                cliente.setCpf(txtCpf.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setTelefone(txtTelefone.getText());
                dao.atualizar(cliente);
                JOptionPane.showMessageDialog(this, "Atualizado!");
                atualizarTabela(txtPesquisa.getText());
                limparCampos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um item na tabela!");
    }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        int row = tblLista.getSelectedRow();
        if (row >= 0) {
            try {
                int id = (int) tblLista.getValueAt(row, 0);
                dao.deletar(id);
                JOptionPane.showMessageDialog(this, "Deletado!");
                atualizarTabela(txtPesquisa.getText());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item na tabela!");
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void btnRetornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetornarActionPerformed
       new TelaPrincipal().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnRetornarActionPerformed
    
    private void atualizarTabela(String filtro) {
    try {
        List<Cliente> clientes;
        if (filtro.isEmpty()) {
            clientes = dao.listar();
        } else {
            clientes = dao.listarPorNome(filtro);
        }
        DefaultTableModel model = (DefaultTableModel) tblLista.getModel();
        model.setRowCount(0); // Limpa tabela
        for (Cliente c : clientes) {
            model.addRow(new Object[]{c.getId(), c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone()});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao listar: " + e.getMessage());
    }
}
    
    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnRetornar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLista;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
