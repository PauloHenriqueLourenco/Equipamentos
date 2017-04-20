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
import modelo.Conexao;
import modelo.Scanner;

/**
 *
 * @author P4ul0
 */
public class ctrlScanner {
    
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM scanner ORDER BY codigo DESC LIMIT 1";
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
    
    public int inserirScanner (Scanner s){
       int rc=0;
       String sqli = "INSERT INTO scanner VALUES("+s.getNum_componente()+","+s.getCodigo()+")";
       String sqlc = "INSERT INTO componente VALUES("+s.getNum_componente()+","+s.getNum_serie()+",'"+s.getDescricao()+"','"+s.getFabricante()+"','"+s.getModelo()+"','"+s.getProcedencia()+"')";
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           st.executeUpdate(sqlc);
           st.executeUpdate(sqli);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }       
      return rc;
   }
    
   public int alterarScanner (Scanner s){
      int rc=0;
       String sqlc = "UPDATE componente SET num_serie="+s.getNum_serie()+", descricao='"+s.getDescricao()+"', fabricante='"+s.getFabricante()+"', modelo='"+s.getModelo()+"', procedencia='"+s.getProcedencia()+"' WHERE num_comp="+s.getNum_componente();
       Connection cn = Conexao.getConnection();
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           st.executeUpdate(sqlc);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }
      return rc;
   }
   
   public int excluirScanner (Scanner s){
       int rc=0;
       String sqli = "DELETE FROM scanner WHERE codigo="+s.getCodigo();
       String sqlc = "DELETE FROM componente WHERE num_comp="+s.getNum_componente();
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       System.out.println(sqlc);
     try{
           Statement st = cn.createStatement();
           st.executeUpdate(sqli);
           st.executeUpdate(sqlc);
           rc=0;
           cn.close();
       }catch(SQLException e){
           rc=111;
           e.printStackTrace();
       }
      return rc;
   }
   
   public int consultar (int cod, Scanner s){
      int rc=111;
       String sqli = "SELECT * FROM scanner WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqli);
           if(rs.next()){
               s.setNum_componente(rs.getInt(1));
               s.setCodigo(rs.getInt(2));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       if (rc!=0) return rc;
       
       String sqlc = "SELECT * FROM componente WHERE num_comp="+s.getNum_componente();
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           ResultSet rsc = st.executeQuery(sqlc);
           if(rsc.next()){
               s.setNum_serie(rsc.getInt(2));
               s.setDescricao(rsc.getString(3));
               s.setFabricante(rsc.getString(4));
               s.setModelo(rsc.getString(5));
               s.setProcedencia(rsc.getString(6));
               cn.close();
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
}
