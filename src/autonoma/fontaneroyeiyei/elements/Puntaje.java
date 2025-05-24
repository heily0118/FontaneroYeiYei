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
 *
 */
public class Puntaje {
    
    /*
     * Puntaje actual acumulado por el jugador. 
     */
    private int puntajeActual;
    
    /**
     * Puntaje máximo alcanzado históricamente por el jugador, guardado en archivo.
     */
    private int puntajeMaximo;
    
    /**
     * Nombre del jugador.
     */
    private String nombreJugador;

    
    /**
     * Nombre del jugador de Puntaje Maximo.
     */
    private String MaximoNombre;

    /**
     * Constructor de la clase Puntaje.
     */
    public Puntaje() {
        this.puntajeActual = 0;
    }

    /**
     * Devuelve el puntaje actual.
     * 
     * @return Retorna el puntaje actual
     */
    public int getPuntajeActual() {
        return puntajeActual;
    }

    /**
     * Establece el puntaje actual a un nuevo valor.
     * 
     * @param puntajeActual Es el nuevo valor del puntaje
     */
    public void setPuntajeActual(int puntajeActual) {
        this.puntajeActual = puntajeActual;
    }

    /**
     * Reinicia el puntaje a cero.
     */
    public void reiniciarPuntaje() {
        this.puntajeActual = 0;
    }

    /**
     * Disminuye el puntaje actual en el valor dado.
     * Si el puntaje resultante es negativo, se establece en cero.
     * 
     * @param valor Es la cantidad a restar al puntaje actual
     */
    public void disminuirPuntaje(int valor) {
        this.puntajeActual -= valor;
        if (puntajeActual < 0) {
            puntajeActual = 0;
        }
    }

    /**
     * Aumenta el puntaje en 1 punto por el uso del sellador.
     */
    public void aumentarPuntajePorSellador() {
        this.puntajeActual += 1;
    }

    /**
     * Aumenta el puntaje en 2 puntos por el uso de la llave inglesa.
     */
    public void aumentarPuntajePorLlaveInglesa() {
        this.puntajeActual += 2;
    }
    
    /**
     * Obtiene el nombre del jugador.
     * 
     * @return Retorna el nombre del jugador.
     */
    public String getNombreJugador() {
        return nombreJugador;
    }
    
    /**
     * Obtiene el nombre del jugador con el máximo puntaje.
     * 
     * @return Retorna el nombre del jugador con el máximo puntaje.
     */
    public String getMaximoNombre() {
        return MaximoNombre;
    }

    /**
     * Establece el nombre del jugador.
     * 
     * @param nombreJugador Es el nombre del jugador.
     */
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    /**
     * Establece el nombre del jugador con el puntaje máximo.
     * 
     * @param MaximoNombre Es el nombre del jugador con el puntaje máximo.
     */
    public void setMaximoNombre(String MaximoNombre) {
        this.MaximoNombre = MaximoNombre;
    }
    
    /**
     * Obtiene el puntaje máximo del jugador.
     * 
     * @return Retorna el puntaje máximo.
     */
    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    /**
     * Establece el puntaje máximo del jugador.
     * @param puntajeMaximo Es el puntaje máximo del jugador.
     */
    public void setPuntajeMaximo(int puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }
}
