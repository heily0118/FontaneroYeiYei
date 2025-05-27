package autonoma.fontaneroyeiyei.elements;

/**
 * Clase que representa una fuga en un tubo.
 * Puede ser de tipo "tuerca" o "grieta" y permite repararla.
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class Fuga {
    private int x, y;                  // Posición de la fuga
    private String tipo;               // Tipo de fuga ("tuerca" o "grieta")
    private boolean reparada;          // Estado de reparación

    /**
     * Constructor de la clase Fuga.
     * 
     * @param x Posición X de la fuga
     * @param y Posición Y de la fuga
     * @param tipo Tipo de fuga ("tuerca" o "grieta")
     */
    public Fuga(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.reparada = false;
    }

    /**
     * Intenta reparar la fuga usando una herramienta.
     * 
     * @param herramienta Herramienta utilizada ('L' para llave, 'S' para sellador)
     * @return true si la fuga fue reparada, false en caso contrario
     */
    public boolean reparar(char herramienta) {
        if (reparada) return false;

        if ((tipo.equals("tuerca") && herramienta == 'L') ||
            (tipo.equals("grieta") && herramienta == 'S')) {
            reparada = true;
            return true;
        }

        return false; 
    }

    /**
     * Verifica si la fuga ha sido reparada.
     * 
     * @return true si está reparada, false en caso contrario
     */
    public boolean estaReparada() {
        return reparada;
    }

    /**
     * Obtiene la posición X de la fuga.
     * 
     * @return Posición X
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la posición X de la fuga.
     * 
     * @param x Nueva posición X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtiene la posición Y de la fuga.
     * 
     * @return Posición Y
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la posición Y de la fuga.
     * 
     * @param y Nueva posición Y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtiene el tipo de fuga.
     * 
     * @return Tipo de fuga
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de fuga.
     * 
     * @param tipo Nuevo tipo de fuga
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Verifica el estado de reparación de la fuga.
     * 
     * @return true si está reparada, false en caso contrario
     */
    public boolean isReparada() {
        return reparada;
    }

    /**
     * Establece el estado de reparación de la fuga.
     * 
     * @param reparada Nuevo estado de reparación
     */
    public void setReparada(boolean reparada) {
        this.reparada = reparada;
    }
}