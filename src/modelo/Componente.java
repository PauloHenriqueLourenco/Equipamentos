/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public abstract class Componente {
    
    private int num_componente;
    private int num_serie;
    private String descricao;
    private String fabricante;
    private String modelo;
    private String procedencia;

    
    public int getNum_componente() {
        return num_componente;
    }
    public void setNum_componente(int num_componente) {
        this.num_componente = num_componente;
    }

    
    public int getNum_serie() {
        return num_serie;
    }
    public void setNum_serie(int num_serie) {
        this.num_serie = num_serie;
    }

    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
    public String getProcedencia() {
        return procedencia;
    }
    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }
    
    
    public int calcNumComp(){
        int cod=9999;
        String sql = "SELECT num_comp FROM componente ORDER BY num_comp DESC LIMIT 1";
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
    
    public int verificar (int cod, String peca){
       int rc=111;
       String sql = "SELECT cod_"+peca+" FROM maquina WHERE cod_"+peca+"="+cod;
       Connection cn = Conexao.getConnection();
       System.out.println(sql);
       try{
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               JOptionPane.showMessageDialog(null, "Este(a) "+peca+" ja está em uma máquina!!");
               rc=0;
           }
       }catch(SQLException e){
           System.out.printf(e.getMessage());
       }
       return rc;
   }
    
}
