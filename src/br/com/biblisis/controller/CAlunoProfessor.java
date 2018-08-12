/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.controller;

import br.com.biblisis.model.bean.AlunoProfessor;
import br.com.biblisis.model.dao.DAOAlunoProfessor;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jock
 */
public abstract class CAlunoProfessor {
    
    private static AlunoProfessor alunoProfessor;
    private static DAOAlunoProfessor daoAlunoProfessor;

     /** 
      * Rotina apenas o Administrador realiza - codCargo = 1
      *  Porem o Funcionario pode realizar um seu cadastro (Caso não exista!)
      *  Ao executar o cadastro faça sempre uma busca na base antes
      */
    public static void create(String login, String senha, String nome, boolean status) throws SQLException {
        daoAlunoProfessor = new DAOAlunoProfessor();
        alunoProfessor    = new AlunoProfessor(login, senha, nome, status);
        daoAlunoProfessor.create(alunoProfessor);
    }
    
     /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static List<String[]> read() throws SQLException {
        daoAlunoProfessor                       = new DAOAlunoProfessor();
        ArrayList<String[]> alunoProfessores    = new ArrayList<>();
        
        for (AlunoProfessor usuario : daoAlunoProfessor.read()) {
            String[] busca = new String[3];
            busca[0]       = usuario.getLogin();
            busca[1]       = usuario.getNome();
            busca[2]       = String.valueOf(usuario.getStatus());
            alunoProfessores.add(busca);
        }
        
        return alunoProfessores;
    }
    
    public static void update(String login, String senha, String nome, boolean status) throws SQLException {
        daoAlunoProfessor = new DAOAlunoProfessor();
        alunoProfessor    = new AlunoProfessor(login, senha, nome, status);;
        daoAlunoProfessor.update(alunoProfessor);
    }
    
     /** Rotina apenas o Administrador realiza - codCargo = 1
      *   Porem um funcionario pode excluir sua conta
      */
    public static void delete(String login) throws SQLException {
        daoAlunoProfessor = new DAOAlunoProfessor();
        alunoProfessor    = new AlunoProfessor(login);
        daoAlunoProfessor.delete(alunoProfessor);
    }
    
    /*rotina do administrador -- somente ele pode busca sem senha*/
    public static String[] search(String login) throws SQLException {
        try {
            daoAlunoProfessor = new DAOAlunoProfessor();
            alunoProfessor    = daoAlunoProfessor.search(new AlunoProfessor(login));
            String[] retorno = new String[3];
            if (alunoProfessor!= null){
               retorno[0] = alunoProfessor.getLogin();
               retorno[1] = alunoProfessor.getNome();
               retorno[2] = String.valueOf(alunoProfessor.getStatus()); 
            }else{
                retorno = null;
            }
            return retorno;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: login do AlunoProfessor está incorreto ou ele é Inexistente na BD.\n" + e);
        }
    }
        
    
    public static boolean login(String login, String senha) throws SQLException {
        daoAlunoProfessor = new DAOAlunoProfessor();
        alunoProfessor    = new AlunoProfessor(login, senha, "", false);
        return daoAlunoProfessor.login(alunoProfessor) != null;
    }
    
    public static List<String[]> consultaAcervo() throws SQLException {
        return CObra.read();
    }
    
    public static List<String[]> consultaHistorioEmprestimo(String loginUser) throws SQLException, ParseException {
        return CEmprestimo.userRead(loginUser);
    }
    
    public static List<String[]> consultaHistoricoReservas() {
        //será desenvolvido em proximas releases...
        return null;
    }
    
}
