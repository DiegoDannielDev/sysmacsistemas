/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.view;

import br.com.controller.UpperCase;
import br.com.controller.UsuarioControler;
import br.com.dao.ConexaoDAO;
import br.com.model.Usuario;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Danniel
 */
public class FormUsuarios extends javax.swing.JInternalFrame {

    FormPrincipal pri;
    ConexaoDAO conect = new ConexaoDAO();
    PreparedStatement pst = null;
    ResultSet rs = null;

    private UsuarioControler user;
    private static FormUsuarios cadUsuarios;
    String resposta;

    public static FormTabelaPesquisaUsuario pesquisar;

    public Object converte() {

        try {
            String password = new String(this.txtSenhaUser.getPassword());
            String s = password;  // SENHA QUE VAI SER FEITA A CONVERSÃO PARA O MD5

            MessageDigest m = MessageDigest.getInstance("SHA-256");

            m.update(s.getBytes(), 0, s.length());
            resposta = new BigInteger(1, m.digest()).toString();
            //JOptionPane.showMessageDialog(null,resposta );

        } catch (NoSuchAlgorithmException ex) {

        }
        return null;
    }

    public FormUsuarios() {

        initComponents();
        barra.setVisible(false);
        conect.conexao();
        user = new UsuarioControler();
        txtLoginUser.setDocument(new UpperCase().getDocument());
        txtNomeUser.setDocument(new UpperCase().getDocument());
        Thread t = new Thread();
    }

    public void chamarTelaUsuario() {
        pri = new FormPrincipal();
        pesquisar = new FormTabelaPesquisaUsuario(this, true);
       pesquisar.setVisible(true);
//       pri.getDesktop().add(pesquisar);
    }

     public void Thread() {

        new Thread() {
            public void run() {
                int i = 0;
                while (i < 101) {
                    try {
                        barra.setValue(i);
                        barra.setVisible(true);
                        i++;
                        if (i > 0) {
                            barra.setStringPainted(true);
                            barra.setString("Validando usuario");
                        }
                        i++;
                        if (i > 40) {
                            converte();
                        }
                        i++;
                        if (i > 60) {
                            barra.setString("Aguarde");
                            
                        }
                        i++;
                        if (i > 90) {
                            barra.setString("Validado...Salvando");
                            
                        }
                        i++;
                        if (i > 100) {
                            
                            cadastrarUsuario(user);
                        }
                        sleep(35);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FormUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                       
                        barra.setVisible(false);
                    }
                   
                }
                
            

        }.start();
    }

    public void novoId() {

        String sql = "SELECT auto_increment as  codigo FROM information_schema.tables IT WHERE IT.table_name = 'usuarios'";
        try {
            pst = conect.conn.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()) {
                txtCodigoUser.setText(Integer.toString(rs.getInt("CODIGO")));
                int novoId = rs.getInt("codigo") + 1;
                txtCodigoUser.setText(Integer.toString(novoId));
                txtCodigoUser.setEditable(false);
                txtNomeUser.setEditable(true);
                txtEmailUser.setEditable(true);

                txtLoginUser.setEditable(true);
                txtSenhaUser.setEditable(true);

                btnGravar.setEnabled(true);
                btnNovo.setEnabled(false);
                btnPesquisar.setEnabled(false);
                txtNomeUser.requestFocus();
            }
            //txtCodigo.setText(Integer.toString(rs.getInt("id")));

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static FormUsuarios getCadUsuarios() {
        if (cadUsuarios == null) {
            cadUsuarios = new FormUsuarios();

        }

        return cadUsuarios;
    }

    /**
     *
     * @param user
     */
    public void cadastrarUsuario(UsuarioControler user) {
       
        try {
            user.getUsuario().setNomeUser(txtNomeUser.getText());
            user.getUsuario().setLoginUser(txtLoginUser.getText());
            user.getUsuario().setSenhaUser(resposta);
            user.getUsuario().setEmailUser(txtEmailUser.getText());

            boolean add = user.inserirUsuario();
            if (add == true) {
                txtNomeUser.setEditable(false);
                txtEmailUser.setEditable(false);

                txtLoginUser.setEditable(false);
                txtSenhaUser.setEditable(false);
                
                txtCodigoUser.setText("");
                txtNomeUser.setText("");
                txtEmailUser.setText("");

                txtLoginUser.setText("");
                txtSenhaUser.setText("");

                btnGravar.setEnabled(false);
                btnNovo.setEnabled(true);

            }

        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO! " + e.getMessage());
            
            

        }

    }

    public void listarUsuarios() throws SQLException {
        try {
            user.listarUsuarios();
            if (user.getUsuarioModels().isEmpty()) {
                JOptionPane.showMessageDialog(null, "não existe usuario cadastrado com essas informações");
            } else {
                user.getUsuarioModels().forEach((usuario) -> {
                    String codigo = String.valueOf(usuario.getCodigoUser());
                    String nome = usuario.getNomeUser();
                    String login = usuario.getLoginUser();
                    String email = usuario.getEmailUser();

                    txtCodigoUser.setText(codigo);
                    txtNomeUser.setText(nome);
                    txtLoginUser.setText(login);
                    txtEmailUser.setText(email);
                });
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel listar os usuarios " + e.getMessage());
        }
    }

    public void listarPrimeiro() {
        user.buscarIdPrimeiro();
        user.getUsuarioModels().forEach((Usuario t) -> {
            String codigo = String.valueOf(t.getCodigoUser());
            String nome = t.getNomeUser();
            String login = t.getLoginUser();
            String email = t.getEmailUser();

            txtCodigoUser.setText(codigo);
            txtNomeUser.setText(nome);
            txtLoginUser.setText(login);
            txtEmailUser.setText(email);
            //txtSenhaUser.setText("Criptografada");
        });
    }

    public void listarProximo() {
        user.buscarIdProximo();
        user.getUsuarioModels().forEach((t) -> {
            String codigo = String.valueOf(t.getCodigoUser());
            String nome = t.getNomeUser();
            String login = t.getLoginUser();
            String email = t.getEmailUser();

            txtCodigoUser.setText(codigo);
            txtNomeUser.setText(nome);
            txtLoginUser.setText(login);
            txtEmailUser.setText(email);
            txtSenhaUser.setText("Criptografada");
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomeUser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLoginUser = new javax.swing.JTextField();
        txtEmailUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnGravar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSenhaUser = new javax.swing.JPasswordField();
        barra = new javax.swing.JProgressBar();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setBackground(java.awt.Color.darkGray);
        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Usuários");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icon/ADICIONARCLIENTES.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Cadastro e Usuários"));
        jPanel1.setToolTipText("Cadastro de Usuários");
        jPanel1.setName("Cadastro de Usuários"); // NOI18N
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("CÓDIGO:");

        txtCodigoUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCodigoUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoUserKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("NOME:");

        txtNomeUser.setEditable(false);
        txtNomeUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("LOGIN:");

        txtLoginUser.setEditable(false);
        txtLoginUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtLoginUser.setToolTipText("<html><b>COLOQUE O LOGIN SEM ESPAÇOS ENTRE OS NOMES</b></html>");

        txtEmailUser.setEditable(false);
        txtEmailUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("E-MAIL:");

        btnGravar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnGravar.setText("GRAVAR");
        btnGravar.setAutoscrolls(true);
        btnGravar.setEnabled(false);
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.setAutoscrolls(true);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnPesquisar.setText("PESQUISAR");
        btnPesquisar.setAutoscrolls(true);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnDeletar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnDeletar.setText("DELETAR");
        btnDeletar.setAutoscrolls(true);
        btnDeletar.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("SENHA:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel8.setText("F2-Pesquisar");

        txtSenhaUser.setEditable(false);
        txtSenhaUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jButton9.setText("<-");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("->");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeUser)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtLoginUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSenhaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmailUser, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeletar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGravar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCodigoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLoginUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenhaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmailUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9)
                        .addComponent(jButton10))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGravar)
                        .addComponent(btnNovo)
                        .addComponent(btnPesquisar)
                        .addComponent(btnDeletar)))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(450, 100, 544, 229);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        // TODO add your handling code here:ca
        Thread();


    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        novoId();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtCodigoUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoUserKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            chamarTelaUsuario();
        }


    }//GEN-LAST:event_txtCodigoUserKeyPressed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            // TODO add your handling code here:
            listarUsuarios();
        } catch (SQLException ex) {
            Logger.getLogger(FormUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        listarPrimeiro();

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        listarProximo();
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar barra;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField txtCodigoUser;
    private javax.swing.JTextField txtEmailUser;
    private javax.swing.JTextField txtLoginUser;
    private javax.swing.JTextField txtNomeUser;
    private javax.swing.JPasswordField txtSenhaUser;
    // End of variables declaration//GEN-END:variables
}
