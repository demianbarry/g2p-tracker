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
import java.util.StringTokenizer;
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
import org.g2p.tracker.model.entities.EstadosEntity;
import org.g2p.tracker.model.entities.ImportanciaEntity;
import org.g2p.tracker.model.entities.PostsEntity;
import org.g2p.tracker.model.entities.PrioridadesEntity;
import org.g2p.tracker.model.entities.TracksEntity;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.zkoss.zul.Listbox;
import org.g2p.tracker.model.models.EstadosModel;
import org.g2p.tracker.model.models.ImportanciaModel;
import org.g2p.tracker.model.models.PostModel;
import org.g2p.tracker.model.models.PrioridadesModel;
import org.g2p.tracker.model.models.TracksModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

/**
 *
 * @author nacho
 */
public class AbmcTracksController extends BaseController {

    protected WebsiteUserModel websiteUserModel = null;
    protected WebsiteUserModel websiteUserModelTrabajador = null;
    protected EstadosModel estadosModel = null;
    protected PrioridadesModel prioridadesModel = null;
    protected ImportanciaModel importanciaModel = null;
    protected TracksModel trackModel = null;
    protected Toolbar trackToolbar;
    protected Button nuevoTrack;
    protected Button guardarTrack;
    protected Button cancelarAltaTrack;
    protected Button editarTrack;
    protected Listbox tracksList;
    protected TracksEntity tracks;
    //Campos del track
    protected Textbox titulo;
    protected Textbox descripcion;
    protected Textbox observaciones;
    protected Combobox propietario;
    protected Combobox trabajador;
    protected Datebox fechaCreacion;
    protected Datebox fechaEstimadaRealizacion;
    protected Datebox fechaLimite;
    protected Datebox fechaRealizacion;
    protected Combobox estado;
    protected Combobox prioridad;
    protected Combobox importancia;
    protected Button addUser;
    //protected Button deleteUser;
    protected Component trackDetail;
    protected Component nuevoTrackView;
    protected Component listTrackView;
    protected boolean nuevoTrackMode;
    protected boolean listMode;
    protected boolean editMode;
    protected boolean trabajadorDos;
    Vbox workersBox;

    public AbmcTracksController() {
        super(true);
        websiteUserModel = new WebsiteUserModel();
        websiteUserModelTrabajador = new WebsiteUserModel();
        prioridadesModel = new PrioridadesModel();
        importanciaModel = new ImportanciaModel();
        estadosModel = new EstadosModel();
        trackModel = new TracksModel();
    }

    public WebsiteUserModel getWebsiteUserModel() {
        return websiteUserModel;
    }

    public void setWebsiteUserModel(WebsiteUserModel websiteUserModel) {
        this.websiteUserModel = websiteUserModel;
    }

    public WebsiteUserModel getWebsiteUserModelTrabajador() {
        return websiteUserModelTrabajador;
    }

    public void setWebsiteUserModelTrabajador(WebsiteUserModel websiteUserModelTrabajador) {
        this.websiteUserModelTrabajador = websiteUserModelTrabajador;
    }

    public EstadosModel getEstadosModel() {
        return estadosModel;
    }

    public void setEstadosModel(EstadosModel estadosModel) {
        this.estadosModel = estadosModel;
    }

    public PrioridadesModel getPrioridadesModel() {
        return prioridadesModel;
    }

    public void setPrioridadesModel(PrioridadesModel prioridadesModel) {
        this.prioridadesModel = prioridadesModel;
    }

    public ImportanciaModel getImportanciaModel() {
        return importanciaModel;
    }

    public void setImportanciaModel(ImportanciaModel importanciaModel) {
        this.importanciaModel = importanciaModel;
    }

    public TracksModel getTrackModel() {
        return trackModel;
    }

    public void setTrackModel(TracksModel trackModel) {
        this.trackModel = trackModel;
    }

    public void onCreate$abmcTracksWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        setListMode(true);
        trabajadorDos = false;

        refresh();
    }

    public boolean isListMode() {
        return listMode;
    }

    protected void setListMode(boolean list) {
        nuevoTrackView.setVisible(!list);
        listTrackView.setVisible(list);
        cancelarAltaTrack.setVisible(!list);
        guardarTrack.setVisible(!list);
        nuevoTrack.setVisible(list);
        editarTrack.setVisible(list);
        listMode = list;
    }

    protected void nuevoTrack() {
        trackModel.setSelected(new TracksEntity());
        titulo.setText("");
        descripcion.setText("");
        observaciones.setText("");
        websiteUserModel.setSelected(null);
        websiteUserModelTrabajador.setSelected(null);
        trabajador.setSelectedIndex(1);
        ((TracksEntity) trackModel.getSelected()).setFechaCreacion(new Date());
        fechaEstimadaRealizacion.setText("");
        fechaLimite.setText("");
        fechaRealizacion.setText("");
        estadosModel.setSelected(estadosModel.getAll().get(0));
        prioridadesModel.setSelected(prioridadesModel.getAll().get(3));
        importanciaModel.setSelected(importanciaModel.getAll().get(3));
    }

    public void onClick$nuevoTrack(Event event) {
        nuevoTrack();
    }

    public void onClick$cancelarAltaTrack(Event event) {
        setListMode(true);
        refresh();
    }

    public void onClick$guardarTrack(Event event) {
        //save into bean
        binder.saveComponent(trackDetail); //reload model to force refresh

        ((TracksEntity) trackModel.getSelected()).setTitulo(titulo.getValue());
        ((TracksEntity) trackModel.getSelected()).setDescripcion(descripcion.getValue());
        ((TracksEntity) trackModel.getSelected()).setObservaciones(observaciones.getValue());
        ((TracksEntity) trackModel.getSelected()).setUserIdOwner((WebsiteUsersEntity) websiteUserModel.getSelected());
        //((TracksEntity)trackModel.getSelected()).setFechaCreacion(fechaCreacion.getValue());
        ((TracksEntity) trackModel.getSelected()).setDeadline(fechaLimite.getValue());
        ((TracksEntity) trackModel.getSelected()).setFechaEstimadaRealizacion(fechaEstimadaRealizacion.getValue());
        ((TracksEntity) trackModel.getSelected()).setFechaRealizacion(fechaRealizacion.getValue());
        ((TracksEntity) trackModel.getSelected()).setEstadoId((EstadosEntity) estadosModel.getSelected());
        ((TracksEntity) trackModel.getSelected()).setPrioridadId((PrioridadesEntity) prioridadesModel.getSelected());
        ((TracksEntity) trackModel.getSelected()).setImportanciaId((ImportanciaEntity) importanciaModel.getSelected());

        try {
            //store into db

            if (trackModel.getSelected().getPK() != null) {
                trackModel.merge(true);
            } else {
                trackModel.persist(true);
            }

            showMessage("El track se guardo correctamente");

        } catch (Exception ex) {
            try {
                showMessage("Ocurrió un error mientras se intentaba crear el track: ", ex);
                trackModel.getUtx().rollback();
            } catch (Exception ex1) {
                showMessage("Ocurrió un error mientras se intentaba hacer rollback de la operacion: ", ex);
            }
        } finally {
            //refresh the rolesList
            setListMode(true);
            refresh();
        }
    }

    /*public void onClick$addUser (Event event) {
    Hbox box = new Hbox();
    Combobox combo = new Combobox();
    Comboitem item = new Comboitem();
    Button button = new Button();

    box.setId("worker"+workersBox.getC);

    box.appendChild(combo);
    workersBox.appendChild(box);

    refresh();
    }*/
    public void onClick$deleteUser(Event event) {
        trabajadorDos = false;
        refresh();
    }

    public void refresh() {
        binder.loadAttribute(trackDetail, "model"); //reload model to force refresh
        binder.loadComponent(trackDetail); //reload visible to force refresh
    }

    public void onClick$editarTrack(ForwardEvent event) {
        setListMode(false);
    }

    public void onSelect$tracksList(Event event) {
    }

    public void onEcualizar$ecualizador(Event event) {
        System.out.println("ECUALIZAR");
    }


    /********************************************
     *
     * Agregado por Cristian
     *
     ********************************************/

    protected Textbox tbComentario;     // ingreso de comentario
    protected Vbox vboxShowComments;    // caja que muestra los comentarios
    protected Rows filas;               // lista de comentarios
    protected FCKeditor ingresoComentario;  // editor para escribir un comentario
    protected Checkbox descendiente;    // indica el modo de ordenamiento
    protected Label track;              // guarda la pk del track actual
    //protected String tituloTrack;
    protected Vbox postBox;


    public void onClick$btnSubmit(){

        guardarComentario();
        mostrarComentarios();
    }

    private void guardarComentario(){
        String comentario = ingresoComentario.getValue();
        PostsEntity post = new PostsEntity();
        //int pk = Integer.parseInt(track.getValue());
        //String titulo = track.getValue();

        int pk = Integer.parseInt(trackModel.getSelected().getPK().toString());

//        String salida = procesarCadena(comentario);
//        System.out.println("############# cadena procesada: " + salida );

        //post.setContenido(procesarCadena(comentario));
        post.setContenido(comentario);
        post.setFechaCreacion(new Date());
        post.setUserId(getUserFromSession());
        post.setTrackId((TracksEntity) BaseModel.findEntityByPK(pk, TracksEntity.class));
        try {

            PostModel.createEntity(post, true);

            enviarEmail();

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

    private void enviarEmail() throws MessagingException{
    String emailRemitente = "laykondash@gmail.com";
    String contraseniaRemitente = "";

    //int pk = Integer.parseInt(track.getValue());
    int pk = Integer.parseInt(trackModel.getSelected().getPK().toString());
    TracksEntity trackActual = (TracksEntity) BaseModel.findEntityByPK(pk, TracksEntity.class);

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

         Session   sesion = Session.getDefaultInstance(conf);
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
            t.connect(emailRemitente,contraseniaRemitente);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();


        } catch (AddressException ex) {
            Logger.getLogger(DetallesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarComentarios(){
        filas.getChildren().clear();
        // se ordenara de acuerdo a la eleccion del usuario
        // String order = (descendiente.isChecked()) ? "DESC" : "ASC";
        String consulta = (descendiente.isChecked()) ? "PostsEntity.findByTrackDesc" : "PostsEntity.findByTrackAsc";
        // busca el track asociado
        //int pk = Integer.parseInt(track.getValue());
        int pk = Integer.parseInt(trackModel.getSelected().getPK().toString());
    TracksEntity trackActual = (TracksEntity) BaseModel.findEntityByPK(pk, TracksEntity.class);
        String buscarPor = trackActual.getTitulo();
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

    private String procesarCadena(String cadena){
        String salida, elemento;
        StringTokenizer tokens = new StringTokenizer(cadena,"$");

        //salida = tokens.nextToken();
        salida = cadena;

        while (tokens.hasMoreTokens()){
            elemento = tokens.nextToken();
            Hashtable<String,String> parametros = new Hashtable<String, String>();
            
            parametros.put("titulo", elemento);
            
            List<BaseEntity> entities = BaseModel.findEntities("TracksEntity.findByTitulo", parametros);
            
            if (entities.size() > 0){
                TracksEntity trackActual = (TracksEntity) entities.get(0);
                int pk = ((Integer) trackActual.getPK()).intValue();

                salida += "<A href=\"#"+ pk +"\"></A>";
            }
            

        }

        return salida;
    }

    public void setTitulo(String track){
        System.out.println("################ El titulo es " + track + " ####################");
        this.track.setValue(track);
    }
}
