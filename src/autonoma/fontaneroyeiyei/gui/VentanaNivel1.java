/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package autonoma.fontaneroyeiyei.gui;

import autonoma.fontaneroyeiyei.elements.Casa;
import autonoma.fontaneroyeiyei.elements.FontaneroBueno;
import autonoma.fontaneroyeiyei.elements.FontaneroMaldadoso;
import autonoma.fontaneroyeiyei.elements.GestorJuego;
import autonoma.fontaneroyeiyei.elements.HitBox;
import autonoma.fontaneroyeiyei.elements.Recorrido;
import autonoma.fontaneroyeiyei.elements.Tubo;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Mateo Quintero <mateo.quinterom@autonoma.edu.co>
 * @version 1.0.0
 * @since 20250501
 * @see autonoma.fontaneroyeiyei.gui
 */
public class VentanaNivel1 extends javax.swing.JDialog {

    private GestorJuego juego;
    private ImageIcon fondo;
    private FontaneroBueno f;
    private BufferedImage buffer; 
    private boolean timerGameOverStarted;
    private Clip clip;
    private ArrayList<HitBox> hitBoxs = new ArrayList<>();
    private boolean juegoTerminado;
    private Timer timerReloj;
    private int tiempoRestante = 40;
    




    public VentanaNivel1(java.awt.Frame parent, boolean modal, GestorJuego juego) {
        super(parent, modal);
        initComponents();

        
        this.juego = juego;
        
        ///se va indicar la siguientes posiciones para ej jugador
       
        this.f = new FontaneroBueno("teo");

        
        
        // se pone el fontanero en el lado de la izquierda
        f.setY(160);
        f.setX(600);
        
        //se crea la hit box del nivel 

                    
        // Segundo Piso HITBOX
        HitBox SegundoPiso = new HitBox(180, 250, 325, 530);
        HitBox techoSegundoPiso = new HitBox(0, 0,150 , 700);
        hitBoxs.add(techoSegundoPiso);
        hitBoxs.add(SegundoPiso);
        //escalas HITBOX
        HitBox escalas = new HitBox(180, 250, 250, 30);
        HitBox escalas2 = new HitBox(0, 250, 250, 60);
        hitBoxs.add(escalas2);
        hitBoxs.add(escalas);
        
        f.setHitBoxs(hitBoxs);
        juego.getCasaNivel1().getFontaneroMalo().setHitBoxs(hitBoxs);
        
        
        ArrayList<Recorrido> recorridos = new ArrayList<>();
        
        Recorrido recoridoSegundoPiso = new Recorrido("segundo piso recoridos", 600,160);
        Recorrido recoridoPrimerPiso = new Recorrido("segundo piso recoridos", 0,580);
        
        recorridos.add(recoridoSegundoPiso);
        recorridos.add(recoridoPrimerPiso);
        
        juego.getCasaNivel1().getFontaneroMalo().setRecorridos(recorridos);
        
        Thread hiloFontanero = new Thread(juego.getCasaNivel1().getFontaneroMalo());
        hiloFontanero.start();
        
        Casa casaNivel1 = juego.getCasaNivel1(); 
     

        setTitle("FontaneroYeiYei Nivel 1");
        setLocationRelativeTo(null);
        setResizable(false);


        this.setSize(700,700);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        try{ 
            this.setIconImage(new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/Logo.jpeg")).getImage());
            fondo = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/casa1.jpg"));
        }catch(NullPointerException e){
            System.out.println("Imagen no encontrada");
            
        }

        JPanel panelFondo = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);

                    // Pinta elementos que están dentro de Casa
                    casaNivel1.paint(g); 
//                    for(HitBox h : hitBoxs){
//                        
//                        h.paint(g);
//                    
//                    }
//                    // Pinta al fontanero bueno
                    f.paint(g);
                    
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

        
        
        //  agrega panel al JFrame y lanza el hilo
        setContentPane(panelFondo);

        Timer timer = new Timer(30, e -> panelFondo.repaint());
        timer.start();
        
        iniciarReloj();
        
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

        // reparar la fuga
       if(evt.getKeyChar() == 'l' || evt.getKeyChar() == 'L' ||
            evt.getKeyChar() == 's' || evt.getKeyChar() == 'S') {
             juego.manejarTecla(evt.getKeyChar());
         }

        
        
       
        
        this.repaint();

        
        
    }
    
    private void perderJuego() {
        
        System.out.println("Se acabó el tiempo, perdiste una vida!");

        juegoTerminado = true;
        repaint();

        Timer timerGameOver = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opciones = {"Volver a Intentar", "Volver al Menú"};
                int seleccion = javax.swing.JOptionPane.showOptionDialog(
                        VentanaNivel1.this,
                        "¡Tiempo terminado! ¿Qué deseas hacer?",
                        "Fin del Juego",
                        javax.swing.JOptionPane.DEFAULT_OPTION,
                        javax.swing.JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion == 0) {
                    ArrayList<Casa> nuevaListaCasas = new ArrayList<>();
                    nuevaListaCasas.add(new Casa(700, 700,1)); 
                    GestorJuego nuevoJuego = new GestorJuego(nuevaListaCasas);

                    VentanaNivel1 nuevaVentana = new VentanaNivel1(null, true, nuevoJuego);
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



    
    
        
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

