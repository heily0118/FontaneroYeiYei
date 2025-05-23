/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

/**
 *
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public abstract class Herramienta {

    /**
     * Aplica la herramienta a un tubo.
     * 
     * @param tubo El tubo sobre el que se aplica la herramienta.
     */
    public abstract void usarEn(Tubo tubo);

    /**
     * Nombre o tipo de la herramienta (opcional).
     */
    public abstract String getNombre();
}