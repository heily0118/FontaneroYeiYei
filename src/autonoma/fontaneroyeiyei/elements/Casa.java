/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.util.List;

/**
 *
 * @author Mateo Quintero Morales <mateo.quinterom@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */

public class Casa {

    private List<Tubo> tubos;
    private FontaneroMaldadoso enemigo;
    private Thread hiloEnemigo;
    
    
    
    /////////////////////////////////
    /// Constructor
    ////
    
    
    public Casa() {
        
        
    }

    //////////////////////////////////
    /// Metodos de acceso (get)
    ///

   

    public List<Tubo> getTubos() {
        return tubos;
    }

    
    
    //////////////////////////////////
    /// Metodos de acceso (set)
    ///
  

    public void setTubos(List<Tubo> tubos) {
        this.tubos = tubos;
    }
    
     public FontaneroMaldadoso getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(FontaneroMaldadoso enemigo) {
        this.enemigo = enemigo;
    }
    
    
    
    
    //////////////////////////////////
    /// Metodos
    
    public void generarFugasAleatorias(){
    
    }
    
   
    
    public boolean verificarEstadoCasa(){
    
    return false;}
    
     public void iniciarEnemigo(int x, int y, int width, int height) {
        if (enemigo == null) {
            enemigo = new FontaneroMaldadoso(x, y, width, height, this);
            hiloEnemigo = new Thread(enemigo);
            hiloEnemigo.start();
        }
    }

    public void detenerEnemigo() {
        if (enemigo != null) {
            enemigo.detener(); 
        }
    }
    
    
}
