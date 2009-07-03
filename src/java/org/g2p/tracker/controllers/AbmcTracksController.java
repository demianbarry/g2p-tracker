/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import org.g2p.tracker.model.models.PrioridadesModel;
import org.g2p.tracker.model.models.TracksModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

/**
 *
 * @author nacho
 */
public class AbmcTracksController extends BaseController {

    protected WebsiteUserModel websiteUserModel = null;
    protected WebsiteUserModel workersModel;
    protected WebsiteUsersEntity workerSelected;
    protected EstadosModel estadosModel = null;
    protected PrioridadesModel prioridadesModel = null;
    protected ImportanciaModel importanciaModel = null;
    protected TracksModel trackModel = null;
    protected Button nuevoTrack;
    protected Button guardarTrack;
    protected Button cancelarAltaTrack;
    protected Button editarTrack;
    protected Listbox tracksList;
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
    protected Button deleteUser;
    protected Listbox workersList;
    protected Component trackDetail;
    protected Component nuevoTrackView;
    protected Component listTrackView;
    protected boolean nuevoTrackMode;
    protected boolean listMode;
    Vbox workersBox;
    Hbox ecualizadorBox;
    Div tituloDiv;

    public AbmcTracksController() {
        super(true);
        websiteUserModel = new WebsiteUserModel();
        workersModel = new WebsiteUserModel();
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

    public boolean isListMode() {
        return listMode;
    }

    protected void setListMode(boolean list) {

        if (!list) {
            tituloDiv.setVisible(!(trackModel.getSelected() != null && trackModel.getSelected().getTrackId() != null));
        }

        nuevoTrackView.setVisible(!list);
        listTrackView.setVisible(list);
        cancelarAltaTrack.setVisible(!list);
        guardarTrack.setVisible(!list);
        nuevoTrack.setVisible(list);
        editarTrack.setVisible(list);
        ecualizadorBox.setVisible(list);
        listMode = list;
    }

    public WebsiteUsersEntity getWorkerSelected() {
        return workerSelected;
    }

    public void setWorkerSelected(WebsiteUsersEntity workerSelected) {
        this.workerSelected = workerSelected;
    }

    public WebsiteUserModel getWorkersModel() {
        return workersModel;
    }

    public void setWorkersModel(WebsiteUserModel workersModel) {
        this.workersModel = workersModel;
    }

    public void onCreate$abmcTracksWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        setListMode(true);

        refresh();
    }

    protected void nuevoTrack() {
        trackModel.setSelected(new TracksEntity());
        trackModel.getSelected().setFechaCreacion(new Date());
        setListMode(false);
        refresh();
        binder.loadComponent(trackDetail);
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
        TracksEntity track = trackModel.getSelected();
        WebsiteUsersEntity userOwner = websiteUserModel.getSelected();

        track.setTitulo(titulo.getValue());
        track.setDescripcion(descripcion.getValue());
        track.setObservaciones(observaciones.getValue());
        binder.saveComponent(getFellow("ownerWorkersGrid"));
        track.setUserIdOwner(userOwner);
        track.setDeadline(fechaLimite.getValue());
        track.setFechaEstimadaRealizacion(fechaEstimadaRealizacion.getValue());
        track.setFechaRealizacion(fechaRealizacion.getValue());
        binder.saveComponent(getFellow("estadoPrioImport"));
        track.setEstadoId((EstadosEntity) estadosModel.getSelected());
        track.setPrioridadId((PrioridadesEntity) prioridadesModel.getSelected());
        track.setImportanciaId((ImportanciaEntity) importanciaModel.getSelected());

        try {
            //store into db
            trackModel.beginTransaction();

            if (track.getPK() != null) {
                trackModel.edit(track, false);
            } else {

                trackModel.create(track, false);
            }

            /*workersModel.mergeAll(false);
            workersModel.mergeFiltered(false);*/

            trackModel.commitTransaction();
            showMessage("El track se guardo correctamente");

        } catch (Exception ex) {
            try {
                showMessage("Ocurrió un error mientras se intentaba crear el track: " + ex.getClass(), ex);
                trackModel.rollbackTransaction();
            } catch (Exception ex1) {
                showMessage("Ocurrió un error mientras se intentaba hacer rollback de la operacion: " + ex1.getClass(), ex);
            }
        } finally {
            //refresh the rolesList
            trackModel.refreshAll();
            binder.loadAttribute(tracksList, "model");
            setListMode(true);
            trackModel.refreshAll();
            refresh();
        }
    }

    public void onClick$addUser(Event event) {
        if (workersModel.getSelected() != null) {
            try {
                trackModel.getSelected().addWorker(workersModel.getSelected());
                refresh();
            } catch (IllegalStateException ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onClick$deleteUser(Event event) {
        if (workerSelected != null) {
            try {
                trackModel.getSelected().removeWorker(workerSelected);
                refresh();
            } catch (IllegalStateException ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void refresh() {
        if (!isListMode()) {

            workersModel.filter(trackModel.getSelected().getWebsiteUsersEntityCollection());

            websiteUserModel.setSelected(trackModel.getSelected().getUserIdOwner());
            estadosModel.setSelected(trackModel.getSelected().getEstadoId());
            prioridadesModel.setSelected(trackModel.getSelected().getPrioridadId());
            importanciaModel.setSelected(trackModel.getSelected().getImportanciaId());

            if (workersModel.getAll().size() > 0) {
                deleteUser.setVisible(true);
            } else {
                deleteUser.setVisible(false);
            }

            if (workersModel.getFiltered().size() > 0) {
                addUser.setVisible(true);
            } else {
                addUser.setVisible(false);
            }
        }
        binder.loadAttribute(trackDetail, "model"); //reload model to force refresh
        binder.loadComponent(trackDetail); //reload visible to force refresh
        binder.loadAttribute(workersList, "model");
        binder.loadAttribute(trabajador, "model");
        binder.loadAttribute(propietario, "selectedItem");
        binder.loadAttribute(getFellow("postsGrid"), "model");
    }

    public void onClick$editarTrack(ForwardEvent event) {
        if (trackModel.getSelected() != null) {
            setListMode(false);
            refresh();
        } else {
            showMessage("Debe seleccionar un track para editarlo.");
        }
    }

    public void onSelect$tracksList(Event event) {
        binder.saveAttribute(tracksList, "selectedItem");
    }

    public void onEcualizar$ecualizador(ForwardEvent event) {

        if (event.getOrigin().getData() instanceof ArrayList) {
            trackModel.filter((List) event.getOrigin().getData());
        }

        binder.loadAttribute(tracksList, "model");
    }

    public void onOpen$groupbox(Event event) {
        OpenEvent oEvent = (OpenEvent) ((ForwardEvent) event).getOrigin();
        Groupbox gb = (Groupbox) oEvent.getTarget();
        Component comp = null;
        Iterator it = getFellows().iterator();
        Boolean flag = true;
        while (it.hasNext() && flag) {
            comp = (Component) it.next();
            if (comp instanceof Groupbox && !gb.equals(comp)) {
                if (((Groupbox) comp).isOpen()) {
                    ((Groupbox) comp).setOpen(false);
                    flag = false;
                }
            }
        }
    }
    protected Textbox tbComentario;
    protected Vbox vboxShowComments;
    protected Paging pgPaginado;
    protected Rows filas;
    protected FCKeditor ingresoComentario;
    protected Checkbox descendiente;
    protected ZScript script;

    public void onClick$submitComment(Event event) {
        guardarComentario();
        refresh();
    }

    private void guardarComentario() {
        TracksEntity track = trackModel.getSelected();
        String comentario = ingresoComentario.getValue();
        if (track != null && comentario != null) {
            try {
                PostsEntity post = new PostsEntity();
                post.setContenido(comentario);
                post.setFechaCreacion(new Date());
                post.setUserId(getUserFromSession());
                track.addPost(post);
                BaseModel.createEntity(post, true);
                enviarEmail();
                ingresoComentario.setValue(null);
            } catch (Exception ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void enviarEmail() throws MessagingException {
        String emailRemitente = "g2patagonico@gmail.com";
        String contraseniaRemitente = "gedospee";

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
        TracksEntity trackActual = trackModel.getSelected();

        Session sesion = Session.getDefaultInstance(conf);
        Message mensaje = new MimeMessage(sesion);

        contenido = getUserNameFromSession() + " ha comentado el track " + trackActual.getTitulo();

        try {
            InternetAddress de = new InternetAddress(emailRemitente);

            InternetAddress a[] = new InternetAddress[trackActual.getWebsiteUsersEntityCollection().size()+1];
            a[0] = (new InternetAddress((trackActual.getUserIdOwner().getEmail())));

            Iterator worker = trackActual.getWebsiteUsersEntityCollection().iterator();

            String userEmail = null;
            int i = 1;
            while (i < a.length) {
                userEmail = ((WebsiteUsersEntity) worker.next()).getEmail();
                if (userEmail != null && userEmail.length() > 0) {
                    a[i] = (new InternetAddress(userEmail));
                }
                i++;
            }

            mensaje.setFrom(de);
            mensaje.setRecipients(Message.RecipientType.TO, a);
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

    private String procesarCadena(String cadena) {
        String salida, elemento;
        StringTokenizer tokens = new StringTokenizer(cadena, "$");

        //salida = tokens.nextToken();
        salida = cadena;

        while (tokens.hasMoreTokens()) {
            elemento = tokens.nextToken();
            Hashtable<String, String> parametros = new Hashtable<String, String>();

            parametros.put("titulo", elemento);

            List<BaseEntity> entities = BaseModel.findEntities("TracksEntity.findByTitulo", parametros);

            if (entities.size() > 0) {
                TracksEntity trackActual = (TracksEntity) entities.get(0);
                int pk = ((Integer) trackActual.getPK()).intValue();

                salida += "<A href=\"#" + pk + "\"></A>";
            }


        }

        return salida;
    }
}
