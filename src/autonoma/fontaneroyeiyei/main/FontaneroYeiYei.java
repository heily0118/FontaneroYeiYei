/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package autonoma.fontaneroyeiyei.main;

import autonoma.fontaneroyeiyei.elements.GestorJuego;
import autonoma.fontaneroyeiyei.gui.VentanaPrincipal;

/**
 *
 * @author USUARIO
 */
public class FontaneroYeiYei {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       GestorJuego juego = new GestorJuego();
        

        
        VentanaPrincipal ventana = new VentanaPrincipal (juego);
        ventana.setVisible(true);
    }
    
}
