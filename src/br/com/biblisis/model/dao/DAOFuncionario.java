/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;

import br.com.biblisis.model.bean.Funcionario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jock
 */
public class DAOFuncionario {
 
    /*Crud Básico*/
    public void create(Funcionario funcionario) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbfuncionario values (?, ?, ?, ?)");
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getNome());
            stmt.setInt(4, funcionario.getCargo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Funcionario> read() throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
        List funcionarios       = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfuncionario");
            rs   = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionario tupla = new Funcionario(rs.getString("loginFun"), rs.getString("senhaFun"), rs.getString("nomeFun"), rs.getInt("codCargo"));
                funcionarios.add(tupla);
            }
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return funcionarios;
    }
    
    public void update(Funcionario funcionario) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbfuncionario SET loginFun = ?, senhaFun = ?, nomeFun = ?, codCargo = ? WHERE loginFun = ?");
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getNome());
            stmt.setInt(4, funcionario.getCargo());
            stmt.setString(5, funcionario.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Funcionario funcionario) throws SQLException {
        Connection con           = ConnectionFactory.getConnection();
        PreparedStatement stmt   = null;
       
        try {
            stmt = con.prepareStatement("DELETE FROM tbfuncionario where loginFun = ?");
            stmt.setString(1, funcionario.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public Funcionario search(Funcionario funcionario) throws SQLException {
        Connection con              = ConnectionFactory.getConnection();
        PreparedStatement stmt      = null;
        ResultSet rs                = null;
        Funcionario funBuscado      = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfuncionario WHERE loginFun = ?");
            stmt.setString(1, funcionario.getLogin());
            rs = stmt.executeQuery();

            while (rs.next()) {
               funBuscado = new Funcionario(rs.getString("loginFun"), rs.getString("senhaFun"), rs.getString("nomeFun"), rs.getInt("codCargo"));
            }
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return funBuscado;
        
    }
    
    public Funcionario login(Funcionario funcionario) throws SQLException, NullPointerException {
        Connection con              = ConnectionFactory.getConnection();
        PreparedStatement stmt      = null;
        ResultSet rs                = null;
        Funcionario funBuscado      = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfuncionario WHERE loginFun = ? and senhaFun = ? and codCargo = ?");
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());
            stmt.setInt(3, funcionario.getCargo());
            rs = stmt.executeQuery();

            while (rs.next()) {
               funBuscado = new Funcionario(rs.getString("loginFun"), rs.getString("senhaFun"), rs.getString("nomeFun"), rs.getInt("codCargo"));
            }
            
            
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return funBuscado;
    }
    
    
}
