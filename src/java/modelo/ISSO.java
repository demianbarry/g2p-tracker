/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import javax.servlet.http.HttpServletRequest;


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
    public IUsuario login(HttpServletRequest httpReq) throws NoAutentificadoException;
}
