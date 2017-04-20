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
import modelo.Teclado;

/**
 *
 * @author P4ul0
 */
public class ctrlTeclado {
    
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM teclado ORDER BY codigo DESC LIMIT 1";
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
    
    public int inserirTeclado(Teclado t){
       int rc=0;
       String sqli = "INSERT INTO teclado VALUES("+t.getNum_componente()+","+t.getCodigo()+")";
       String sqlc = "INSERT INTO componente VALUES("+t.getNum_componente()+","+t.getNum_serie()+",'"+t.getDescricao()+"','"+t.getFabricante()+"','"+t.getModelo()+"','"+t.getProcedencia()+"')";
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
    
    public int alterarTeclado(Teclado t){
      int rc=0;
       String sqlc = "UPDATE componente SET num_serie="+t.getNum_serie()+", descricao='"+t.getDescricao()+"', fabricante='"+t.getFabricante()+"', modelo='"+t.getModelo()+"', procedencia='"+t.getProcedencia()+"' WHERE num_comp="+t.getNum_componente();
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
   
   public int excluirTeclado(Teclado t){
       int rc=0;
       String sqli = "DELETE FROM teclado WHERE codigo="+t.getCodigo();
       String sqlc = "DELETE FROM componente WHERE num_comp="+t.getNum_componente();
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
   
   public int consultar(int cod, Teclado t){
       int rc=111;
       String sqli = "SELECT * FROM teclado WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqli);
           if(rs.next()){
               t.setNum_componente(rs.getInt(1));
               t.setCodigo(rs.getInt(2));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       if (rc!=0) return rc;
       
       String sqlc = "SELECT * FROM componente WHERE num_comp="+t.getNum_componente();
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           ResultSet rsc = st.executeQuery(sqlc);
           if(rsc.next()){
               t.setNum_serie(rsc.getInt(2));
               t.setDescricao(rsc.getString(3));
               t.setFabricante(rsc.getString(4));
               t.setModelo(rsc.getString(5));
               t.setProcedencia(rsc.getString(6));
               cn.close();
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
    
}
