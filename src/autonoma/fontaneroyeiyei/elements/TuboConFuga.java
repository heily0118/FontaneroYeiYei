package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * Clase que representa un tubo con fuga en el sistema.
 * 
 * @author Mateo Quintero Morales <mateo.quinterom@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class TuboConFuga extends Tubo {
    
    private Fuga fuga;

    /**
     * Constructor de la clase TuboConFuga.
     * 
     * @param estado El estado del tubo
     * @param x La coordenada X inicial del tubo
     * @param y La coordenada Y inicial del tubo
     * @param width El ancho del tubo
     * @param height La altura del tubo
     * @param fuga La fuga asociada al tubo
     */
    public TuboConFuga(String estado, int x, int y, int width, int height, Fuga fuga) {
        super(estado, x, y, width, height);
        this.fuga = fuga;
        ImageIcon icono = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboMalo.png"));
        this.setImage(icono); 
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
     * Obtiene la fuga asociada al tubo.
     * 
     * @return La fuga asociada
     */
    public Fuga getFuga() {
        return fuga;
    }

    /**
     * Repara la fuga con una llave si es del tipo "tuerca".
     */
    public void repararConLlave() {
        if (fuga != null && !fuga.estaReparada() && "tuerca".equalsIgnoreCase(fuga.getTipo())) {
            fuga.reparar('L');
        }
    }

    /**
     * Repara la fuga con sellador si es del tipo "grieta".
     */
    public void repararConSellador() {
        if (fuga != null && !fuga.estaReparada() && "grieta".equalsIgnoreCase(fuga.getTipo())) {
            fuga.reparar('S');
        }
    }

    /**
     * Verifica si el tubo tiene fuga.
     * 
     * @return true ya que este tubo tiene fuga
     */
    @Override
    public boolean tieneFuga() {
        return true;
    }

    /**
     * Verifica si el fontanero está cerca del tubo.
     * 
     * @param fontaneroX La coordenada X del fontanero
     * @param fontaneroY La coordenada Y del fontanero
     * @param fontaneroWidth El ancho del fontanero
     * @param fontaneroHeight La altura del fontanero
     * @return true si el fontanero está cerca, false en caso contrario
     */
    public boolean estaCerca(int fontaneroX, int fontaneroY, int fontaneroWidth, int fontaneroHeight) {
        // Distancia máxima para considerar "cerca" (ajusta según necesites)
        int distanciaMaxima = 50; 

        // Centro del fontanero
        int centroFontaneroX = fontaneroX + fontaneroWidth / 2;
        int centroFontaneroY = fontaneroY + fontaneroHeight / 2;

        // Centro del tubo
        int centroTuboX = this.x + this.width / 2;
        int centroTuboY = this.y + this.height / 2;

        // Calcular distancia
        double distancia = Math.sqrt(Math.pow(centroFontaneroX - centroTuboX, 2) + 
                                     Math.pow(centroFontaneroY - centroTuboY, 2));

        return distancia <= distanciaMaxima;
    }
}