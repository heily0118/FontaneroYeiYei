/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package autonoma.fontaneroyeiyei.gui;

import autonoma.fontaneroyeiyei.elements.Casa;
import autonoma.fontaneroyeiyei.elements.FontaneroBueno;
import autonoma.fontaneroyeiyei.elements.GestorJuego;
import autonoma.fontaneroyeiyei.elements.HitBox;
import autonoma.fontaneroyeiyei.elements.Recorrido;
import autonoma.fontaneroyeiyei.exceptions.HerramientaInvalidaException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;

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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Mateo Quintero <mateo.quinterom@autonoma.edu.co>
 * @version 1.0.0
 * @since 20250501
 * @see autonoma.fontaneroyeiyei.gui
 */
public class VentanaNivel3 extends javax.swing.JDialog {


    private GestorJuego juego;
    private ImageIcon fondo;
    private BufferedImage buffer; 
    private boolean timerGameOverStarted;
    private Clip clip;
    private ArrayList<HitBox> hitBoxs = new ArrayList<>();
    private FontaneroBueno f;
    private boolean juegoTerminado;
    private int tiempoRestante = 120;
    private Timer timerReloj;
    private boolean dialogoMostrado = false;
    private String nombreJugador;
    
    public VentanaNivel3(java.awt.Frame parent, boolean modal, GestorJuego juego, FontaneroBueno fontanero ) {
        super(parent, modal);
        initComponents();
         reproducirSonido("/autonoma/fontaneroyeiyei/sounds/sonidoJuego.wav");
        this.juego = juego;
        

        this.f =  fontanero;
        
        this.juego.getCasaNivel3().setTubosReparados(0);
        this.f.setVida(3);
        Casa casaNivel3= juego.getCasaNivel3(); 
        setTitle("FontaneroYeiYei Nivel 3");
        setLocationRelativeTo(null);
        setResizable(false);


        this.setSize(700,700);
        setResizable(false);
        this.setLocationRelativeTo(null);
        
        try{ 
            this.setIconImage(new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/Logo.jpeg")).getImage());
            fondo = new ImageIcon(getClass().getResource("/autonoma/FontaneroYeiYei/images/casa3.jpg"));
        }catch(NullPointerException e){
            System.out.println("Imagen no encontrada");
            
        }
        
        
        
        ///se va indicar la siguientes posiciones para ej jugador
       
        
        
        // se pone el fontanero en el lado de la izquierda
        f.setY(150);
        f.setX(0);
        
        //SEGUNDO PISO HITBOX
        HitBox techoSegundoPiso2 = new HitBox(0, 0,700,150);//150,700
        HitBox SegundoPiso = new HitBox(0, 240,300,100);//100, 300
        HitBox SegundoPiso2 = new HitBox(450, 240,450,100);//100, 450
        
        hitBoxs.add(techoSegundoPiso2);
        hitBoxs.add(SegundoPiso);
        hitBoxs.add(SegundoPiso2);
        
        //ESCALAS SEGUNDO PISO HITBOX
        HitBox escalasSegundoPiso1 = new HitBox(300, 240, 30, 100);//100,30
        HitBox escalasSegundoPiso2 = new HitBox(450, 240, 30, 100);
        
        hitBoxs.add(escalasSegundoPiso1);
        hitBoxs.add(escalasSegundoPiso2);
        
        
        //PRIMER PISO HITBOX
        HitBox primerPiso = new HitBox(0, 450,350,120);//120,350
        HitBox primerPiso2 = new HitBox(450, 450,450,120);


        hitBoxs.add(primerPiso);
        hitBoxs.add(primerPiso2);
        
        f.setHitBoxs(hitBoxs);
        
        ////PONER RECORRDOS AL MALO
        ///
        ///
        ArrayList<Recorrido> recorridos = new ArrayList<>();
        
        Recorrido recoridoPrimerPiso = new Recorrido("segundo piso recoridos", 0,350);

        Recorrido sotano = new Recorrido("segundo piso recoridos", 600,570);
        Recorrido recoridoSegundoPiso = new Recorrido("segundo piso recoridos", 0,150);
        recorridos.add(recoridoPrimerPiso);
        
        
        
        recorridos.add(sotano);
        recorridos.add(recoridoSegundoPiso);
        juego.getCasaNivel3().getFontaneroMalo().setRecorridos(recorridos);
        
        System.out.println("----------nivel 3---------");
        System.out.println("----------casa en analisis---------");
        System.out.println(juego.getCasaNivel3().getNivel());
        
        Thread hiloFontanero = new Thread(juego.getCasaNivel3().getFontaneroMalo());
        hiloFontanero.start();
        
        
        
        
        
        
        JPanel panelFondo = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                    ///codigo temporar
                    
                     f.paint(g);
                     casaNivel3.paint(g); 
//                    Para pintar el los bloques de hitboxs
//                    for(HitBox h : hitBoxs){                
//                        h.paint(g);
//                    
//                    }

                    //Cantidad de Tubos a reparar
                    Casa casaActual = juego.getCasaNivel3(); 
                    int reparados = casaActual.getTubosReparados();
                    int max = casaActual.getMaxTubos();
                    
//                    System.out.println("------nivel 3 ------");
//                    System.out.println("cantidad de reparados"+ reparados);
                    
                    

                    // Dibuja la barra de progreso (por ejemplo, una barra horizontal)
                    int anchoBarra = 200;   // ancho total de la barra
                    int altoBarra = 20;
                    int xBarra = 500;
                    int yBarra = 70;

                    // Fondo de la barra (gris)
                    g.setColor(Color.GRAY);
                    g.fillRect(xBarra, yBarra, anchoBarra, altoBarra);

                    // Parte llena de la barra (verde)
                    int anchoProgreso = (int) ((reparados / (double) max) * anchoBarra);
                    g.setColor(Color.GREEN);
                    g.fillRect(xBarra, yBarra, anchoProgreso, altoBarra);

                    // Texto del progreso
                    g.setColor(Color.WHITE);
                    g.drawString("Tubos reparados: " + reparados + " / " + max, xBarra, yBarra - 10);
                   
                    // Tiempo
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

                  
                    String textoPuntaje = "Puntaje: " + f.getPuntaje().getPuntajeActual();

                    Font fuentePuntaje = new Font("Comic Sans MS", Font.BOLD, 28);
                    g3d.setFont(fuentePuntaje);
                    FontMetrics fmP = g3d.getFontMetrics();

                    int xP = 20;
                    int yP = 40;

                    g3d.setColor(Color.BLACK);
                    g3d.drawString(textoPuntaje, xP + 2, yP + 2);
                    g3d.setColor(Color.YELLOW);
                    g3d.drawString(textoPuntaje, xP, yP);
                    
                    
                    String textoVidas = "Vidas: " + f.getVida();
                    Font fuenteVidas = new Font("Comic Sans MS", Font.BOLD, 28);
                    g3d.setFont(fuenteVidas);
                    
                    int xV = 300;
                    int yV = 40;
                    
                    g3d.setColor(Color.BLACK);
                    g3d.drawString(textoVidas, xV + 2, yV + 2);
                    g3d.setColor(Color.red);
                    g3d.drawString(textoVidas, xV, yV);
                    
                    
                    if (f.TocoSerpiente(casaNivel3.getServientes())) {

                        FontMetrics fm = g3d.getFontMetrics();
                        String mensaje = "¡Fue golpeado!";
                        int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
                        int y = getHeight() / 2;
                        g3d.drawString(mensaje, x, y);

                    }
                    
                    
                    

                    // Mensaje de Game Over
                    if (juegoTerminado  || f.getVida()<=0) {
                        g3d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                        g3d.setColor(Color.BLACK);
                        g3d.fillRect(0, 0, getWidth(), getHeight());

                        
                        if (!dialogoMostrado) {
                            reproducirSonido("/autonoma/fontaneroyeiyei/sounds/sonidoPerdido.wav");
                            perderJuego();
                        }
                        
                        
                        g3d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                        g3d.setColor(Color.RED);
                        g3d.setFont(new Font("Arial", Font.BOLD, 60));
                        FontMetrics fm = g3d.getFontMetrics();
                        String mensaje = "GAME OVER";
                        int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
                        int y = getHeight() / 2;
                        g3d.drawString(mensaje, x, y);
                        
                    }
                }
            };
        
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

        if(evt.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }

        if(evt.getKeyCode() == KeyEvent.VK_UP ||
           evt.getKeyCode() == KeyEvent.VK_DOWN ||
           evt.getKeyCode() == KeyEvent.VK_LEFT ||
           evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            try {
                f.move(evt.getKeyCode());
            } catch (IOException ex) {
                Logger.getLogger(VentanaNivel1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(evt.getKeyCode() == KeyEvent.VK_SPACE) {
           f.saltar(evt.getKeyCode());
        }

        // Reparar la fuga
       if(evt.getKeyChar() == 'l' || evt.getKeyChar() == 'L' ||
               evt.getKeyChar() == 's' || evt.getKeyChar() == 'S') {

                juego.setFontanero(f);

                try {
                    boolean reparo = juego.manejarTecla(evt.getKeyChar(), juego.getCasaNivel1().getTubos());

                    if (reparo) {
                        juego.getCasaNivel1().repararTubo();
                        if (juego.getCasaNivel1().getTubosReparados() == juego.getCasaNivel1().getMaxTubos()) {
                            reproducirSonido("/autonoma/fontaneroyeiyei/sounds/sonidoGanado.wav");
                            nivelCompletado();
                        }
                    }
                } catch (HerramientaInvalidaException ex) {
                   
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Herramienta Inválida", JOptionPane.ERROR_MESSAGE);
                }
            }


    this.repaint();
        
        
    }//GEN-LAST:event_formKeyPressed

    private void perderJuego() {
        if (dialogoMostrado) return;
        dialogoMostrado = true;

        juegoTerminado = true;
        repaint();

        Timer timerGameOver = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opciones = {"Volver a Intentar", "Volver al Menú"};
                int seleccion = JOptionPane.showOptionDialog(
                        VentanaNivel3.this,
                        "¡El juego terminó antes de tiempo! ¿Qué deseas hacer?",
                        "Fin del Juego",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion == 0) {
                   
                    juego.reiniciarNivelActual(); 

                    detenerSonido();
                    dispose();

<<<<<<< HEAD
                    VentanaNivel3 nuevaVentana = new VentanaNivel3(null, true, nuevoJuego, f);
                  
                    nuevaVentana.setVisible(true); 
                } else {
                    detenerSonido();
                    dispose(); 
=======
                    // Reabre la misma ventana con el mismo gestor y jugador
                    VentanaNivel3 nuevaVentana = new VentanaNivel3(null, true, juego, nombreJugador);
                    nuevaVentana.setVisible(true);

                } else {
                    detenerSonido();
                    dispose();

>>>>>>> 9627bfbb0336a7324be57defc127e6db116b43ec
                    Frame miFrame = new Frame();
                    boolean esModal = true;
                    new VentanaInformacionJuego(miFrame, esModal, juego).setVisible(true);
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
                    reproducirSonido("/autonoma/fontaneroyeiyei/sounds/sonidoPerdido.wav");
                    perderJuego();
                }
            }
        });
        timerReloj.start();
    }

    
    public void reproducirSonido(String ruta) {
         try {
           
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource(ruta));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al reproducir el sonido: " + e.getMessage());
        }
    }
    
    
    
    public void detenerSonido() {
        if (clip != null) {
                clip.stop();
                clip.close();
            }
        }

    private void nivelCompletado() {
           int opcion = JOptionPane.showOptionDialog(this,
            "¡Felicidades! Has completado el Nivel 3 ! \nEres un verdadero Fontanero Yei Yei.",
            "Nivel Completado",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{"Aceptar"},
            "Aceptar");

        if (opcion == 0) {
            this.dispose(); 
           new VentanaInformacionJuego(new Frame(), true, juego).setVisible(true);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
