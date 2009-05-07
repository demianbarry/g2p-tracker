/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Variables globales
 *
 * Podría evolucionar hacia una fachada o algo parecido (o podría desaparecer)
 *
 * @author Cristian Pacheco
 */
public class Global {
    // clase singleton
    private static Global instance = new Global();

    // usuarios conectados actualmente (podría desaparecer)
    private Vector<IUsuario> usuarios;

    // objeto de autentificacion en comun entre MainServ y BienvenidoServ
    private ISSO sso;

    private Global(){
        unsetSso();
        usuarios = new Vector<IUsuario>();
    }

    /**
     * Clase Singleton
     * @return la referencia al único objeto existente
     */
    public static Global getInstance(){
        return instance;
    }

    /**
     * Recupera el objeto de autentificación usado
     *
     * @return el objeto de autentificacion
     */
    public ISSO getSso() {
        return sso;
    }

    /**
     * Especifica el objeto de autentificacion
     * @param sso objeto de autentificacion
     */
    public void setSso(ISSO sso){
        this.sso = sso;
    }

    /**
     * Agrega un nuevo usuario conectado
     * @param usuario el usuario autentificado que esta online
     */
    public void addUsuario(IUsuario usuario){
        usuarios.add(usuario);
    }

    /**
     * Recupera los usuarios conectados
     * 
     * @return los usuarios conectados actualmente
     */
    public Enumeration getUsuarios(){
        return usuarios.elements();
    }

    /**
     * Desvincula el objeto de autentificacion
     */
    public void unsetSso(){
        sso = null;
    }
}
