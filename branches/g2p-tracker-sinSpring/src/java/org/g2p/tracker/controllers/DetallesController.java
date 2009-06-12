/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import com.jhlabs.vecmath.Vector3f;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.PostsEntity;
import org.g2p.tracker.model.entities.TracksEntity;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.model.models.PostModel;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

/**
 *
 * @author kristian
 */
public class DetallesController extends BaseController implements AfterCompose{
    protected Textbox tbComentario;
    //protected Textbox tbShow;
    protected Vbox vboxShowComments;
    protected Paging pgPaginado;
    protected Rows filas;
    protected FCKeditor ingresoComentario;
    protected Checkbox descendiente;

    public DetallesController(){
        super(true);
    }
    
    public void onCreate$detallesWin(Event evento){
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);
        //binder.loadComponent(vistasDetail);

        //getHttpRequest().getSession().getServletContext().getResourcePaths("/")

    }

    public void onClick$btnSubmit(){
//        Textbox tbShow = new Textbox();
//        tbShow.setReadonly(true);
//        tbShow.setMultiline(true);

        //tbShow.setText(tbComentario.getText());
        //vboxShowComments.appendChild(tbShow);
        //pgPaginado.smartUpdate(SSO, SSO);

//        FCKeditor tbShow = new FCKeditor();
//        tbShow.setValue(ingresoComentario.getValue());
//
//        Row comentario = new Row();
//        comentario.appendChild(tbShow);
//
//        List<FCKeditor> hijos = filas.getChildren();
//
//        Collections.sort(hijos,new Comparator() {
//
//            @Override
//            public int compare(Object arg0, Object arg1) {
//                String cadena = ((FCKeditor) arg0).getValue();
//                String otraCadena = ((FCKeditor) arg1).getValue();
//
//                return cadena.compareTo(otraCadena);
//            }
//        });
//
//        filas.appendChild(comentario);

        guardarComentario();
        mostrarComentarios();
    }

    private void guardarComentario(){
        String comentario = ingresoComentario.getValue();
        PostsEntity post = new PostsEntity();
        final int ELIMINAR = 10;

        post.setContenido(comentario);
        post.setFechaCreacion(new Date());
        post.setUserId(getUserFromSession());
        post.setTrackId((TracksEntity) BaseModel.findEntityByPK(1, TracksEntity.class));
        try {

            PostModel.createEntity(post, true);

            //enviarEmail();

        } catch (RollbackFailureException ex) {
            showMessage("No se pudo guardar su comentario", ex);
        } catch (NamingException ex) {
            showMessage("Error de nombrado", ex);
        } catch (IllegalStateException ex) {
            showMessage("Estado ilegal", ex);
        } catch (SecurityException ex) {
            showMessage("Se ha violado la seguridad", ex);
        } catch (SystemException ex) {
            showMessage("Error del sistema", ex);
        } catch (Exception ex) {
            showMessage("Sucedio un error desconocido", ex);
        }
    }

    private void enviarEmail() throws MessagingException{
        Properties conf = new Properties();
        String contenido;
        conf.put("mail.smtp.host", "smtp.gmail.com");
        final int ELIMINAR = 10;

        // cambiar por el track actual!!!!!!!!!!!!
            TracksEntity trackActual = (TracksEntity) BaseModel.findEntityByPK(1, TracksEntity.class);

        Message mensaje = new MimeMessage(Session.getDefaultInstance(conf));

        contenido = getUserNameFromSession() + " ha comentado el track " + trackActual.getTitulo();

        try {
            InternetAddress de = new InternetAddress("laykondash@gmail.com");

            InternetAddress a = new InternetAddress(trackActual.getUserIdOwner().getEmail());

            mensaje.setFrom(de);
            mensaje.setRecipient(Message.RecipientType.TO, a);
            mensaje.setSubject("Se ha comentado el track " + trackActual.getTitulo());
            mensaje.setContent(contenido, "text/plain");
            mensaje.setSentDate(new Date());

            Transport.send(mensaje);


        } catch (AddressException ex) {
            Logger.getLogger(DetallesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarComentarios(){
        // se ordenara de acuerdo a la eleccion del usuario
        // String order = (descendiente.isChecked()) ? "DESC" : "ASC";
        String consulta = (descendiente.isChecked()) ? "PostsEntity.findByTrackDesc" : "PostsEntity.findByTrackAsc";
        // busca el track asociado
        String buscarPor = ( (TracksEntity) BaseModel.findEntityByPK(1, TracksEntity.class)).getTitulo(); // CAMBIAR1!!!!
        Hashtable<String,String> parametros = new Hashtable<String, String>();

        //parametros.put("direccion", order);
        parametros.put("titulo", buscarPor);

        List<BaseEntity> comentarios = PostModel.findEntities(consulta, parametros);

        Iterator<BaseEntity> itComentarios = comentarios.iterator();

        FCKeditor visualizadorActual = null;
        Row fila = null;

        while (itComentarios.hasNext()){
            PostsEntity postActual = (PostsEntity) itComentarios.next();
            visualizadorActual = new FCKeditor();
            fila = new Row();

            visualizadorActual.setCustomConfigurationsPath("/config.js");
            visualizadorActual.setToolbarSet("visualizar");

            visualizadorActual.setValue(postActual.getContenido());
            fila.appendChild(visualizadorActual);
            filas.appendChild(fila);
        }

    }
}
