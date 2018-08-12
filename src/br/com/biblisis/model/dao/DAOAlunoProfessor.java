/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;

import br.com.biblisis.model.bean.AlunoProfessor;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Jock
 */
public class DAOAlunoProfessor {
    
    /*Crud Básico*/
    public void create(AlunoProfessor usuario) throws SQLException {
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt  = null;
       
       try {
           stmt = con.prepareStatement("INSERT INTO tbusuario values (?, ?, ?, ?)");
           stmt.setString(1, usuario.getLogin());
           stmt.setString(2, usuario.getSenha());
           stmt.setString(3, usuario.getNome());
           stmt.setBoolean(4, usuario.getStatus());
           stmt.executeUpdate();
       } catch (SQLException e) {
           System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
       } finally {
           ConnectionFactory.closeConnection(con, stmt);
       }
    }
    
    
    public List<AlunoProfessor> read() throws SQLException {
       Connection con                = ConnectionFactory.getConnection();
       PreparedStatement stmt        = null;
       ResultSet rs                  = null;
       List<AlunoProfessor> usuarios = new ArrayList<>();
       
       try {
           stmt = con.prepareStatement("SELECT * FROM tbusuario");
           rs = stmt.executeQuery();
           
           while (rs.next()) {
               AlunoProfessor uap = new AlunoProfessor(rs.getString("loginUser"), rs.getString("senhaUser"), rs.getString("nomeUser"), rs.getBoolean("statusUser"));
               usuarios.add(uap);
           }
     
       } catch (SQLException e) {
           System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
       } finally {
           ConnectionFactory.closeConnection(con, stmt, rs);
       }
       
       return usuarios;
    }
    
    public void update(AlunoProfessor usuario) throws SQLException {
       Connection con               = ConnectionFactory.getConnection();
       PreparedStatement stmt       = null;
       
        try {
            stmt = con.prepareStatement("UPDATE tbusuario SET loginUser = ?, senhaUser = ?, nomeUser = ?, statusUser = ? WHERE loginUser = ?");
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setBoolean(4, usuario.getStatus());
            stmt.setString(5, usuario.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(AlunoProfessor usuario) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
      
        try {
            stmt = con.prepareStatement("DELETE FROM tbusuario where loginUser = ?");
            stmt.setString(1, usuario.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }


    public AlunoProfessor search(AlunoProfessor usuario) throws SQLException {
        Connection con           = ConnectionFactory.getConnection();
        PreparedStatement stmt      = null;
        ResultSet rs                  = null;
        AlunoProfessor usarioBuscado  = null;
        
        try {
            stmt = con.prepareStatement("select * from tbusuario where loginUser = ?");
            stmt.setString(1, usuario.getLogin());
            rs = stmt.executeQuery();
            
           
            while(rs.next()) {
                usarioBuscado = new AlunoProfessor(rs.getString("loginUser"), rs.getString("senhaUser"), rs.getString("nomeUser"), rs.getBoolean("statusUser"));
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usarioBuscado;  
    }
    
    public AlunoProfessor login(AlunoProfessor usuario) throws SQLException {
        Connection con                  = ConnectionFactory.getConnection();
        PreparedStatement stmt          = null;
        ResultSet rs                    = null;
        AlunoProfessor usarioBuscado    = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbusuario WHERE loginUser = ? and senhaUser = ?");
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                usarioBuscado = new AlunoProfessor(rs.getString("loginUser"), rs.getString("senhaUser"), rs.getString("nomeUser"), rs.getBoolean("statusUser"));
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usarioBuscado;  //o certo seria lancar um throws new illegalArgumente, mas deixa assim
    }    
    
}
