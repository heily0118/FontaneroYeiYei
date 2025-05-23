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
public class Recorrido {
    
    
    private String nombre;
    
    private int inicioX;
    
    private int inicioY;

    public Recorrido(String nombre,int inicioX, int inicioY) {
        this.nombre = nombre;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
    }

    public String getNombre() {
        return nombre;
    }

    public int getInicioX() {
        return inicioX;
    }

    public int getInicioY() {
        return inicioY;
    }
    
    
    
    
}
