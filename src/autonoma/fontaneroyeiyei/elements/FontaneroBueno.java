/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class FontaneroBueno extends Sprite{
    
    
    ////////////////////////////////
    /// Atributos
    ///
    private Puntaje puntaje;
    private String nombre;
    private Image jugadorImage;
    private int pasos = 20;
    private boolean saltando;
    
    private ArrayList<HitBox> hitBoxs = new ArrayList<>();


    private Herramienta herramientaSeleccionada;

    

    /////////////////////////////////
    /// Constructor
    ////
    ///
    
    
    public FontaneroBueno(String nombre) {
        super(0,0, 80, 80);
        this.puntaje = new Puntaje();
        this.nombre = nombre;
        jugadorImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroBueno.png")).getImage();
    }

    //////////////////////////////////
    /// Metodos de acceso (set)
    ///
    public void setPuntaje(Puntaje puntaje) {
        this.puntaje = puntaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setHitBoxs(ArrayList<HitBox> hitBoxs) {
        this.hitBoxs = hitBoxs;
    }

    



    
    
    
    //////////////////////////////////
    /// Metodos de acceso (get)
    ///
    public Puntaje getPuntaje() {
        return puntaje;
    }

    public String getNombre() {
        return nombre;
    }


    
    //////////////////////////////////
    /// Metodos
    ///

        public void move(int direccion) throws IOException  {
            int nx = x;
            int ny = y;

            switch(direccion) {
                case KeyEvent.VK_UP:
                    ny -= pasos;
                    break;
                case KeyEvent.VK_DOWN:
                    ny += pasos;
                    break;
                case KeyEvent.VK_LEFT:
                    nx -= pasos;
                    break;
                case KeyEvent.VK_RIGHT:
                    nx += pasos;
                    break;
            }

           
                   
                   
               // Verifica si el movimiento es válido
                // Primero verificamos si hay colisión
                boolean hayColision = false;
                if (!saltando){
                for (HitBox h : hitBoxs) {
                    if (this.colisionaConhHitbox(h,nx,ny)) {
                        hayColision = true;
                        break; // Salimos del ciclo en cuanto detectamos una colisión
                    }
                }
                }
                // Si no hay colisión, verificamos si está dentro del límite del mapa
                if (!hayColision && limiteDeMapa(nx, ny)) {
                    // Si es válido, actualiza x e y
                    this.x = nx;
                    this.y = ny;
                } else {
                    System.out.println("Movimiento no permitido: " + (hayColision ? "hay colision" : "fuera de los lImites"));
                }
            
        }

            public void saltar(int direccion) {
                int nx = x;
                int ny = y;

                switch (direccion) {
                    case KeyEvent.VK_SPACE:
                        ny -= 80; // Salto hacia arriba
                        saltando = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        ny += pasos;
                        break;
                    case KeyEvent.VK_LEFT:
                        nx -= pasos;
                        break;
                    case KeyEvent.VK_RIGHT:
                        nx += pasos;
                        break;
                }

                this.x = nx;
                this.y = ny;

                if (direccion == KeyEvent.VK_SPACE) {
                    caerLento(y + 80); // Llamar a caída lenta hacia la posición original
                }
            }



        public void caerLento(int posicionOriginalY) {
            Timer timer = new Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (y < posicionOriginalY) {
                        y += 2;
                    } else {
                        y = posicionOriginalY;
                        saltando = false;
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            timer.start();
        }
        
        
        public boolean limiteDeMapa(int nx, int ny) {
            // Verifica si nx y ny están dentro del rango permitido
            
            
            if (nx < 0 || nx > 700 - width || ny < 0 || ny > 670 - height) {
                

                return false; // Movimiento no permitido
            }

            // Si el movimiento es válido, se asignan los valores a x e y

            return true; // Movimiento permitido
        }

        public boolean colisionaConhHitbox(HitBox h,int nx, int ny) {
            
            
                if ( nx < h.getX() + h.getWidth() &&
                nx + this.getWidth() > h.getX() &&
                ny < h.getY() + h.getHeight() &&
                ny + this.getHeight() > h.getY()) {
                    return true;
                }

        
        return false;
    }
        
    @Override
    public void paint(Graphics g){ 
            
//        System.out.println("lugar de donde se pinta");
//        System.out.println("x :"+x + "y :" + y);
//        g.drawImage(jugadorImage, x, y, width, height, null);
        
//              HIT bOXS
//        this.setColor(Color.BLACK);
//        g.fillRect(x,y, height, width);
    }
    
    
   public void usarHerramientaEnTubos(char tecla, List<Tubo> tubos) {
        Herramienta herramienta = null;
        if (tecla == 'L' || tecla == 'l') {
            herramienta = new LlaveIglesa();
        } else if (tecla == 'S' || tecla == 's') {
            herramienta = new Sellador();
        }
        if (herramienta == null) return;

        for (Tubo tubo : tubos) {
           
            if (tubo instanceof TuboConFuga) {
                TuboConFuga tuboConFuga = (TuboConFuga) tubo;
                Fuga fuga = tuboConFuga.getFuga();
                if (fuga != null && !fuga.estaReparada() && fuga.estaCerca(x, y)) {
                    herramienta.usarEn(tuboConFuga);
                    if (fuga.estaReparada()) {
                        System.out.println("Fuga reparada con " + herramienta.getNombre());
                       
                    } else {
                        System.out.println("No se pudo reparar con " + herramienta.getNombre());
                    }
                    break; 
                }
            }
        }
}

    

}
