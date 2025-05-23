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
import javax.swing.JPanel;

/**
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class FontaneroMaldadoso extends SpriteMobile implements Runnable{
    
    
    
    private int pasos = 10;
    private int inicioX = 0;
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

    public void setInicioX(int inicioX) {
        this.inicioX = inicioX;
    }


    
    
    
    
    
    /**
     * Dibuja la imagen del fontanero maldadoso en la pantalla.
     * 
     * @param g Objeto Graphics para dibujar en pantalla
     */

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
    
    
    @Override
    public void paint(Graphics g) {
        
        g.drawImage(this.fontaneroMaldadosoImage, this.x, this.y, this.width, this.height, null);
    }

    
        private void caminarRecorridosDerecha() {

            if(limiteDeMapa(x + pasos , y)){
            this.x += pasos;
            }
            
        }
    
        
         private void caminarRecorridosIzquierda() {

            if(limiteDeMapa(x - pasos , y)){
                System.out.println("se va a la izquierda");
                System.out.println(x + " - " +pasos + " = " + (x-pasos));
                System.out.println("y :" +y);
            this.x -= pasos;
            }
            
        }
    
    public boolean limiteDeMapa(int nx, int ny) {
        // Verifica si nx y ny están dentro del rango permitido


        if (nx < 0 || nx > 700 - width || ny < 0 || ny > 700 - height) {

            System.out.println("esta afuera");
            return false; // Movimiento no permitido
        }

        // Si el movimiento es válido, se asignan los valores a x e y

        return true; // Movimiento permitido
        }
    
    
    
    
        @Override
        public void run() {
            
            while (true) {
                try {
                    if (inicioX < 450) {
                        System.out.println("es menor");
                           caminarRecorridosDerecha();
                        if (limiteDeMapa(x, y)) {
                            
                            
                            break;
                        }
                    } else {
                        System.out.println("es mayor");
                        caminarRecorridosIzquierda();

                        if (limiteDeMapa(x, y)) {
                            break;
                        }
                    }
                    Thread.sleep(5000000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } 
        }
    
        
    
   
