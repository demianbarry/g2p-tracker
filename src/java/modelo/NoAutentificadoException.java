/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * Excepción que se da cuando el usuario no se ha autentificado
 * 
 * @author Cristian Pacheco
 */
public class NoAutentificadoException extends Exception{

    public NoAutentificadoException(String mensaje){
        super(mensaje);
    }
}
