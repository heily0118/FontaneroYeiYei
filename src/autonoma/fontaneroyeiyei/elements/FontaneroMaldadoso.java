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

public class FontaneroMaldadoso extends SpriteMobile {
    
    private final int maxTubos;
    private final int tiempoEntreTubos;
    private volatile boolean activo = true;
    private List<Integer> pisosY; 
    private int pisoActual = 0;
    private boolean visible = true;

    private FontaneroBueno fontaneroBueno;
    
    /**
     * Incremento horizontal de la posición, usado para desplazamiento en X.
     */
    private int dx = 3;  
    
    /**
     * Incremento vertical de la posición, usado para desplazamiento en Y.
     */
    private int dy = -3; 
    
    /**
     * 
     * Referencia a la casa asociada al objeto.
     */
    private final Casa casa;
    
    /**
     * Lista de áreas de colisión (hitboxes) que se utilizan para detectar colisiones con el entorno.
     */
    private ArrayList<HitBox> hitBoxs = new ArrayList<>();


    /**
     * Constructor de la clase FontaneroMaldadoso.
     * 
     * @param x Es la posición X inicial
     * @param y Es la poosición Y inicial
     *
     */
    public FontaneroMaldadoso(int x,int y,int w,int h,Casa casa, List<Integer> pisosY, FontaneroBueno fontaneroBueno, int maxTubos, int tiempoEntreTubos){
       super(x,y,w,h);
        this.casa = casa;
      this.pisosY = pisosY;
      this.fontaneroBueno = fontaneroBueno;
      this.maxTubos = maxTubos;
      this.tiempoEntreTubos = tiempoEntreTubos;
      this.pisoActual = 0;
      this.y = pisosY.get(pisoActual);
      this.setVisible(true);
      this.setImage(new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroMalo.png")));
   }


    /** Suelta un tubo exactamente en la posición actual */
    private void dejarTuboConFuga(){
        
        // Verifica si el movimiento es válido
        // Primero verificamos si hay colisión
        boolean hayColision = false;
        
        for (HitBox h : hitBoxs) {
            if (this.colisionaConhHitbox(h,x,y)) {
                hayColision = true;
                break; // Salimos del ciclo en cuanto detectamos una colisión
            }
        }
        
        if(!hayColision){
            Fuga fuga = new Fuga(this.x+10, this.y+10,
                                 Math.random()<0.5?"tuerca":"grieta");
            TuboConFuga nuevo = new TuboConFuga("malo",
                             this.x, this.y, 60, 20, fuga);
            casa.agregarTubo(nuevo);
        }
    }

    /**
     * Establece la lista de hitboxes para detectar colisiones.
     * 
     * @param hitBoxs Es la lista de hitboxes que serán usadas para la detección de colisiones.
     */
    public void setHitBoxs(ArrayList<HitBox> hitBoxs) {
        this.hitBoxs = hitBoxs;
    }

    /**
     * Verifica si las coordenadas (nx, ny) del objeto colisionan con la hitbox dada.
     * 
     * @param h   HitBox con la cual se verifica la colisión.
     * @param nx  Es la nueva posición X del objeto.
     * @param ny  Es la nueva posición Y del objeto.
     * @return    Retorna true si hay colisión con la hitbox o false en caso contrario.
     */
    public boolean colisionaConhHitbox(HitBox h,int nx, int ny) {


        if ( nx < h.getX() + h.getWidth() &&
        nx + this.getWidth() > h.getX() &&
        ny < h.getY() + h.getHeight() &&
        ny + this.getHeight() > h.getY()) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Método que se ejecuta en un hilo separado para mover el objeto y soltar tubos con fuga periódicamente.
     */
        @Override
     public void run() {
         long ultimoTubo = System.currentTimeMillis();

         while (activo) {
             if (!visible) {
                 // Teletransportar al siguiente piso
                 pisoActual = (pisoActual + 1) % pisosY.size();
                 y = pisosY.get(pisoActual);
                 visible = true;
             }

             // Movimiento horizontal
             x += dx;

             // Rebotar en los bordes de la casa
             if (x < 0 || x + width > casa.getWidth()) {
                 dx = -dx;
             }

             // Soltar tubo si ha pasado suficiente tiempo
             if (System.currentTimeMillis() - ultimoTubo >= tiempoEntreTubos) {
                 dejarTuboConFuga();
                 visible = false;
                 ultimoTubo = System.currentTimeMillis();
             }

             // Verificar si está cerca del fontanero bueno
             if (cercaDelFontaneroBueno()) {
                 visible = false;
                 break;
             }

             try {
                 Thread.sleep(20);
             } catch (InterruptedException e) {
                 e.printStackTrace();
                 break; // Salir si el hilo fue interrumpido
             }
         }

         this.setVisible(false);
         casa.eliminarFontaneroMalo();
     }



    /**
     * Dibuja el objeto en pantalla si está visible y tiene imagen asignada.
     * 
     * @param g Es el objeto Graphics para el dibujo.
     */
    @Override
    public void paint(Graphics g) {
         if (this.isVisible() && this.getImage() != null) {
            g.drawImage(((ImageIcon) this.getImage()).getImage(), x, y, width, height, null);
        }
    }  
    
    private boolean cercaDelFontaneroBueno() {
        if (fontaneroBueno == null) return false;
        int distanciaX = Math.abs(this.x - fontaneroBueno.getX());
        int distanciaY = Math.abs(this.y - fontaneroBueno.getY());
       
        return distanciaX < 50 && distanciaY < 50;
    }
    
      public void detener() {
        activo = false;
    }

}

