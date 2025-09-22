package telas;

import dao.VendaDAO;
import dao.CDDAO;
import dao.FuncionarioDAO;
import dao.ClienteDAO;
import modelo.VendaHeader;
import modelo.VendaItem;
import modelo.Funcionario;
import modelo.Cliente;
import modelo.CD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaDetalhesVenda extends javax.swing.JFrame implements CarrinhoManager {

    private int idVenda;
    private Integer idCliente = null;
    private int idFuncionario = -1;
    private List<VendaItem> carrinho = new ArrayList<>();
    private VendaDAO vendaDAO = new VendaDAO();
    private CDDAO cdDAO = new CDDAO();
    private FuncionarioDAO funcDAO = new FuncionarioDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public TelaDetalhesVenda(int idVenda) {
        this.idVenda = idVenda;
        initComponents();
        if (!UserSession.isGerente()) {
    btnAdicionar.setEnabled(false);
    btnRemover.setEnabled(false);
    btnSalvarAlteracoes.setEnabled(false);
    btnSeleFunc.setEnabled(false);
    btnSeleCliente.setEnabled(false);
    txtData.setEditable(false);
    btnDeletar.setVisible(false);
} else {
    btnDeletar.setVisible(true);
}
        carregarVenda();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblQnt = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSeleFunc = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnSeleCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnSalvarAlteracoes = new javax.swing.JButton();
        txtData = new javax.swing.JTextField();
        lblFunc = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        btnRetornar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Data:");

        jLabel2.setText("Valor:");

        lblValor.setText("lblValor");

        jLabel3.setText("Quantidade de Itens:");

        lblQnt.setText("lblQnt");

        jLabel4.setText("Funcionário Atendente:");

        btnSeleFunc.setText("Alterar Atendente");
        btnSeleFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleFuncActionPerformed(evt);
            }
        });

        jLabel5.setText("Cliente:");

        btnSeleCliente.setText("Alterar Cliente");
        btnSeleCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleClienteActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Título", "Artista", "Gênero", "Ano", "Preço", "Qnt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnAdicionar.setText("Adicionar CD");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover CD");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnSalvarAlteracoes.setText("Salvar Alterações");
        btnSalvarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlteracoesActionPerformed(evt);
            }
        });

        txtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataActionPerformed(evt);
            }
        });

        lblFunc.setText("lblFunc");

        lblCliente.setText("lblCliente");

        btnRetornar.setText("Retornar");
        btnRetornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetornarActionPerformed(evt);
            }
        });

        btnDeletar.setText("Deletar Registro");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeletar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvarAlteracoes))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblQnt))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCliente))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFunc)))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSeleFunc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSeleCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblValor)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRetornar)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetornar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblQnt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleFunc)
                    .addComponent(jLabel4)
                    .addComponent(lblFunc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleCliente)
                    .addComponent(jLabel5)
                    .addComponent(lblCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnRemover)
                    .addComponent(btnSalvarAlteracoes)
                    .addComponent(btnDeletar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleFuncActionPerformed
        new TelaSelecaoFunc(this).setVisible(true);
    }//GEN-LAST:event_btnSeleFuncActionPerformed

    private void btnSeleClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleClienteActionPerformed
        new TelaSelecaoCliente(this).setVisible(true);
    }//GEN-LAST:event_btnSeleClienteActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        new TelaSelecaoCD(this).setVisible(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            removeItem(row);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlteracoesActionPerformed
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime data = LocalDateTime.parse(txtData.getText(), formatter);
            VendaHeader venda = new VendaHeader(data, idCliente, idFuncionario, vendaDAO.calcularTotal(carrinho, idCliente));
            venda.setId(idVenda);
            venda.setItens(carrinho);
            vendaDAO.atualizar(venda);
            JOptionPane.showMessageDialog(this, "Venda atualizada!");
            new TelaVenda().setVisible(true);
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
    }
    }//GEN-LAST:event_btnSalvarAlteracoesActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void btnRetornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetornarActionPerformed
        new TelaVenda().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRetornarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja deletar esta venda?", "Confirmação de Deleção", JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
        try {
            vendaDAO.deletar(idVenda);
            JOptionPane.showMessageDialog(this, "Venda deletada!");
            new TelaVenda().setVisible(true);
            this.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnDeletarActionPerformed
    
    private void carregarVenda() {
        try {
            VendaHeader venda = vendaDAO.buscarPorId(idVenda);
            if (venda != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                txtData.setText(venda.getDataHora().format(formatter));
                idFuncionario = venda.getIdFuncionario();
                idCliente = venda.getIdCliente();
                carrinho = venda.getItens();
                // Carregar nomes
                Funcionario func = funcDAO.buscarPorId(idFuncionario);
                lblFunc.setText(func != null ? func.getNome() : "Desconhecido");
                if (idCliente != null) {
                    Cliente cliente = clienteDAO.buscarPorId(idCliente);
                    lblCliente.setText(cliente != null ? cliente.getNome() : "Desconhecido");
                } else {
                    lblCliente.setText("Sem cliente");
                }
                updateCarrinhoUI();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
    
    @Override
    public void setFuncionario(Funcionario funcionario) {
        idFuncionario = funcionario.getId();
        lblFunc.setText(funcionario.getNome());
    }

    @Override
public void setCliente(Cliente cliente) {
    if (cliente != null) {
        idCliente = cliente.getId();
        lblCliente.setText(cliente.getNome());
    } else {
        idCliente = null;
        lblCliente.setText("Sem cliente");
    }
    updateCarrinhoUI();
}

    @Override
    public void addItem(VendaItem item) {
        carrinho.add(item);
        updateCarrinhoUI();
    }

    @Override
    public void removeItem(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < carrinho.size()) {
            carrinho.remove(rowIndex);
            updateCarrinhoUI();
        }
    }

    @Override
    public void updateCarrinhoUI() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (VendaItem item : carrinho) {
            try {
                CD cd = cdDAO.buscarPorId(item.getIdCd());
                model.addRow(new Object[]{cd.getId(), cd.getTitulo(), cd.getArtista(), cd.getGenero(), cd.getAno(), cd.getPreco(), item.getQuantidade()});
            } catch (SQLException e) {
                // Ignorar
            }
        }
        BigDecimal total = vendaDAO.calcularTotal(carrinho, idCliente);
        lblValor.setText(total.toString());
        lblQnt.setText(String.valueOf(carrinho.size()));
    }

    @Override
    public Integer getIdVenda() {
        return idVenda;
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
            java.util.logging.Logger.getLogger(TelaDetalhesVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDetalhesVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDetalhesVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDetalhesVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDetalhesVenda(0).setVisible(true); // Exemplo, passe ID real
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRetornar;
    private javax.swing.JButton btnSalvarAlteracoes;
    private javax.swing.JButton btnSeleCliente;
    private javax.swing.JButton btnSeleFunc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFunc;
    private javax.swing.JLabel lblQnt;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTextField txtData;
    // End of variables declaration//GEN-END:variables
}
