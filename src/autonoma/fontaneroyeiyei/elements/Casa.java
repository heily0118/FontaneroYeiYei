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

    private FontaneroBueno fontanero;
    private List<Tubo> tubos;
    
    
    
    /////////////////////////////////
    /// Constructor
    ////
    
    
    public Casa() {
    }

    //////////////////////////////////
    /// Metodos de acceso (get)
    ///

    public FontaneroBueno getFontanero() {
        return fontanero;
    }

    public void setFontanero(FontaneroBueno fontanero) {
        this.fontanero = fontanero;
    }
   

    public List<Tubo> getTubos() {
        return tubos;
    }

    
    
    //////////////////////////////////
    /// Metodos de acceso (set)
    ///
  

    public void setTubos(List<Tubo> tubos) {
        this.tubos = tubos;
    }
    
    
    
    
    
    
    //////////////////////////////////
    /// Metodos
    
    public void generarFugasAleatorias(){
    
    }
    
    public void colocarHerramientas(){}
    
    public boolean verificarEstadoCasa(){
    
    return false;}
    
    
    
    
}
