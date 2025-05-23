/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

/**
 * 
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class Sellador extends Herramienta {

      @Override
    public void usarEn(Tubo tubo) {
       
        if (tubo instanceof TuboConFuga) {
            TuboConFuga tuboConFuga = (TuboConFuga) tubo;
            if (tuboConFuga.getFuga() != null && 
                !tuboConFuga.getFuga().estaReparada() &&
                "grieta".equalsIgnoreCase(tuboConFuga.getFuga().getTipo())) {
                tuboConFuga.repararConSellador();
           }
        }
    }

    @Override
    public String getNombre() {
        return "Sellador";
    }

}
