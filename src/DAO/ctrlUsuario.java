/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Conexao;
import modelo.Usuario;

/**
 *
 * @author semuso
 */
public class ctrlUsuario {
    
    public int criarUsuario(Usuario u){
       int rc=0;
       String sql = "INSERT INTO usuario VALUES('"+u.getNome()+"','"+u.getLogin()+"',MD5('"+u.getSenha()+"'),"+u.getTipo()+")";       
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           st.executeUpdate(sql);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }       
      return rc;
   }
   
   public int alterarUsuario(Usuario u){
       int rc=0;
       String sql = "UPDATE usuario SET nome='"+u.getNome()+"', login='"+u.getLogin()+"', password='"+u.getSenha()+"',codigo_tipo_usuario="+u.getTipo();
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           st.executeUpdate(sql);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }
      return rc;
   }
   
   public int excluirUsuario(String login){
       int rc=0;
       String sql = "DELETE FROM usuario WHERE login='"+login+"'";
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
     try{
           Statement st = cn.createStatement();
           st.executeUpdate(sql);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }
      return rc;
   }
   
   public int conectar(String login, String senha,Usuario u){
       int rc=111;
       String sql = "SELECT fun_valida_usuario('"+login+"','"+senha+"')";
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);           
           if(rs.next()){
               rc=(rs.getInt(1));
           }          
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }      
       return rc;
   }
   
   public int consultar(String login,String nome, Usuario u){
       int rc=111;
       String sql="";
       if (!login.equals(""))
           sql = "SELECT * FROM usuario WHERE login='"+login+"'";
       else sql = "SELECT * FROM usuario WHERE nome LIKE '%"+nome+"%'";
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               u.setNome(rs.getString(1));
               u.setLogin(rs.getString(2));
               u.setTipo(rs.getInt(4));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }      
       return rc;
   }
   
   public int relatorio(Usuario u){
       int rc=111;
       String sql = "SELECT * FROM usuario";
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               u.setNome(rs.getString(1));
               u.setLogin(rs.getString(2));
               u.setTipo(rs.getInt(4));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }       
       return rc;
   }
   
   public ResultSet encontrarTipos (){
        ResultSet rs = null;
        String sql = "SELECT descricao FROM tipo_usuario";
        Connection cn = Conexao.getConnection();
        System.out.println(sql);
        try{ 
            Statement st = cn.prepareStatement(sql); 
            rs = st.executeQuery(sql); 
        } 
        catch(Exception erro){ 
            JOptionPane.showMessageDialog(null,"Erro ao consultar" +erro.toString()); 
        } 
        return rs; 
    }
    
   public String encontrarTipo (int cod){
        String query="SELECT DISTINCT descricao FROM usuario INNER JOIN tipo_usuario on usuario.codigo_tipo_usuario = tipo_usuario.codigo WHERE tipo_usuario.codigo="+cod;
        String tipo = "";
        Connection cn=Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                tipo=rs.getString(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return tipo;
    }
   
   public int encontrarCodigo (String tipo){
        String query = "SELECT codigo from tipo_usuario WHERE descricao LIKE '"+tipo+"'";
        int cod = 0;
        Connection cn=Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                cod = rs.getInt(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return cod;
    }
}
