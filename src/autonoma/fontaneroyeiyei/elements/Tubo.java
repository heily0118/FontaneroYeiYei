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

public abstract class Tubo extends HitBox{
    
    
    /**
     * Estado actual del tubo, por ejemplo: "funcionando", "dañado", "averiado".
     */
    protected String estado;

    /**
     * Constructor del tubo.
     * 
     * @param estado Es el estado inicial del tubo.
     * @param x Es la posición X
     * @param y Es la posición Y
     * @param width Es el ancho del tubo
     * @param height Es el alto del tubo
     */
    public Tubo(String estado, int x, int y, int width, int height) {
        super(x, y, height, width);
        this.estado = estado;
    }

    /**
     * Obtiene el estado actual del tubo.
     * @return Retorna el estado del tubo
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del tubo.
     * @param estado Es el nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Indica si el tubo es reparable.
     * @return Retorna true si está "dañado" o "averiado" o false si está "funcionando"
     */
    public boolean esReparable() {
        return "dañado".equalsIgnoreCase(estado) || "averiado".equalsIgnoreCase(estado);
    }

    /**
     * Método de dibujo común para todos los tubos.
     * Se puede sobrescribir si es necesario, pero sirve por defecto.
     * 
     * @param g Es el objeto gráfico
     */
    @Override
    public void paint(Graphics g) {
        if (this.isVisible() && this.getImage() != null) {
            g.drawImage(this.getImage().getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
    }
    
    /**
     * Indica si el tubo tiene una fuga.
     *
     * @return Retorna true si el tubo presenta una fuga o false en caso contrario.
     */
    public abstract boolean tieneFuga();

}
