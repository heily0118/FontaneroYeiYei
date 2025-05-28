/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Heily Yohana Rios Ayala <heilyy.riosa@autonoma.edu.co>
 * @since 20250516
 * @version 1.0.0
 */
public interface Escritor {
    
    /**
     * Escribe el archivo de memoria interna por lineas
     * 
     * @param archivo Es la lista de cadenas que representan el contenido del archivo, línea por línea.
     * @throws java.io.IOException si el archivo no existe
     */
    
    public abstract void escribir(ArrayList<String> archivo) throws IOException ;
    
}
