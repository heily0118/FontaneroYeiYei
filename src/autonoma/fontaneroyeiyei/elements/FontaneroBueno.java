/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

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
    private int alturaMax;
    private int largoMax;
    private int alturaMin;
    private int largoMin;
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

    public void setAlturaMax(int alturaMax) {
        this.alturaMax = alturaMax;
    }

    public void setLargoMax(int largoMax) {
        this.largoMax = largoMax;
    }

    public void setAlturaMin(int alturaMin) {
        this.alturaMin = alturaMin;
    }

    public void setLargoMin(int largoMin) {
        this.largoMin = largoMin;
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
            if (limiteDeMapa(nx, ny)) {
                // Si es válido, actualiza x e y
                

                x = nx;
                y = ny;            
            }
        }

        public boolean limiteDeMapa(int nx, int ny) {
            // Verifica si nx y ny están dentro del rango permitido
            
            System.out.println(nx +" < "+ largoMin +" o "+ nx +" > "+ largoMax +"-"+ width +" o "+ ny +" < "+ alturaMin  +" o "+ ny +">"+ alturaMax + "-" + height);
            if (nx < largoMin || nx > largoMax - width || ny < alturaMin || ny > alturaMax - height) {
                

                return false; // Movimiento no permitido
            }

            // Si el movimiento es válido, se asignan los valores a x e y
            this.x = nx;
            this.y = ny;
            return true; // Movimiento permitido
        }

    @Override
    public void paint(Graphics g){ 
            
        System.out.println("lugar de donde se pinta");
        System.out.println("x :"+x + "y :" + y);
        g.drawImage(jugadorImage, x, y, width, height, null);
    }
    
    
   public void usarHerramientaEnTubos(char tecla, ArrayList<Tubo> tubos) {
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
