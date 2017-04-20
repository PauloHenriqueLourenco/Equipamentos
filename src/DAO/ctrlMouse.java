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
import modelo.Mouse;

/**
 *
 * @author P4ul0
 */
public class ctrlMouse {
    
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM mouse ORDER BY codigo DESC LIMIT 1";
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
    
    public int inserirMouse (Mouse m){
       int rc=0;
       String sqli = "INSERT INTO mouse VALUES("+m.getNum_componente()+","+m.getCodigo()+")";
       String sqlc = "INSERT INTO componente VALUES("+m.getNum_componente()+","+m.getNum_serie()+",'"+m.getDescricao()+"','"+m.getFabricante()+"','"+m.getModelo()+"','"+m.getProcedencia()+"')";
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
    
   public int alterarMouse (Mouse m){
      int rc=0;
       String sqlc = "UPDATE componente SET num_serie="+m.getNum_serie()+", descricao='"+m.getDescricao()+"', fabricante='"+m.getFabricante()+"', modelo='"+m.getModelo()+"', procedencia='"+m.getProcedencia()+"' WHERE num_comp="+m.getNum_componente();
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
   
   public int excluirMouse (Mouse m){
       int rc=0;
       String sqli = "DELETE FROM mouse WHERE codigo="+m.getCodigo();
       String sqlc = "DELETE FROM componente WHERE num_comp="+m.getNum_componente();
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
   
   public int consultar (int cod, Mouse m){
      int rc=111;
       String sqli = "SELECT * FROM mouse WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqli);
           if(rs.next()){
               m.setNum_componente(rs.getInt(1));
               m.setCodigo(rs.getInt(2));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       if (rc!=0) return rc;
       
       String sqlc = "SELECT * FROM componente WHERE num_comp="+m.getNum_componente();
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           ResultSet rsc = st.executeQuery(sqlc);
           if(rsc.next()){
               m.setNum_serie(rsc.getInt(2));
               m.setDescricao(rsc.getString(3));
               m.setFabricante(rsc.getString(4));
               m.setModelo(rsc.getString(5));
               m.setProcedencia(rsc.getString(6));
               cn.close();
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
}
