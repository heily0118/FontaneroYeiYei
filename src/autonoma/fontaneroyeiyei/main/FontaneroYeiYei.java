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
 * @author USUARIO
 */
public class FontaneroYeiYei {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Casa> casas = new ArrayList<>();
        casas.add(new Casa(700, 700));
        casas.add(new Casa(700, 700));
        casas.add(new Casa(700, 700));

        casas.get(0).iniciarEnemigo(100, 50, 80, 80);
        casas.get(1).iniciarEnemigo(200, 50, 80, 80);
        casas.get(2).iniciarEnemigo(300, 50, 80, 80);


        GestorJuego juego = new GestorJuego(casas);
        VentanaPrincipal ventana = new VentanaPrincipal(juego);
        ventana.setVisible(true);
    }
    
}
