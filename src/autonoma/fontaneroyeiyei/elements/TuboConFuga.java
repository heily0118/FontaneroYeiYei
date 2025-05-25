 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Mateo Quintero Morales <mateo.quinterom@autonoma.edu.co>
 * @since 20250425
 * @version 1.0.0
 */
public class TuboConFuga extends Tubo {
    
    private Fuga fuga;

    public TuboConFuga(String estado, int x, int y, int width, int height, Fuga fuga) {
        super(estado, x, y, width, height);
        this.fuga = fuga;
       ImageIcon icono = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboMalo.png"));
//        System.out.println("Tamaño real de la imagen: " + icono.getIconWidth() + " x " + icono.getIconHeight());
        this.setImage(icono); 

    }

    @Override
    public void paint(Graphics g) {
        if (this.isVisible() && this.getImage() != null) {
            g.drawImage(this.getImage().getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
    }

    public Fuga getFuga() {
        return fuga;
    }

    public void repararConLlave() {
        if (fuga != null && !fuga.estaReparada() && "tuerca".equalsIgnoreCase(fuga.getTipo())) {
            fuga.reparar('L');
        }
    }

    public void repararConSellador() {
        if (fuga != null && !fuga.estaReparada() && "grieta".equalsIgnoreCase(fuga.getTipo())) {
            fuga.reparar('S');
        }
    }

    
     @Override
    public boolean tieneFuga() {
        return true;
    }
    
    public boolean estaCerca(int fontaneroX, int fontaneroY, int fontaneroWidth, int fontaneroHeight) {
         // Distancia máxima para considerar "cerca" (ajusta según necesites)
         int distanciaMaxima = 50; 

         // Centro del fontanero
         int centroFontaneroX = fontaneroX + fontaneroWidth / 2;
         int centroFontaneroY = fontaneroY + fontaneroHeight / 2;

         // Centro del tubo
         int centroTuboX = this.x + this.width / 2;
         int centroTuboY = this.y + this.height / 2;

         // Calcular distancia
         double distancia = Math.sqrt(Math.pow(centroFontaneroX - centroTuboX, 2) + 
                                     Math.pow(centroFontaneroY - centroTuboY, 2));

         return distancia <= distanciaMaxima;
     }


}
