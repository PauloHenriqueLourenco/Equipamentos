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
import modelo.Sala;

/**
 *
 * @author P4ul0
 */
public class ctrlSala {
    
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM sala ORDER BY codigo DESC LIMIT 1";
        Connection cn = Conexao.getConnection();//ACESSO Á CONEXÃO
        try{
            Statement st = cn.createStatement();//LIGAÇÃO AO BANCO
            ResultSet rs = st.executeQuery(sql);//EXECUTAR O COMANDO SQL
            if(rs.next())
                cod = Integer.parseInt(rs.getString(1))+1;
            else
                cod=1;
          cn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cod;
    }
    
   public int inserirSala (Sala s){
       int rc=0;
       String sql = "INSERT INTO sala VALUES("+s.getCodigo()+",'"+s.getNome()+"')";       
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
   
   public int alterarSala (Sala s){
       int rc=0;       
       String sql = "UPDATE sala SET nome='"+s.getNome()+"' WHERE codigo="+s.getCodigo();
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
   
   public int excluirSala (Sala s){
       int rc=0;
       String sql = "DELETE FROM sala WHERE codigo="+s.getCodigo();
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
   
   public int consultar (int cod, Sala s){
       int rc=111;
       String sql = "SELECT * FROM sala WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               s.setCodigo(rs.getInt(1));
               s.setNome(rs.getString(2));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
   
   public int verificar (int cod){
       int rc=111;
       String sql = "SELECT codigo_sala FROM sm WHERE codigo_sala="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               JOptionPane.showMessageDialog(null, "Esta sala ja possui máquinas alocadas, impossivel deleta-la sem antes remover as máquinas!!");
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
    
}
