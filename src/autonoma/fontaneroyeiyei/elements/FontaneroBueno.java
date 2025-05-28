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

    private long lastHitTime = 0;      // Tiempo del último golpe recibido
    private static final int HIT_DELAY = 1000; // Retardo entre golpes

    private Puntaje puntaje;            // Puntaje del jugador
    private String nombre;               // Nombre del jugador
    private Image jugadorImage;          // Imagen del jugador
    private int pasos = 20;              // Pasos de movimiento
    private boolean saltando;            // Estado de salto
    private ArrayList<HitBox> hitBoxs;  // Lista de hitboxes
    private Herramienta herramientaSeleccionada; // Herramienta seleccionada
    private int vida = 3;                // Vida del jugador
    private HitBox hitBox;               // Hitbox del jugador

    /**
     * Constructor de la clase FontaneroBueno.
     * 
     * @param nombre Nombre del jugador
     */
    public FontaneroBueno(String nombre) {
        super(0, 0, 90, 90);
        this.puntaje = new Puntaje();
        this.nombre = nombre;
        this.puntaje.setNombreJugador(nombre);
        jugadorImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroBueno.png"))
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    
    
    public void setPuntaje(Puntaje puntaje) {
        this.puntaje = puntaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHitBoxs(ArrayList<HitBox> hitBoxs) {
        this.hitBoxs = hitBoxs;
    }

    public Puntaje getPuntaje() {
        return puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }
    

    /**
     * Mueve al fontanero en la dirección dada.
     * 
     * @param direccion Dirección del movimiento (tecla presionada)
     * @throws IOException Si ocurre un error de entrada/salida
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
                    return;
                }

            }
        }

        if (!hayColision && limiteDeMapa(nx, ny)) {
            this.x = nx;
            this.y = ny;
        }
    }


    /**
     * Hace que el fontanero salte.
     * 
     * @param direccion Dirección del salto
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
     * Hace que el fontanero caiga lentamente.
     * 
     * @param posicionOriginalY Posición original en Y antes del salto
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
     * Verifica si el movimiento está dentro de los límites del mapa.
     * 
     * @param nx Nueva posición en X
     * @param ny Nueva posición en Y
     * @return true si está dentro de los límites, false en caso contrario
     */
    public boolean limiteDeMapa(int nx, int ny) {
        return !(nx < 0 || nx > 700 - width || ny < 0 || ny > 670 - height);
    }

    /**
     * Verifica si colisiona con una hitbox específica.
     * 
     * @param h Hitbox a verificar
     * @param nx Nueva posición en X
     * @param ny Nueva posición en Y
     * @return true si colisiona, false en caso contrario
     */
    public boolean colisionaConhHitbox(HitBox h, int nx, int ny) {
        return nx < h.getX() + h.getWidth() &&
               nx + this.getWidth() > h.getX() &&
               ny < h.getY() + h.getHeight() &&
               ny + this.getHeight() > h.getY();
    }

    /**
     * Verifica si el fontanero toca a alguna serpiente.
     * 
     * @param serpientes Lista de serpientes
     * @return true si toca a alguna serpiente, false en caso contrario
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
     * Usa una herramienta en los tubos.
     * 
     * @param tecla Tecla presionada para seleccionar la herramienta
     * @param tubos Lista de tubos
     * @return true si se repara algún tubo, false en caso contrario
     */
    public boolean usarHerramientaEnTubos(char tecla, List<Tubo> tubos) {
        Herramienta herramienta = crearHerramienta(tecla);
        if (herramienta == null) {
            System.out.println("no hay herramientas"); 
            return false;
        }
        boolean reparoAlguno = intentarRepararTubosConHerramienta(herramienta, tubos);

        if (!reparoAlguno) {
            System.out.println("no se pudo reparar");
            return false;
        }
        
        return reparoAlguno;
    }

    /**
     * Crea una herramienta según la tecla presionada.
     * 
     * @param tecla Tecla presionada
     * @return Herramienta creada o null si no es válida
     */
    private Herramienta crearHerramienta(char tecla) {
        return switch (Character.toLowerCase(tecla)) {
            case 'l' -> new LlaveIglesa();
            case 's' -> new Sellador();
            default -> null;
        };
    }

    /**
     * Intenta reparar tubos usando la herramienta seleccionada.
     * 
     * @param herramienta Herramienta utilizada
     * @param tubos Lista de tubos
     * @return true si se repara algún tubo, false en caso contrario
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
     * Actualiza el puntaje del jugador según la herramienta utilizada.
     * 
     * @param herramienta Herramienta utilizada
     */
    private void actualizarPuntajePorHerramienta(Herramienta herramienta) {
        if (herramienta instanceof LlaveIglesa) {
            puntaje.aumentarPuntajePorLlaveInglesa();
        } else if (herramienta instanceof Sellador) {
            puntaje.aumentarPuntajePorSellador();
        }
    }

    /**
     * Calcula la distancia entre el fontanero y un tubo.
     * 
     * @param fontaneroX Posición X del fontanero
     * @param fontaneroY Posición Y del fontanero
     * @param fontaneroW Ancho del fontanero
     * @param fontaneroH Alto del fontanero
     * @param tuboX Posición X del tubo
     * @param tuboY Posición Y del tubo
     * @param tuboW Ancho del tubo
     * @param tuboH Alto del tubo
     * @return Distancia entre el fontanero y el tubo
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
     * Obtiene la hitbox del fontanero.
     * 
     * @return HitBox del fontanero
     */
    public HitBox getHitBox() {
        return new HitBox(this.x, this.y, this.width, this.height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(jugadorImage, x, y, width, height, null);
    }
}