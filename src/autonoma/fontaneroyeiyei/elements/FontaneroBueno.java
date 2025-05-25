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

    private long lastHitTime = 0;
    private static final int HIT_DELAY = 1000;

    private Puntaje puntaje;
    private String nombre;
    private Image jugadorImage;
    private int pasos = 20;
    private boolean saltando;
    private ArrayList<HitBox> hitBoxs;
    private Herramienta herramientaSeleccionada;
    private int vida = 3;
    private HitBox hitBox;

    public FontaneroBueno(String nombre) {
        super(0, 0, 90, 90);
        this.puntaje = new Puntaje();
        this.nombre = nombre;
        this.puntaje.setNombreJugador(nombre);
        jugadorImage = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/FontaneroBueno.png"))
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
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

    public boolean limiteDeMapa(int nx, int ny) {
        return !(nx < 0 || nx > 700 - width || ny < 0 || ny > 670 - height);
    }

    public boolean colisionaConhHitbox(HitBox h, int nx, int ny) {
        return nx < h.getX() + h.getWidth() &&
               nx + this.getWidth() > h.getX() &&
               ny < h.getY() + h.getHeight() &&
               ny + this.getHeight() > h.getY();
    }

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

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(jugadorImage, x, y, width, height, null);
    }

    public void usarHerramientaEnTubos(char tecla, List<Tubo> tubos) {
        Herramienta herramienta = crearHerramienta(tecla);
        if (herramienta == null) return;

        boolean reparoAlguno = intentarRepararTubosConHerramienta(herramienta, tubos);

        if (!reparoAlguno) {
            System.out.println("no se pudo reparar");
        }
    }

    private Herramienta crearHerramienta(char tecla) {
        return switch (Character.toLowerCase(tecla)) {
            case 'l' -> new LlaveIglesa();
            case 's' -> new Sellador();
            default -> null;
        };
    }

    private boolean intentarRepararTubosConHerramienta(Herramienta herramienta, List<Tubo> tubos) {
       boolean reparoAlguno = false;
          // Aquí es donde agregas los prints para depurar posiciones:
            System.out.println("Fontanero posicion: (" + x + "," + y + ")");
            System.out.println("lista de tubos"+ tubos.size());
            for (Tubo tubo : tubos) {
                
                if (tubo instanceof TuboConFuga tuboConFuga) {
                    System.out.println("Tubo posición: (" + tuboConFuga.getX() + "," + tuboConFuga.getY() + ")");
                }
            }

        for (Tubo tubo : tubos) {
            if (tubo instanceof TuboConFuga tuboConFuga) {
                System.out.println("Revisando tubo en: (" + tuboConFuga.getX() + ", " + tuboConFuga.getY() + ")");

                if (tuboConFuga.estaCerca(x, y, width, height)) {
                    System.out.println("Fontanero está cerca del tubo");

                    Fuga fuga = tuboConFuga.getFuga();
                    if (fuga != null) {
                        boolean antesReparada = fuga.estaReparada();
                        System.out.println("Tipo de fuga: " + fuga.getTipo() + ", Reparada antes: " + antesReparada);
                        
                        System.out.println("Usando herramienta: " + herramienta.getNombre());

                       
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
                } else {
                    System.out.println("Fontanero no está cerca del tubo (distancia > 50)");
                    
                    int distancia = calcularDistancia(x, y, width, height, tuboConFuga.getX(), tuboConFuga.getY(), tuboConFuga.getWidth(), tuboConFuga.getHeight());
                    System.out.println("Distancia actual: " + distancia);
                }
            }
        }

        return reparoAlguno;
    }

    private void actualizarPuntajePorHerramienta(Herramienta herramienta) {
        if (herramienta instanceof LlaveIglesa) {
            puntaje.aumentarPuntajePorLlaveInglesa();
        } else if (herramienta instanceof Sellador) {
            puntaje.aumentarPuntajePorSellador();
        }
    }
    
    private int calcularDistancia(int fontaneroX, int fontaneroY, int fontaneroW, int fontaneroH, 
                                int tuboX, int tuboY, int tuboW, int tuboH) {
        int centroFontaneroX = fontaneroX + fontaneroW / 2;
        int centroFontaneroY = fontaneroY + fontaneroH / 2;
        int centroTuboX = tuboX + tuboW / 2;
        int centroTuboY = tuboY + tuboH / 2;

        return (int) Math.sqrt(Math.pow(centroFontaneroX - centroTuboX, 2) + 
                              Math.pow(centroFontaneroY - centroTuboY, 2));
    }
    public HitBox getHitBox() {
        return new HitBox(this.x, this.y, this.width, this.height);
    }



}
