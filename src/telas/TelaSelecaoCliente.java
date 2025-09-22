
package telas;

import dao.ClienteDAO;
import modelo.Cliente;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaSelecaoCliente extends javax.swing.JFrame {
    private ClienteDAO dao = new ClienteDAO();
    private CarrinhoManager origem;

    public TelaSelecaoCliente(CarrinhoManager origem) {
        this.origem = origem;
        initComponents();
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
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnConfCliente = new javax.swing.JButton();
        btnRemoverCliente = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jLabel7.setText("Pesquisar por nome:");

        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });

        btnConfCliente.setText("Confirmar Cliente");
        btnConfCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfClienteActionPerformed(evt);
            }
        });

        btnRemoverCliente.setText("Descelecionar Cliente");
        btnRemoverCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfCliente)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfCliente)
                    .addComponent(btnRemoverCliente))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void btnConfClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfClienteActionPerformed
        int row = tblLista.getSelectedRow();
        if (row >= 0) {
            Cliente cliente = new Cliente();
            cliente.setId((Integer) tblLista.getValueAt(row, 0));
            cliente.setNome((String) tblLista.getValueAt(row, 1));
            origem.setCliente(cliente);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
        }
    }//GEN-LAST:event_btnConfClienteActionPerformed

    private void btnRemoverClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverClienteActionPerformed
        origem.setCliente(null);
        this.dispose();
    }//GEN-LAST:event_btnRemoverClienteActionPerformed
    
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
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSelecaoCliente(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfCliente;
    private javax.swing.JToggleButton btnRemoverCliente;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable tblLista;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
