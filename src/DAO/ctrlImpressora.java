package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Conexao;
import modelo.Impressora;



public class ctrlImpressora {
    
    //Calculo da Chave
    public int calcChave(){
        int cod=9999;
        String sql = "SELECT codigo FROM impressora ORDER BY codigo DESC LIMIT 1";
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
    
   public int inserirImpressora(Impressora i){
       int rc=0;
       String sqli = "INSERT INTO impressora VALUES("+i.getNum_componente()+","+i.getCodigo()+","+i.getVelocidade()+")";
       String sqlc = "INSERT INTO componente VALUES("+i.getNum_componente()+","+i.getNum_serie()+",'"+i.getDescricao()+"','"+i.getFabricante()+"','"+i.getModelo()+"','"+i.getProcedencia()+"')";
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
   
   public int alterarImpressora(Impressora i){
       int rc=0;
       String sqlc = "UPDATE componente SET num_serie="+i.getNum_serie()+", descricao='"+i.getDescricao()+"', fabricante='"+i.getFabricante()+"', modelo='"+i.getModelo()+"', procedencia='"+i.getProcedencia()+"' WHERE num_comp="+i.getNum_componente();
       String sqli = "UPDATE impressora SET velocidade="+i.getVelocidade()+" WHERE codigo="+i.getCodigo();
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
   
   public int excluirImpressora(Impressora i){
       int rc=0;
       String sqli = "DELETE FROM impressora WHERE codigo="+i.getCodigo();
       String sqlc = "DELETE FROM componente WHERE num_comp="+i.getNum_componente();
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
   
   public int consultar(int cod, Impressora i){
       int rc=111;
       String sqli = "SELECT * FROM impressora WHERE codigo="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqli);
           if(rs.next()){
               i.setNum_componente(rs.getInt(1));
               i.setCodigo(rs.getInt(2));
               i.setVelocidade(rs.getInt(3));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       if (rc!=0) return rc;
       
       String sqlc = "SELECT * FROM componente WHERE num_comp="+i.getNum_componente();
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           ResultSet rsc = st.executeQuery(sqlc);
           if(rsc.next()){
               i.setNum_serie(rsc.getInt(2));
               i.setDescricao(rsc.getString(3));
               i.setFabricante(rsc.getString(4));
               i.setModelo(rsc.getString(5));
               i.setProcedencia(rsc.getString(6));
               cn.close();
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
   
   public int relatorio(Impressora i){
       int rc=111;
       String sqli = "SELECT * FROM impressora";
       Connection cn = Conexao.getConnection();
       System.out.println(sqli);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sqli);
           if(rs.next()){
               i.setNum_componente(rs.getInt(1));
               i.setCodigo(rs.getInt(2));
               i.setVelocidade(rs.getInt(3));
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       if (rc!=0) return rc;
       
       String sqlc = "SELECT * FROM componente WHERE num_comp="+i.getNum_componente();
       System.out.println(sqlc);
       try{
           Statement st = cn.createStatement();
           ResultSet rsc = st.executeQuery(sqlc);
           if(rsc.next()){
               i.setNum_serie(rsc.getInt(2));
               i.setDescricao(rsc.getString(3));
               i.setFabricante(rsc.getString(4));
               i.setModelo(rsc.getString(5));
               i.setProcedencia(rsc.getString(6));
               cn.close();
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
   
}