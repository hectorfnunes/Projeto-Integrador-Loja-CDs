
package telas;

import dao.CDDAO;
import modelo.CD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;   
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class TelaCadastroCD extends javax.swing.JFrame {
    private CDDAO dao = new CDDAO();

    
    public TelaCadastroCD() {
        initComponents();
        if (!UserSession.isGerente()) {
        btnDeletar.setEnabled(false);
}
        


tblLista.getColumnModel().getColumn(0).setPreferredWidth(50); 
tblLista.getColumnModel().getColumn(1).setPreferredWidth(200);  
tblLista.getColumnModel().getColumn(2).setPreferredWidth(150);  
tblLista.getColumnModel().getColumn(3).setPreferredWidth(120); 
tblLista.getColumnModel().getColumn(4).setPreferredWidth(80);  
tblLista.getColumnModel().getColumn(5).setPreferredWidth(100);  
tblLista.getColumnModel().getColumn(6).setPreferredWidth(100); 
        
        carregarLista("");
        
        
        txtPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                carregarLista(txtPesquisa.getText());
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
                    CD cd = dao.buscarPorId(id);
                    if (cd != null) {
                        txtTitulo.setText(cd.getTitulo());
                        txtArtista.setText(cd.getArtista());
                        txtGenero.setText(cd.getGenero());
                        txtAno.setText(String.valueOf(cd.getAno()));
                        txtPreco.setText(cd.getPreco().toString());
                        txtEstoque.setText(String.valueOf(cd.getEstoque()));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(TelaCadastroCD.this, "Erro ao carregar CD: " + ex.getMessage());
                }
            }
        }
    }
});
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtArtista = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        txtPreco = new javax.swing.JTextField();
        txtEstoque = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        txtPesquisa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnRetornar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CDs");

        jLabel2.setText("Título");

        jLabel3.setText("Artista");

        jLabel4.setText("Gênero");

        jLabel5.setText("Ano");

        jLabel6.setText("Preço");

        jLabel7.setText("Estoque");

        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });

        txtArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArtistaActionPerformed(evt);
            }
        });

        txtGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGeneroActionPerformed(evt);
            }
        });

        txtEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstoqueActionPerformed(evt);
            }
        });

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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Título", "Artista", "Gênero", "Ano", "Preço", "Qnt. Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLista.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tblLista);

        jLabel8.setText("Pesquisar Título:");

        btnRetornar.setText("Retornar");
        btnRetornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetornarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnRetornar)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAtualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeletar)))
                .addContainerGap(283, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGenero, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtArtista)
                            .addComponent(txtTitulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEstoque)
                            .addComponent(txtAno)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnRetornar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnAtualizar)
                    .addComponent(btnDeletar))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void txtArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArtistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArtistaActionPerformed

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String msgErro = "";
    try {
        Integer.parseInt(txtAno.getText());
    } catch (NumberFormatException e) {
        msgErro += "Formato do ano inválido. ";
    }
    try {
        String precoStr = txtPreco.getText().trim().replace(',', '.');
        new BigDecimal(precoStr);
    } catch (NumberFormatException e) {
        msgErro += "Formato do preço inválido, use XX.XX ou XX,XX. ";
    }
    try {
        Integer.parseInt(txtEstoque.getText());
    } catch (NumberFormatException e) {
        msgErro += "Número do estoque inválido. ";
    }
    if (!msgErro.isEmpty()) {
        JOptionPane.showMessageDialog(this, msgErro);
        return;
    }
    
    try {
        CD cd = new CD();
        cd.setTitulo(txtTitulo.getText());
        cd.setArtista(txtArtista.getText());
        cd.setGenero(txtGenero.getText());
        cd.setAno(Integer.parseInt(txtAno.getText()));
        cd.setPreco(new BigDecimal(txtPreco.getText().replace(',', '.')));
        cd.setEstoque(Integer.parseInt(txtEstoque.getText()));
        dao.inserir(cd);
        JOptionPane.showMessageDialog(this, "Salvo!");
        carregarLista("");
        limparCampos();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
    }
    }//GEN-LAST:event_btnSalvarActionPerformed
    private void carregarLista(String filtro) {
        try {
            List<CD> cds = dao.listar(filtro);
            DefaultTableModel model = (DefaultTableModel) tblLista.getModel();
            model.setRowCount(0);
            for (CD cd : cds) {
                model.addRow(new Object[]{cd.getId(), cd.getTitulo(), cd.getArtista(), cd.getGenero(), cd.getAno(), cd.getPreco(), cd.getEstoque()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        int row = tblLista.getSelectedRow();
    if (row >= 0) {
        String msgErro = "";
        try {
            Integer.parseInt(txtAno.getText());
        } catch (NumberFormatException e) {
            msgErro += "Formato do ano inválido. ";
        }
        try {
            String precoStr = txtPreco.getText().trim().replace(',', '.');
            new BigDecimal(precoStr);
        } catch (NumberFormatException e) {
            msgErro += "Formato do preço inválido, use XX.XX ou XX,XX. ";
        }
        try {
            Integer.parseInt(txtEstoque.getText());
        } catch (NumberFormatException e) {
            msgErro += "Número do estoque inválido. ";
        }
        if (!msgErro.isEmpty()) {
            JOptionPane.showMessageDialog(this, msgErro);
            return;
        }
        
        int option = JOptionPane.showConfirmDialog(this, "Deseja atualizar este CD?", "Confirmação de Atualização", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                CD cd = new CD();
                cd.setId((int) tblLista.getValueAt(row, 0));
                cd.setTitulo(txtTitulo.getText());
                cd.setArtista(txtArtista.getText());
                cd.setGenero(txtGenero.getText());
                cd.setAno(Integer.parseInt(txtAno.getText()));
                cd.setPreco(new BigDecimal(txtPreco.getText().replace(',', '.')));
                cd.setEstoque(Integer.parseInt(txtEstoque.getText()));
                dao.atualizar(cd);
                JOptionPane.showMessageDialog(this, "Atualizado!");
                carregarLista("");
                limparCampos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um CD.");
    }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        int row = tblLista.getSelectedRow();
        if (row >= 0) {
            try {
                int id = (int) tblLista.getValueAt(row, 0);
                dao.deletar(id);
                JOptionPane.showMessageDialog(this, "Deletado!");
                carregarLista("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void txtEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstoqueActionPerformed

    private void btnRetornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetornarActionPerformed
       new TelaPrincipal().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnRetornarActionPerformed
    private void limparCampos() {
        txtTitulo.setText("");
        txtArtista.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        txtPreco.setText("");
        txtEstoque.setText("");
        // Limpar outros
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
            java.util.logging.Logger.getLogger(TelaCadastroCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCD().setVisible(true);
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLista;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtArtista;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
