package autonoma.fontaneroyeiyei.elements;

/**
 * Clase que representa un nivel en el sistema.
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class Nivel {
    private int numero;

    /**
     * Constructor de la clase Nivel.
     * 
     * @param numero El número del nivel
     */
    public Nivel(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el número del nivel.
     * 
     * @return El número del nivel
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el número del nivel.
     * 
     * @param numero El nuevo número del nivel
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
}