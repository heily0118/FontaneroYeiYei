/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.fontaneroyeiyei.exceptions;

/**
 *
 * @author Heily
 */
public class HerramientaInvalidaException extends RuntimeException{
      public HerramientaInvalidaException() {
        super("Herramienta inválida: estás utilizando una herramienta incorrecta.");
    }
       public HerramientaInvalidaException(String message) {
        super(message);
    }
}
