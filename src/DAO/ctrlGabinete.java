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
import modelo.Gabinete;

/**
 *
 * @author P4ul0
 */
public class ctrlGabinete {
    
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM gabinete ORDER BY codigo DESC LIMIT 1";
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
    
    public int inserirGabinete(Gabinete g){
       int rc=0;
       String sqli = "INSERT INTO gabinete VALUES("+g.getNum_componente()+","+g.getCod()+",'"+g.getPl_mae()+"','"+g.getHD()+"','"+g.getPl_rede()+"','"+g.getMemoria()+"','"+g.getDisquete()+"','"+g.getPl_de_video()+"','"+g.getCd_rom()+"','"+g.getProcessador()+"')";
       String sqlc = "INSERT INTO componente VALUES("+g.getNum_componente()+","+g.getNum_serie()+",'"+g.getDescricao()+"','"+g.getFabricante()+"','"+g.getModelo()+"','"+g.getProcedencia()+"')";
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
    
  public int alterarGabinete(Gabinete g){
       int rc=0;
       String sqlc = "UPDATE componente SET num_serie="+g.getNum_serie()+", descricao='"+g.getDescricao()+"', fabricante='"+g.getFabricante()+"', modelo='"+g.getModelo()+"', procedencia='"+g.getProcedencia()+"' WHERE num_comp="+g.getNum_componente();
       String sqli = "UPDATE gabinete SET pl_mae='"+g.getPl_mae()+"', hd='"+g.getHD()+"', pl_rede='"+g.getPl_rede()+"', memoria='"+g.getMemoria()+"', disquete='"+g.getDisquete()+"', pl_video='"+g.getPl_de_video()+"', cd_rom='"+g.getCd_rom()+"', processador='"+g.getProcessador()+"' WHERE codigo="+g.getCod();
       Connection cn = Conexao.getConnection();
       System.out.println(sqlc);
       System.out.println(sqli);
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
   
   public int excluirGabinete(Gabinete g){
       int rc=0;
       String sqli = "DELETE FROM gabinete WHERE codigo="+g.getCod();
       String sqlc = "DELETE FROM componente WHERE num_comp="+g.getNum_componente();
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
   
   public int consultar(int cod, Gabinete g){
       int rc=111;
       String sqli = "SELECT * FROM gabinete WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqli);
           if(rs.next()){
               g.setNum_componente(rs.getInt(1));
               g.setCod(rs.getInt(2));
               g.setPl_mae(rs.getString(3));
               g.setHD(rs.getString(4));
               g.setPl_rede(rs.getString(5));
               g.setMemoria(rs.getString(6));
               g.setDisquete(rs.getString(7));
               g.setPl_de_video(rs.getString(8));
               g.setCd_rom(rs.getString(9));
               g.setProcessador(rs.getString(10));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       if (rc!=0) return rc;
       
       String sqlc = "SELECT * FROM componente WHERE num_comp="+g.getNum_componente();
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           ResultSet rsc = st.executeQuery(sqlc);
           if(rsc.next()){
               g.setNum_serie(rsc.getInt(2));
               g.setDescricao(rsc.getString(3));
               g.setFabricante(rsc.getString(4));
               g.setModelo(rsc.getString(5));
               g.setProcedencia(rsc.getString(6));
               cn.close();
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
    
}
