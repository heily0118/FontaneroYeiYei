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

<<<<<<< HEAD
public class FontaneroMaldadoso extends SpriteMobile implements Runnable{
    
    
    
    private int pasos = 10;
    private int inicioX = 0;
    /** 
     * Imagen que representa visualmente al fontanero maldadoso. 
     */
    private Image fontaneroMaldadosoImage;
    
    private Casa casa;
=======
public class FontaneroMaldadoso extends SpriteMobile {
>>>>>>> e10203d87f00bc1a0d4c5c536ac1dfbd17a484d4

    private int dx = 3;  
    private int dy = -3; 
    private final Casa casa;

<<<<<<< HEAD
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
=======
    public FontaneroMaldadoso(int x,int y,int w,int h,Casa casa){
        super(x,y,w,h);
>>>>>>> e10203d87f00bc1a0d4c5c536ac1dfbd17a484d4
        this.casa = casa;
        this.setVisible(true);            
        this.setImage(new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroMalo.png")));

    }

<<<<<<< HEAD
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
    
        
    
   
=======
    /** Suelta un tubo exactamente en la posición actual */
    private void dejarTuboConFuga(){
        Fuga fuga = new Fuga(this.x+10, this.y+10,
                             Math.random()<0.5?"tuerca":"grieta");
        TuboConFuga nuevo = new TuboConFuga("malo",
                         this.x, this.y, 60, 20, fuga);
        casa.agregarTubo(nuevo);
    }

    @Override
    public void run(){
        int tubosColocados = 0;
        long ultimoTubo = System.currentTimeMillis();

        while(tubosColocados < 10){
     
            x += dx;
            y += dy;

            if(x<0 || x+width>casa.getWidth()){ dx = -dx; }
            if(y<0 || y+height>casa.getHeight()){ dy = -dy; }

           
            if(System.currentTimeMillis() - ultimoTubo >= 2000){
                dejarTuboConFuga();
                tubosColocados++;
                ultimoTubo = System.currentTimeMillis();
            }

            try{ Thread.sleep(20); }catch(InterruptedException e){}

        }
    
        this.setVisible(false);        
        casa.eliminarFontaneroMalo();    
    }

    @Override
    public void paint(Graphics g) {
         if (this.isVisible() && this.getImage() != null) {
            g.drawImage(((ImageIcon) this.getImage()).getImage(), x, y, width, height, null);
        }
    }
    
    
}
>>>>>>> e10203d87f00bc1a0d4c5c536ac1dfbd17a484d4
