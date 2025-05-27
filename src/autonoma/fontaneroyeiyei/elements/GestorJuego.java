package autonoma.fontaneroyeiyei.elements;

import autonoma.fontaneroyeiyei.exceptions.HerramientaInvalidaException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el estado del juego, incluyendo el fontanero, 
 * los niveles y el progreso del jugador.
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public class GestorJuego {
   
    private FontaneroBueno fontanero;  // El fontanero principal
    private ArrayList<Casa> casas;      // Lista de casas en el juego
    private Nivel nivel;                 // Nivel actual del juego
    private LectorArchivoTextoPlano lector; // Lector de archivos
    private EscritorArchivoTextoPlano escritor; // Escritor de archivos
    private final String archivoProgreso = "progreso.txt"; // Archivo de progreso
    private String nombreJugador; // Nombre del jugador
    private Puntaje puntaje;

    /**
     * Constructor de la clase GestorJuego.
     * 
     * @param casas Lista de casas en el juego
     */
    public GestorJuego(ArrayList<Casa> casas) {
        this.casas = casas;
        this.puntaje = new Puntaje ();
        this.lector = new LectorArchivoTextoPlano();
        this.escritor = new EscritorArchivoTextoPlano(archivoProgreso);
        cargarNivel();
    }

    /**
     * Inicializa el fontanero con el nombre dado.
     * 
     * @param nombreFontanero Nombre del fontanero
     */
    public void inicializarFontanero(String nombreFontanero) {
        this.fontanero = new FontaneroBueno(nombreFontanero);
    }

    /**
     * Carga el nivel desde el archivo progreso.txt.
     */
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

    /**
     * Guarda el nivel actual en el archivo.
     */
    public void guardarNivel() {
        try {
            ArrayList<String> lineas = lector.leer(archivoProgreso);
            ArrayList<String> nuevasLineas = new ArrayList<>();
            boolean reemplazado = false;

            for (String linea : lineas) {
                if (linea.startsWith("nivel=") && !reemplazado) {
                    nuevasLineas.add("nivel=" + nivel.getNumero());
                    reemplazado = true;
                } else if (!linea.startsWith("nivel=")) {
                    nuevasLineas.add(linea);
                }
            }

            if (!reemplazado) {
                nuevasLineas.add("nivel=" + nivel.getNumero());
            }

            escritor.escribir(nuevasLineas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extrae el nivel de la lista de líneas.
     * 
     * @param lineas Lista de líneas leídas del archivo
     * @return El nivel leído, o -1 si no se encuentra
     */
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

    /**
     * Actualiza el nivel en la lista de líneas.
     * 
     * @param lineas Lista de líneas existentes
     * @param nuevoNivel Nuevo nivel a establecer
     * @return Lista actualizada de líneas
     */
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

    /**
     * Obtiene el número del nivel actual.
     * 
     * @return Número del nivel actual
     */
    public int getNivel() {
        return nivel.getNumero();
    }

    /**
     * Sube al siguiente nivel si es posible.
     */
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

    /**
     * Obtiene la lista de casas.
     * 
     * @return Lista de casas
     */
    public ArrayList<Casa> getCasas() {
        return casas;
    }

    /**
     * Establece la lista de casas.
     * 
     * @param casas Nueva lista de casas
     */
    public void setCasas(ArrayList<Casa> casas) {
        this.casas = casas;
    }

    /**
     * Maneja la tecla presionada y repara tubos si es posible.
     * 
     * @param tecla Tecla presionada
     * @param tubosCasaActual Lista de tubos de la casa actual
     * @return true si se reparó un tubo, false en caso contrario
     */
    public boolean manejarTecla(char tecla, List<Tubo> tubosCasaActual) throws HerramientaInvalidaException {
        if (fontanero == null) {
            System.out.println("Fontanero no inicializado, ingresa el nombre primero.");
            return false;
        }

        Casa casaActual = null;
        int nivelActual = getNivel();

        if (nivelActual > 0 && nivelActual <= casas.size()) {
            casaActual = casas.get(nivelActual - 1);
        } else {
            System.out.println("Nivel inválido o sin casa asignada.");
            return false;
        }

        int reparadosAntes = casaActual.getTubosReparados();
        int maxTubos = casaActual.getMaxTubos();

        // Intenta usar herramienta y repara tubos si es posible
        if (fontanero.usarHerramientaEnTubos(tecla, tubosCasaActual)) {
            casaActual.repararTubo();
        }

        // Verifica si aumentó el número de tubos reparados
        if (casaActual.getTubosReparados() > reparadosAntes) {
            System.out.println("Tubos reparados: " + casaActual.getTubosReparados() + " / " + maxTubos);

            if (casaActual.getTubosReparados() >= maxTubos) {
                System.out.println("¡Nivel completado! Subiendo de nivel...");
                puntaje.setPuntajeActual(puntaje.getPuntajeActual() + 5);
                subirNivel();
            }
            return true;  // Se reparó un tubo
        }

        return false;  // No se reparó tubo
    }

    /**
     * Obtiene la casa del nivel 1.
     * 
     * @return Casa del nivel 1
     */
    public Casa getCasaNivel1() {
        return casas.get(0); 
    }

    /**
     * Obtiene la casa del nivel 2.
     * 
     * @return Casa del nivel 2
     */
    public Casa getCasaNivel2() {
        return casas.get(1); 
    }

    /**
     * Obtiene la casa del nivel 3.
     * 
     * @return Casa del nivel 3
     */
    public Casa getCasaNivel3() {
        return casas.get(2); 
    }

    /**
     * Obtiene el fontanero actual.
     * 
     * @return Fontanero actual
     */
    public FontaneroBueno getFontanero() {
        return fontanero;
    }

    /**
     * Establece el fontanero.
     * 
     * @param fontanero Nuevo fontanero
     */
    public void setFontanero(FontaneroBueno fontanero) {
        this.fontanero = fontanero;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return Nombre del jugador
     */
    public String getNombreJugador() {
        return nombreJugador;
    }

    /**
     * Establece el nombre del jugador.
     * 
     * @param nombreJugador Nuevo nombre del jugador
     */
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    
    /**
 * Reinicia la casa del nivel actual sin cambiar el progreso.
 */
    public void reiniciarNivelActual() {
        int actual = nivel.getNumero();
        if (actual > 0 && actual <= casas.size()) {
            casas.get(actual - 1).reiniciar();
            System.out.println("Reiniciaste el nivel " + actual);
        } else {
            System.out.println("No se puede reiniciar el nivel actual.");
        }
    }

    public Puntaje getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Puntaje puntaje) {
        this.puntaje = puntaje;
    }
    
    
}