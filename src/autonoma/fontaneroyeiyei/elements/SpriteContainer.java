/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public abstract class SpriteContainer implements GraphicContainer {
    /**
     * Coordenada horizontal del contenedor.
     */
    protected int x;

    /**
     * Coordenada vertical del contenedor.
     */
    protected int y;

    /**
     * Alto del contenedor.
     */
    protected int height;

    /**
     * Ancho del contenedor.
     */
    protected int width;
    /**
     * Lista de sprites contenidos en este contenedor.
     */
    protected ArrayList<Sprite> sprites;

    /**
     * @param x      coordenada horizontal del contenedor
     * @param y      coordenada vertical del contenedor
     * @param height alto del contenedor
     * @param width  ancho del contenedor
     */
    public SpriteContainer(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        sprites = new ArrayList<>();
    }

    /** 
     * @param sprite Es el sprite a agregar
     * @return Retorna true si fue agregado correctamente
     */
    public boolean add(Sprite sprite) {
        return sprites.add(sprite);
    }
    /**
     * @param index Es el índice del sprite a eliminar
     */
    public void remove(int index) {
        sprites.remove(index);
    }

    /**
     * 
     * @param sprite sprite a eliminar
     */
    public void remove(Sprite sprite) {
        sprites.remove(sprite);
    }

    /**
     * @return Retorna el número de sprites
     */
    public int size() {
        return sprites.size();
    }

    /**
     * Redibuja el contenedor y todos los sprites que contiene.
     * 
     * @param g Es el objeto Graphics para pintar
     */
    @Override
    public void refresh(Graphics g) {
       g.setColor(Color.WHITE);
       g.fillRect(0, 0,  width, height);
    }

    /**
     * Retorna los límites del contenedor como un objeto Rectangle.
     * 
     * @return Retorna los límites del contenedor.
     */
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
    
}