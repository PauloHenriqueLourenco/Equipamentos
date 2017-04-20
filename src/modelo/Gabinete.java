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
public class Gabinete extends Componente {
    
    private int cod;
    private String Pl_mae;
    private String HD;
    private String Pl_rede;
    private String memoria;
    private String disquete;
    private String Pl_de_video;
    private String cd_rom;
    private String processador;

    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public String getPl_mae() {
        return Pl_mae;
    }

    public String getHD() {
        return HD;
    }

    public String getPl_rede() {
        return Pl_rede;
    }

    public String getMemoria() {
        return memoria;
    }

    public String getDisquete() {
        return disquete;
    }

    public String getPl_de_video() {
        return Pl_de_video;
    }

    public String getCd_rom() {
        return cd_rom;
    }

    public String getProcessador() {
        return processador;
    }

    public void setPl_mae(String Pl_mae) {
        this.Pl_mae = Pl_mae;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public void setPl_rede(String Pl_rede) {
        this.Pl_rede = Pl_rede;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public void setDisquete(String disquete) {
        this.disquete = disquete;
    }

    public void setPl_de_video(String Pl_de_video) {
        this.Pl_de_video = Pl_de_video;
    }

    public void setCd_rom(String cd_room) {
        this.cd_rom = cd_room;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }
    
}
