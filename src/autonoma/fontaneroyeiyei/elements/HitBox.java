/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Mateo Quintero <mateo.quinterom@autonoma.edu.co>
 * @version 1.0.0
 * @since 20250501
 * @see autonoma.fontaneroyeiyei.elements
 */


    public class HitBox extends Sprite {

        public HitBox (int x, int y, int height, int width) {
           super(x,y,height,width);
        }

        @Override
        public void paint(Graphics g) {

    //         PROBAR HIT BOXS
            this.setColor(Color.black);
            g.fillRect(this.x,this.y, this.width,this.height );
        }
 
    
}



