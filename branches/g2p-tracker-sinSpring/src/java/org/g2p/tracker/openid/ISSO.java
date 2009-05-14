/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.openid;

import javax.servlet.http.HttpServletRequest;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;

/**
 * Especifica el comportamiento estandar que todos los objetos de autentificación deben tener
 * @author Cristian Pacheco
 */
public interface ISSO {

    /**
     * Verifica que todo este en regla y registra al usuario en el sistema
     * @param httpReq
     * @return los datos del usuario autentificado
     * @throws modelo.NoAutentificadoException
     */
    public WebsiteUsersEntity login(HttpServletRequest httpReq) throws NoAutentificadoException;

    public boolean isUserLogged(HttpServletRequest req, WebsiteUsersEntity user);
}
