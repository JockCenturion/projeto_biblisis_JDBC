/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.controller;

import br.com.biblisis.model.bean.Editora;
import br.com.biblisis.model.dao.DAOEditora;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jock
 */
public abstract class CEditora {
    private static Editora editora;
    private static DAOEditora daoEditora;

     /** 
      * Rotina apenas o Administrador realiza - codCargo = 1
      *  Porem o Funcionario pode realizar um seu cadastro (Caso não exista!)
      *  Ao executar o cadastro faça sempre uma busca na base antes
      */
    public static void create(String nomeEdit) throws SQLException {
        daoEditora      = new DAOEditora();
        editora         = new Editora(nomeEdit);
        daoEditora.create(editora);
    }
    
    /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static List<String[]> read() throws SQLException {
       daoEditora      = new DAOEditora();
       ArrayList<String[]> editoras   = new ArrayList<>();
        
        for (Editora editora : daoEditora.read()) {
            String[] busca = new String[2];
            busca[0]       = String.valueOf(editora.getCodEdit());
            busca[1]       = editora.getNomeEdit();
            editoras.add(busca);
        }
        
        return editoras;
    }
    
    public static void update(int codEdit, String nomeEdit) throws SQLException {
        daoEditora      = new DAOEditora();
        editora         = new Editora(codEdit, nomeEdit);
        daoEditora.update(editora);
    }
    
    /** Rotina apenas o Administrador realiza - codCargo = 1
      * Porem um funcionario pode excluir sua conta
      */
    public static void delete(int codEdit) throws SQLException {
        daoEditora      = new DAOEditora();
        editora         = new Editora(codEdit);
        daoEditora.delete(editora);
    }
    
    public static String[] search(int codEdit) throws SQLException {
        daoEditora          = new DAOEditora();
        editora             = daoEditora.search(new Editora(codEdit));
        String[] retorno    = new String[2];
        
        if (editora!= null){
        retorno[0] = String.valueOf(editora.getCodEdit());
        retorno[1] = editora.getNomeEdit();
        }else{
            retorno = null;
        }
        return retorno;
    }      
}
