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

    private List<Tubo> tubos;
    private List<Serpiente> servientes;
    private FontaneroMaldadoso fontaneroMalo;

    public Casa(int width, int height, int nivel) {
        this.width = width;
      this.height = height;

      this.tubos = new ArrayList<>();
      this.servientes = new ArrayList<>();

      int limiteIzquierdo = 0;
      int limiteDerecho = width;

      int[] posicionesY;

      switch (nivel) {
          case 1:
            
              posicionesY = new int[] {
                   height - 100,  //1
                   height - 500  //2
              };
              break;
          case 2:

              posicionesY = new int[] {
                  height - 100,  // 1
                  height - 450   // 2
              };
              break;
          case 3:
              // Casa de 3 pisos
              posicionesY = new int[] {
                  height - 90,  // 1
                  height - 300,  // 2
                  height - 510   // 3
              };
              break;
          default:
              posicionesY = new int[] {height - 160};  
      }

      for (int i = 0; i < posicionesY.length; i++) {
          int x = 100 + i * 150; 
          int y = posicionesY[i];

          Serpiente s = new Serpiente(x, y, 50, 50, limiteIzquierdo, limiteDerecho);
          servientes.add(s);

          Thread hiloServiente = new Thread(s);
          hiloServiente.start();
      }

    
      int fontaneroMaloAncho = 90;
      int fontaneroMaloAlto = 90;
      this.fontaneroMalo = new FontaneroMaldadoso(0, posicionesY[0], fontaneroMaloAncho, fontaneroMaloAlto, this);
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
      
           for (Tubo tubo : tubos) {
            tubo.paint(g);
        }

        for (Serpiente s : servientes) {
            s.paint(g);
        }

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
