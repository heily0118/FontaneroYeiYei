/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

/**
 *
 * @author Mateo Quintero Morales <mateo.quinterom@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class Fuga {
    
    ////////////////////////////////
    /// Atributos
    
    private int posX;
    private int posy;
    private boolean activa;

    
    
    /////////////////////////////////
    /// Constructor
    ////
    public Fuga(int posX, int posy, boolean activa) {
        this.posX = posX;
        this.posy = posy;
        this.activa = activa;
    }

    
    //////////////////////////////////
    /// Metodos de acceso (get)
    ///
    public int getPosX() {
        return posX;
    }
    
    public boolean isActiva() {
        return activa;
    }
    
    public int getPosy() {
        return posy;
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

    

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    
    
    //////////////////////////////////
    /// Metodos
    
    public void activarFuga(){
    
      this.activa = true;
    }
    
    
    public void desactivarFuga(){
    
      this.activa = false;
    }
    
    public boolean esActiva() {
        return activa;
    }

    
    
    
    
}
