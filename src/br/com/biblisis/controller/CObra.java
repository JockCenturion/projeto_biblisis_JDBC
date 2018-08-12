/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.controller;

import br.com.biblisis.model.bean.Data;
import br.com.biblisis.model.bean.Editora;
import br.com.biblisis.model.bean.Obra;
import br.com.biblisis.model.dao.DAOEditora;
import br.com.biblisis.model.dao.DAOObra;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jock
 */
public abstract class CObra {
    private static Obra obra;
    private static DAOObra daoObra;
    private static DAOEditora daoEditora;

     /** 
      * Rotina apenas o Administrador realiza - codCargo = 1
      *  Porem o Funcionario pode realizar um seu cadastro (Caso não exista!)
      *  Ao executar o cadastro faça sempre uma busca na base antes
      */
    public static void create(int codObra, String titulo, String categoria, String autor, String dataDePublicacao, int codEdit) throws SQLException { //data no formato --> yyyy/MM/DD <--- ler cada campo como string e juntar '+'
        daoObra = new DAOObra();
        obra    = new Obra(codObra, titulo, categoria, autor, new Data(dataDePublicacao), (new DAOEditora()).search(new Editora(codEdit)));
        daoObra.create(obra);
    }
    
    /*Rotina apenas o Administrador realiza - codCargo = 1*/
    public static List<String[]> read() throws SQLException {
        daoObra = new DAOObra();
        ArrayList<String[]> obras    = new ArrayList<String[]>();
        
        for (Obra obraEncontrada : daoObra.read()) {
            String[] busca = new String[6];
            busca[0]       = String.valueOf(obraEncontrada.getCodigoObra());
            busca[1]       = obraEncontrada.getTitulo();
            busca[2]       = obraEncontrada.getCategoria();
            busca[3]       = obraEncontrada.getAutor();
            busca[4]       = obraEncontrada.getDataPublicacao().toString();
            busca[5]       = String.valueOf(obraEncontrada.getEditora().getCodEdit());
            obras.add(busca);
        }
        
        return obras;
    }
    
    public static void update(int codObra, String titulo, String categoria, String autor, String dataDePublicao, int codEdit) throws SQLException {
        daoObra = new DAOObra();
        obra    = new Obra(codObra, titulo, categoria, autor, new Data(dataDePublicao), (new DAOEditora()).search(new Editora(codEdit)));
        daoObra.update(obra);
    }
    
     /** Rotina apenas o Administrador realiza - codCargo = 1
      *   Porem um funcionario pode excluir sua conta
      */
    public static void delete(int codEdit) throws SQLException {
        daoObra = new DAOObra();
        obra    = new Obra(codEdit);
        daoObra.delete(obra);
    }
    
    
    public static String[] search(int codObra) throws SQLException {
        daoObra = new DAOObra();
        obra    = daoObra.search(new Obra(codObra));
        String[] retorno = new String[6];
        if (obra != null){
            retorno[0] = String.valueOf(obra.getCodigoObra());
            retorno[1] = obra.getTitulo();
            retorno[2] = obra.getCategoria();
            retorno[3] = obra.getAutor();
            retorno[4] = obra.getDataPublicacao().toString();
            retorno[5] = String.valueOf(obra.getEditora().getCodEdit());
        }else{
            retorno = null;
        }
        return retorno;
    }    
}
