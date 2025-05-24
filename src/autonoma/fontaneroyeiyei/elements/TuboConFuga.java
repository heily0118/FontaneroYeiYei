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
        this.setImage(new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboMalo.png"))); 
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
    
}
