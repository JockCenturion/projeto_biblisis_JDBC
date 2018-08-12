/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;

import br.com.biblisis.model.bean.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jock
 */
public class DAOReserva {
    
    
    public void create(Reserva reserva) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbreserva VALUES (?, ?, ?, ?, ?)");
        } catch (SQLException e) {
            System.err.println("ERRO ::: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public List<Reserva> read() throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("");
        } catch (SQLException e) {
            System.err.println("ERRO ::: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return null;
        
    }
    
    public void update(Reserva reserva) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("");
        } catch (SQLException e) {
            System.err.println("ERRO ::: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void delete(Reserva reserva) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("");
        } catch (SQLException e) {
            System.err.println("ERRO ::: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public Reserva search(Reserva reserva) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
        
        try {
            stmt = con.prepareStatement("");
        } catch (SQLException e) {
            System.err.println("ERRO ::: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return null;
    }
    
}
