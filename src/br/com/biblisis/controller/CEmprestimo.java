/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.controller;


import br.com.biblisis.model.bean.AlunoProfessor;
import br.com.biblisis.model.bean.Data;
import br.com.biblisis.model.bean.Emprestimo;
import br.com.biblisis.model.bean.Exemplar;
import br.com.biblisis.model.bean.Funcionario;
import br.com.biblisis.model.bean.Obra;
import br.com.biblisis.model.dao.DAOAlunoProfessor;
import br.com.biblisis.model.dao.DAOEmprestimo;
import br.com.biblisis.model.dao.DAOExemplar;
import br.com.biblisis.model.dao.DAOFuncionario;
import br.com.biblisis.model.dao.DAOObra;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jock
 */
public abstract class CEmprestimo {
    private static Emprestimo emprestimo;
    private static Exemplar exemplar;
    private static Obra obra;
    private static AlunoProfessor alunoProfessor;
    private static Funcionario funcionario;
    private static Data dtDevolucao, dtEmprestimo;
    private static DAOEmprestimo daoEmprestimo;
    private static DAOExemplar daoExemplar;
    private static DAOObra daoObra;
    private static DAOAlunoProfessor daoAlunoProfessor;
    private static DAOFuncionario daoFuncionario;
   
    private static Emprestimo cud(String loginFun, String loginUser, int codExemplar, int codObra) throws SQLException {
        //Instancias de daos
        daoExemplar = new DAOExemplar();
        daoObra = new DAOObra();
        daoAlunoProfessor = new DAOAlunoProfessor();
        daoFuncionario = new DAOFuncionario();

        //instancias do model
        exemplar = daoExemplar.search(new Exemplar(codExemplar, new Obra(codObra))); // cuida com null pointerException
        obra = daoObra.search(new Obra(codObra));
        alunoProfessor = daoAlunoProfessor.search(new AlunoProfessor(loginUser));
        funcionario = daoFuncionario.search(new Funcionario(loginFun));
        dtDevolucao = dtEmprestimo = new Data("yyyy-MM-dd HH:mm:ss");

        return (new Emprestimo(funcionario, alunoProfessor, exemplar, obra, dtEmprestimo, dtDevolucao) );
    }
    
    public static Data create(String loginFun, String loginUser, int codExemplar, int codObra, int diasParaDevolucao) throws SQLException {
        try {
            daoEmprestimo = new DAOEmprestimo();
            emprestimo = cud(loginFun, loginUser, codExemplar, codObra);
            emprestimo.getDataDevolucao().adicionarDias(diasParaDevolucao);
            daoEmprestimo.create(emprestimo);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: login(funcionario ou usuario), codExmplar ou codObra incorretos.\n" + e);
        }
        return emprestimo.getDataEmprestimo();
    }
    
    public static void update(String loginFun, String loginUser, int codExemplar, int codObra, int diasParaDevolucao) throws SQLException {
         try {
            daoEmprestimo = new DAOEmprestimo();
            emprestimo = cud(loginFun, loginUser, codExemplar, codObra);
            emprestimo.getDataDevolucao().adicionarDias(diasParaDevolucao);
            daoEmprestimo.update(emprestimo);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: login(funcionario ou usuario), codExmplar ou codObra incorretos.\n" + e);
        }       
    }

    /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static void delete(String loginFun, String loginUser, int codExemplar, int codObra) throws SQLException {
        try {
            daoEmprestimo = new DAOEmprestimo();
            emprestimo = cud(loginFun, loginUser, codExemplar, codObra);
            daoEmprestimo.delete(emprestimo);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: login(funcionario ou usuario), codExmplar ou codObra incorretos.\n" + e);
        }         
    }        
    
     /*Rotina apenas o Administrador realiza - codCargo = 1*/
    private static List<String[]> read(List<Emprestimo> read) throws SQLException, ParseException {
        daoEmprestimo       = new DAOEmprestimo();
        ArrayList<String[]> emprestimos   = new ArrayList<>();
        
        for (Emprestimo emprestimo : read) {
            String[] busca = new String[6];
            busca[0]       = emprestimo.getFuncionario().getLogin();
            busca[1]       = emprestimo.getUsuario().getLogin();
            busca[2]       = String.valueOf(emprestimo.getExemplar().getCodExemplar());
            busca[3]       = String.valueOf(emprestimo.getObra().getCodigoObra());
            busca[4]       = emprestimo.getDataEmprestimo().toString();
            busca[5]       = emprestimo.getDataDevolucao().toString();
            emprestimos.add(busca);
        }
        
        return emprestimos;
    }
    
    public static List<String[]> readAll() throws SQLException, ParseException {
        daoEmprestimo                      = new DAOEmprestimo();
        ArrayList<String[]> emprestimos    = new ArrayList<>();
        
        for (Emprestimo emprestimo : daoEmprestimo.readAll()) {
            String[] busca = new String[6];
            busca[0]       = emprestimo.getFuncionario().getLogin();
            busca[1]       = emprestimo.getUsuario().getLogin();
            busca[2]       = String.valueOf(emprestimo.getExemplar().getCodExemplar());
            busca[3]       = String.valueOf(emprestimo.getObra().getCodigoObra());
            busca[4]       = emprestimo.getDataEmprestimo().toString();
            busca[5]       = emprestimo.getDataDevolucao().toString();
            emprestimos.add(busca);
        }
        
        return emprestimos;
    }
    
    public static List<String[]> allRead() throws SQLException, ParseException {
        return read( (new DAOEmprestimo() ).allRead() );
    }
    
    public static List<String[]> userRead(String loginUser) throws SQLException, ParseException {
        return read( (new DAOEmprestimo().userRead(loginUser)) ); 
    }
    
    public static List<String[]> funcionarioRead(String loginFun) throws SQLException, ParseException {
        return read( (new DAOEmprestimo()).funcionarioRead(loginFun) ); 
    }
    
    
      /** 
      * Rotina apenas o Administrador realiza - codCargo = 1
      *  Porem o Funcionario pode realizar um seu cadastro (Caso não exista!)
      *  Ao executar o cadastro faça sempre uma busca na base antes
      */
    /*public static void create(String loginFun, String loginUser, int codExemplar, int codObra, int diasParaDevolucao) {
        //Instancias de daos
        daoEmprestimo       = new DAOEmprestimo();
        daoExemplar         = new DAOExemplar();
        daoObra             = new DAOObra();
        daoAlunoProfessor   = new DAOAlunoProfessor();
        daoFuncionario      = new DAOFuncionario();
        
        try {
            //instancias do model
            exemplar            = daoExemplar.search(new Exemplar(codExemplar, new Obra(codObra))); // cuida com null pointerException
            obra                = daoObra.search(new Obra(codObra));
            alunoProfessor      = daoAlunoProfessor.search(new AlunoProfessor(loginUser));
            funcionario         = daoFuncionario.search(new Funcionario(loginFun));
            dtDevolucao         = dtEmprestimo = new Data("yyyy-MM-dd HH:mm:ss");
            
            //Altera o status do exemplar e atualiza no banco
            //exemplar.setIsEmprestado(true); //quem deve fazer isso é o controler de fun (faz emprestimo)
            //daoExemplar.update(exemplar);   //quem deve fazer isso é o controler de fun (faz emprestimo)
            
            //Altera a data de devolucao e guarda o emprestimo no banco
            dtDevolucao.adicionarDias(diasParaDevolucao);
            daoEmprestimo.create(new Emprestimo(funcionario, alunoProfessor, exemplar, obra, dtEmprestimo, dtDevolucao));
            
        } catch (NullPointerException | SQLException e) {
            System.err.println("ERRO, exemplar ou obra ou alunoProfessor ou funionario inexistente ou ERRO ao salvar no Banco de Dados ::: \n" + e);
        }
    }*/
    
        
    /*public static void update(String loginFun, String loginUser, int codExemplar, int codObra, int diasParaDevolucao) throws SQLException {
        try {
            //Instancias de daos
            daoEmprestimo       = new DAOEmprestimo();
            daoExemplar         = new DAOExemplar();
            daoObra             = new DAOObra();
            daoAlunoProfessor   = new DAOAlunoProfessor();
            daoFuncionario      = new DAOFuncionario();
   
            //instancias do model
            exemplar            = daoExemplar.search(new Exemplar(codExemplar, new Obra(codObra))); // cuida com null pointerException
            obra                = daoObra.search(new Obra(codObra));
            alunoProfessor      = daoAlunoProfessor.search(new AlunoProfessor(loginUser));
            funcionario         = daoFuncionario.search(new Funcionario(loginFun));
            dtDevolucao         = new Data("yyyy-MM-dd HH:mm:ss");
            
            //Altera o status do exemplar e atualiza no banco
            //exemplar.setIsEmprestado(true); //quem deve fazer isso é o controler de fun (faz emprestimo)
            //daoExemplar.update(exemplar);   //quem deve fazer isso é o controler de fun (faz emprestimo)
            
            //Altera a data de devolucao e guarda o emprestimo no banco
            dtDevolucao.adicionarDias(diasParaDevolucao);
            daoEmprestimo.update(new Emprestimo(funcionario, alunoProfessor, exemplar, obra, dtEmprestimo, dtDevolucao));
            
        } catch (NullPointerException | SQLException e) {
            System.err.println("ERRO, exemplar ou obra ou alunoProfessor ou funionario inexistente ou ERRO ao salvar no Banco de Dados ::: \n" + e);
        }
    }*/
    
    /** Rotina apenas o Administrador realiza - codCargo = 1
     *   Porem um funcionario pode excluir sua conta
     */
    /*public static void delete(String loginFun, String loginUser, int codExemplar, int codObra) throws SQLException {
        try {
            //Instancias de daos
            daoEmprestimo       = new DAOEmprestimo();
            daoExemplar         = new DAOExemplar();
            daoObra             = new DAOObra();
            daoAlunoProfessor   = new DAOAlunoProfessor();
            daoFuncionario      = new DAOFuncionario();
   
            //instancias do model
            exemplar            = daoExemplar.search(new Exemplar(codExemplar, new Obra(codObra))); // cuida com null pointerException
            obra                = daoObra.search(new Obra(codObra));
            alunoProfessor      = daoAlunoProfessor.search(new AlunoProfessor(loginUser));
            funcionario         = daoFuncionario.search(new Funcionario(loginFun));

            
            //Altera o status do exemplar e atualiza no banco
            //exemplar.setIsEmprestado(true); //quem deve fazer isso é o controler de fun (faz emprestimo)
            //daoExemplar.update(exemplar);   //quem deve fazer isso é o controler de fun (faz emprestimo)
            
            //Altera a data de devolucao e guarda o emprestimo no banco
            daoEmprestimo.delete(new Emprestimo(funcionario, alunoProfessor, exemplar, obra, null, null));
            
        } catch (NullPointerException | SQLException e) {
            System.err.println("ERRO, exemplar ou obra ou alunoProfessor ou funionario inexistente ou ERRO ao salvar no Banco de Dados ::: \n" + e);
        }
    }*/
}
