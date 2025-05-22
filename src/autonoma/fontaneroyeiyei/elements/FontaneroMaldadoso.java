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
    
    /** 
     * Referencia al fontanero bueno.
     */
    private FontaneroBueno fontaneroBueno;
    
    /** 
     * Lista de herramientas que el fontanero maldadoso ha robado al fontanero bueno. 
     */
    private List<Herramienta> herramientasRobadas = new ArrayList<>();

    /**
     * Constructor de la clase FontaneroMaldadoso.
     * 
     * @param x Es la posición X inicial
     * @param y Es la poosición Y inicial
     * @param width Es el ancho del sprite
     * @param height Es el alto del sprite
     * @param fontaneroBueno Referencia al fontanero bueno que debe seguir
     */
    public FontaneroMaldadoso(int x, int y, int width, int height, FontaneroBueno fontaneroBueno) {
        super(x, y, width, height);
        this.fontaneroBueno = fontaneroBueno;
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

    /**
     * Método que se ejecuta en un hilo separado.
     * Mueve al fontanero maldadoso en dirección al fontanero bueno, paso a paso.
     */
    @Override
    public void run() {
        while (true) {
            if (this.fontaneroBueno != null) {
                if (this.x < fontaneroBueno.getX()) {
                    this.x++; 
                } else if (this.x > fontaneroBueno.getX()) {
                    this.x--; 
                }

                if (this.y < fontaneroBueno.getY()) {
                    this.y++;
                } else if (this.y > fontaneroBueno.getY()) {
                    this.y--; 
                }
            }

            try {
                Thread.sleep(20); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Roba una herramienta y la guarda en la lista interna de herramientas robadas.
     * 
     * @param herramienta Es la herramienta a robar.
     */
    public void robarHerramienta(Herramienta herramienta) {
        if (herramienta != null) {
            herramientasRobadas.add(herramienta);
            System.out.println("¡Herramienta robada!: " + herramienta.getNombre());
        } else {
            System.out.println("No se pudo robar la herramienta: es nula.");
        }
    }
}
