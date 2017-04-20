/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;




/**
 *
 * @author P4ul0
 */
public class Instalacao {
    
    private int codigo_software;
    private int codigo_maquina;
    private String data_instalacao; 

    
    public int getCodigo_software() {
        return codigo_software;
    }

    public void setCodigo_software(int codigo_software) {
        this.codigo_software = codigo_software;
    }

    public int getCodigo_maquina() {
        return codigo_maquina;
    }

    public void setCodigo_maquina(int codigo_maquina) {
        this.codigo_maquina = codigo_maquina;
    }

    public String getData_instalacao() {
        return data_instalacao;
    }

    public void setData_instalacao(String data_instalacao) {
        this.data_instalacao = data_instalacao;
    }
    
    
    
    
}
