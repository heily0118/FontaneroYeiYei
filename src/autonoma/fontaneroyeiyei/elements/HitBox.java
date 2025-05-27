package autonoma.fontaneroyeiyei.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que representa una caja de colisión (HitBox) en el juego.
 * Extiende de la clase Sprite para tener propiedades de posición y tamaño.
 * 
 * @author Mateo Quintero <mateo.quinterom@autonoma.edu.co>
 * @version 1.0.0
 * @since 20250501
 * @see autonoma.fontaneroyeiyei.elements
 */
public class HitBox extends Sprite {

    /**
     * Constructor de la clase HitBox.
     * 
     * @param x Posición X de la HitBox
     * @param y Posición Y de la HitBox
     * @param width Ancho de la HitBox
     * @param height Alto de la HitBox
     */
    public HitBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Dibuja la HitBox en el contexto gráfico proporcionado.
     * 
     * @param g Contexto gráfico donde se dibuja la HitBox
     */
    @Override
    public void paint(Graphics g) {
        // Dibuja la HitBox
        this.setColor(Color.black);
        g.setColor(color);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    /**
     * Verifica si esta HitBox intersecta con otra HitBox.
     * 
     * @param other Otra HitBox a verificar
     * @return true si hay intersección, false en caso contrario
     */
    public boolean intersects(HitBox other) {
        return this.x < other.x + other.width &&
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }
}