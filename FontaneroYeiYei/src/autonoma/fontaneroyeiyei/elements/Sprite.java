/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Color color;
    protected ImageIcon image;
    protected boolean visible = true;
    protected GraphicContainer gameContainer;

    /**
     * Constructor base de Sprite.
     * @param x posición en X
     * @param y posición en Y
     * @param height altura del sprite
     * @param width ancho del sprite
     */
    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Verifica si el sprite está fuera de los límites del contenedor gráfico.
     * @return true si está fuera, false si está dentro
     */
    public boolean isOutOfGraphicContainer() {
        return isOutOfGraphicContainer(x, y, width, height);
    }

    /**
     * Verifica si las coordenadas y dimensiones dadas están fuera del contenedor gráfico.
     * @param x posición X
     * @param y posición Y
     * @param width ancho
     * @param height alto
     * @return true si está fuera de los límites, false si está dentro
     */
    public boolean isOutOfGraphicContainer(int x, int y, int width, int height) {
        if (gameContainer == null)
            return false;

        Rectangle bounds = gameContainer.getBoundaries();

        return !(x >= bounds.getX() &&
                 y >= bounds.getY() &&
                 x + width  <= bounds.getX() + bounds.getWidth() &&
                 y + height <= bounds.getY() + bounds.getHeight());
    }

    /**
     * Verifica si este sprite colisiona con otro sprite.
     * @param other el otro sprite
     * @return true si hay colisión, false en caso contrario
     */
    public boolean checkCollision(Sprite other) {
        boolean collisionX = this.getX() + this.getWidth() >= other.getX() &&
                             this.getX() < other.getX() + other.getWidth();

        boolean collisionY = this.getY() + this.getHeight() >= other.getY() &&
                             this.getY() < other.getY() + other.getHeight();

        return collisionX && collisionY;
    }

    /**
     * Método abstracto para pintar el sprite en pantalla.
     * Debe ser implementado por las subclases.
     *
     * @param g objeto Graphics donde se dibuja el sprite
     */
    public abstract void paint(Graphics g);

    /**
     * Devuelve los límites rectangulares de este sprite para la detección de colisiones.
     *
     * @return Un objeto Rectangle que representa los límites del sprite.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Métodos getters y setters documentados abajo

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setGraphicContainer(GraphicContainer gContainer) {
        this.gameContainer = gContainer;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}