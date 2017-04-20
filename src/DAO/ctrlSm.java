package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Conexao;
import modelo.Sm;

/**
 *
 * @author P4ul0
 */
public class ctrlSm {
    
   public int alocarMaquina (Sm s){
       int rc=0;
       String sql = "INSERT INTO sm VALUES("+s.getCodigo_sala()+","+s.getCodigo_maquina()+")";       
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
   
   public int realocarMaquina (Sm s){
       int rc=0;       
       String sql = "UPDATE sm SET codigo_sala="+s.getCodigo_sala()+" WHERE codigo_maquina="+s.getCodigo_maquina();
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
   
   public int removerMaquina (Sm s){
       int rc=0;
       String sql = "DELETE FROM sm WHERE codigo_maquina="+s.getCodigo_maquina();
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
   
   public int verificar (int cod, Sm s){
       int rc=111;
       String sql = "SELECT * FROM sm WHERE codigo_maquina="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               s.setCodigo_sala(rs.getInt(1));
               s.setCodigo_maquina(rs.getInt(2));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
   
   public ResultSet encontrarCodigos (String tabela){
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
        String query="SELECT nome FROM sala WHERE codigo = "+cod;
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
    
    public int encontrarCodigo_sala (String nome){
        String query="SELECT codigo FROM sala WHERE nome = '"+nome+"'";
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

