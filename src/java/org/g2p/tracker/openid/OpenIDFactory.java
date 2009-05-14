/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.openid;

import org.openid4java.consumer.ConsumerException;

/**
 * Fábrica de objetos de autentificación que implementan el protocolo OpenID
 * @author Cristian Pacheco
 */
public class OpenIDFactory extends SSOFactory{

    public OpenIDFactory(){
        super();
    }

    /**
     * Crea una nueva instancia de un objeto de autentificación
     * @return el objeto de autentificación creado
     * @throws org.openid4java.consumer.ConsumerException
     */
    @Override
    public ISSO create() throws ConsumerException {
        return new OpenID();
    }
}
