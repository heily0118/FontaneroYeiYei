/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.util.List;

/**
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public abstract class Tubo {
    
    /**
     * Posición X del tubo en un plano.
     */
    private int posX;
    
    /**
     * Posición Y del tubo en un plano.
     */
    private int posy;
    
    /**
     * Estado actual del tubo, por ejemplo: "funcionando", "dañado", "averiado".
     */
    private String estado;
  
    /**
     * Constructor de la clase Tubo.
     * 
     * @param posX Es la posición horizontal del tubo.
     * @param posy Es la posición vertical del tubo.
     * @param estado Es el estado actual del tubo.
     */
    public Tubo(int posX, int posy, String estado) {
        this.posX = posX;
        this.posy = posy;
        this.estado = estado;
    }
    
    /**
     * Obtiene la posición X del tubo.
     * 
     * @return Retorna la posición X.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Obtiene la posición Y del tubo.
     * 
     * @return Retorna la posición Y.
     */
    public int getPosy() {
        return posy;
    }

    /**
     * Obtiene el estado actual del tubo.
     * 
     * @return Es el estado del tubo.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Modifica la posición X del tubo.
     * 
     * @param posX Nueva posición X.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Modifica la posición Y del tubo.
     * 
     * @param posy Es la nueva posición Y.
     */
    public void setPosy(int posy) {
        this.posy = posy;
    }

    /**
     * Modifica el estado actual del tubo.
     * 
     * @param estado Es el nuevo estado del tubo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * Indica si el tubo es reparable.
     * 
     * @return Retorna true si el tubo es reparable o false en caso contrario.
     */
    public boolean esReparable() {
        return "dañado".equalsIgnoreCase(this.estado) || "averiado".equalsIgnoreCase(this.estado);
    }
    
}
