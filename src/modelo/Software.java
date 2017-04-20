/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author aluno
 */
public class Software {
	
	private int codigo;
	private String nome;
	private String licenca;
		
	public int getCodigo() {
		return codigo;
	}
        
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
        
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLicenca() {
		return licenca;
	}
        
	public void setLicenca(String licenca) {
		this.licenca = licenca;
	}
	
}
