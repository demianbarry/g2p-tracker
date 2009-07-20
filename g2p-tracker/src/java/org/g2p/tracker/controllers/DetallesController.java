/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
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
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.model.models.PostModel;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

/**
 *
 * @author kristian
 */
public class DetallesController extends BaseController implements AfterCompose {

    protected Textbox tbComentario;
    protected Vbox vboxShowComments;
    protected Paging pgPaginado;
    protected Rows filas;
    protected FCKeditor ingresoComentario;
    protected Checkbox descendiente;
    protected ZScript script;

    public DetallesController() {
        super(true);        
    }

    public void onCreate$detallesWin(Event evento) {
        // Obtengo el DataBinder que instancia la página
    }

    public void onClick$btnSubmit() {

        guardarComentario();
        mostrarComentarios();
//        String comentario = ingresoComentario.getValue();
//        Html visor = new Html();
//
//        visor.setContent(comentario);

    }

    private void guardarComentario() {
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
        } catch (MessagingException ex) {
            showMessage("Ha sucedido un error al intentar enviar el email", ex);
        } catch (Exception ex) {
            showMessage("Sucedio un error desconocido", ex);
        }
    }

    private void enviarEmail() throws MessagingException {
        String emailRemitente = "laykondash@gmail.com";
        String contraseniaRemitente = "";

        Properties conf = new Properties();
        String contenido;

        // nombre del host de correo
        conf.put("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        conf.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        conf.setProperty("mail.smtp.port", "587");

        // Nombre del usuario
        conf.setProperty("mail.smtp.user", emailRemitente);

        // Si requiere o no usuario y password para conectarse.
        conf.setProperty("mail.smtp.auth", "true");

        // cambiar por el track actual!!!!!!!!!!!!
        TracksEntity trackActual = (TracksEntity) BaseModel.findEntityByPK(1, TracksEntity.class);

        Session sesion = Session.getDefaultInstance(conf);
        Message mensaje = new MimeMessage(sesion);

        contenido = getUserNameFromSession() + " ha comentado el track " + trackActual.getTitulo();

        try {
            InternetAddress de = new InternetAddress(emailRemitente);

            InternetAddress a = new InternetAddress(trackActual.getUserIdOwner().getEmail());

            mensaje.setFrom(de);
            mensaje.setRecipient(Message.RecipientType.TO, a);
            mensaje.setSubject("Se ha comentado el track " + trackActual.getTitulo());
            mensaje.setContent(contenido, "text/plain");
            mensaje.setSentDate(new Date());

            Transport t = sesion.getTransport("smtp");
            t.connect(emailRemitente, contraseniaRemitente);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();


        } catch (AddressException ex) {
            Logger.getLogger(DetallesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void afterCompose() {
        super.afterCompose();
        mostrarComentarios();
    }



    private void mostrarComentarios() {
        if(filas.getChildren()!= null)
        filas.getChildren().clear();
        // se ordenara de acuerdo a la eleccion del usuario
        // String order = (descendiente.isChecked()) ? "DESC" : "ASC";
        String consulta = (descendiente.isChecked()) ? "PostsEntity.findByTrackDesc" : "PostsEntity.findByTrackAsc";
        // busca el track asociado
        String buscarPor = ((TracksEntity) BaseModel.findEntityByPK(1, TracksEntity.class)).getTitulo(); // CAMBIAR1!!!!
        Hashtable<String, String> parametros = new Hashtable<String, String>();

        //parametros.put("direccion", order);
        parametros.put("titulo", buscarPor);

        List<BaseEntity> comentarios = PostModel.findEntities(consulta, parametros);

        Iterator<BaseEntity> itComentarios = comentarios.iterator();

        FCKeditor visualizadorActual = null;
        Row fila = null;

        while (itComentarios.hasNext()) {
            PostsEntity postActual = (PostsEntity) itComentarios.next();
            visualizadorActual = new FCKeditor();
            fila = new Row();
            Vbox caja = new Vbox();
            String fecha = postActual.getFechaCreacion().getDay() + "/" + postActual.getFechaCreacion().getMonth() + "/" + postActual.getFechaCreacion().getYear();
            Label lblAutor = new Label(postActual.getUserId().getLoginName());
            Label lblFecha = new Label(fecha);

            visualizadorActual.setCustomConfigurationsPath("/config.js");
            visualizadorActual.setToolbarSet("visualizar");

            visualizadorActual.setValue(postActual.getContenido());

            caja.appendChild(visualizadorActual);
            caja.appendChild(lblAutor);
            caja.appendChild(lblFecha);

            //fila.appendChild(visualizadorActual);
//            filas.appendChild(fila);
//            filas.appendChild(lblAutor);
//            filas.appendChild(lblFecha);

            fila.appendChild(caja);
            filas.appendChild(fila);
        }

    }
}
