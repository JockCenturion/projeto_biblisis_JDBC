/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;

import br.com.biblisis.model.bean.Editora;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jock
 */
public class DAOEditora {
    
    public void create(Editora editora) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbeditora VALUES (?, ?) ");
            stmt.setString(1, null);
            stmt.setString(2, editora.getNomeEdit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO SQL :: Observe se os parâmetros estão corretos! ou ERRO nullPointerException :: ediotra inexistente(check os Parâmetros)!\n" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
    
    public List<Editora> read() throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
        List<Editora> tuplas          = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbeditora");
            rs   = stmt.executeQuery();
   
            while (rs.next()) {
                Editora editora = new Editora(rs.getInt("codEdit"), rs.getString("nomeEdit"));
                tuplas.add(editora);
            }
            
        } catch (SQLException e) {
           System.err.println("ERRO SQL :: Observe se os parâmetros estão corretos! ou ERRO nullPointerException :: ediotra inexistente(check os Parâmetros)!\n" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return tuplas;
    }
    
    public void update(Editora editora) throws SQLException {
        Connection con          =   ConnectionFactory.getConnection();
        PreparedStatement stmt  =   null;
        
        try {
          stmt = con.prepareStatement("UPDATE tbeditora SET codEdit = ?, nomeEdit = ? where codEdit = ?");
          stmt.setInt(1, editora.getCodEdit());
          stmt.setString(2, editora.getNomeEdit());
          stmt.setInt(3, editora.getCodEdit());
          stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Editora editora) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
      
        try {
            stmt = con.prepareStatement("DELETE FROM tbeditora WHERE codEdit = ?");
            stmt.setInt(1, editora.getCodEdit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public Editora search(Editora editora) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
        Editora tupla           = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbeditora WHERE codEdit = ?");
            stmt.setInt(1, editora.getCodEdit());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                tupla = new Editora(rs.getInt("codEdit"), rs.getString("nomeEdit"));
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            
        }

        return tupla;
    }
        
    
}
