/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import javax.swing.ImageIcon;

/**
 *
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class LlaveIglesa extends Herramienta {
    @Override
    public void usarEn(Tubo tubo) {
        if (!(tubo instanceof TuboConFuga)) {
            System.out.println("La llave inglesa solo funciona en tubos con fuga");
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
            boolean reparado = fuga.reparar('L');
            
            if (reparado) {
                System.out.println("¡Tuerca ajustada con llave inglesa!");
                // Cambiar imagen del tubo a reparado
                cambiarImagenTuboReparado(tuboConFuga);
            } else {
                System.out.println("La llave inglesa no puede reparar " + fuga.getTipo() + ". Necesitas sellador.");
            }
        }
    }

    @Override
    public String getNombre() {
        return "Llave Inglesa";
    }
    private void cambiarImagenTuboReparado(TuboConFuga tubo) {
        ImageIcon iconoBueno = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboBueno.png"));
        tubo.setImage(iconoBueno);
        tubo.setEstado("bueno"); 
    }
}

