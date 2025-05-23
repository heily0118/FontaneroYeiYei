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
public class LlaveIglesa extends Herramienta {

   
    @Override
    public void usarEn(Tubo tubo) {
    
        if (tubo instanceof TuboConFuga) {
            TuboConFuga tuboConFuga = (TuboConFuga) tubo;
            if (tuboConFuga.getFuga() != null && 
                !tuboConFuga.getFuga().estaReparada() &&
                "tuerca".equalsIgnoreCase(tuboConFuga.getFuga().getTipo())) {
                tuboConFuga.repararConLlave();
            }
        }
    }

    @Override
    public String getNombre() {
        return "Llave Inglesa";
    }

}