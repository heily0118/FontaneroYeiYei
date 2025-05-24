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

    /**
     * Usa el sellador sobre un tubo. 
     * Si el tubo es una instancia de TuboConFuga y tiene una fuga 
     * de tipo "grieta" que no ha sido reparada, se aplica la reparación.
     * 
     * @param tubo Es el tubo sobre el que se desea aplicar la herramienta.
     */
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

    /**
     * Devuelve el nombre de la herramienta.
     * 
     * @return Retorna el nombre "Sellador".
     */
    @Override
    public String getNombre() {
        return "Sellador";
    }

}
