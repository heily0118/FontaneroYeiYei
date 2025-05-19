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
    
    
    ////////////////////////////////
    /// Atributos
    
    private int posX;
    private int posy;
    private String estado;
  
    
    public Tubo(int posX, int posy, String estado) {
        this.posX = posX;
        this.posy = posy;
        this.estado = estado;
    }
    
    
    //////////////////////////////////
    /// Metodos de acceso (get)
    ///
    public int getPosX() {
        return posX;
    }

   
    public int getPosy() {
        return posy;
    }

    public String getEstado() {
        return estado;
    }

    

    //////////////////////////////////
    /// Metodos de acceso (set)
    ///
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
  
    
}
