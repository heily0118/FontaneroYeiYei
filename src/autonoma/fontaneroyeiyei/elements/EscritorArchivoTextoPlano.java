/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */

public class EscritorArchivoTextoPlano implements Escritor {

    /**
     * charset en el cual se desea leer el archivo
     */
    private final Charset charset = Charset.forName("UTF-8");
    
    /**
     * Ruta del archivo de texto plano donde se escribirán los datos.
     */
    private String filePath;

    /**
     * Constructor de la clase EscritorArchivoTextoPlano.
     * @param filePath  Es la ruta del archivo de texto plano en el cual se desea escribir.
     */
    public EscritorArchivoTextoPlano(String filePath) {
        this.filePath = filePath;
    }
 
    /**
     * Escribe el contenido de una lista de cadenas en un archivo de texto plano.
     * @param archivo Es la lista de líneas de texto a escribir en el archivo.
     * @throws IOException Se lanza esta excepción cuando ocurre un error al abrir o escribir en el archivo.
     */
    @Override
    public void escribir(ArrayList<String> archivo) throws IOException {
        
        File fichero = new File(this.filePath);
        FileWriter writer = new FileWriter(fichero,false);
        PrintWriter pw = new PrintWriter(writer);
        
        for (String linea : archivo) {
            pw.println(linea);
        }
        
        writer.close();
    
    }
    
}