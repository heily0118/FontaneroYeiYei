package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class Casa {

    private int width;
    private int height;
    private int nivel;

    private List<Tubo> tubos;
    private List<Serpiente> servientes;
    private FontaneroMaldadoso fontaneroMalo;

    private int maxTubos;           // Total tubos a reparar
    private int tubosReparados;     // Tubos reparados actualmente

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

    public FontaneroMaldadoso getFontaneroMalo() {
        return fontaneroMalo;
    }

    public List<Tubo> getTubos() {
        return tubos;
    }

    public List<Serpiente> getServientes() {
        return servientes;
    }

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

    public void iniciarEnemigo(int x, int y, int ancho, int alto) {
        fontaneroMalo.setX(x);
        fontaneroMalo.setY(y);
        fontaneroMalo.setWidth(ancho);
        fontaneroMalo.setHeight(alto);
    }

    public synchronized void agregarTubo(Tubo tubo){
        tubos.add(tubo);
    }

    public void eliminarFontaneroMalo(){
        this.fontaneroMalo = null;        
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getNivel() {
        return nivel;
    }

    public int getTubosReparados() {
        return tubosReparados;
    }
    

    // Retorna la cantidad de tubos reparados hasta ahora
    public int getTubo() {
        return tubosReparados; 
    }

    // Retorna la cantidad máxima de tubos a reparar en este nivel
    public int getMaxTubos() {
        return maxTubos; 
    }

    // Método para incrementar la cantidad de tubos reparados (debes llamar a este cuando repares un tubo)
    public void repararTubo() {
        if (tubosReparados < maxTubos) {
            System.out.println("----------casa "+ nivel+"-------");
            System.out.println("se aumtena reparados" + tubosReparados);
            tubosReparados++;
        }
    }

    public void setFontaneroMalo(FontaneroMaldadoso fontaneroMalo) {
        this.fontaneroMalo = fontaneroMalo;
    }
    
    
}
