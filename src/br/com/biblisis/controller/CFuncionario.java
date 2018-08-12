/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.controller;

import br.com.biblisis.model.bean.AlunoProfessor;
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
public abstract class CFuncionario {
    private static Funcionario funcionario;
    private static DAOFuncionario daoFuncionario;

    /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static void create(String login, String senha, String nome, int cargo) throws SQLException {
        daoFuncionario = new DAOFuncionario();
        funcionario    = new Funcionario(login, senha, nome, cargo);
        daoFuncionario.create(funcionario);
    }
    
     /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static List<String[]> read() throws SQLException {
        daoFuncionario                   = new DAOFuncionario();
        ArrayList<String[]> funcionarios = new ArrayList<>();
        
        for (Funcionario usuario : daoFuncionario.read()) {
            String[] busca = new String[2];
            busca[0]       = usuario.getLogin();
            busca[1]       = usuario.getNome();
            funcionarios.add(busca);
        }
        
        return funcionarios;
    }
    
    public static void update(String login, String senha, String nome, int cargo) throws SQLException {
        daoFuncionario = new DAOFuncionario();
        funcionario    = new Funcionario(login, senha, nome, cargo);
        daoFuncionario.update(funcionario);
    }
    
    public static boolean login(String login, String senha, int cargo) throws SQLException {
        daoFuncionario = new DAOFuncionario();
        funcionario    = new Funcionario(login, senha, "", cargo);
        return daoFuncionario.login(funcionario) != null;
    }
    
    /** Rotina apenas o Administrador realiza - codCargo = 1
     *  Porem um funcionario pode excluir sua conta
     */
    public static void delete(String login) throws SQLException {
        daoFuncionario = new DAOFuncionario();
        funcionario    = new Funcionario(login);
        daoFuncionario.delete(funcionario);
    }
    
    /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static String[] search(String login) throws SQLException {
        try {
            daoFuncionario = new DAOFuncionario();
            funcionario    = daoFuncionario.search(new Funcionario(login));
            String[] retorno = new String[2];
            if (funcionario!= null){
                retorno[0] = funcionario.getLogin();
                retorno[1] = funcionario.getNome();
                return retorno;    
            }else{
               return null;
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: login do funcionario está incorreto ou ele é Inexistente na BD.\n" + e);
        }  
    }
    
    public static void fazEmprestimo(String loginFun, String loginUser, int codExemplar, int codObra) throws SQLException, ParseException {
        try {
            DAOAlunoProfessor   daoUser  = new DAOAlunoProfessor();
            DAOExemplar daoExemplar      = new DAOExemplar();
            DAOObra daoObra              = new DAOObra();
            AlunoProfessor usuario       = null;
            Exemplar exemplar            = null;
            Obra obra                    = null;
            
   
            if (verificaEmprestimosAtrasados(loginUser)) {
                usuario = daoUser.search(new AlunoProfessor(loginUser));
                usuario.setStaus(true); //alunoProfessor bloqueado
                daoUser.update(usuario);
            } else {
                exemplar = daoExemplar.search(new Exemplar(codExemplar, daoObra.search(new Obra(codObra))));
                exemplar.setIsEmprestado(true);
                daoExemplar.update(exemplar);
                CEmprestimo.create(loginFun, loginUser, codExemplar, codObra, 7);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: login do funcionario está incorreto ou ele é Inexistente na BD.\n" + e);
        }
    }
    
    public static void fazDevolucao(int codDevolucao, String loginFun, int codEx, int codObra) {
        //fazendo a devolucao EFETUO O DESBLOQUEIO
        // busca um emprestimo no banco de dados
        // busca o fun no banco no banco de dados
        // cria uma Data (!não eh date) -> ela já pega a data do serviço no momento de criacao
        // cria uma devolucao (cod, loginFun, codEx, data)
        //salva no banco
    }
    
    public static void fazRenovacao() {
       //definir regras para espirar, data de retirada
    }
    
    public static List<String[]> consultaAcervo() throws SQLException {
        return  CObra.read(); 
    }
    
    private static boolean verificaEmprestimosAtrasados(String loginUser) throws SQLException, ParseException {
        
        for (Emprestimo emprestimo : (new DAOEmprestimo()).userRead(loginUser) ) {
            if (emprestimo.isAtraso()) {
                return true;
            }
        }
        
        return false;
    }
    
    public static String[] geraRelatorio() {
        //UsuarioPendentes
        //frequencias de livros + emprestados
        return null;
    }

}
