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
import modelo.Instalacao;

/**
 *
 * @author P4ul0
 */
public class ctrlInstalacao {
    
    public int instalarSoftware (Instalacao i){
       int rc=0;
       String sql = "INSERT INTO instalacao VALUES("+i.getCodigo_software()+","+i.getCodigo_maquina()+",'"+i.getData_instalacao()+"')";       
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
    
    public int removerSoftware (Instalacao i){
       int rc=0;
       String sql = "DELETE FROM instalacao WHERE codigo_software="+i.getCodigo_software()+" and codigo_maquina="+i.getCodigo_maquina();
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
    
    public int encontrarCodigo (String nome){
        String query = "SELECT codigo from software WHERE nome='"+nome+"'";
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
    
    public ResultSet buscarCodigos (String tabela){
        int rc=111;
        ResultSet rs = null;
        String sql = "SELECT codigo FROM "+tabela;
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
    
    public String encontrarNome (int cod){
        String query = "SELECT nome from software WHERE codigo="+cod;
        String nome = "";
        Connection cn = Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                nome = rs.getString(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return nome;
    }
   
   
}
