/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public interface GraphicContainer {
    
    /**
     * Refresca/redibuja el contenedor gráfico.
     */
    public void refresh(Graphics g);
    
    /**
     * Devuelve los límites (dimensiones) del contenedor gráfico.
     * 
     * @return un objeto Rectangle que representa los bordes del contenedor
     */
    public Rectangle getBoundaries();
    
}
