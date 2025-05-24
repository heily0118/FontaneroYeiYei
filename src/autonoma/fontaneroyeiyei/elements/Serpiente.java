/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
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

    public Serpiente(int x, int y, int ancho, int alto, int limiteIzquierdo, int limiteDerecho) {
        super(x, y, ancho, alto);
        servienteImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/Serviente.png")).getImage();
        this.limiteIzquierdo = limiteIzquierdo;
        this.limiteDerecho = limiteDerecho;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(servienteImage, getX(), getY(), getWidth(), getHeight(), null);
    }

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

    public boolean colisionaCon(SpriteMobile otro) {
        return this.getBounds().intersects(otro.getBounds());
    }
}