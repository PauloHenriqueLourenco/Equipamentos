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
import modelo.Montagem;

/**
 *
 * @author P4ul0
 */
public class ctrlMontagem {
    
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM maquina ORDER BY codigo DESC LIMIT 1";
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
    
    public int inserirMaquina (Montagem m){
       int rc=0;
       String sqlm = "INSERT INTO maquina VALUES("+m.getCodigo()+","+m.getCod_monitor()+","+m.getCod_teclado()+","+m.getCod_gabinete()+","+m.getCod_impressora()+","+m.getCod_scanner()+","+m.getCod_mouse()+")";
       Connection cn = Conexao.getConnection();
       System.out.println(sqlm);
       try{
           Statement st = cn.createStatement();
           st.executeUpdate(sqlm);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }       
      return rc;
   }
    
  public int alterarMaquina (Montagem m){
       int rc=0;
       String sqlm = "UPDATE maquina SET cod_monitor="+m.getCod_monitor()+", cod_teclado="+m.getCod_teclado()+", cod_gabinete="+m.getCod_gabinete()+", cod_impressora="+m.getCod_impressora()+", cod_scanner="+m.getCod_scanner()+", cod_mouse="+m.getCod_mouse()+" WHERE codigo="+m.getCodigo();
       Connection cn = Conexao.getConnection();
       System.out.println(sqlm);
       try{
           Statement st = cn.createStatement();
           st.executeUpdate(sqlm);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }
      return rc;
   }
   
   public int excluirMaquina (Montagem m){
       int rc=0;
       String sqlm = "DELETE FROM maquina WHERE codigo="+m.getCodigo();
       String sqli = "DELETE FROM instalacao WHERE codigo_maquina="+m.getCodigo();
       String sqls = "DELETE FROM sm WHERE codigo_maquina="+m.getCodigo();
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       System.out.println(sqls);
       System.out.println(sqlm);
     try{
           Statement st = cn.createStatement();
           st.executeUpdate(sqli);
           st.executeUpdate(sqls);
           st.executeUpdate(sqlm);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }
      return rc;
   }
   
   public int consultar (int cod, Montagem m){
       int rc=111;
       String sqlm = "SELECT * FROM maquina WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqlm);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqlm);
           if(rs.next()){
               m.setCodigo(rs.getInt(1));
               m.setCod_monitor(rs.getInt(2));
               m.setCod_teclado(rs.getInt(3));
               m.setCod_gabinete(rs.getInt(4));
               m.setCod_impressora(rs.getInt(5));
               m.setCod_scanner(rs.getInt(6));
               m.setCod_mouse(rs.getInt(7));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
    
    public ResultSet encontrarNum_comp (String peca){
        int rc=111;
        ResultSet rs = null;
        String sql = "SELECT num_comp FROM "+peca;
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
    
     public int encontrarCodigo (String fabricante, String peca, String descricao){
        String query = "SELECT codigo from "+peca+" inner join componente on componente.fabricante = '"+fabricante+"' and componente.num_comp = "+peca+".num_comp and componente.descricao='"+descricao+"'";
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
     
    public String encontrarFabricante (int num){
        String query="SELECT fabricante FROM componente WHERE num_comp = "+num;
        String fabricante = "";
        Connection cn=Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                fabricante=rs.getString(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return fabricante;
    }
    
    public String encontrarDescricao (int num){
        String query="SELECT descricao FROM componente WHERE num_comp = "+num;
        String descricao = "";
        Connection cn=Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                descricao=rs.getString(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return descricao;
    }
    
    public String encontrarFabricanteCod (int cod, String peca){
        String query="SELECT fabricante FROM componente INNER JOIN "+peca+" on "+peca+".num_comp = componente.num_comp WHERE "+peca+".codigo = "+cod;
        String fabricante = "";
        Connection cn=Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                fabricante=rs.getString(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return fabricante;
    }
    
    public String encontrarDescricaoCod (int cod, String peca){
        String query="SELECT descricao FROM componente INNER JOIN "+peca+" on "+peca+".num_comp = componente.num_comp WHERE "+peca+".codigo = "+cod;
        String descricao = "";
        Connection cn=Conexao.getConnection();
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                descricao=rs.getString(1);
            }
        }catch(SQLException E){
            System.out.print(E.getMessage());
        }
        return descricao;
    }
    
    public int verificar (int cod){
       int rc=111;
       String sql = "SELECT codigo_maquina FROM instalacao WHERE codigo_maquina="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       if (rc==0) return rc;
       
       sql = "SELECT codigo_maquina FROM sm WHERE codigo_maquina="+cod;
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rsc = st.executeQuery(sql);
           if(rsc.next()){
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
   
}
