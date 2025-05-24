/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package autonoma.fontaneroyeiyei.main;

import autonoma.fontaneroyeiyei.elements.Casa;
import autonoma.fontaneroyeiyei.elements.GestorJuego;
import autonoma.fontaneroyeiyei.gui.VentanaPrincipal;
import java.util.ArrayList;

/**
 *
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class FontaneroYeiYei {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Casa> casas = new ArrayList<>();
    
       
        casas.add(new Casa(700, 700, 1));  // Casa nivel 1, 2 serpientes
        casas.add(new Casa(700, 700, 2));  // Casa nivel 2, 2 serpientes
        casas.add(new Casa(700, 700, 3));  // Casa nivel 3, 3 serpientes

       
        casas.get(0).iniciarEnemigo(100, 50, 80, 80);
        casas.get(1).iniciarEnemigo(200, 50, 80, 80);
        casas.get(2).iniciarEnemigo(300, 50, 80, 80);

        GestorJuego juego = new GestorJuego(casas);
        VentanaPrincipal ventana = new VentanaPrincipal(juego);
        ventana.setVisible(true);
    }
    
}
