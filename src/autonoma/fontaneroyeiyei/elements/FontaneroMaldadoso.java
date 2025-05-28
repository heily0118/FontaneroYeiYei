/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
    
    /**
     * Número máximo de tubos que pueden aparecer en el juego simultáneamente.
     */
    private final int maxTubos;

    /**
     * Tiempo (en milisegundos) que debe transcurrir entre la aparición de tubos.
     */
    private final int tiempoEntreTubos;

    /**
     * Indica si el hilo o proceso actual del personaje está activo.
     * Usado para controlar la ejecución de forma segura entre hilos.
     */
    private volatile boolean activo = true;

    /**
     * Lista de posiciones verticales (coordenadas Y) correspondientes a los pisos del edificio.
     */
    private List<Integer> pisosY;

    /**
     * Índice del piso en el que actualmente se encuentra el personaje.
     */
    private int pisoActual = 0;

    /**
     * Indica si el personaje es visible o no en la interfaz gráfica del juego.
     */
    private boolean visible = true;

    /**
     * Referencia al objeto {@link FontaneroBueno}, que podría ser el jugador principal
     * o un personaje aliado con el que se coordina.
     */
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
     * Lista de recorridos realizados o posibles por el personaje.
     */
    private ArrayList<Recorrido> recorridos = new ArrayList<>();


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
                             this.x, this.y+10, 60, 60, fuga);
                             /// INFORMACION DE DEJAR TUBOS
    //            System.out.println("------fontanero maliloso-----");
    //            System.out.println("se agrego tubo");
    //            System.out.println(" en casa "+ casa.getNivel());
    //            System.out.println("tamanio actual "+ casa.getTubos().size());
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
     * Establece la lista de recorridos del personaje.
     *
     * @param recorridos Es la lista de objetos Recorrido que representan los nuevos trayectos.
     */
    public void setRecorridos(ArrayList<Recorrido> recorridos) {
        
//        System.out.println("sse ingresa los recorridos");
        this.recorridos = recorridos;
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
     * Método principal del hilo del fontanero malo.
     * Controla el movimiento del fontanero por los recorridos predefinidos dentro de la casa
     * y coloca tubos con fuga en su camino cada 2 segundos.
     * 
     */
    @Override
    public void run() {
        int tubosColocados = 0;         // Contador de tubos colocados
        int numeroRecorrido = 0;        // Indice del recorrido actual
        long ultimoTubo = System.currentTimeMillis();  // Marca de tiempo del ultimo tubo colocado

        // Validar que la lista de recorridos exista y tenga al menos un elemento
        if (recorridos != null && !recorridos.isEmpty()) {

            // Posicion inicial del fontanero
            x = recorridos.get(numeroRecorrido).getInicioX();
            y = recorridos.get(numeroRecorrido).getInicioY();
            System.out.println("se mueve");

            // Ciclo principal: colocar 10 tubos
            while (tubosColocados < 15) {

                // Verifica si se ha salido del limite de la lista y reinicia
                if (numeroRecorrido >= recorridos.size()) {
                    numeroRecorrido = 0;
                }

                // Movimiento segun la posicion del recorrido actual
                Recorrido recorridoActual = recorridos.get(numeroRecorrido);

                if ((casa.getWidth() / 2) > recorridoActual.getInicioX()) {
                    // Si el recorrido esta a la izquierda de la casa, se mueve a la derecha

//                        System.out.println(" x "+x +" + "+ " dx" +dx);
                    x += dx;

                    // Si choca con los bordes, pasa al siguiente recorrido
                    if (x < 0 || x + width > casa.getWidth()) {
                        numeroRecorrido = (numeroRecorrido + 1) % recorridos.size(); // Reinicia si es necesario
                        x = recorridos.get(numeroRecorrido).getInicioX();
                        y = recorridos.get(numeroRecorrido).getInicioY();
                    }

                } else {
                    // Si el recorrido esta a la derecha de la casa, se mueve a la izquierda
                    x -= dx;

                    // Si choca con los bordes, pasa al siguiente recorrido
                    if (x < 0 || x + width > casa.getWidth()) {
                        numeroRecorrido = (numeroRecorrido + 1) % recorridos.size(); // Reinicia si es necesario
                        x = recorridos.get(numeroRecorrido).getInicioX();
                        y = recorridos.get(numeroRecorrido).getInicioY();
                    }
                }

                // Colocacion de tubos cada 2 segundos
                if (System.currentTimeMillis() - ultimoTubo >= 2000) {
                    dejarTuboConFuga();
                    tubosColocados++;
                    ultimoTubo = System.currentTimeMillis();
                }

                try {
                    Thread.sleep(20); // Pequeña pausa para evitar saturar la CPU
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Ocultar y eliminar el fontanero una vez termina
            this.setVisible(false);
            casa.eliminarFontaneroMalo();
        }
    }


    /**
     * Dibuja el objeto en pantalla si está visible y tiene imagen asignada.
     * 
     * @param g Es el objeto Graphics para el dibujo.
     */
    @Override
    public void paint(Graphics g) {
         if (this.isVisible() && this.getImage() != null) {
             Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g.drawImage(((ImageIcon) this.getImage()).getImage(), x, y, width, height, null);
        }
    }  
    
    /**
     * Detiene la ejecución del fontanero malo.
     */
    public void detener() {
        activo = false;
    }

}

