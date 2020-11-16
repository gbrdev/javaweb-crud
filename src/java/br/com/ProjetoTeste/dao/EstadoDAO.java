/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjetoTeste.dao;
import br.com.ProjetoTeste.model.Estado;
import br.com.ProjetoTeste.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author z1
 */
public class EstadoDAO implements GenericDAO{
    
    private Connection conexao;
    public EstadoDAO(){
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Problemas ao conectar no DB! ERRO: "
                        +ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Estado oEstado = (Estado) objeto;
        Boolean retorno = false;
        if (oEstado.getIdEstado() == 0) {
            retorno = this.inserir(oEstado);
        } else {
            retorno = this.alterar(oEstado);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) { 
        Estado oEstado = (Estado) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into estado (nomeestado, siglaestado) values (?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEstado.getNomeEstado());
            stmt.setString(2, oEstado.getSiglaEstado ());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar a Estado! Erro: "+
                    ex.getMessage());
            return false;
        }
        finally{
            try{
                ConnectionFactory.closeConnection (conexao, stmt);
            } catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Estado oEstado = (Estado) objeto;
        PreparedStatement stmt = null;
        String sql="update estado set nomeestado=?, siglaestado=? " + "where idEstado=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEstado.getNomeEstado());
            stmt.setString(2, oEstado.getSiglaEstado());
            stmt.setInt(3, oEstado.getIdEstado());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Estado! Erro: " +ex.getMessage());
            return false;
        }
        finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch(Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexão! " + "Erro: "+ex.getMessage());
                }
            }
        }

    @Override
    public Boolean excluir(int numero) {
        int idEstado = numero;
        PreparedStatement stmt= null;

        String sql = "delete from estado where idestado=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEstado);
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Estado! Erro: "
                        +ex.getMessage());
            return false;
        }
        finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Object carregar(int numero) {
        int idEstado = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Estado oEstado = null;
        String sql = "select * from estado where idestado = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEstado);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                oEstado = new Estado();
                oEstado.setIdEstado(rs.getInt("idestado"));
                oEstado.setNomeEstado(rs.getString("nomeestado"));
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar dados de Estado! Erro:"+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao,stmt,rs);
            } catch (Exception ex) {
                Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return oEstado;
    }
    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from estado order by idEstado";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()) {
                Estado oEstado = new Estado();
                oEstado.setIdEstado(rs.getInt("idEstado"));
                oEstado.setNomeEstado(rs.getString("nomeEstado"));
                oEstado.setSiglaEstado(rs.getString("siglaEstado"));
                resultado.add(oEstado);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Estado! Erro: "
                    +ex.getMessage());
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros de conexão!"
                        + "Erro: " +ex.getMessage());
            }
        }
        return resultado;
    }
        
}