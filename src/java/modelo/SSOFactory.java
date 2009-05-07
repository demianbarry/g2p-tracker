/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import org.openid4java.consumer.ConsumerException;

/**
 * Fábrica de objetos que implementan algún protocolo de Single Sign-on
 *
 * @author Cristian Pacheco
 */
public abstract class SSOFactory {

    /**
     *  Constructor de la clase
     */
    public SSOFactory(){
        
    }

    /**
     * Crea una instancia de un objeto de autentificacion de Single Sign-on
     *
     * @return el objeto que gestiona la autentificacion
     */
    public abstract ISSO create() throws ConsumerException;

}
