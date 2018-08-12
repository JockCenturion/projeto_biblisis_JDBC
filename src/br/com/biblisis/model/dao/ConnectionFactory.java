/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;

/**
 *
 * @author Jock
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Jock
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL    = "jdbc:mysql://localhost:3306/dbbiblisis";
    private static final String USER   = "root";
    private static final String PASS   = "";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
           throw new RuntimeException("Erro na Conexão", e);
        }
    }
    
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
              throw new RuntimeException("Não Foi Possível Fechar o Connection", e);
        }    
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
         
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
              throw new RuntimeException("Não Foi Possível Fechar o Statment", e);
        }    
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
         
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
              throw new RuntimeException("Não Foi Possível Fechar ResultSet", e);
        }    
    }
    
}
