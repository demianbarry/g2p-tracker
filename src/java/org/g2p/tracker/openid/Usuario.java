/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.openid;

import java.util.Enumeration;
import javax.servlet.http.HttpSession;

/**
 * Clase que encapsula los datos del usuario obtenidos del proveedor
 *
 * @author Cristian Pacheco
 */
public class Usuario implements IUsuario{


    private String nombre;
    private String apellido;
    private Enumeration<String> emails;
    private String ID;

    private HttpSession sesion;
    private boolean conectado;

    public Usuario(){

        conectado = true;
    }

    /**
     * Finaliza la sesion del usuario en el sistema
     */
    public void desconectar() {
        // TODO eliminar sesion provider?
        // se crea un nuevo objeto OpenID para logout?
        sesion.invalidate();

        conectado = false;
    }

    /**
     * Averigua el nombre del usuario
     * 
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Averigua el nombre del usuario
     *
     * @return el apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Averigua los emails del usuario
     *
     * @return los emails asociados al usuario
     */
    public Enumeration<String> getEmails() {
        return emails;
    }

    /**
     * Averigua si el usuario ha finalizado sesion o no
     *
     * @return false si ha finalizado sesión, true en cualquier otro caso
     */
    public boolean isConectado() {
        return conectado;
    }

    /**
     * Averigua el ID del usuario
     *
     * @return el id que identifica al usuario
     */
    public String getID() {
        return ID;
    }

    /**
     * Especifica el ID del usuario
     *
     * @param ID id del usuario
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Especifica el apellido del usuario
     *
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Especifica los emails asociados al usuario
     *
     * @param emails
     */
    public void setEmails(Enumeration<String> emails) {
        this.emails = emails;
    }

    /**
     * Especifica el nombre del usuario
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Especifica la sesión del usuario
     *
     * @param sesion
     */
    public void setSession(HttpSession sesion){
        this.sesion = sesion;
    }

    /**
     * Libera todos los recursos usados
     */
    @Override
    public void finalize(){
        desconectar();
    }
}

