package autonoma.fontaneroyeiyei.elements;

import autonoma.fontaneroyeiyei.exceptions.HerramientaInvalidaException;
import javax.swing.ImageIcon;

/**
 * Clase que representa una llave inglesa, una herramienta utilizada 
 * para reparar tubos con fuga.
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class LlaveIglesa extends Herramienta {

    /**
     * Usa la llave inglesa en un tubo.
     * 
     * @param tubo El tubo sobre el que se usará la llave inglesa
     */
    @Override
    public void usarEn(Tubo tubo) {
        if (!(tubo instanceof TuboConFuga)) {
            throw new HerramientaInvalidaException("La llave inglesa solo funciona en tubos con fuga");
    
    
        }
        
        TuboConFuga tuboConFuga = (TuboConFuga) tubo;
        Fuga fuga = tuboConFuga.getFuga();
        
        if (fuga != null) {
            if (fuga.estaReparada()) {
                System.out.println("Esta fuga ya está reparada");
                return;
            }

            // Usar el método reparar(char) de la clase Fuga
            boolean reparado = fuga.reparar('L');
            
            if (reparado) {
                System.out.println("¡Tuerca ajustada con llave inglesa!");
                // Cambiar imagen del tubo a reparado
                cambiarImagenTuboReparado(tuboConFuga);
            } else {
               throw new HerramientaInvalidaException("La llave inglesa no puede reparar " + fuga.getTipo() + ". Necesitas sellador.");

            }
        }
    }

    /**
     * Obtiene el nombre de la herramienta.
     * 
     * @return Nombre de la herramienta
     */
    @Override
    public String getNombre() {
        return "Llave Inglesa";
    }

    /**
     * Cambia la imagen del tubo a una imagen de tubo reparado.
     * 
     * @param tubo El tubo que ha sido reparado
     */
    private void cambiarImagenTuboReparado(TuboConFuga tubo) {
        ImageIcon iconoBueno = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboBueno.png"));
        tubo.setImage(iconoBueno);
        tubo.setEstado("bueno"); 
    }
}