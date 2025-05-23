/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

/**
 *
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class Fuga {
    private int x, y; 
    private String tipo; // "tuerca" o "grieta"
    private boolean reparada;

    public Fuga(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.reparada = false;
    }

    public boolean estaCerca(int jugadorX, int jugadorY) {
        double distancia = Math.sqrt(Math.pow(jugadorX - x, 2) + Math.pow(jugadorY - y, 2));
        return distancia < 40; 
    }

    public boolean reparar(char herramienta) {
        if (reparada) return false;

        if ((tipo.equals("tuerca") && herramienta == 'L') ||
            (tipo.equals("grieta") && herramienta == 'S')) {
            reparada = true;
            return true;
        }

        return false; 
    }

    public boolean estaReparada() {
        return reparada;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isReparada() {
        return reparada;
    }

    public void setReparada(boolean reparada) {
        this.reparada = reparada;
    }


}
