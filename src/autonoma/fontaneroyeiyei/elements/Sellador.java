/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import javax.swing.ImageIcon;

/**
 * 
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class Sellador extends Herramienta {
    @Override
    public void usarEn(Tubo tubo) {
        if (!(tubo instanceof TuboConFuga)) {
            System.out.println("El sellador solo funciona en tubos con fuga");
            return;
        }
        
        TuboConFuga tuboConFuga = (TuboConFuga) tubo;
        Fuga fuga = tuboConFuga.getFuga();
        
        if (fuga != null) {
            if (fuga.estaReparada()) {
                System.out.println("Esta fuga ya está reparada");
                return;
            }
            
            // Usar el método reparar(char) de tu clase Fuga
            boolean reparado = fuga.reparar('S');
            
            if (reparado) {
                System.out.println("¡Grieta sellada con sellador!");
                // Cambiar imagen del tubo a reparado
                cambiarImagenTuboReparado(tuboConFuga);
            } else {
                System.out.println("El sellador no puede reparar " + fuga.getTipo() + ". Necesitas llave inglesa.");
     }
        }
    }

    @Override
    public String getNombre() {
        return "Sellador";
    }
    
    private void cambiarImagenTuboReparado(TuboConFuga tubo) {
        ImageIcon iconoBueno = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboBueno.png"));
        tubo.setImage(iconoBueno);
        tubo.setEstado("bueno"); 
    }
}
