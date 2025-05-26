/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package autonoma.fontaneroyeiyei.gui;

import autonoma.fontaneroyeiyei.elements.GestorJuego;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class VentanaInformacionJuego extends javax.swing.JDialog {
    private GestorJuego juego;
    private Clip clip;    

    
    /**
     * Creates new form VentanaInformacionJuego
     */
    public VentanaInformacionJuego(java.awt.Frame parent, boolean modal,GestorJuego juego) {
            super(parent, modal);
            initComponents();
            this.juego = juego;
            reproducirSonido();

            setTitle("Fontanero Yei Yei");
            setSize(700, 700);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.setIconImage(new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/Logo.jpeg")).getImage());

            // Fondo
            ImageIcon fondo = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/Menu.jpeg"));
            JPanel panelFondo = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            panelFondo.setLayout(null);

            
             


            // Botón de Información con ícono
            ImageIcon iconoInfo = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/Informacion.png"));
            JButton btnInformacion = new JButton(iconoInfo);
            btnInformacion.setBounds(20, 10, 64, 64);

            // Fondo claro para que se vea
            btnInformacion.setOpaque(true);
            btnInformacion.setBackground(new Color(255, 255, 255, 255)); 
            btnInformacion.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

          
            btnInformacion.setContentAreaFilled(true); 
            btnInformacion.setFocusPainted(false);
            btnInformacion.setToolTipText("Ver controles del juego");

            // Acción
            btnInformacion.addActionListener(e -> {
                String mensaje = """
                    Controles del juego:

                    L → Usar llave inglesa
                    S → Usar sellador
                    Espacio → Saltar
                    ← → Mover a la izquierda
                    → → Mover a la derecha
                    ↑ → Subir escalera 
                    """;

                JOptionPane.showMessageDialog(this, mensaje, "Controles del juego", JOptionPane.INFORMATION_MESSAGE);
            });


            // === Botones con imágenes de casas ===
            ImageIcon iconoNivel1 = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/casa1Fuera.png"));
            ImageIcon iconoNivel2 = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/casa2Fuera.png"));
            ImageIcon iconoNivel3 = new ImageIcon(getClass().getResource("/autonoma/fontaneroyeiyei/images/casa3Fuera.png"));

            JButton botonNivel1 = new JButton(iconoNivel1);
            botonNivel1.setBounds(200, 400, 90, 100);
            botonNivel1.setContentAreaFilled(false);
            botonNivel1.setBorderPainted(false);
            botonNivel1.setFocusPainted(false);
            botonNivel1.setToolTipText("Nivel 1");

            JButton botonNivel2 = new JButton(iconoNivel2);
            botonNivel2.setBounds(350, 200, 70, 70);
            botonNivel2.setContentAreaFilled(false);
            botonNivel2.setBorderPainted(false);
            botonNivel2.setFocusPainted(false);
            botonNivel2.setToolTipText("Nivel 2");

            JButton botonNivel3 = new JButton(iconoNivel3);
            botonNivel3.setBounds(380, 70, 90, 64);
            botonNivel3.setContentAreaFilled(false);
            botonNivel3.setBorderPainted(false);
            botonNivel3.setFocusPainted(false);
            botonNivel3.setToolTipText("Nivel 3");

            // Activar botones según nivel actual
            int nivelActual = juego.getNivel();
            botonNivel1.setEnabled(nivelActual >= 1);
            botonNivel2.setEnabled(nivelActual >= 2);
            botonNivel3.setEnabled(nivelActual >= 3);

            String nombreJugador = juego.getNombreJugador();
           // Acciones botones con sonido detenido
            botonNivel1.addActionListener(e -> {
                detenerSonido();  
                new VentanaNivel1(null, true, juego,nombreJugador).setVisible(true);
                 dispose();
            });

            botonNivel2.addActionListener(e -> {
                detenerSonido();  
                new VentanaNivel2(null, true, juego, nombreJugador).setVisible(true);
                 dispose();
            });

            botonNivel3.addActionListener(e -> {
                detenerSonido();  
                new VentanaNivel3(null, true, juego, nombreJugador).setVisible(true);
                 dispose();
            });


           
    

            // Añadir al panel
            panelFondo.add(botonNivel1);
            panelFondo.add(botonNivel2);
            panelFondo.add(botonNivel3);
            panelFondo.add(btnInformacion);



            setContentPane(panelFondo);
            
            addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que deseas salir del juego?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION
            );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }  
            }
        });
       
    }

    
    public void reproducirSonido() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/autonoma/fontaneroyeiyei/sounds/sonidoEspera.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    
    public void detenerSonido() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
