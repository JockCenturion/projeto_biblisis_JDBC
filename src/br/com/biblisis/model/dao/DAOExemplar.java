/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;

import br.com.biblisis.model.bean.Exemplar;
import br.com.biblisis.model.bean.Obra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jock
 */
public class DAOExemplar {
     
    public void create(Exemplar exemplar) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbexemplar VALUES (?, ?, ?)");
            stmt.setInt(1, exemplar.getCodExemplar());
            stmt.setInt(2, exemplar.getObra().getCodigoObra());
            stmt.setBoolean(3, exemplar.getIsEmprestado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Exemplar> read() throws SQLException {
        Connection con              = ConnectionFactory.getConnection();
        PreparedStatement stmt      = null;
        ResultSet rs                = null;
        ArrayList<Exemplar> tuplas       = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbexemplar ORDER BY codObra, codEx");
            rs   = stmt.executeQuery();
            
            while (rs.next()) {
                Exemplar exemplar = new Exemplar(rs.getInt("codEx"), (new DAOObra()).search(new Obra(rs.getInt("codObra"))) ,rs.getBoolean("isEmprestado"));
                tuplas.add(exemplar);
            }
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return tuplas;
    }
    
    public void update(Exemplar exemplar) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbexemplar SET isEmprestado = ? WHERE codEx = ? and codObra = ?");
            stmt.setBoolean(1, exemplar.getIsEmprestado());
            stmt.setInt(2, exemplar.getCodExemplar());
            stmt.setInt(3, exemplar.getObra().getCodigoObra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public void delete(Exemplar exemplar) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM tbexemplar WHERE codEx = ? and codObra = ?");
            stmt.setInt(1, exemplar.getCodExemplar());
            stmt.setInt(2, exemplar.getObra().getCodigoObra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public Exemplar search(Exemplar exemplar) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
        Exemplar tupla          = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbexemplar WHERE codEx = ? and codObra = ?");
            stmt.setInt(1, exemplar.getCodExemplar());
            stmt.setInt(2, exemplar.getObra().getCodigoObra());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                tupla = new Exemplar(rs.getInt("codEx"), (new DAOObra()).search(new Obra(rs.getInt("codObra"))), rs.getBoolean("isEmprestado"));
            }
        } catch (Exception e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return tupla;
    }
    
}
