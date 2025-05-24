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
}
