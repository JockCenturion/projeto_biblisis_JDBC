/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;
import br.com.biblisis.controller.CAlunoProfessor;
import br.com.biblisis.controller.CFuncionario;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author romeu
 */
public class TelaFuncionario extends javax.swing.JFrame {
    
    /**
     * Creates new form TelaFuncionario
     */
    private String login;
    public TelaFuncionario() {
        initComponents();
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    private void chamaNovoCadastro (String tipoDoUsuario){
        TelaCadastroUsuario novaTelaDeCadastro = new TelaCadastroUsuario ();
        novaTelaDeCadastro.setTipoDoUsuario(tipoDoUsuario);
        novaTelaDeCadastro.setVisible(true);
    }
    
    private void chamaNovoEmprestimo(){
        TelaNovoEmprestimo novaTelaDeEmprestimo = new TelaNovoEmprestimo ();
        novaTelaDeEmprestimo.setLoginFuncionario(this.login);
        novaTelaDeEmprestimo.setVisible(true);
    }
    
    private String[] buscaAlunoProfessor(String login){
        String[] usuario = null;
       
        try {
            usuario = CAlunoProfessor.search(login);
         }catch (SQLException e) {
            System.out.println("Erro!" + e);
        }
        if (!usuario[0].equals(login)){
            usuario = null;
        }
        return usuario;
    }
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFunc = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuFuncionario = new javax.swing.JMenu();
        menuItemNovoUsuario = new javax.swing.JMenuItem();
        menuItemSituacao = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuItemConsultarAcervo = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        menuItemConsultarUmEmprestimo = new javax.swing.JMenuItem();
        menuItemNovoEmprestimo = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        menuItemEncerrarSessao1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu do Funcionário");
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/backgroundPadrao.jpg"))); // NOI18N

        menuFunc.setText("Funcionário");

        jMenuItem1.setText("Editar dados cadastrais");
        menuFunc.add(jMenuItem1);

        menuFuncionario.setText("Usuários");

        menuItemNovoUsuario.setText("Cadastrar novo usuário");
        menuItemNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNovoUsuarioActionPerformed(evt);
            }
        });
        menuFuncionario.add(menuItemNovoUsuario);

        menuItemSituacao.setText("Consultar situação de um usuário");
        menuItemSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSituacaoActionPerformed(evt);
            }
        });
        menuFuncionario.add(menuItemSituacao);

        jMenuItem8.setText("Editar dados cadastrais de um usuário");
        menuFuncionario.add(jMenuItem8);

        menuFunc.add(menuFuncionario);

        jMenuBar1.add(menuFunc);

        jMenu5.setText("Acervo");

        menuItemConsultarAcervo.setText("Consultar acervo");
        menuItemConsultarAcervo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultarAcervoActionPerformed(evt);
            }
        });
        jMenu5.add(menuItemConsultarAcervo);

        jMenuBar1.add(jMenu5);

        jMenu7.setText("Empréstimo");

        menuItemConsultarUmEmprestimo.setText("Consultar um empréstimo");
        menuItemConsultarUmEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultarUmEmprestimoActionPerformed(evt);
            }
        });
        jMenu7.add(menuItemConsultarUmEmprestimo);

        menuItemNovoEmprestimo.setText("Novo empréstimo");
        menuItemNovoEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNovoEmprestimoActionPerformed(evt);
            }
        });
        jMenu7.add(menuItemNovoEmprestimo);

        jMenuItem13.setText("Devolver item");
        jMenu7.add(jMenuItem13);

        jMenuItem14.setText("Renovar um empréstimo");
        jMenu7.add(jMenuItem14);

        jMenuBar1.add(jMenu7);

        jMenu4.setText("Reserva");

        jMenuItem3.setText("Fazer reserva");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem4.setText("Consultar reserva");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Cancelar reserva");
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Relatório");

        jMenuItem10.setText("Consultar relatório");
        jMenu3.add(jMenuItem10);

        jMenuItem11.setText("Gerar relatório");
        jMenu3.add(jMenuItem11);

        jMenuBar1.add(jMenu3);

        jMenu8.setText("Logout");

        menuItemEncerrarSessao1.setText("Encerrar sessão");
        menuItemEncerrarSessao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEncerrarSessao1ActionPerformed(evt);
            }
        });
        jMenu8.add(menuItemEncerrarSessao1);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void menuItemSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSituacaoActionPerformed
        //Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
        String codigoDoUsuario = JOptionPane.showInputDialog(null,"Digite o ID de login do usuário", "Consulta", 3);
        String [] alunoProfessor = buscaAlunoProfessor(codigoDoUsuario);
        if (alunoProfessor != null){
            //verificar resposta do banco - true ou false e produzir mensagem
            if (Boolean.parseBoolean(alunoProfessor[2])){
               JOptionPane.showMessageDialog(null, "Usuário bloqueado", "Situação",0);
            }else{
               JOptionPane.showMessageDialog(null, "Usuário livre", "Situação",1);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuário não encontrado na base de dados!"); 
        }
        //Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
    }//GEN-LAST:event_menuItemSituacaoActionPerformed

    private void menuItemConsultarAcervoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultarAcervoActionPerformed
        TelaConsultaAcervo novaTelaDeConsulta = new TelaConsultaAcervo();
        novaTelaDeConsulta.setVisible(true);
    }//GEN-LAST:event_menuItemConsultarAcervoActionPerformed

    private void menuItemNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNovoUsuarioActionPerformed
        Object[] itens = { "Aluno","Professor"};
      Object tipoEscolhido = JOptionPane.showInputDialog(null, "Qual o tipo do usuário?", "Novo usuário",
              JOptionPane.INFORMATION_MESSAGE, null,
                  itens, itens [0]); //
      if (tipoEscolhido != null){
          chamaNovoCadastro(tipoEscolhido.toString());
      }
    }//GEN-LAST:event_menuItemNovoUsuarioActionPerformed

    private void menuItemEncerrarSessao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEncerrarSessao1ActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja encerrar sua sessão?");
        if (resposta == 0){
           TelaPrincipal novaTela = new TelaPrincipal ();
            novaTela.setVisible(true);
            this.dispose(); 
        }
    }//GEN-LAST:event_menuItemEncerrarSessao1ActionPerformed

    private void menuItemNovoEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNovoEmprestimoActionPerformed
        chamaNovoEmprestimo();
    }//GEN-LAST:event_menuItemNovoEmprestimoActionPerformed

    private void menuItemConsultarUmEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultarUmEmprestimoActionPerformed
        TelaConsultaEmprestimo novaTelaDeConsulta = new TelaConsultaEmprestimo();
        novaTelaDeConsulta.setVisible(true);
    }//GEN-LAST:event_menuItemConsultarUmEmprestimoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        menuFunc.setText("Olá, " + login+"!");
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenu menuFunc;
    private javax.swing.JMenu menuFuncionario;
    private javax.swing.JMenuItem menuItemConsultarAcervo;
    private javax.swing.JMenuItem menuItemConsultarUmEmprestimo;
    private javax.swing.JMenuItem menuItemEncerrarSessao1;
    private javax.swing.JMenuItem menuItemNovoEmprestimo;
    private javax.swing.JMenuItem menuItemNovoUsuario;
    private javax.swing.JMenuItem menuItemSituacao;
    // End of variables declaration//GEN-END:variables
}
