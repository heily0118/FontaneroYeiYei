package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una casa en el juego.
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class Casa {

    private int width;                  // Ancho de la casa
    private int height;                 // Alto de la casa
    private int nivel;                  // Nivel de dificultad

    private List<Tubo> tubos;           // Lista de tubos en la casa
    private List<Serpiente> servientes; // Lista de servientes (serpientes)
    private FontaneroMaldadoso fontaneroMalo; // Fontanero malvado

    private int maxTubos;               // Total de tubos a reparar
    private int tubosReparados;         // Tubos reparados actualmente

    /**
     * Constructor de la clase Casa.
     * 
     * @param width Ancho de la casa
     * @param height Alto de la casa
     * @param nivel Nivel de dificultad
     */
    public Casa(int width, int height, int nivel) {
        this.width = width;
        this.height = height;
        this.nivel = nivel;
        this.tubos = new ArrayList<>();
        this.servientes = new ArrayList<>();

        int limiteIzquierdo = 0;
        int limiteDerecho = width;

        int[] posicionesY;
        int tiempoEntreTubos;

        switch (nivel) {
            case 1:
                posicionesY = new int[] {
                    height - 90,
                    height - 500
                };
                tiempoEntreTubos = 4000; // 4 segundos
                this.maxTubos = 5;
                break;
            case 2:
                posicionesY = new int[] {
                    height - 90,
                    height - 425
                };
                tiempoEntreTubos = 3000; // 3 segundos
                this.maxTubos = 10;
                break;
            case 3:
                posicionesY = new int[] {
                    height - 90,
                    height - 300,
                    height - 510
                };
                tiempoEntreTubos = 2000; // 2 segundos
                this.maxTubos = 15;
                break;
            default:
                posicionesY = new int[] {height - 160};
                tiempoEntreTubos = 4000;
                this.maxTubos = 10;
        }

        this.tubosReparados = 0;

        for (int i = 0; i < posicionesY.length; i++) {
            int x = 100 + i * 150; 
            int y = posicionesY[i];

            Serpiente s = new Serpiente(x, y, 50, 50, limiteIzquierdo, limiteDerecho);
            servientes.add(s);

            Thread hiloServiente = new Thread(s);
            hiloServiente.start();
        }

        List<Integer> pisosY = new ArrayList<>();
        for (int pos : posicionesY) {
            pisosY.add(pos);
        }

        int fontaneroMaloAncho = 80;
        int fontaneroMaloAlto = 80;

        this.fontaneroMalo = new FontaneroMaldadoso(
            0, 
            pisosY.get(0), 
            fontaneroMaloAncho, 
            fontaneroMaloAlto, 
            this, 
            pisosY, 
            null, 
            tiempoEntreTubos, 
            maxTubos
        );
        Thread hiloFontanero = new Thread(fontaneroMalo);
        hiloFontanero.start();
    }

    /**
     * Obtiene el fontanero malvado.
     * 
     * @return FontaneroMaldadoso
     */
    public FontaneroMaldadoso getFontaneroMalo() {
        return fontaneroMalo;
    }

    /**
     * Obtiene la lista de tubos.
     * 
     * @return Lista de tubos
     */
    public List<Tubo> getTubos() {
        return tubos;
    }

    /**
     * Obtiene la lista de servientes.
     * 
     * @return Lista de servientes
     */
    public List<Serpiente> getServientes() {
        return servientes;
    }

    /**
     * Dibuja la casa y sus componentes.
     * 
     * @param g Objeto Graphics para dibujar
     */
    public void paint(Graphics g) {
        List<TuboConFuga> copiaTubos = new ArrayList<>();
        for (Tubo t : tubos) {
            if (t instanceof TuboConFuga) {
                copiaTubos.add((TuboConFuga) t);
            }
        }

        for (TuboConFuga tubo : copiaTubos) {
            tubo.paint(g);
        }

        for (Serpiente s : servientes) {
            s.paint(g);
        }

        if (fontaneroMalo != null) {
            fontaneroMalo.detener();
            fontaneroMalo.paint(g);
        }
    }

    /**
     * Inicia al enemigo (fontanero malo) en una posición específica.
     * 
     * @param x Posición X
     * @param y Posición Y
     * @param ancho Ancho del fontanero
     * @param alto Alto del fontanero
     */
    public void iniciarEnemigo(int x, int y, int ancho, int alto) {
        fontaneroMalo.setX(x);
        fontaneroMalo.setY(y);
        fontaneroMalo.setWidth(ancho);
        fontaneroMalo.setHeight(alto);
    }

    /**
     * Agrega un tubo a la lista de tubos.
     * 
     * @param tubo Tubo a agregar
     */
    public synchronized void agregarTubo(Tubo tubo) {
        tubos.add(tubo);
    }

    /**
     * Elimina al fontanero malo.
     */
    public void eliminarFontaneroMalo() {
        this.fontaneroMalo = null;        
    }

    /**
     * Obtiene el ancho de la casa.
     * 
     * @return Ancho de la casa
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Obtiene el alto de la casa.
     * 
     * @return Alto de la casa
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Obtiene el nivel de la casa.
     * 
     * @return Nivel de la casa
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene la cantidad de tubos reparados.
     * 
     * @return Cantidad de tubos reparados
     */
    public int getTubosReparados() {
        return tubosReparados;
    }

    /**
     * Retorna la cantidad de tubos reparados hasta ahora.
     * 
     * @return Cantidad de tubos reparados
     */
    public int getTubo() {
        return tubosReparados; 
    }

    /**
     * Retorna la cantidad máxima de tubos a reparar en este nivel.
     * 
     * @return Cantidad máxima de tubos
     */
    public int getMaxTubos() {
        return maxTubos; 
    }

    /**
     * Método para incrementar la cantidad de tubos reparados.
     * Debes llamar a este cuando repares un tubo.
     */
    public void repararTubo() {
        if (tubosReparados < maxTubos) {
//            System.out.println("----------casa " + nivel + "-------");
//            System.out.println("se aumtena reparados " + tubosReparados);
            tubosReparados++;
        }
    }

    /**
     * Establece el fontanero malo.
     * 
     * @param fontaneroMalo Fontanero malvado a establecer
     */
    public void setFontaneroMalo(FontaneroMaldadoso fontaneroMalo) {
        this.fontaneroMalo = fontaneroMalo;
    }

    public void setTubosReparados(int tubosReparados) {
        this.tubosReparados = tubosReparados;
    }
    
    
    
}