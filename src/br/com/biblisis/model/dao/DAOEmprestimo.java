/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;

import br.com.biblisis.model.bean.AlunoProfessor;
import br.com.biblisis.model.bean.Data;
import br.com.biblisis.model.bean.Emprestimo;
import br.com.biblisis.model.bean.Exemplar;
import br.com.biblisis.model.bean.Funcionario;
import br.com.biblisis.model.bean.Obra;
import br.com.biblisis.model.dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jock
 */
public class DAOEmprestimo {
    
    public void create(Emprestimo emprestimo) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbemprestimo VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, emprestimo.getFuncionario().getLogin());
            stmt.setString(2, emprestimo.getUsuario().getLogin());
            stmt.setInt(3, emprestimo.getExemplar().getCodExemplar());
            stmt.setInt(4, emprestimo.getObra().getCodigoObra());
            stmt.setString(5, null);
            stmt.setString(6, emprestimo.getDataDevolucao().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public List<Emprestimo> readAll() throws SQLException, ParseException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
        List<Emprestimo> tuplas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbemprestimo");
            rs   = stmt.executeQuery();
            
            while (rs.next()) {
                
                Obra obra           = ( new DAOObra() ).search( new Obra(rs.getInt("codObra")) );
                Funcionario fun     = ( new DAOFuncionario() ).search( new Funcionario(rs.getString("LoginFun")) );
                AlunoProfessor ap   = ( new DAOAlunoProfessor() ).search(  new AlunoProfessor(rs.getString("LoginUser")) );
                Exemplar ex         = ( new DAOExemplar() ).search(  new Exemplar(rs.getInt("codEx"), obra) ); 
                
                Emprestimo emprestimo = new Emprestimo(fun, ap, ex, obra, new Data("yyyy-MM-dd HH:mm:ss", rs.getString("dataEmprestimo")),new Data("yyyy-MM-dd HH:mm:ss", rs.getString("dataDevolucao")) );
                tuplas.add(emprestimo);
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO ::: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return tuplas;
        
    }
 
    
    public void update(Emprestimo emprestimo) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbemprestimo SET loginFun = ?, loginUser = ?, codEx = ?, codObra = ?, dataEmprestimo = ?, dataDevolucao = ? WHERE loginFun = ? and loginUser = ? and codEx = ? and codObra = ?");
            stmt.setString(1, emprestimo.getFuncionario().getLogin());
            stmt.setString(2, emprestimo.getUsuario().getLogin());
            stmt.setInt(3, emprestimo.getExemplar().getCodExemplar());
            stmt.setInt(4, emprestimo.getObra().getCodigoObra());
            stmt.setString(5, emprestimo.getDataEmprestimo().toString());
            stmt.setString(6, emprestimo.getDataDevolucao().toString());
            stmt.setString(7, emprestimo.getFuncionario().getLogin());
            stmt.setString(8, emprestimo.getUsuario().getLogin());
            stmt.setInt(9, emprestimo.getExemplar().getCodExemplar());
            stmt.setInt(10, emprestimo.getObra().getCodigoObra());
            stmt.executeUpdate();   
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void delete(Emprestimo emprestimo) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM tbemprestimo WHERE loginFun = ? and loginUser = ? and codEx = ? and codObra = ?");
            stmt.setString(1, emprestimo.getFuncionario().getLogin());
            stmt.setString(2, emprestimo.getUsuario().getLogin());
            stmt.setInt(3, emprestimo.getExemplar().getCodExemplar());
            stmt.setInt(4, emprestimo.getObra().getCodigoObra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    private List<Emprestimo> read(PreparedStatement stmt, ResultSet rs) throws SQLException, ParseException {
        Connection con          = ConnectionFactory.getConnection();
        List<Emprestimo> tuplas = new ArrayList<>();
        
        while (rs.next()) {
                
            Obra obra           = ( new DAOObra() ).search( new Obra(rs.getInt("codObra")) );
            Funcionario fun     = ( new DAOFuncionario() ).search( new Funcionario(rs.getString("LoginFun")) );
            AlunoProfessor ap   = ( new DAOAlunoProfessor() ).search(  new AlunoProfessor(rs.getString("LoginUser")) );
            Exemplar ex         = ( new DAOExemplar() ).search(  new Exemplar(rs.getInt("codEx"), obra) ); 
                
            Emprestimo emprestimo = new Emprestimo(fun, ap, ex, obra, new Data("yyyy-MM-dd HH:mm:ss", rs.getString("dataEmprestimo")),new Data("yyyy-MM-dd HH:mm:ss", rs.getString("dataDevolucao")) );
            tuplas.add(emprestimo);
        }   
           
        return tuplas;
    }
    
    public List<Emprestimo> allRead() throws SQLException, ParseException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
   
        try {
            stmt = con.prepareStatement("SELECT * FROM tbemprestimo");
            rs   = stmt.executeQuery();
            
            return read(stmt, rs);
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return null;
    }
    
    
    public List<Emprestimo> userRead(String loginUser) throws SQLException, ParseException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
   
        try {
            stmt = con.prepareStatement("SELECT * FROM tbemprestimo WHERE loginUser = ?");
            stmt.setString(1, loginUser);
            rs   = stmt.executeQuery();
            return read(stmt, rs);
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return null;
    }
    
    public List<Emprestimo> funcionarioRead(String loginFun) throws SQLException, ParseException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
   
        try {
            stmt = con.prepareStatement("SELECT * FROM tbemprestimo WHERE loginFun = ?");
            stmt.setString(1, loginFun);
            rs   = stmt.executeQuery();
            return read(stmt, rs);
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return null;        
    }
    
}
