/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package autonoma.fontaneroyeiyei.gui;

import autonoma.fontaneroyeiyei.elements.Casa;
import autonoma.fontaneroyeiyei.elements.FontaneroBueno;
import autonoma.fontaneroyeiyei.elements.GestorJuego;
import autonoma.fontaneroyeiyei.elements.HitBox;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Mateo Quintero <mateo.quinterom@autonoma.edu.co>
 * @version 1.0.0
 * @since 20250501
 * @see autonoma.fontaneroyeiyei.gui
 */
public class VentanaNivel2 extends javax.swing.JDialog {

    private GestorJuego juego;
    private ImageIcon fondo;
    private FontaneroBueno f;
    private BufferedImage buffer; 
    private boolean timerGameOverStarted;
    private Clip clip;
    private ArrayList<HitBox> hitBoxs = new ArrayList<>();
    private boolean juegoTerminado;
    private Timer timerReloj;
    private int tiempoRestante = 30;
    
    public VentanaNivel2(java.awt.Frame parent, boolean modal, GestorJuego juego ) {
        super(parent, modal);
        initComponents();
        
        this.juego = juego;
        
        this.f = new FontaneroBueno("teo");
        
        Casa casaNivel2 = juego.getCasaNivel2(); 
        setTitle("FontaneroYeiYei Nivel 2");
        setLocationRelativeTo(null);
        setResizable(false);


        this.setSize(700,700);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        try{ 
            this.setIconImage(new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/Logo.jpeg")).getImage());
            fondo = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/casa2.jpg"));
        }catch(NullPointerException e){
            System.out.println("Imagen no encontrada");
            
        }
        
        ///se va indicar la siguientes posiciones para ej jugador
       
        
        
        
        // se pone el fontanero en el lado de la izquierda
        f.setY(240);
        f.setX(0);
        
        //SEGUNDO PISO HITBOX
        HitBox techoSegundoPiso2 = new HitBox(0, 0,230,700);
        HitBox SegundoPiso = new HitBox(0, 325,250,480);
        HitBox SegundoPiso2 = new HitBox(625, 325,250,100);
        
        hitBoxs.add(techoSegundoPiso2);
        hitBoxs.add(SegundoPiso);
        hitBoxs.add(SegundoPiso2);
        
        //ESCALAS HITBOX
        HitBox escalas = new HitBox(480, 325, 250, 30);
        hitBoxs.add(escalas);
        
        f.setHitBoxs(hitBoxs);
        
        
        
        
        
        
        
        
        JPanel panelFondo = new JPanel() {
            
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                    
                    f.paint(g);
                     // Pinta elementos que están dentro de Casa
                    casaNivel2.paint(g); 
                    
                    //////codigo temporar
//                    Para pintar el los bloques de hitboxs
//                    for(HitBox h : hitBoxs){                
//                        h.paint(g);
//                    }
                    int minutos = tiempoRestante / 60;
                    int segundos = tiempoRestante % 60;

                    String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);

                    Graphics2D g3d = (Graphics2D) g;
                    g3d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    Font fuenteBonita = new Font("Comic Sans MS", Font.BOLD, 28);
                    g3d.setFont(fuenteBonita);


                    FontMetrics fm1 = g3d.getFontMetrics();
                    int x1 = getWidth() - fm1.stringWidth("Tiempo: 00:00") - 20;  
                    int y1 = 40;

                    g3d.setColor(Color.BLACK);
                    g3d.drawString("Tiempo: " + tiempoFormateado, x1 + 2, y1 + 2);

                    g3d.setColor(Color.WHITE);
                    g3d.drawString("Tiempo: " + tiempoFormateado, x1, y1);
                    
                    if (juegoTerminado) {
                        Graphics2D g2d = (Graphics2D) g;

                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(0, 0, getWidth(), getHeight());

                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                        g2d.setColor(Color.RED);
                        g2d.setFont(new Font("Arial", Font.BOLD, 60));
                        FontMetrics fm = g2d.getFontMetrics();
                        String mensaje = "GAME OVER";
                        int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
                        int y = getHeight() / 2;
                        g2d.drawString(mensaje, x, y);
                    }
                }
            };
        
        setContentPane(panelFondo);
         Timer timer = new Timer(30, e -> panelFondo.repaint());
        timer.start();
        
        iniciarReloj();
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

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

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_Q)
        {
            System.exit(0);
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_UP |
           evt.getKeyCode() == KeyEvent.VK_DOWN |
           evt.getKeyCode() == KeyEvent.VK_LEFT |
           evt.getKeyCode() == KeyEvent.VK_RIGHT)
        {
                  
            try {
                
                f.move(evt.getKeyCode());
            } catch (IOException ex) {
                Logger.getLogger(VentanaNivel1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
        
        f.saltar(evt.getKeyCode());

        }
        
        this.repaint();

        
        ;

        
        
        
    }//GEN-LAST:event_formKeyPressed
    private void perderJuego() {
        
        System.out.println("Se acabó el tiempo, perdiste una vida!");

        juegoTerminado = true;
        repaint();

        Timer timerGameOver = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opciones = {"Volver a Intentar", "Volver al Menú"};
                int seleccion = javax.swing.JOptionPane.showOptionDialog(
                        VentanaNivel2.this,
                        "¡Tiempo terminado, has perdido! ¿Qué deseas hacer?",
                        "Fin del Juego",
                        javax.swing.JOptionPane.DEFAULT_OPTION,
                        javax.swing.JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion == 0) {
                    ArrayList<Casa> nuevaListaCasas = new ArrayList<>();
                    nuevaListaCasas.add(new Casa(700, 700,2)); 
                    nuevaListaCasas.add(new Casa(700, 700,2));
                    GestorJuego nuevoJuego = new GestorJuego(nuevaListaCasas);

                    VentanaNivel2 nuevaVentana = new VentanaNivel2(null, true, nuevoJuego);
                    dispose(); 
                    nuevaVentana.setVisible(true); 
                } else {
                    dispose(); 
                }
            }
        });

        timerGameOver.setRepeats(false);
        timerGameOver.start();
    }
    
    private void iniciarReloj() {
        timerReloj = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                    repaint();
                } else {
                    timerReloj.stop();
                    juegoTerminado = true;
                    perderJuego();
                }
            }
        });
        timerReloj.start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
