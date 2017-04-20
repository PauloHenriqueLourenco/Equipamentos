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
import modelo.OrdemServico;

/**
 *
 * @author P4ul0
 */
public class ctrlOrdemServico {
    
    public int abrirOrdemServico (OrdemServico o){
       int rc=0;
       String sql = "INSERT INTO ordem_servico VALUES("+o.getNum_comp()+","+o.getCodigo_defeito()+",'"+o.getData_abertura()+"','','"+o.getEstado()+"','')";
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
    
    public int alterarOrdemServico (OrdemServico o, int num, int cod){
       int rc=0;
       String sql = "UPDATE ordem_servico SET num_comp="+o.getNum_comp()+", codigo_defeito="+o.getCodigo_defeito()+", data_abertura='"+o.getData_abertura()+"', estado='"+o.getEstado()+"' WHERE num_comp="+num+" and codigo_defeito="+cod;
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
    
    public int fecharOrdemServico (OrdemServico o, int num, int cod){
       int rc=0;
       String sql = "UPDATE ordem_servico SET data_fechamento='"+o.getData_fechamento()+"', solucao='"+o.getSolucao()+"' WHERE num_comp="+num+" and codigo_defeito="+cod;
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
   
    public int excluirOrdemServico (OrdemServico o){
       int rc=0;
       String sql = "DELETE FROM ordem_servico WHERE num_comp="+o.getNum_comp()+" and codigo_defeito="+o.getCodigo_defeito();
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
   
    public ResultSet encontrarDefeitos (int num){
        int rc=111;
        ResultSet rs = null;
        String sql = "SELECT tipo FROM ordem_servico WHERE num_comp="+num;
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
   
   public int consultar (int num, int cod, OrdemServico o){
       int rc=111;
       String sqlm = "SELECT * FROM ordem_servico WHERE num_comp="+num+" and codigo_defeito="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqlm);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqlm);
           if(rs.next()){
               o.setNum_comp(rs.getInt(1));
               o.setCodigo_defeito(rs.getInt(2));
               o.setData_abertura(rs.getString(3));
               o.setData_fechamento(rs.getString(4));
               o.setEstado(rs.getString(5));
               o.setSolucao(rs.getString(6));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
    
    public ResultSet encontrarCodigos (String tabela){
        int rc=111;
        String sql = "";
        ResultSet rs = null;
        if ("componente".equals(tabela)) sql = "SELECT num_comp FROM componente";
        else sql = "SELECT codigo FROM defeito";
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
    
     public int encontrarCodigo (String nome, String tabela){
        String query = "";
        if ("componente".equals(tabela)) query = "SELECT num_comp FROM componente WHERE fabricante='"+nome+"'";
        else query = "SELECT codigo FROM defeito WHERE tipo='"+nome+"'";
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
     
    public String encontrarNome (int cod, String tabela){
        String query = "";
        if ("componente".equals(tabela)) query = "SELECT fabricante FROM componente WHERE num_comp='"+cod+"'";
        else query = "SELECT tipo FROM defeito WHERE codigo='"+cod+"'";
        String nome = "";
        Connection cn=Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                nome=rs.getString(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return nome;
    }

    
}
