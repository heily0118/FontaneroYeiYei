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
import autonoma.fontaneroyeiyei.exceptions.HerramientaInvalidaException;

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
     * 
     * @param nombre Nombre del jugador
     */
    public FontaneroBueno(String nombre) {
        super(0, 0, 90, 90);
        this.puntaje = new Puntaje();
        this.nombre = nombre;

        jugadorImage = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/FontaneroBueno.png"))
                  .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        


    }

<<<<<<< HEAD
    public void setVida(int vida) {
        this.vida = vida;
    }

    
    
=======
    /**
     * Establece el puntaje actual del fontanero.
     * 
     * @param puntaje Es el objeto Puntaje que se asignará al fontanero.
     */
>>>>>>> 9627bfbb0336a7324be57defc127e6db116b43ec
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

<<<<<<< HEAD
=======

>>>>>>> 9627bfbb0336a7324be57defc127e6db116b43ec
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


    public boolean usarHerramientaEnTubos(char tecla, List<Tubo> tubos) throws HerramientaInvalidaException {
        Herramienta herramienta = crearHerramienta(tecla);
        if (herramienta == null) {
            throw new HerramientaInvalidaException();
        }
        boolean reparoAlguno = intentarRepararTubosConHerramienta(herramienta, tubos);

        if (!reparoAlguno) {
            System.out.println("No se pudo reparar ningún tubo con la herramienta " + herramienta.getNombre());
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

        g.drawImage(jugadorImage, x, y, width, height, null);
    }


    /**
     * Obtiene el tiempo del último golpe recibido por el jugador.
     * 
     * @return Retorna tiempo del último golpe en milisegundos.
     */
    public long getLastHitTime() {
        return lastHitTime;
    }

    /**
     * Establece el tiempo del último golpe recibido por el jugador.
     * 
     * @param lastHitTime Es el nuevo tiempo del último golpe en milisegundos.
     */
    public void setLastHitTime(long lastHitTime) {
        this.lastHitTime = lastHitTime;
    }

    /**
     * Obtiene la imagen asociada al jugador.
     * 
     * @return Retorna la imagen del jugador.
     */
    public Image getJugadorImage() {
        return jugadorImage;
    }

    /**
     * Establece la imagen del jugador.
     * 
     * @param jugadorImage Es la nueva imagen del jugador.
     */
    public void setJugadorImage(Image jugadorImage) {
        this.jugadorImage = jugadorImage;
    }

    /**
     * Obtiene la cantidad de pasos dados por el jugador.
     * 
     * @return Retorna el número de pasos.
     */
    public int getPasos() {
        return pasos;
    }

    /**
     * Establece la cantidad de pasos dados por el jugador.
     * 
     * @param pasos Es el nuevo número de pasos.
     */
    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    /**
     * Indica si el jugador está actualmente saltando.
     * 
     * @return Retorna true si el jugador está saltando o false en caso contrario.
     */
    public boolean isSaltando() {
        return saltando;
    }

    /**
     * Establece el estado de salto del jugador.
     * 
     * @param saltando Retorna true si el jugador está saltando o false en caso contrario.
     */
    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }

    /**
     * Obtiene la herramienta actualmente seleccionada por el jugador.
     * 
     * @return Retorna la herramienta seleccionada.
     */
    public Herramienta getHerramientaSeleccionada() {
        return herramientaSeleccionada;
    }

    /**
     * Establece la herramienta seleccionada por el jugador.
     * 
     * @param herramientaSeleccionada Es la nueva herramienta seleccionada.
     */
    public void setHerramientaSeleccionada(Herramienta herramientaSeleccionada) {
        this.herramientaSeleccionada = herramientaSeleccionada;
    }

    /**
     * Establece la cantidad de vida del jugador.
     * 
     * @param vida Es la nueva cantidad de vida.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }    
    
}

