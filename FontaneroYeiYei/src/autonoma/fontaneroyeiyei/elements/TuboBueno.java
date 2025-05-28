package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * Clase que representa un tubo en buen estado en el sistema.
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class TuboBueno extends Tubo {
    
    /**
     * Constructor de la clase TuboBueno.
     * 
     * @param x La coordenada X inicial del tubo
     * @param y La coordenada Y inicial del tubo
     * @param width El ancho del tubo
     * @param height La altura del tubo
     */
    public TuboBueno(int x, int y, int width, int height) {
        super("funcionando", x, y, width, height);
        this.setImage(new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboBueno.png")));
    }

    /**
     * Dibuja el tubo en el contexto gráfico proporcionado.
     * 
     * @param g El contexto gráfico en el que se dibuja el tubo
     */
    @Override
    public void paint(Graphics g) {
        if (this.isVisible() && this.getImage() != null) {
            g.drawImage(this.getImage().getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
    }
    
    /**
     * Verifica si el tubo tiene fuga.
     * 
     * @return false ya que este tubo está en buen estado
     */
    @Override
    public boolean tieneFuga() {
        return false;
    }
}