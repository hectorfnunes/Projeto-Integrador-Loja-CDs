package telas;

import dao.CDDAO;
import modelo.CD;
import modelo.VendaItem;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;   
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

public class TelaSelecaoCD extends javax.swing.JFrame {
    private CDDAO dao = new CDDAO();
    private CarrinhoManager origem;

    public TelaSelecaoCD(CarrinhoManager origem) {
        this.origem = origem;
        initComponents();

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
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        txtPesquisa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnAdicionarCD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btnAdicionarCD.setText("Adicionar CD");
        btnAdicionarCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdicionarCD)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(btnAdicionarCD))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCDActionPerformed
        int row = tblLista.getSelectedRow();
        if (row >= 0) {
            int idCd = (Integer) tblLista.getValueAt(row, 0);
            try {
                CD cd = dao.buscarPorId(idCd);
                if (cd == null) return;
                String qntStr = JOptionPane.showInputDialog(this, "Quantidade (estoque: " + cd.getEstoque() + "):");
                if (qntStr != null) {
                    int qnt = Integer.parseInt(qntStr);
                    if (qnt > 0 && qnt <= cd.getEstoque()) {
                        VendaItem item = new VendaItem();
                        item.setIdCd(idCd);
                        item.setQuantidade(qnt);
                        item.setSubtotal(cd.getPreco().multiply(new BigDecimal(qnt)));
                        origem.addItem(item);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Quantidade inválida ou sem estoque.");
                    }
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um CD.");
        }
    }//GEN-LAST:event_btnAdicionarCDActionPerformed
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
   
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSelecaoCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSelecaoCD(null).setVisible(true); 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCD;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLista;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
