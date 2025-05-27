package autonoma.fontaneroyeiyei.elements;

/**
 * Clase que representa un recorrido en el sistema.
 * 
 * @author Mateo Quintero <mateo.quinterom@autonoma.edu.co>
 * @version 1.0.0
 * @since 20250501
 * @see autonoma.fontaneroyeiyei.elements
 */
public class Recorrido {
    
    private String nombre;
    private int inicioX;
    private int inicioY;

    /**
     * Constructor de la clase Recorrido.
     * 
     * @param nombre El nombre del recorrido
     * @param inicioX La coordenada X de inicio del recorrido
     * @param inicioY La coordenada Y de inicio del recorrido
     */
    public Recorrido(String nombre, int inicioX, int inicioY) {
        this.nombre = nombre;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
    }

    /**
     * Obtiene el nombre del recorrido.
     * 
     * @return El nombre del recorrido
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la coordenada X de inicio del recorrido.
     * 
     * @return La coordenada X de inicio
     */
    public int getInicioX() {
        return inicioX;
    }

    /**
     * Obtiene la coordenada Y de inicio del recorrido.
     * 
     * @return La coordenada Y de inicio
     */
    public int getInicioY() {
        return inicioY;
    }
}