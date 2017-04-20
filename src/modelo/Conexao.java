/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.*;
// @author P4ul0

public class Conexao {
  public static String status = "";

  public static Connection getConnection(){
    Connection con = null;
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
   //   String url = "jdbc:mysql://bdserv/equipamentos?user=aluno&password=aluno"; 
   //  String url = "jdbc:mysql://127.0.0.1/equipamentos?user=aluno&password=aluno";
      String url = "jdbc:mysql://127.0.0.1/equipamentos?user=root&password=";
      con = DriverManager.getConnection(url);
      status = "Conexão Aberta!";
      System.out.println(status);
    }catch(SQLException e){
      status = e.getMessage();
        System.out.println(status);
    }catch (ClassNotFoundException e){
      status =e.getMessage();
        System.out.println(status);
    }catch (Exception e){
      status = e.getMessage();
        System.out.println(status);
    }
    return con;
  }
}
