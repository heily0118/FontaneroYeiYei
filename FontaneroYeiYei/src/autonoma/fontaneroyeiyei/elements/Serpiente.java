package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Clase que representa una serpiente móvil en el sistema.
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class Serpiente extends SpriteMobile {

    private Image servienteImage;
    private int direccionMovimiento = 1; // 1 para derecha, -1 para izquierda
    private int limiteIzquierdo;
    private int limiteDerecho;
    private int velocidad = 2; 

    /**
     * Constructor de la clase Serpiente.
     * 
     * @param x La coordenada X inicial
     * @param y La coordenada Y inicial
     * @param ancho El ancho de la serpiente
     * @param alto La altura de la serpiente
     * @param limiteIzquierdo El límite izquierdo del movimiento
     * @param limiteDerecho El límite derecho del movimiento
     */
    public Serpiente(int x, int y, int ancho, int alto, int limiteIzquierdo, int limiteDerecho) {
        super(x, y, ancho, alto);
        servienteImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/Serviente.png")).getImage();
        this.limiteIzquierdo = limiteIzquierdo;
        this.limiteDerecho = limiteDerecho;
    }

    /**
     * Dibuja la serpiente en el contexto gráfico proporcionado.
     * 
     * @param g El contexto gráfico en el que se dibuja la serpiente
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(servienteImage, getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Método que ejecuta el movimiento de la serpiente en un bucle.
     */
    @Override
    public void run() {
        while (true) {
            mover();
            try {
                Thread.sleep(30); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /**
     * Mueve la serpiente dentro de los límites establecidos.
     */
    public void mover() {
        setX(getX() + direccionMovimiento * velocidad);

        if (getX() <= limiteIzquierdo) {
            setX(limiteIzquierdo);
            direccionMovimiento = 1;
        } else if (getX() + getWidth() >= limiteDerecho) {
            setX(limiteDerecho - getWidth());
            direccionMovimiento = -1;
        }
    }

    /**
     * Verifica si la serpiente colisiona con otro objeto móvil.
     * 
     * @param otro El otro objeto móvil a verificar
     * @return true si hay colisión, false en caso contrario
     */
    public boolean colisionaCon(SpriteMobile otro) {
        return this.getBounds().intersects(otro.getBounds());
    }
    
    /**
     * Detiene el hilo que mueve la serpiente.
     */
    public void detener() {
        running = false;
    }
}