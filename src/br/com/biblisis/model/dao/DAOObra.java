/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.dao;


import br.com.biblisis.model.bean.Data;
import br.com.biblisis.model.bean.Editora;
import br.com.biblisis.model.bean.Obra;
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
public class DAOObra {

    public void create(Obra obra) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbobra VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, null);
            stmt.setString(2, obra.getTitulo());
            stmt.setString(3, obra.getCategoria());
            stmt.setString(4, obra.getAutor());
            stmt.setString(5, obra.getDataPublicacao().toString());
            stmt.setInt(6, obra.getEditora().getCodEdit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
    
    public List<Obra> read() throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        ResultSet rs            = null;
        List<Obra> tuplas       = new ArrayList<>();
        Editora editora         = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbobra");
            rs   = stmt.executeQuery();
            
            while (rs.next()) {
                Obra obra = new Obra(rs.getInt("codObra"), rs.getString("tituloObra"), rs.getString("categoriaObra"), rs.getString("autorObra"), new Data(rs.getString("dataPublicacao")), 
                                     (new DAOEditora()).search(new Editora(rs.getInt("codEdit")))  );
                tuplas.add(obra);
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return tuplas;
    }
   
    
    public void update(Obra obra) throws SQLException {
       Connection con           =  ConnectionFactory.getConnection();
       PreparedStatement stmt   = null;
       
       
        try {
            stmt = con.prepareStatement("UPDATE tbobra SET codObra = ?, tituloObra = ?, categoriaObra = ?, autorObra = ?, dataPublicacao = ?, codEdit = ? WHERE codObra = ?");
            stmt.setInt(1, obra.getCodigoObra());
            stmt.setString(2, obra.getTitulo());
            stmt.setString(3, obra.getCategoria());
            stmt.setString(4, obra.getAutor());
            stmt.setString(5, obra.getDataPublicacao().toString());
            stmt.setInt(6, obra.getEditora().getCodEdit());
            stmt.setInt(7, obra.getCodigoObra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Obra obra) throws SQLException {
        Connection con          = ConnectionFactory.getConnection();
        PreparedStatement stmt  = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM tbobra WHERE codObra = ?");
            stmt.setInt(1, obra.getCodigoObra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public Obra search(Obra obra) throws SQLException {
        Connection con              = ConnectionFactory.getConnection();
        PreparedStatement stmt      = null;
        ResultSet rs                = null;
        Obra tupla                  = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbobra WHERE codObra = ?");
            stmt.setInt(1, obra.getCodigoObra());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                tupla = new Obra(rs.getInt("codObra"), rs.getString("tituloObra"), rs.getString("categoriaObra"), rs.getString("autorObra"), new Data(rs.getString("dataPublicacao")), 
                                     (new DAOEditora()).search(new Editora(rs.getInt("codEdit")))  );
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO AO SALVAR NO BD ::: PK inexistente, FK inexistente, ...Check os Parametros!" + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
      
        return tupla;
    }

}
