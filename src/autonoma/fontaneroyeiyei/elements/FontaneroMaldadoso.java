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


    private int dx = 3;  
    private int dy = -3; 
    private final Casa casa;
    private ArrayList<HitBox> hitBoxs = new ArrayList<>();
   private ArrayList<Recorrido> recorridos = new ArrayList<>();


    /**
     * Constructor de la clase FontaneroMaldadoso.
     * 
     * @param x Es la posición X inicial
     * @param y Es la poosición Y inicial
     * @param width Es el ancho del sprite
     * @param height Es el alto del sprite
    
     */
    

    public FontaneroMaldadoso(int x,int y,int w,int h,Casa casa){
        super(x,y,w,h);

        this.casa = casa;
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
                         this.x+50, this.y+50, 60, 20, fuga);
        casa.agregarTubo(nuevo);
        }
    }

    public void setHitBoxs(ArrayList<HitBox> hitBoxs) {
        this.hitBoxs = hitBoxs;
    }
    
    public void setRecorridos(ArrayList<Recorrido> recorridos) {
        
        System.out.println("sse ingresa los recorridos");
        this.recorridos = recorridos;
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
            public void run() {
                int tubosColocados = 0;         // Contador de tubos colocados
                int numeroRecorrido = 0;        // Indice del recorrido actual
                long ultimoTubo = System.currentTimeMillis();  // Marca de tiempo del ultimo tubo colocado

                  
                // Validar que la lista de recorridos exista y tenga al menos un elemento
                if (recorridos != null && !recorridos.isEmpty()) {
                    
                    System.out.println("esta en caminar los recorridos");
                    // Posicion inicial del fontanero
                    x = recorridos.get(numeroRecorrido).getInicioX();
                    y = recorridos.get(numeroRecorrido).getInicioY();

                    // Ciclo principal: colocar 10 tubos
                    while (tubosColocados < 10) {

                        // Si el indice de recorrido supera el tamaño, reiniciar
                        if (numeroRecorrido >= recorridos.size()) {
                            numeroRecorrido = 0;
                        }

                        // Movimiento segun la posicion del recorrido
                        if ((casa.getWidth() / 2) > recorridos.get(numeroRecorrido).getInicioX()) {
                            
                            System.out.println("es mennor se va por la derecha ");
                            System.out.println(" x "+x +" + "+ " dx" +dx);
                            x += dx;
                            if (x < 0 || x + width > casa.getWidth()) {
                                numeroRecorrido++;
                                if (numeroRecorrido < recorridos.size()) {
                                    x = recorridos.get(numeroRecorrido).getInicioX();
                                    y = recorridos.get(numeroRecorrido).getInicioY();
                                }
                            }
                        } else {
                            x -= dx;
                            if (x < 0 || x + width > casa.getWidth()) {
                                numeroRecorrido++;
                                if (numeroRecorrido < recorridos.size()) {
                                    x = recorridos.get(numeroRecorrido).getInicioX();
                                    y = recorridos.get(numeroRecorrido).getInicioY();
                                }
                            }
                        }

                        // Colocacion de tubos cada 2 segundos
                        if (System.currentTimeMillis() - ultimoTubo >= 2000) {
                            dejarTuboConFuga();
                            tubosColocados++;
                            ultimoTubo = System.currentTimeMillis();
                        }

                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Ocultar y eliminar el fontanero
                    this.setVisible(false);
                    casa.eliminarFontaneroMalo();
                }
            }

    @Override
    public void paint(Graphics g) {
         if (this.isVisible() && this.getImage() != null) {
            g.drawImage(((ImageIcon) this.getImage()).getImage(), x, y, width, height, null);
        }
    }
    
    
    
    
    
}

