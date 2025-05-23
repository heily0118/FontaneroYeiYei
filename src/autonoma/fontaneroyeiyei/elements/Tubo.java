/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.util.List;
import java.awt.Graphics;
/**
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public abstract class Tubo extends Sprite{
    
    
    /**
     * Estado actual del tubo, por ejemplo: "funcionando", "dañado", "averiado".
     */
    protected String estado;

    /**
     * Constructor del tubo.
     * 
     * @param estado Estado inicial del tubo.
     * @param x Posición X
     * @param y Posición Y
     * @param width Ancho del tubo
     * @param height Alto del tubo
     */
    public Tubo(String estado, int x, int y, int width, int height) {
        super(x, y, height, width);
        this.estado = estado;
    }

    /**
     * Obtiene el estado actual del tubo.
     * @return Estado del tubo
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del tubo.
     * @param estado Nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Indica si el tubo es reparable.
     * @return true si está "dañado" o "averiado", false si está "funcionando"
     */
    public boolean esReparable() {
        return "dañado".equalsIgnoreCase(estado) || "averiado".equalsIgnoreCase(estado);
    }

    /**
     * Método de dibujo común para todos los tubos.
     * Se puede sobrescribir si es necesario, pero sirve por defecto.
     * 
     * @param g Objeto gráfico
     */
    @Override
    public void paint(Graphics g) {
        if (this.isVisible() && this.getImage() != null) {
            g.drawImage(this.getImage().getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
    }
    
    

}
