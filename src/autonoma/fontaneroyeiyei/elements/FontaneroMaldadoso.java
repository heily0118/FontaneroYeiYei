/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class FontaneroMaldadoso extends SpriteMobile {
    
    /** 
     * Posición horizontal del fontanero en el escenario. 
     */
    private int posX;
    
    /** 
     * Posición vertical del fontanero en el escenario. 
     */
    private int posY;
    
    /** 
     * Imagen que representa visualmente al fontanero maldadoso. 
     */
    private Image fontaneroMaldadosoImage;
    
    private Casa casa;


    /**
     * Constructor de la clase FontaneroMaldadoso.
     * 
     * @param x Es la posición X inicial
     * @param y Es la poosición Y inicial
     * @param width Es el ancho del sprite
     * @param height Es el alto del sprite
    
     */
    public FontaneroMaldadoso(int x, int y, int width, int height, Casa casa) {
       super(x, y, width, height);
        this.casa = casa;
        this.fontaneroMaldadosoImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroMalo.png")).getImage();
    }
    
    /**
     * Dibuja la imagen del fontanero maldadoso en la pantalla.
     * 
     * @param g Objeto Graphics para dibujar en pantalla
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(this.fontaneroMaldadosoImage, this.x, this.y, this.width, this.height, null);
    }

    
    private void dejarTuboConFuga() {
        List<Tubo> tubos = casa.getTubos();
        if (tubos != null && !tubos.isEmpty()) {
            int indice = (int) (Math.random() * tubos.size());

            
            Tubo tuboOriginal = tubos.get(indice);
            int x = tuboOriginal.getX();
            int y = tuboOriginal.getY();
            int ancho = tuboOriginal.getWidth();
            int alto = tuboOriginal.getHeight();

            
           Tubo tuboFuga = new TuboConFuga("tuboFuga", x, y, ancho, alto, new Fuga(10, 20, "TipoDeFuga"));
 

            tubos.set(indice, tuboFuga);
        }
    }

    /**
     * Método que se ejecuta en un hilo separado.
     * Mueve al fontanero maldadoso en dirección al fontanero bueno, paso a paso.
     */
    @Override
    public void run() {
       long tiempoUltimaFuga = System.currentTimeMillis();

        while (true) {
           
            if (System.currentTimeMillis() - tiempoUltimaFuga > 2000) {
                dejarTuboConFuga();
                tiempoUltimaFuga = System.currentTimeMillis();
            }

            try {
                Thread.sleep(20); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
    }
    
  
}
