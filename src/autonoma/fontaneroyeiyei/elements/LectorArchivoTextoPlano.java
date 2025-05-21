/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * 
 * @author Maria Paz Puerta Acevedo <mariap.puertaa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class LectorArchivoTextoPlano implements Lector {
    /**
     * Archivo guardado por lineas
     */
    private ArrayList<String> archivo;
    /**
     * charset en el cual se desea leer el archivo
     */
    private Charset charset = Charset.forName("UTF-8");
    
    
   
    /**
     * Contructor de la clase
     */
    public LectorArchivoTextoPlano() {
        this.archivo = new ArrayList<>();
    }

 
    /**
     * Lee el archivo de texto dónde tenemos el programa que deseamos interpretar
     *
     * @param localizacionArchivo ruta del archivo
     * @return arreglo con el archivo de texto
     * @throws IOException: si el archivo no existe
     */
    @Override
    public ArrayList<String> leer(String localizacionArchivo) throws IOException {
        archivo.clear();
        File file =  null;
        FileReader reader = null;
        BufferedReader buffer = null;
        
        file = new File(localizacionArchivo);
        reader = new FileReader(file);
        buffer = new BufferedReader(reader);
        
        String linea;
        while ((linea= buffer.readLine())!=null ) { 
            agregarLinea(linea);
        }
        
        reader.close();
        return this.archivo;
    }

    /**
     * Agrega lineas al Arraylist en el cual le guarda el archivo
     *
     * @param linea Es la línea de texto que se desea agregar al archivo.
     */
    public void agregarLinea(String linea) {
        archivo.add(linea);
    }
    
 
    /**
     * Limpia el archivo de posiciones en blanco
     * @param texto Es la lista de cadenas de texto que será limpiada de líneas vacías.
     */
    public void limpiarTexto(ArrayList<String> texto){
        for (int i = 0; i < texto.size(); ) {
           if (texto.get(i).trim().isEmpty()) {
                texto.remove(i); 
            } else {
             i++;
             }
         }
    }
    
    
}