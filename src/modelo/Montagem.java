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
public class Montagem {
    
    private int codigo;
    private int cod_monitor;
    private int cod_teclado;
    private int cod_gabinete;
    private int cod_impressora;
    private int cod_scanner;
    private int cod_mouse;

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCod_monitor() {
        return cod_monitor;
    }

    public void setCod_monitor(int cod_monitor) {
        this.cod_monitor = cod_monitor;
    }

    public int getCod_teclado() {
        return cod_teclado;
    }

    public void setCod_teclado(int cod_teclado) {
        this.cod_teclado = cod_teclado;
    }

    public int getCod_gabinete() {
        return cod_gabinete;
    }

    public void setCod_gabinete(int cod_gabinete) {
        this.cod_gabinete = cod_gabinete;
    }

    public int getCod_impressora() {
        return cod_impressora;
    }

    public void setCod_impressora(int cod_impressora) {
        this.cod_impressora = cod_impressora;
    }

    public int getCod_scanner() {
        return cod_scanner;
    }

    public void setCod_scanner(int cod_scanner) {
        this.cod_scanner = cod_scanner;
    }

    public int getCod_mouse() {
        return cod_mouse;
    }

    public void setCod_mouse(int cod_mouse) {
        this.cod_mouse = cod_mouse;
    }
    
}
