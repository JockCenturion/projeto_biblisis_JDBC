/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import br.com.biblisis.controller.CAlunoProfessor;
import br.com.biblisis.controller.CEmprestimo;
import br.com.biblisis.controller.CExemplar;
import br.com.biblisis.controller.CObra;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author romeu
 */
public class TelaNovoEmprestimo extends javax.swing.JFrame {

    /**
     * Creates new form TelaNovoEmprestimo
     */
    ArrayList <String[]> acervo;
    ArrayList <String[]> acervoDeExemplares;
    ArrayList <String[]> emprestimos;
    
    
    
    private DefaultListModel modeloLista = new DefaultListModel();
    private String loginFuncionario;
    private String loginUsuario;
    private int codigoDoExemplar;
    private int codigoDaObra;
    private int diasParaADevolucao;
    
    
    public TelaNovoEmprestimo() {
        initComponents();
        this.acervo = new ArrayList<String[]>();
        this.acervoDeExemplares = new ArrayList<String[]>();
        this.emprestimos = new ArrayList<String[]>();
    }
    
    private void constroiListaDeEmprestimos() throws ParseException{
        if (!this.emprestimos.isEmpty()){
            this.emprestimos.clear();
        }
        try {
            this.emprestimos = (ArrayList<String[]>) CEmprestimo.readAll();
         }catch (SQLException e)  {
                System.out.println("Erro!" + e);
        }  
    }

    public void setLoginFuncionario(String loginFuncionario) {
        this.loginFuncionario = loginFuncionario;
    }

    private void adicionaExemplaroNaLista (String codigoDoExemplar){
        listaExemplares.setModel(modeloLista);
        modeloLista.addElement(codigoDoExemplar);
    }
    
    private String[] buscaAlunoProfessor(String login){
        String[] resposta = null;
        try {
            resposta = CAlunoProfessor.search(login);
        }catch (SQLException e) {
            System.out.println("Erro!" + e);
        }
        return resposta;
    }
    
    private void constroiAcervo(){
        try {
            this.acervo = (ArrayList<String[]>) CObra.read();
            }catch (SQLException e) {
                System.out.println("Erro!" + e);
        }   
    }
    
    private void constroiAcervoDeExemplares(){
        try {
            this.acervoDeExemplares = (ArrayList<String[]>) CExemplar.read();
            }catch (SQLException e) {
                System.out.println("Erro!" + e);
        }   
    }
    
    private String [] buscaExemplar(int codigoDoExemplar, int codigoDaObra){
        String [] resposta = null;
        try {
            resposta = CExemplar.search(codigoDoExemplar, codigoDaObra);
        }catch (SQLException e) {
            System.out.println("Erro!" + e);
            return resposta;
        }
        return resposta;
    }
    
    private String [] buscaObra (int codigoDaObra){
        
        Iterator it = this.acervo.iterator();
        String [] obra;
        while (it.hasNext()){
            obra = (String [])it.next();
            if (Integer.parseInt(obra[0]) == codigoDaObra ){
                return obra;
            }
        }
        return null;
    }
    
    private ArrayList<String> buscaExemplares(int codigoDaObra){
        
        Iterator it = this.acervoDeExemplares.iterator();
        String [] exemplar;
        String [] obra;
        ArrayList<String> exemplaresDesejados = new ArrayList();
        while (it.hasNext()){
            exemplar = (String [])it.next();
            obra = exemplar[1].split(",");
            obra = obra[0].split("=");
            if (codigoDaObra == Integer.parseInt(obra[1]) ){
                exemplaresDesejados.add(exemplar[0]);
            } 
        }
        return exemplaresDesejados;
    }
    
    private boolean disponibilidadeDoExemplar (int codigoDoExemplar, int codigoDaObra){
        String [] exemplarProcurado = buscaExemplar(codigoDoExemplar, codigoDaObra);
        //System.out.println(exemplarProcurado[2]);
        if (Boolean.parseBoolean(exemplarProcurado[2]) == true){
            return false;
        }else{
            return true;
        }
    }
    
    private void registraNovoEmprestimo (String loginFunc, String loginUsu, int codEx, int codOb, int dias){
        try {
            CEmprestimo.create(loginFunc, loginUsu, codEx, codOb, dias);
            }catch (SQLException e) {
                System.out.println("Erro!" + e);
        }  
        try {
            CExemplar.update(codEx, codOb, true);
            }catch (SQLException e) {
                System.out.println("Erro!" + e);
        } 
    }
    
     private Date geraDataLimite (int diasEmprestados , Date dataAtual){
        Date dataLimite;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataAtual);
        calendar.add(Calendar.DATE, + diasEmprestados); 
        dataLimite = calendar.getTime(); 
        
        return dataLimite;
    }
     /*
    private int buscaCodigoDoEmprestimo (String [] emprestimoProcurado){
        int indiceProcurado;
        int codigoDoEmprestimo;
        if (this.emprestimos.contains(emprestimoProcurado)){
           indiceProcurado = this.emprestimos.indexOf(emprestimoProcurado);
        }else{
           return -1;   
        } 
        codigoDoEmprestimo = this.emprestimos.size() - indiceProcurado;
        return codigoDoEmprestimo;
    }
    
     
     
     private int buscaCodigoDoEmprestimo (String logFunc, String logUsu, String dataEmp  ){
        Iterator it = this.emprestimos.iterator();
        String [] emprestimoDaLista;
       
        while (it.hasNext()){
            emprestimoDaLista = (String[]) it.next();
            String dataDoEmprestimoDaLista = emprestimoDaLista[4];
            String [] dataDividida = dataDoEmprestimoDaLista.split(" ");
            String hora = dataDividida[1];
            dataDividida = dataDividida[0].split("-");
            String dataFormatada = dataDividida[2]+"/"+dataDividida[1] + "/" + dataDividida[0] +" "+ hora;
            
            System.out.println(this.emprestimos.size() - this.emprestimos.indexOf(emprestimoDaLista));
            System.out.println(emprestimoDaLista[0] +  " - " + logFunc + emprestimoDaLista[1] +  " - " + logUsu + dataFormatada + " - " + dataEmp );
            System.out.println(emprestimoDaLista[0].equals(logFunc) + " " + emprestimoDaLista[1].equals(logUsu) + " " + dataEmp.equals(dataFormatada) );
            
            if ( (emprestimoDaLista[0].equals(logFunc)) && (emprestimoDaLista[1].equals(logUsu)) && (dataEmp.equals(dataFormatada)) ){
                return this.emprestimos.size() - this.emprestimos.indexOf(emprestimoDaLista);
            }
        }
        return -1;
    */
    
    private String geraCodigoDeEmprestimo (String data){
       String [] dataDividida = data.split(" ");
       String hora = dataDividida[1];
       dataDividida = dataDividida[0].split("/");
       String [] horaDividida = hora.split(":");
       String codigoDoEmprestimo = "E" + dataDividida[0] + dataDividida[1] + dataDividida[2] + "-" + horaDividida[0] + horaDividida[1] + horaDividida[2];
       return codigoDoEmprestimo;
    }
     
    private String [] buscaReferenciaDoEmprestimo (String logFunc, String logUsu, int codEx, int codObra, String dataEmp  ){ //OK
        Iterator it = this.emprestimos.iterator();
        String [] emprestimoDaLista;
       
        while (it.hasNext()){
            emprestimoDaLista = (String[]) it.next();
            
            System.out.println(dataEmp);
            System.out.println(emprestimoDaLista[4]);
            System.out.println(dataEmp.equals(emprestimoDaLista[4]));
            
            if ( (emprestimoDaLista[0].equals(logFunc)) && (emprestimoDaLista[1].equals(logUsu)) && (emprestimoDaLista[2].equals(Integer.toString(codEx))) && (emprestimoDaLista[3].equals(Integer.toString(codObra))) && (dataEmp.equals(emprestimoDaLista[4])) ){
                
                return emprestimoDaLista;
            }
        }
        return null;
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
        jSeparator1 = new javax.swing.JSeparator();
        txtLoginId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnVerificarSituacao = new javax.swing.JButton();
        lblSituacaoDoUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblSituacaoDaObra = new javax.swing.JLabel();
        btnVerificarDisponibilidade = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoDaObra = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaExemplares = new javax.swing.JList<>();
        btnConfirmarEmprestimo = new javax.swing.JButton();
        lblCodigoDoExemplar = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnFechar3 = new javax.swing.JButton();
        lblConsultarAcervo = new javax.swing.JButton();
        btnColarCodigoCopiado = new javax.swing.JButton();
        lblDisponibilidadeDoExemplar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDiasParaDevolucao = new javax.swing.JLabel();
        txtDiasParaADevolucao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Novo Empréstimo");
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("NOVO EMPRÉSTIMO");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtLoginId.setToolTipText("seu ID pode conter letras ou números");
        txtLoginId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoginIdKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("LOGIN/ID");

        btnVerificarSituacao.setText("Verificar Situação");
        btnVerificarSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarSituacaoActionPerformed(evt);
            }
        });

        lblSituacaoDoUsuario.setText("Situação");
        lblSituacaoDoUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("NOME");

        lblNome.setText("Digite o ID do usuário");
        lblNome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSituacaoDaObra.setText("Situação");
        lblSituacaoDaObra.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnVerificarDisponibilidade.setText("Verificar Disponibildade");
        btnVerificarDisponibilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarDisponibilidadeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("CÓDIGO DA OBRA");

        txtCodigoDaObra.setToolTipText("seu ID pode conter letras ou números");
        txtCodigoDaObra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoDaObraKeyPressed(evt);
            }
        });

        lblTitulo.setText("Digite o código da obra");
        lblTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("TÍTULO");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("EXEMPLARES DISPONÍVEIS");

        listaExemplares.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Exemplares" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaExemplares.setToolTipText("clique sobre o exemplar desejado para mais detalhes");
        listaExemplares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaExemplaresMouseClicked(evt);
            }
        });
        listaExemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listaExemplaresKeyPressed(evt);
            }
        });
        listaExemplares.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaExemplaresValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaExemplares);

        btnConfirmarEmprestimo.setText("Confirmar Empréstimo");
        btnConfirmarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarEmprestimoActionPerformed(evt);
            }
        });

        lblCodigoDoExemplar.setText("Digite o código da obra");
        lblCodigoDoExemplar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("EXEMPLAR SELECIONADO");

        btnFechar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-close-window-24.png"))); // NOI18N
        btnFechar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechar3ActionPerformed(evt);
            }
        });

        lblConsultarAcervo.setText("Consultar Acervo");
        lblConsultarAcervo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblConsultarAcervoActionPerformed(evt);
            }
        });

        btnColarCodigoCopiado.setText("Colar Código Copiado");
        btnColarCodigoCopiado.setToolTipText("acesse o acervo e copie um código para colar");
        btnColarCodigoCopiado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColarCodigoCopiadoActionPerformed(evt);
            }
        });

        lblDisponibilidadeDoExemplar.setText("Digite o código da obra");
        lblDisponibilidadeDoExemplar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("DISPONIBILIDADE DO EXEMPLAR");

        txtDiasParaDevolucao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDiasParaDevolucao.setText("DIAS PARA A DEVOLUÇÃO");

        txtDiasParaADevolucao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiasParaADevolucaoFocusLost(evt);
            }
        });
        txtDiasParaADevolucao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiasParaADevolucaoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtLoginId)
                                .addGap(18, 18, 18)
                                .addComponent(btnVerificarSituacao)
                                .addGap(18, 18, 18)
                                .addComponent(lblSituacaoDoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblCodigoDoExemplar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(165, 165, 165)))
                                        .addGap(0, 200, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblDisponibilidadeDoExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDiasParaDevolucao)
                                            .addComponent(txtDiasParaADevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(89, 89, 89))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnConfirmarEmprestimo)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblConsultarAcervo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoDaObra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVerificarDisponibilidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSituacaoDaObra, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)))
                        .addComponent(btnColarCodigoCopiado)
                        .addGap(18, 18, 18))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(216, 216, 216)
                .addComponent(btnFechar3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(btnFechar3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtLoginId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVerificarSituacao)
                        .addComponent(lblSituacaoDoUsuario)))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblNome))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCodigoDaObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerificarDisponibilidade)
                    .addComponent(lblSituacaoDaObra))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTitulo)
                    .addComponent(btnColarCodigoCopiado)
                    .addComponent(lblConsultarAcervo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfirmarEmprestimo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(11, 11, 11)
                        .addComponent(lblCodigoDoExemplar)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDiasParaDevolucao))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDisponibilidadeDoExemplar)
                            .addComponent(txtDiasParaADevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        constroiAcervo();
        constroiAcervoDeExemplares();
        try {
            constroiListaDeEmprestimos();
        } catch (ParseException ex) {
            Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }//GEN-LAST:event_formWindowOpened
   
   
    private void btnConfirmarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarEmprestimoActionPerformed
        try {
            constroiListaDeEmprestimos ();
        } catch (ParseException ex) {
            Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (!txtLoginId.getText().equals("") && lblSituacaoDoUsuario.getText().equals("Liberado")){
            if (!txtCodigoDaObra.getText().equals("") && lblSituacaoDaObra.getText().equals("Disponível")){
                if (lblDisponibilidadeDoExemplar.getText().equals("Disponível")){
                   if(!txtDiasParaADevolucao.getText().equals("")  && !txtDiasParaADevolucao.getText().matches("[A-Za-z]{1,}")){
                       int resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar o empréstimo?");
                       if (resposta == 0){
                           this.diasParaADevolucao = Integer.parseInt(txtDiasParaADevolucao.getText());
                           registraNovoEmprestimo(this.loginFuncionario, this.loginUsuario, this.codigoDoExemplar, this.codigoDaObra, this.diasParaADevolucao);
                           Date dataDoEmprestimo = new Date ();
                           Date dataLimite = geraDataLimite(this.diasParaADevolucao, dataDoEmprestimo);
                           SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
                           
                           try {//recarrega do banco para ArrayList de emprestimos
                               constroiListaDeEmprestimos();
                           } catch (ParseException ex) {
                               Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           SimpleDateFormat formatoParaGerarCodigo = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
                           
                           String codigoDoNovoEmprestimo = geraCodigoDeEmprestimo(formatoParaGerarCodigo.format(dataDoEmprestimo) );
                           System.out.println(codigoDoNovoEmprestimo);
                           
                           JOptionPane.showMessageDialog(null,"Código do Empréstimo: " + codigoDoNovoEmprestimo + "  Data de devolução: "  + formato.format(dataLimite));
                       }
                   }else{
                      JOptionPane.showMessageDialog(null, "Digite os dias para a devolução!");  
                   }
                }else{
                   JOptionPane.showMessageDialog(null, "Exemplar indisponível!"); 
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Obra indisponível ou código não digitado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuário bloqueado ou nome não digitado!");
        }
        
    }//GEN-LAST:event_btnConfirmarEmprestimoActionPerformed

    private void listaExemplaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaExemplaresMouseClicked
        if (!modeloLista.isEmpty()){
           String codigoDoExemplar = listaExemplares.getSelectedValue();
            lblCodigoDoExemplar.setText(codigoDoExemplar);
            boolean isExemplarDisponivel = disponibilidadeDoExemplar(Integer.parseInt(codigoDoExemplar), this.codigoDaObra);
            if (isExemplarDisponivel){
                lblDisponibilidadeDoExemplar.setText("Disponível");
                lblDisponibilidadeDoExemplar.setForeground(Color.blue);
                this.codigoDoExemplar = Integer.parseInt(codigoDoExemplar);//ATRIBUTO
            }else{
                lblDisponibilidadeDoExemplar.setText("Não disponível");
                lblDisponibilidadeDoExemplar.setForeground(Color.red);
            } 
        }
    }//GEN-LAST:event_listaExemplaresMouseClicked

    private void btnFechar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechar3ActionPerformed
        dispose();
    }//GEN-LAST:event_btnFechar3ActionPerformed

    private void btnVerificarSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarSituacaoActionPerformed
        String loginDoUsuario = txtLoginId.getText();
        String [] alunoProfessor;
        if (!loginDoUsuario.equals("") ){
           alunoProfessor = buscaAlunoProfessor(loginDoUsuario);
           if (alunoProfessor != null){
               lblNome.setText(alunoProfessor[1]);
               lblNome.setForeground(Color.black);
                if (Boolean.parseBoolean(alunoProfessor[2])){
                    lblSituacaoDoUsuario.setText("Bloqueado"); 
                    lblSituacaoDoUsuario.setForeground(Color.red);
                }else{
                    lblSituacaoDoUsuario.setText("Liberado"); 
                    lblSituacaoDoUsuario.setForeground(Color.blue);
                    this.loginUsuario = txtLoginId.getText(); //ATRIBUTO
                }
           }else{
                lblNome.setText("Usuário não encontrado!");
           }
           
        }else{
            lblNome.setText("Digite um ID!");
            lblNome.setForeground(Color.red);
        }
    }//GEN-LAST:event_btnVerificarSituacaoActionPerformed

    private void lblConsultarAcervoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblConsultarAcervoActionPerformed
        TelaConsultaAcervo novaTela = new TelaConsultaAcervo ();
        novaTela.setVisible(true);
        
    }//GEN-LAST:event_lblConsultarAcervoActionPerformed

    private void btnColarCodigoCopiadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColarCodigoCopiadoActionPerformed
       TextTransfer clipboard = new TextTransfer();
       txtCodigoDaObra.setText(clipboard.getClipboardContents());
    }//GEN-LAST:event_btnColarCodigoCopiadoActionPerformed

    private void btnVerificarDisponibilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarDisponibilidadeActionPerformed
         
            if (!txtCodigoDaObra.getText().matches("[A-Za-z]{1,}")){
                if (txtCodigoDaObra.getText().equals("")){
            lblSituacaoDaObra.setText("Digite um código!");
            lblSituacaoDaObra.setForeground(Color.red);
        }
        if (!txtCodigoDaObra.getText().equals("")){
            modeloLista.clear();
        String codigoDigitado = txtCodigoDaObra.getText();
        String [] obraDesejada = null;
        if (!codigoDigitado.equals("")){
            obraDesejada = buscaObra(Integer.parseInt(codigoDigitado));
            if (obraDesejada != null){
                lblTitulo.setText(obraDesejada[1]);
                lblTitulo.setForeground(Color.black);
            }else{
                lblTitulo.setText("Obra não encontrada!");
                lblTitulo.setForeground(Color.red);
            }
        }else{
            lblTitulo.setText("Digite um código para prosseguir!");
            lblTitulo.setForeground(Color.red);
        }
        
        ArrayList<String > exemplaresEncontrados;
        boolean isObraDisponivel = false;
        if (obraDesejada != null){
            exemplaresEncontrados = buscaExemplares(Integer.parseInt(codigoDigitado));
            if (!exemplaresEncontrados.isEmpty()){
                Iterator it = exemplaresEncontrados.iterator();
                String exemplar;
                while (it.hasNext()){
                    exemplar = (String)it.next();
                    if (isObraDisponivel == false){
                        isObraDisponivel = disponibilidadeDoExemplar(Integer.parseInt(exemplar), Integer.parseInt(codigoDigitado));//ARRUMAR ERRO!!!!!!!
                    }
                    listaExemplares.setModel(modeloLista);
                    modeloLista.addElement(exemplar);
                }
            }else{
                lblCodigoDoExemplar.setText("Sem exemplares para a obra! Contate o administrador.");
            }
        }
        if (isObraDisponivel){
            lblSituacaoDaObra.setText("Disponível");
            lblSituacaoDaObra.setForeground(Color.blue);
            this.codigoDaObra = Integer.parseInt(codigoDigitado);//ATRIBUTO
        }else{
            this.codigoDaObra = Integer.parseInt(codigoDigitado);
            lblSituacaoDaObra.setText("Não disponível");
            lblSituacaoDaObra.setForeground(Color.red);
        }
            }
        
        }
     
        
        
        
    }//GEN-LAST:event_btnVerificarDisponibilidadeActionPerformed

    private void listaExemplaresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaExemplaresValueChanged
        
    }//GEN-LAST:event_listaExemplaresValueChanged

    private void txtLoginIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginIdKeyReleased
       //txtLoginId.setText(txtLoginId.getText().toUpperCase());
    }//GEN-LAST:event_txtLoginIdKeyReleased

    private void txtDiasParaADevolucaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiasParaADevolucaoFocusLost
if(!txtDiasParaADevolucao.getText().equals("")  && !txtDiasParaADevolucao.getText().matches("[A-Za-z]{1,}")){
    int diasDigitados = Integer.parseInt(txtDiasParaADevolucao.getText());
        if(diasDigitados > 10){
            JOptionPane.showMessageDialog(null, "Não é permitido um prazo maior que 10 dias!");
            txtDiasParaADevolucao.setText("10");
        }
}        
        
    }//GEN-LAST:event_txtDiasParaADevolucaoFocusLost

    private void listaExemplaresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaExemplaresKeyPressed
        if (!modeloLista.isEmpty()){
           String codigoDoExemplar = listaExemplares.getSelectedValue();
            lblCodigoDoExemplar.setText(codigoDoExemplar);
            boolean isExemplarDisponivel = disponibilidadeDoExemplar(Integer.parseInt(codigoDoExemplar), this.codigoDaObra);
            if (isExemplarDisponivel){
                lblDisponibilidadeDoExemplar.setText("Disponível");
                lblDisponibilidadeDoExemplar.setForeground(Color.blue);
                this.codigoDoExemplar = Integer.parseInt(codigoDoExemplar);//ATRIBUTO
            }else{
                lblDisponibilidadeDoExemplar.setText("Não disponível");
                lblDisponibilidadeDoExemplar.setForeground(Color.red);
            } 
        }
    }//GEN-LAST:event_listaExemplaresKeyPressed

    private void txtLoginIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginIdKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             String loginDoUsuario = txtLoginId.getText();
        String [] alunoProfessor;
        if (!loginDoUsuario.equals("") ){
           alunoProfessor = buscaAlunoProfessor(loginDoUsuario);
           if (alunoProfessor != null){
               lblNome.setText(alunoProfessor[1]);
               lblNome.setForeground(Color.black);
                if (Boolean.parseBoolean(alunoProfessor[2])){
                    lblSituacaoDoUsuario.setText("Bloqueado"); 
                    lblSituacaoDoUsuario.setForeground(Color.red);
                }else{
                    lblSituacaoDoUsuario.setText("Liberado"); 
                    lblSituacaoDoUsuario.setForeground(Color.blue);
                    this.loginUsuario = txtLoginId.getText(); //ATRIBUTO
                }
           }else{
                lblNome.setText("Usuário não encontrado!");
           }
           
        }else{
            lblNome.setText("Digite um ID!");
            lblNome.setForeground(Color.red);
        }
        }
    }//GEN-LAST:event_txtLoginIdKeyPressed

    private void txtCodigoDaObraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoDaObraKeyPressed
        //tirar selecao
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (!txtCodigoDaObra.getText().matches("[A-Za-z]{1,}")){
                if (txtCodigoDaObra.getText().equals("")){
            lblSituacaoDaObra.setText("Digite um código!");
            lblSituacaoDaObra.setForeground(Color.red);
        }
        if (!txtCodigoDaObra.getText().equals("")){
            modeloLista.clear();
        String codigoDigitado = txtCodigoDaObra.getText();
        String [] obraDesejada = null;
        if (!codigoDigitado.equals("")){
            obraDesejada = buscaObra(Integer.parseInt(codigoDigitado));
            if (obraDesejada != null){
                lblTitulo.setText(obraDesejada[1]);
                lblTitulo.setForeground(Color.black);
            }else{
                lblTitulo.setText("Obra não encontrada!");
                lblTitulo.setForeground(Color.red);
            }
        }else{
            lblTitulo.setText("Digite um código para prosseguir!");
            lblTitulo.setForeground(Color.red);
        }
        
        ArrayList<String > exemplaresEncontrados;
        boolean isObraDisponivel = false;
        if (obraDesejada != null){
            exemplaresEncontrados = buscaExemplares(Integer.parseInt(codigoDigitado));
            if (!exemplaresEncontrados.isEmpty()){
                Iterator it = exemplaresEncontrados.iterator();
                String exemplar;
                while (it.hasNext()){
                    exemplar = (String)it.next();
                    if (isObraDisponivel == false){
                        isObraDisponivel = disponibilidadeDoExemplar(Integer.parseInt(exemplar), Integer.parseInt(codigoDigitado));//ARRUMAR ERRO!!!!!!!
                    }
                    listaExemplares.setModel(modeloLista);
                    modeloLista.addElement(exemplar);
                }
            }else{
                lblCodigoDoExemplar.setText("Sem exemplares para a obra! Contate o administrador.");
            }
        }
        if (isObraDisponivel){
            lblSituacaoDaObra.setText("Disponível");
            lblSituacaoDaObra.setForeground(Color.blue);
            this.codigoDaObra = Integer.parseInt(codigoDigitado);//ATRIBUTO
        }else{
            this.codigoDaObra = Integer.parseInt(codigoDigitado);
            lblSituacaoDaObra.setText("Não disponível");
            lblSituacaoDaObra.setForeground(Color.red);
        }
            }
        
        }
        }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_txtCodigoDaObraKeyPressed

    private void txtDiasParaADevolucaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasParaADevolucaoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
            constroiListaDeEmprestimos ();
        } catch (ParseException ex) {
            Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (!txtLoginId.getText().equals("") && lblSituacaoDoUsuario.getText().equals("Liberado")){
            if (!txtCodigoDaObra.getText().equals("") && lblSituacaoDaObra.getText().equals("Disponível")){
                if (lblDisponibilidadeDoExemplar.getText().equals("Disponível")){
                   if(!txtDiasParaADevolucao.getText().equals("")  && !txtDiasParaADevolucao.getText().matches("[A-Za-z]{1,}")){
                       int resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar o empréstimo?");
                       if (resposta == 0){
                           this.diasParaADevolucao = Integer.parseInt(txtDiasParaADevolucao.getText());
                           registraNovoEmprestimo(this.loginFuncionario, this.loginUsuario, this.codigoDoExemplar, this.codigoDaObra, this.diasParaADevolucao);
                           Date dataDoEmprestimo = new Date ();
                           Date dataLimite = geraDataLimite(this.diasParaADevolucao, dataDoEmprestimo);
                           SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
                           
                           try {//recarrega do banco para ArrayList de emprestimos
                               constroiListaDeEmprestimos();
                           } catch (ParseException ex) {
                               Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           SimpleDateFormat formatoParaGerarCodigo = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
                           
                           String codigoDoNovoEmprestimo = geraCodigoDeEmprestimo(formatoParaGerarCodigo.format(dataDoEmprestimo) );
                           System.out.println(codigoDoNovoEmprestimo);
                           
                           JOptionPane.showMessageDialog(null,"Código do Empréstimo: " + codigoDoNovoEmprestimo + "  Data de devolução: "  + formato.format(dataLimite));
                       }
                   }else{
                      JOptionPane.showMessageDialog(null, "Digite os dias para a devolução!");  
                   }
                }else{
                   JOptionPane.showMessageDialog(null, "Exemplar indisponível!"); 
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Obra indisponível ou código não digitado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuário bloqueado ou nome não digitado!");
        }
        }
    }//GEN-LAST:event_txtDiasParaADevolucaoKeyPressed

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
            java.util.logging.Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaNovoEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaNovoEmprestimo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColarCodigoCopiado;
    private javax.swing.JButton btnConfirmarEmprestimo;
    private javax.swing.JButton btnFechar3;
    private javax.swing.JButton btnVerificarDisponibilidade;
    private javax.swing.JButton btnVerificarSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCodigoDoExemplar;
    private javax.swing.JButton lblConsultarAcervo;
    private javax.swing.JLabel lblDisponibilidadeDoExemplar;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSituacaoDaObra;
    private javax.swing.JLabel lblSituacaoDoUsuario;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listaExemplares;
    private javax.swing.JTextField txtCodigoDaObra;
    private javax.swing.JTextField txtDiasParaADevolucao;
    private javax.swing.JLabel txtDiasParaDevolucao;
    private javax.swing.JTextField txtLoginId;
    // End of variables declaration//GEN-END:variables
}
