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
public class OrdemServico {
    
    private int num_comp;
    private int codigo_defeito;
    private String data_abertura;
    private String data_fechamento;
    private String estado;
    private String solucao;

    
    public int getNum_comp() {
        return num_comp;
    }

    public void setNum_comp(int num_comp) {
        this.num_comp = num_comp;
    }

    public int getCodigo_defeito() {
        return codigo_defeito;
    }

    public void setCodigo_defeito(int codigo_defeito) {
        this.codigo_defeito = codigo_defeito;
    }

    public String getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(String data_abertura) {
        this.data_abertura = data_abertura;
    }

    public String getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(String data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }
    
    
}
