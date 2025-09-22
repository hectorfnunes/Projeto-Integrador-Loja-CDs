
package telas;

import dao.FuncionarioDAO;
import modelo.Funcionario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaSelecaoFunc extends javax.swing.JFrame {
    private FuncionarioDAO dao = new FuncionarioDAO();
    private CarrinhoManager origem;

    public TelaSelecaoFunc(CarrinhoManager origem) {
        this.origem = origem;
        initComponents();
        tblLista.getColumnModel().getColumn(0).setPreferredWidth(50);   
        tblLista.getColumnModel().getColumn(1).setPreferredWidth(180);  
        tblLista.getColumnModel().getColumn(2).setPreferredWidth(120);  
        tblLista.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblLista.getColumnModel().getColumn(4).setPreferredWidth(100);
        listarFuncionarios("");
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listarFuncionarios(txtPesquisa.getText());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnConfFunc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Cargo", "Salario"
            }
        ));
        jScrollPane1.setViewportView(tblLista);

        jLabel2.setText("Pesquisar por Nome ou Cargo:");

        btnConfFunc.setText("Confirmar Funcionário");
        btnConfFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfFuncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfFunc)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConfFunc))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfFuncActionPerformed
        int row = tblLista.getSelectedRow();
        if (row >= 0) {
            Funcionario func = new Funcionario();
            func.setId((Integer) tblLista.getValueAt(row, 0));
            func.setNome((String) tblLista.getValueAt(row, 1));
            // Pode buscar full via DAO se precisar mais campos
            origem.setFuncionario(func);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário.");
        }
    }//GEN-LAST:event_btnConfFuncActionPerformed
    
    private void listarFuncionarios(String filtro) {
        try {
            List<Funcionario> funcionarios = dao.listarPorFiltro(filtro);
            DefaultTableModel model = (DefaultTableModel) tblLista.getModel();
            model.setRowCount(0);
            for (Funcionario func : funcionarios) {
                model.addRow(new Object[]{func.getId(), func.getNome(), func.getCpf(), func.getCargo(), func.getSalario()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
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
            java.util.logging.Logger.getLogger(TelaSelecaoFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSelecaoFunc(null).setVisible(true); // Exemplo
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfFunc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLista;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
