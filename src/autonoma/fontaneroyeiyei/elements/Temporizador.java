/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

/**
 *
 * @author Mateo Quintero <mateo.quinterom@autonoma.edu.co>
 * @version 1.0.0
 * @since 20250501
 * @see autonoma.fontaneroyeiyei.elements
 */
public class Temporizador {
    
    ////////////////////////////////
    /// Atributos
    ///
    
    private int tiempoRestante;


    private boolean activo;

    /////////////////////////////////
    /// Constructo
    public Temporizador(boolean activo) {
        this.activo = activo;
    }

    
    
    //////////////////////////////////
    /// Metodos
    ///
    
    public void iniciar(){}


    public void detener(){}


    public void reiniciar(){}


    public boolean esTiempoAgotado(){
    
        return true;
    }

    
}
