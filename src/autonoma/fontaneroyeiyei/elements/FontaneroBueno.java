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
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Representa al personaje principal del juego: el fontanero bueno.
 * Maneja el movimiento, salto, colisiones, vida, uso de herramientas y renderizado.
 * 
 * @author Maria Paz Puerta
 * @since 20250516
 * @version 1.0.0
 */
public class FontaneroBueno extends Sprite {

    /**
     * Tiempo en milisegundos del último golpe recibido para controlar la frecuencia de daño.
     */
    private long lastHitTime = 0;

    /**
     * Retardo mínimo entre golpes consecutivos que afectan la vida, en milisegundos.
     */
    private static final int HIT_DELAY = 1000;

    /**
     * Objeto Puntaje que lleva el registro del puntaje del jugador.
     */
    private Puntaje puntaje;

    /**
     * Nombre del jugador o personaje.
     */
    private String nombre;

    /**
     * Imagen que representa al jugador en pantalla.
     */
    private Image jugadorImage;

    /**
     * Cantidad de pasos (pixeles) que avanza el personaje con cada movimiento.
     */
    private int pasos = 20;

    /**
     * Indica si el personaje está actualmente saltando.
     */
    private boolean saltando;

    /**
     * Lista de HitBox con las que se verifica colisión para el jugador.
     */
    private ArrayList<HitBox> hitBoxs;

    /**
     * Herramienta actualmente seleccionada o usada por el fontanero.
     */
    private Herramienta herramientaSeleccionada;

    /**
     * Vida actual del fontanero, que representa la cantidad de golpes que puede recibir.
     */
    private int vida = 3;

    /**
     * HitBox actual del fontanero para detección de colisiones.
     */
    private HitBox hitBox;

    /**
     * Constructor de la clase FontaneroBueno.
     * @param nombre Es el nombre del jugador.
     */
    public FontaneroBueno(String nombre) {
        super(0, 0, 90, 90);
        this.puntaje = new Puntaje();
        this.nombre = nombre;
        jugadorImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroBueno.png"))
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * Establece el puntaje actual del fontanero.
     * 
     * @param puntaje Es el objeto Puntaje que se asignará al fontanero.
     */
    public void setPuntaje(Puntaje puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Establece el nombre del fontanero.
     * 
     * @param nombre Es el nombre que se asignará al fontanero.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la lista de hitboxes con las que el fontanero puede colisionar.
     * 
     * @param hitBoxs Es la lista de objetos HitBox que representan las áreas de colisión.
     */
    public void setHitBoxs(ArrayList<HitBox> hitBoxs) {
        this.hitBoxs = hitBoxs;
    }

    /**
     * Obtiene el puntaje actual del fontanero.
     * 
     * @return Retorna el objeto Puntaje asociado al fontanero.
     */
    public Puntaje getPuntaje() {
        return puntaje;
    }

    /**
     * Obtiene el nombre del fontanero.
     * 
     * @return Retorna el nombre del fontanero.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cantidad de vida actual del fontanero.
     * 
     * @return Retorna la vida restante del fontanero.
     */
    public int getVida() {
        return vida;
    }
    
    /**
     * Mueve al fontanero en la dirección indicada, verificando colisiones y límites del mapa.
     * 
     * @param direccion Es la dirección del movimiento, basada en las constantes KeyEvent (UP, DOWN, LEFT, RIGHT).
     * @throws IOException Se lanza cuando ocurra un error relacionado con entrada/salida durante el movimiento.
     * 
     */
    public void move(int direccion) throws IOException {
        int nx = x;
        int ny = y;

        switch (direccion) {
            case KeyEvent.VK_UP -> ny -= pasos;
            case KeyEvent.VK_DOWN -> ny += pasos;
            case KeyEvent.VK_LEFT -> nx -= pasos;
            case KeyEvent.VK_RIGHT -> nx += pasos;
        }

        boolean hayColision = false;


        if (!saltando) {
            HitBox hitBoxFuturo = new HitBox(nx, ny, this.width, this.height);
            for (HitBox h : hitBoxs) {
                if (hitBoxFuturo.intersects(h)) {
                    hayColision = true;
                    System.out.println("¡Colision con " + h.getClass().getSimpleName() + "!");
                    return;
                }

            }
        }

        if (!hayColision && limiteDeMapa(nx, ny)) {
            this.x = nx;
            this.y = ny;
        } else {
            System.out.println("Movimiento no permitido: " + (hayColision ? "hay colision" : "fuera de los límites"));
        }
    }


    /**
     * Realiza un salto o movimiento especial según la dirección indicada.
     * 
     * @param direccion Es la dirección del salto o movimiento, basada en constantes KeyEvent.
     *                  
     */

    public void saltar(int direccion) {
        int nx = x;
        int ny = y;

        switch (direccion) {
            case KeyEvent.VK_SPACE -> {
                ny -= 80;
                saltando = true;
            }
            case KeyEvent.VK_DOWN -> ny += pasos;
            case KeyEvent.VK_LEFT -> nx -= pasos;
            case KeyEvent.VK_RIGHT -> nx += pasos;
        }

        this.x = nx;
        this.y = ny;

        if (direccion == KeyEvent.VK_SPACE) {
            caerLento(y + 80);
        }
    }

    /**
     * Simula una caída lenta del personaje hasta una posición Y original dada.
     * 
     * @param posicionOriginalY Es la coordenada Y a la que debe caer lentamente el personaje.
     */
    public void caerLento(int posicionOriginalY) {
        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y < posicionOriginalY) {
                    y += 1;
                } else {
                    y = posicionOriginalY;
                    saltando = false;
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    /**
     * Verifica si las coordenadas (nx, ny) están dentro de los límites permitidos del mapa.
     * 
     * @param nx Es la nueva posición en el eje X que se desea validar.
     * @param ny Es la nueva posición en el eje Y que se desea validar.
     * @return Retorna true si (nx, ny) está dentro del área válida del mapa o false si está fuera de los límites.
     */
    public boolean limiteDeMapa(int nx, int ny) {
        return !(nx < 0 || nx > 700 - width || ny < 0 || ny > 670 - height);
    }

    /**
     * Verifica si un rectángulo (representado por las coordenadas nx, ny y el ancho y alto
     * del objeto actual) colisiona con otro HitBox h.
     * 
     * @param h Es el HitBox con la que se verifica la colisión.
     * @param nx Es la posición X del rectángulo a comprobar.
     * @param ny Es la posición Y del rectángulo a comprobar.
     * @return Retorna true si hay colisión o false en caso contrario.
     */
    public boolean colisionaConhHitbox(HitBox h, int nx, int ny) {
        return nx < h.getX() + h.getWidth() &&
               nx + this.getWidth() > h.getX() &&
               ny < h.getY() + h.getHeight() &&
               ny + this.getHeight() > h.getY();
    }

    /**
     * Verifica si el objeto actual colisiona con alguna serpiente de la lista.
     * 
     * @param serpientes Es la lista de objetos Serpiente con los que se verifica colisión.
     * @return Retorna true si hubo una colisión que afectó la vida o false si no hubo colisión o no pasó el tiempo suficiente.
     */
    public boolean TocoSerpiente(List<Serpiente> serpientes) {
        long currentTime = System.currentTimeMillis();

        for (Serpiente s : serpientes) {
            if (this.checkCollision(s)) {
                if (currentTime - lastHitTime > HIT_DELAY) {
                    vida--;
                    if (vida < 0) vida = 0;
                    lastHitTime = currentTime;
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Intenta usar una herramienta específica, identificada por una tecla, 
     * para reparar una lista de tubos.
     *
     * @param tecla Es el carácter que representa la herramienta a usar.
     * @param tubos Es la lista de tubos sobre los cuales se intentará usar la herramienta.
     * @return Retorna true si al menos un tubo fue reparado exitosamente o false si no se pudo usar ninguna herramienta o no se reparó ningún tubo.
     */
    public boolean usarHerramientaEnTubos(char tecla, List<Tubo> tubos) {
        Herramienta herramienta = crearHerramienta(tecla);
        if (herramienta == null) {
            System.out.println("no hay erramientas"); 
        return false ;
        }
        boolean reparoAlguno = intentarRepararTubosConHerramienta(herramienta, tubos);

        if (!reparoAlguno) {
            System.out.println("no se pudo reparar");
            return false;
        }
        
        return reparoAlguno;
    }

    /**
     * Crea una instancia de Herramienta según la tecla proporcionada.
     *
     * @param tecla Es el carácter que representa la herramienta a crear.
     * @return Retorna una instancia de Herramienta correspondiente a la tecla, o null si no es válida.
     */
    private Herramienta crearHerramienta(char tecla) {
        return switch (Character.toLowerCase(tecla)) {
            case 'l' -> new LlaveIglesa();
            case 's' -> new Sellador();
            default -> null;
        };
    }

    /**
     * Intenta reparar los tubos con fuga cercanos al fontanero usando la herramienta proporcionada.
     *
     * @param herramienta Es la herramienta que se usará para intentar reparar los tubos.
     * @param tubos Es la lista de tubos que pueden tener fugas.
     * @return Rettorna true si al menos un tubo fue reparado con éxito o false en caso contrario.
     */
    public boolean intentarRepararTubosConHerramienta(Herramienta herramienta, List<Tubo> tubos) {
        boolean reparoAlguno = false;

        for (int i = tubos.size() - 1; i >= 0; i--) {
            Tubo tubo = tubos.get(i);

            if (tubo instanceof TuboConFuga tuboConFuga) {
                if (tuboConFuga.estaCerca(x, y, width, height)) {
                    Fuga fuga = tuboConFuga.getFuga();
                    if (fuga != null) {
                        boolean antesReparada = fuga.estaReparada();

                        herramienta.usarEn(tubo);

                        if (!antesReparada && fuga.estaReparada()) {
                            System.out.println("¡Tubo reparado exitosamente!");
                            actualizarPuntajePorHerramienta(herramienta);
                            reparoAlguno = true;


                        } else if (antesReparada) {
                            System.out.println("Esta fuga ya estaba reparada");
                        }
                    } else {
                        System.out.println("El tubo no tiene fuga");
                    }
                }
            }
        }

        return reparoAlguno;
    }

    /**
     * Actualiza el puntaje según el tipo de herramienta usada para reparar un tubo.
     *
     * @param herramienta Es la herramienta utilizada para la reparación.
     */
    private void actualizarPuntajePorHerramienta(Herramienta herramienta) {
        if (herramienta instanceof LlaveIglesa) {
            puntaje.aumentarPuntajePorLlaveInglesa();
        } else if (herramienta instanceof Sellador) {
            puntaje.aumentarPuntajePorSellador();
        }
    }
    
    /**
     * Calcula la distancia euclidiana entre los centros de dos rectángulos.
     *
     * @param fontaneroX Es la coordenada X del fontanero.
     * @param fontaneroY Es la coordenada Y del fontanero.
     * @param fontaneroW Es el ancho del fontanero.
     * @param fontaneroH Es el alto del fontanero.
     * @param tuboX Es la coordenada X del tubo.
     * @param tuboY Es la coordenada Y del tubo.
     * @param tuboW Es el ancho del tubo.
     * @param tuboH Es el alto del tubo.
     * @return Retorna la distancia entre los centros del fontanero y el tubo.
     */
    private int calcularDistancia(int fontaneroX, int fontaneroY, int fontaneroW, int fontaneroH, 
                                int tuboX, int tuboY, int tuboW, int tuboH) {
        int centroFontaneroX = fontaneroX + fontaneroW / 2;
        int centroFontaneroY = fontaneroY + fontaneroH / 2;
        int centroTuboX = tuboX + tuboW / 2;
        int centroTuboY = tuboY + tuboH / 2;

        return (int) Math.sqrt(Math.pow(centroFontaneroX - centroTuboX, 2) + 
                              Math.pow(centroFontaneroY - centroTuboY, 2));
    }
    
    /**
     * Obtiene el rectángulo de colisión (hitbox) actual del objeto.
     *
     * @return Retorna un objeto HitBox que representa la zona de colisión basada en la posición (x, y) 
     *         y las dimensiones (width, height) actuales.
     */
    public HitBox getHitBox() {
        return new HitBox(this.x, this.y, this.width, this.height);
    }
    
    /**
     * Dibuja el jugador en la pantalla con una imagen escalada y suavizada.
     *
     * @param g Es el objeto Graphics que se utiliza para dibujar en el componente.
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
//        System.out.println("se pinta en ("+ x +","+ y+")");
        g.drawImage(jugadorImage, x, y, width, height, null);
    }
    
}
