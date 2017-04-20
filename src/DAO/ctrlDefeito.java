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
import modelo.Defeito;

/**
 *
 * @author P4ul0
 */
public class ctrlDefeito {
    
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM defeito ORDER BY codigo DESC LIMIT 1";
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
    
    public int inserirDefeito (Defeito d){
       int rc=0;
       String sql = "INSERT INTO defeito VALUES("+d.getCodigo()+",'"+d.getTipo()+"')";
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
   
   public int alterarDefeito (Defeito d){
       int rc=0;
       String sql = "UPDATE defeito SET tipo='"+d.getTipo()+"' WHERE codigo="+d.getCodigo();
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
   
   public int excluirDefeito (Defeito d){
       int rc=0;
       String sql = "DELETE FROM defeito WHERE codigo="+d.getCodigo();
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
   
   public int consultar(int cod, Defeito d){
       int rc=111;
       String sqli = "SELECT * FROM defeito WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqli);
           if(rs.next()){
               d.setCodigo(rs.getInt(1));
               d.setTipo(rs.getString(2));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
   
   public int verificar (int cod){
       int rc=111;
       String sql = "SELECT codigo_defeito FROM ordem_servico WHERE codigo_defeito="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               JOptionPane.showMessageDialog(null, "Este defeito ja está em uma ordem de serviço!!");
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
    
}
