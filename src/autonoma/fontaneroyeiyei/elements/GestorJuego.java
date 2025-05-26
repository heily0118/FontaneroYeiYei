/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class GestorJuego {
   
    private FontaneroBueno fontanero;

    private ArrayList<Casa> casas;
    private Nivel nivel;
    private LectorArchivoTextoPlano lector;
    private EscritorArchivoTextoPlano escritor;
    private final String archivoProgreso = "progreso.txt";
    private String nombreJugador;

    public GestorJuego(ArrayList<Casa> casas) {
        this.casas = casas;
        this.lector = new LectorArchivoTextoPlano();
        this.escritor = new EscritorArchivoTextoPlano(archivoProgreso);
        cargarNivel();
       
    }
    public void inicializarFontanero(String nombreFontanero) {
        
        this.fontanero = new FontaneroBueno(nombreFontanero);
    



    }

    // Cargar nivel desde archivo progreso.txt
    private void cargarNivel() {
         List<String> lineas;
            try {
                lineas = lector.leer("progreso.txt");
            } catch (IOException e) {
              
                lineas = new ArrayList<>();
                lineas.add("nivel=1");
                try {
                    escritor = new EscritorArchivoTextoPlano("progreso.txt");
                    escritor.escribir(new ArrayList<>(lineas));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            int nivelLeido = obtenerNivel(lineas);
            this.nivel = new Nivel(nivelLeido >= 0 ? nivelLeido : 1);
    }

    // Guardar nivel actual al archivo
    public void guardarNivel() {
        try {
            ArrayList<String> lineas = lector.leer(archivoProgreso);
            lineas = new ArrayList<>(actualizarNivel(lineas, nivel.getNumero()));
            escritor.escribir(lineas); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos auxiliares privados para extraer y actualizar nivel en archivo
    private int obtenerNivel(List<String> lineas) {
        for (String linea : lineas) {
            if (linea.startsWith("nivel=")) {
                try {
                    return Integer.parseInt(linea.substring(linea.indexOf('=') + 1).trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error convertir nivel: " + e.getMessage());
                }
            }
        }
        return -1;
    }

    private ArrayList<String> actualizarNivel(List<String> lineas, int nuevoNivel) {
        boolean nivelActualizado = false;
        ArrayList<String> resultado = new ArrayList<>();
        for (String linea : lineas) {
            if (linea.startsWith("nivel=")) {
                resultado.add("nivel=" + nuevoNivel);
                nivelActualizado = true;
            } else {
                resultado.add(linea);
            }
        }
        if (!nivelActualizado) {
            resultado.add("nivel=" + nuevoNivel);
        }
        return resultado;
    }

    public int getNivel() {
        return nivel.getNumero();
    }

    public void subirNivel() {
        int nuevoNivel = nivel.getNumero() + 1;
            if (nuevoNivel <= casas.size()) {
                nivel.setNumero(nuevoNivel);
                guardarNivel();
                System.out.println("Subiste al nivel " + nuevoNivel);
            } else {
                System.out.println("¡Felicidades! Completaste todos los niveles.");
            }
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public void setCasas(ArrayList<Casa> casas) {
        this.casas = casas;
    }

  public boolean manejarTecla(char tecla, List<Tubo> tubosCasaActual) {
        if (fontanero == null) {
            System.out.println("Fontanero no inicializado, ingresa el nombre primero.");
            return false;
        }

        Casa casaActual = null;
        int nivelActual = getNivel();

        if (nivelActual > 0 && nivelActual <= casas.size()) {
            casaActual = casas.get(nivelActual - 1);
            System.out.println("----gestor jjuego---");
            System.out.println("tamnio de casas"+ casas.size());
            System.out.println(" la casa actual la" + (nivelActual-1));
        System.out.println("-------");
            
        } else {
            System.out.println("Nivel inválido o sin casa asignada.");
            return false;
        }

        int reparadosAntes = casaActual.getTubo();
        int maxTubos = casaActual.getMaxTubos();

        // Intenta usar herramienta y repara tubos si es posible
         System.out.println("----gestor jjuego---");
        System.out.println("-------");
        
        if(fontanero.usarHerramientaEnTubos(tecla, tubosCasaActual)){
        System.out.println("----se aumenta las tuberia buenas---");
            casaActual.repararTubo();
        }

        // Verifica si aumentó el número de tubos reparados
        if (casaActual.getTubo() > reparadosAntes) {
            System.out.println("Tubos reparados: " + casaActual.getTubo() + " / " + maxTubos);

            if (casaActual.getTubo() >= maxTubos) {
                System.out.println("¡Nivel completado! Subiendo de nivel...");
                subirNivel();
            }
            return true;  // Se reparó un tubo
        }

        return false;  // No se reparó tubo
    }

     
    public Casa getCasaNivel1() {
        return casas.get(0); 
    }
     public Casa getCasaNivel2() {
        return casas.get(1); 
    }
    public Casa getCasaNivel3() {
        return casas.get(2); 
    }

    public FontaneroBueno getFontanero() {
        return fontanero;
    }

    public void setFontanero(FontaneroBueno fontanero) {
        this.fontanero = fontanero;
    }
    
    public String getNombreJugador() {
        return nombreJugador;
    }
    
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }



}