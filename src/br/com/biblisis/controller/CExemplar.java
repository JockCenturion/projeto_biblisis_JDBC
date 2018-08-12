/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.controller;

import br.com.biblisis.model.bean.Exemplar;
import br.com.biblisis.model.bean.Obra;
import br.com.biblisis.model.dao.DAOObra;
import br.com.biblisis.model.dao.DAOExemplar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jock
 */
public abstract class CExemplar {
    private static Exemplar exemplar;
    private static DAOExemplar daoExemplar;

     /** 
      * Rotina apenas o Administrador realiza - codCargo = 1
      *  Porem o Funcionario pode realizar um seu cadastro (Caso não exista!)
      *  Ao executar o cadastro faça sempre uma busca na base antes
      */
    public static void create(int codExemplar, int codObra, boolean isEmprestado) throws SQLException {
        try {
            daoExemplar       = new DAOExemplar();
            exemplar          = new Exemplar(codExemplar, (new DAOObra()).search(new Obra(codObra)),isEmprestado);
            daoExemplar.create(exemplar);            
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: codExemplar, codObra.\n" + e);
        }
    }
    
     /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static List<String[]> read() throws SQLException {
        daoExemplar                       = new DAOExemplar();
        ArrayList<String[]> exemplares    = new ArrayList<>();
        
        for (Exemplar exemplar : daoExemplar.read()) {
            String[] busca = new String[3];
            busca[0]       = String.valueOf(exemplar.getCodExemplar());
            busca[1]       = exemplar.getObra().toString();
            busca[2]       = String.valueOf(exemplar.getIsEmprestado());
            exemplares.add(busca);
        }
        
        return exemplares;
    }
    
    public static void update(int codExemplar, int codObra, boolean isEmprestado) throws SQLException {
        try {
            daoExemplar       = new DAOExemplar();
            exemplar          = new Exemplar(codExemplar, (new DAOObra()).search(new Obra(codObra)),isEmprestado);
            daoExemplar.update(exemplar);            
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: codExemplar, codObra.\n" + e);  
        }
    }
    
     /** Rotina apenas o Administrador realiza - codCargo = 1
      *   Porem um funcionario pode excluir sua conta
      */
    public static void delete(int codExemplar, int codObra) throws SQLException {
        
        try {
            daoExemplar       = new DAOExemplar();
            exemplar          = new Exemplar(codExemplar, (new DAOObra()).search(new Obra(codObra)));
            daoExemplar.delete(exemplar);        
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("ERRO ::: codExemplar, codObra.\n" + e);  
        }
    }
    
    public static String[] search(int codExemplar, int codObra) throws SQLException {
        try {
            daoExemplar   = new DAOExemplar();
            exemplar      = daoExemplar.search(new Exemplar(codExemplar, new Obra(codObra)));
            String[] retorno = new String[3];
            if (exemplar != null){
                retorno[0] = String.valueOf(exemplar.getCodExemplar());
                retorno[1] = exemplar.getObra().toString();
                retorno[2] = String.valueOf(exemplar.getIsEmprestado());
            }else{
                retorno = null;
            }
            return retorno;       
        } catch (NullPointerException e) {
            
            throw new IllegalArgumentException("ERRO ::: codExemplar, codObra.\n" + e);  
        }
    }      
}
