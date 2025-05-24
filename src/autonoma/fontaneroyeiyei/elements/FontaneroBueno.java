/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
    
    /** Objeto que maneja el puntaje del jugador, incluyendo aciertos e intentos. */
    private Puntaje puntaje;

    /** Nombre del jugador. */
    private String nombre;

    /** Imagen del jugador para renderizar en pantalla. */
    private Image jugadorImage;

    /** Número de píxeles que el jugador avanza por cada movimiento. */
    private int pasos = 20;

    /** Bandera que indica si el jugador está realizando un salto. */
    private boolean saltando;

    /** Lista de objetos HitBox que representan obstáculos en el escenario. */
    private ArrayList<HitBox> hitBoxs;

    /** Herramienta seleccionada por el jugador (ej: Llave inglesa o Sellador). */
    private Herramienta herramientaSeleccionada;

    
    /**
     * Constructor de la clase FontaneroBueno.
     * @param nombre 
     */
    public FontaneroBueno(String nombre) {
        super(0,0, 90, 90);
        this.puntaje = new Puntaje();
        this.nombre = nombre;
        jugadorImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroBueno.png"))
                   .getImage()
                   .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * Asigna el puntaje actual del jugador.
     *
     * @param puntaje Es un objeto que almacena los aciertos e intentos del jugador.
     */
    public void setPuntaje(Puntaje puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre Es el nombre del jugador como una cadena de texto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Asigna la lista de hitboxes con los que puede colisionar el jugador.
     *
     * @param hitBoxs Lista de objetos de HitBoxs que representan los obstáculos del escenario.
     */
    public void setHitBoxs(ArrayList<HitBox> hitBoxs) {
        this.hitBoxs = hitBoxs;
    }

    
    /**
     * Devuelve el puntaje actual del jugador.
     *
     * @return Es un objeto de tipo Punatje que contiene la información de puntuación del jugador.
     */
    public Puntaje getPuntaje() {
        return puntaje;
    }

    /**
     * Devuelve el nombre del jugador.
     *
     * @return Es el nombre del jugador como una cadena de texto.
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * El movimiento se realiza en pasos de tamaño fijo, y se verifica si hay colisiones con objetos representados por HitBoxs.
     * También se asegura de no salir del área permitida del mapa.
     * 
     * @param direccion Es el código de tecla que indica la dirección del movimiento.
     * @throws IOException Si ocurre un error durante el proceso de movimiento.
    */
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

    /**
     * Permite al jugador saltar o moverse en una dirección específica.  
     * @param direccion Es el código de la tecla que indica la dirección del salto o movimiento durante el salto.
     *                 
     */
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


    /**
     * Realiza una animación de caída lenta hacia la posición vertical original después de un salto.
     * @param posicionOriginalY La posición vertical a la que el jugador debe regresar después del salto.
     */
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
        
    /**
     * 
     * Verifica si las coordenadas dadas están dentro de los límites permitidos del mapa.
     *
     * @param nx Es la coordenada X a verificar.
     * @param ny Es la coordenada Y a verificar.
     * @return Retorna true si las coordenadas están dentro del mapa o false si están fuera de los límites.
     */   
    public boolean limiteDeMapa(int nx, int ny) {
        // Verifica si nx y ny están dentro del rango permitido


        if (nx < 0 || nx > 700 - width || ny < 0 || ny > 670 - height) {


            return false; // Movimiento no permitido
        }

        // Si el movimiento es válido, se asignan los valores a x e y

        return true; // Movimiento permitido
    }

    /**
     * Verifica si el objeto colisiona con un HitBox dado al intentar moverse a una posición específica.
     *
     * @param h  Es el HitBox con el cual se va a verificar la colisión.
     * @param nx Es la nueva posición en el eje X donde se intentará ubicar el objeto.
     * @param ny Es la nueva posición en el eje Y donde se intentará ubicar el objeto.
     * @return Retorna true si el objeto colisiona con el HitBox en la posición (nx, ny) o false en caso contrario.
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
     * Dibuja la imagen del jugador en la pantalla en la posición actual.
     * 
     * @param g Es el objeto Graphics que se utiliza para realizar el dibujo.
     */
    @Override
    public void paint(Graphics g){ 
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        System.out.println("lugar de donde se pinta");
//        System.out.println("x :"+x + "y :" + y);

        g.drawImage(jugadorImage, x, y, width, height, null);
        
//              HIT bOXS
//        this.setColor(Color.BLACK);
//        g.fillRect(x,y, height, width);
    }
    
    /**
     * Usa una herramienta específica sobre los tubos que tienen fugas cercanas a la posición actual del jugador.
     * 
     * @param tecla Es la tecla que indica qué herramienta usar ('L' o 'S').
     * @param tubos Es la lista de tubos sobre los cuales se intentará usar la herramienta.
     */
    public void usarHerramientaEnTubos(char tecla, List<Tubo> tubos) {
     Herramienta herramienta = null;

        if (tecla == 'L' || tecla == 'l') {
            herramienta = new LlaveIglesa();
        } else if (tecla == 'S' || tecla == 's') {
            herramienta = new Sellador();
        }

        if (herramienta == null) return;

        System.out.println("Intentando usar herramienta: " + herramienta.getNombre());

        boolean reparoAlguno = false;  

        for (Tubo tubo : tubos) {
            if (tubo instanceof TuboConFuga) {
                TuboConFuga tuboConFuga = (TuboConFuga) tubo;
                Fuga fuga = tuboConFuga.getFuga();

                if (fuga != null && !fuga.estaReparada() && fuga.estaCerca(x, y)) {
                    boolean antesReparada = fuga.estaReparada();

                    herramienta.usarEn(tuboConFuga);

                    if (!antesReparada && fuga.estaReparada()) {
                     
                        if (herramienta instanceof LlaveIglesa) {
                            puntaje.aumentarPuntajePorLlaveInglesa();
                        } else if (herramienta instanceof Sellador) {
                            puntaje.aumentarPuntajePorSellador();
                        }
                        System.out.println("Fuga reparada con " + herramienta.getNombre() + ". Puntaje actual: " + puntaje.getPuntajeActual());
                        reparoAlguno = true;
                        break;  
                    }
                }
            }
        }

        if (!reparoAlguno) {
            System.out.println("No se reparó ninguna fuga, no se suma puntaje.");
        }
    } 

}
