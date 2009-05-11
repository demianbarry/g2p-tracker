/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.openid;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openid4java.discovery.DiscoveryException;

/**
 *Especifica la interfaz de todos los objetos de tipo OpenID
 * 
 * Los objetos OpenID son son un tipo particular Single Sign-on
 *
 * @author Cristian Pacheco
 */
public interface IOpenID extends ISSO{
    /**
     * Redirige al usuario al sitio de autentificacion del proveedor
     *
     * @param userSuppliedString url de donde buscar por el servidor de autentificacion
     * @param httpReq
     * @param httpResp
     * @throws java.io.IOException
     * @throws org.openid4java.discovery.DiscoveryException
     */
    public void solicitarAutentificacion(String userSuppliedString,
                              HttpServletRequest httpReq,
                              HttpServletResponse httpResp)
            throws IOException, DiscoveryException;
}
