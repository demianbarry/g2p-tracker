/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Enumeration;



/**
 * Encapsula los datos del usuario y su estado
 * @author Cristian Pacheco
 */
public interface IUsuario {
    /**
     * Desconecta al usuario de la aplicación
     */
    public void desconectar();

    /**
     * Chequea si esta conectado o no.
     *
     * @return true si esta conectado o false sino lo esta
     */
    public boolean isConectado();

    /**
     * Averigua el ID del usuario
     * 
     * @return el id esociado al usuario
     */
    public String getID();

    /**
     * Averigua el nombre del usuario
     *
     * @return el nombre del usuario
     */
    public String getNombre();

    /**
     * Averigua el apellido del usuario
     *
     * @return el apellido del usuario
     */
    public String getApellido();

    /**
     * Averigua los emails del usuario
     * 
     * @return los emails del usuario
     */
    public Enumeration<String> getEmails();
}
