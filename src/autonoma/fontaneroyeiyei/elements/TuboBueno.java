/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class TuboBueno extends Tubo {
    public TuboBueno(int x, int y, int width, int height) {
        super("funcionando", x, y, width, height);
        this.setImage(new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/TuboBueno.png")));

    }

    @Override
    public void paint(Graphics g) {
        if (this.isVisible() && this.getImage() != null) {
            g.drawImage(this.getImage().getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
    }
    
     @Override
    public boolean tieneFuga() {
        return false;
    }
}
