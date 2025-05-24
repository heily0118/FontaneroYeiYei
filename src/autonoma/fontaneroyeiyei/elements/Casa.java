package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Casa {

    private int width;
    private int height;

    private List<Tubo> tubos;
    private Serviente serviente;
    private FontaneroMaldadoso fontaneroMalo;

    public Casa(int width, int height) {
        this.width = width;
        this.height = height;

        this.tubos = new ArrayList<>();
    

       
        int limiteIzquierdo = 0;
        int limiteDerecho = width;

      
        this.serviente = new Serviente(0, height - 100, 50, 50, limiteIzquierdo, limiteDerecho);
        this.fontaneroMalo = new FontaneroMaldadoso(0, height - 100, 50, 50, this);

      
        Thread hiloServiente = new Thread(serviente);
        hiloServiente.start();

        Thread hiloFontanero = new Thread(fontaneroMalo);
        hiloFontanero.start();
    }

   


    public List<Tubo> getTubos() {
        return tubos;
    }


    public void paint(Graphics g) {
      
        for (Tubo tubo : tubos) {
            tubo.paint(g);
        }

        serviente.paint(g);


        if (fontaneroMalo != null) {
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

}
