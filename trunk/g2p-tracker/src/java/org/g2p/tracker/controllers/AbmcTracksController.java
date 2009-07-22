/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import org.g2p.tracker.model.entities.AttachmentEntity;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.DocumentosEntity;
import org.g2p.tracker.model.entities.EstadosEntity;
import org.g2p.tracker.model.entities.ImportanciaEntity;
import org.g2p.tracker.model.entities.PostsEntity;
import org.g2p.tracker.model.entities.PrioridadesEntity;
import org.g2p.tracker.model.entities.StickyNotesEntity;
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
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Fileupload;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Popup;
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
    protected StickyNotesEntity sticky;
    protected Button nuevoTrack;
    protected Button guardarTrack;
    protected Button cancelarAltaTrack;
    protected Button editarTrack;
    protected Listbox tracksList;
    protected Listbox stickyList;
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
    protected Grid adjuntos;
    Vbox workersBox;
    Hbox ecualizadorBox;
    Div tituloDiv;
    protected Fileupload subir;
    protected Filedownload descargar;
    protected Textbox tituloDoc;
    protected Textbox descripcionDoc;
    protected Checkbox envioEmailAdjuntos;
    protected Checkbox envioEmailPosts;

    public AbmcTracksController() {
        super(true);
        try {
            websiteUserModel = new WebsiteUserModel();
            workersModel = new WebsiteUserModel();
            prioridadesModel = new PrioridadesModel();
            importanciaModel = new ImportanciaModel();
            estadosModel = new EstadosModel();
            trackModel = new TracksModel();
            trackModel.setAll(BaseModel.findEntitiesByParams("TracksEntity.findByUser", "user", getUserFromSession()));
        } catch (Exception ex) {
            Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public StickyNotesEntity getSticky() {
        return sticky;
    }

    public void setSticky(StickyNotesEntity sticky) {
        this.sticky = sticky;
    }

    public void onCreate$abmcTracksWin(Event event) {
        try {
            // Obtengo el DataBinder que instancia la página
            binder = (DataBinder) getVariable("binder", true);
            setListMode(true);
            refresh();
        } catch (Exception ex) {
            Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        binder.saveComponent(getFellow("ownerWorkersGrid"));
        track.setUserIdOwner(userOwner);        
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
            try {
                //refresh the rolesList
                trackModel.setAll(BaseModel.findEntitiesByParams("TracksEntity.findByUser", "user", getUserFromSession()));
                binder.loadAttribute(tracksList, "model");
                setListMode(true);
                refresh();
            } catch (Exception ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public void onClick$editarTrack(ForwardEvent event) {
        if (trackModel.getSelected() != null) {
            setListMode(false);
            refresh();
        } else {
            showMessage("Debe seleccionar un track para editarlo.");
        }
    }

    public void onEcualizar$ecualizador(ForwardEvent event) {
        if (event.getOrigin().getData() instanceof ArrayList) {
            trackModel.filter((List) event.getOrigin().getData());
        }
        binder.loadComponent(tracksList);
    }

    public void onOpen$groupbox(Event event) {
        OpenEvent oEvent = (OpenEvent) ((ForwardEvent) event).getOrigin();
        Groupbox gb = (Groupbox) oEvent.getTarget();
        for (Component comp : (Collection<Component>) getFellows()) {
            if (comp instanceof Groupbox && !gb.equals(comp)) {
                if (((Groupbox) comp).isOpen()) {
                    ((Groupbox) comp).setOpen(false);
                }
            }
        }
    }

    public void onDrop$trackEdit(ForwardEvent event) {
        if (trackModel.getSelected() != null) {
            try {
                DropEvent dEvent = (DropEvent) event.getOrigin();

                Component comp = dEvent.getDragged();

                Textbox titulo = (Textbox) comp.getFellow("stickyTitulo");
                Textbox contenido = (Textbox) comp.getFellow("stickyContenido");

                StickyNotesEntity sticky = new StickyNotesEntity();
                sticky.setTitulo(titulo.getValue());
                sticky.setContenido(contenido.getValue());
                sticky.setTrackId(trackModel.getSelected());

                BaseModel.createEntity(sticky, true);

                trackModel.getSelected().getStickyNotesEntityCollection().add(sticky);
                binder.loadComponent(getFellow("stickyPopup"));
                ((Popup) getFellow("stickyPopup")).open(tracksList.getSelectedItem());
            } catch (Exception ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void onDrop$trackItem(ForwardEvent event) {
        DropEvent dEvent = (DropEvent) event.getOrigin();
        tracksList.setSelectedItem((Listitem) dEvent.getTarget());
        onDrop$trackEdit(event);
    }

    public void onClick$stickyDeleteButton(Event event) {
        if (sticky != null) {
            try {
                BaseModel.deleteEntity(sticky, true);
                trackModel.getSelected().getStickyNotesEntityCollection().remove(sticky);
                binder.loadComponent(stickyList);
            } catch (Exception ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onCheck$stickyCheck(ForwardEvent event) {
        CheckEvent checkEvent = (CheckEvent) event.getOrigin();
        stickyList.setSelectedItem((Listitem) checkEvent.getTarget().getParent().getParent());
        binder.saveComponent(stickyList);
        if (sticky != null) {
            try {
                trackModel.getSelected().getStickyNotesEntityCollection().remove(sticky);
                sticky.setLeido(checkEvent.isChecked());
                BaseModel.editEntity(sticky, true);
                trackModel.getSelected().getStickyNotesEntityCollection().add(sticky);
                binder.loadComponent(stickyList);
            } catch (Exception ex) {
                Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onSelect$tracksList(ForwardEvent event) {
        binder.saveAttribute(tracksList, "selectedItem");
        if (trackModel.getSelected() != null && trackModel.getSelected().getStickyNotesEntityCollection().size() > 0) {
            ((Popup) getFellow("stickyPopup")).open(tracksList.getSelectedItem());
        }
    }

    protected void nuevoTrack() {
        trackModel.setSelected(new TracksEntity());
        trackModel.getSelected().setFechaCreacion(new Date());
        setListMode(false);
        refresh();
        binder.loadComponent(trackDetail);
    }

    public void refresh() {
        if (!isListMode()) {
            TracksEntity track = trackModel.getSelected();
            websiteUserModel.setSelected(track.getUserIdOwner());
            estadosModel.setSelected(track.getEstadoId());
            prioridadesModel.setSelected(track.getPrioridadId());
            importanciaModel.setSelected(track.getImportanciaId());

            System.out.println("WORKERS: "+track.getWebsiteUsersEntityCollection());
            if (track.getWebsiteUsersEntityCollection() != null) {
                workersModel.filter(track.getWebsiteUsersEntityCollection());
            } else {
                workersModel.filter(new ArrayList());
            }

            deleteUser.setVisible(workersModel.getAll().size() > 0);
            addUser.setVisible(workersModel.getFiltered().size() > 0);
        }
        binder.loadComponent(trackDetail); //reload visible to force refresh
        binder.loadAttribute(workersList, "model");
        binder.loadAttribute(trabajador, "model");
        binder.loadAttribute(propietario, "selectedItem");
        binder.loadAttribute(getFellow("postsGrid"), "model");
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
                ingresoComentario.setValue(null);
                if(envioEmailAdjuntos.isChecked())
                    enviarEmail();
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

            InternetAddress a[] = new InternetAddress[trackActual.getWebsiteUsersEntityCollection().size() + 1];
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

    public void onUpload$subir(ForwardEvent evento) {
        UploadEvent event = (UploadEvent) evento.getOrigin();
        Media doc = event.getMedia();
        if(doc != null) {
        String path = subirDocumento(doc);
        guardarAdjunto(path);
        } else
            showMessage("Seleccione un archivo.");
    }

    //private void guardarAdjunto(String path,String tipo){
    private void guardarAdjunto(String path) {
        if (path != null) {

            DocumentosEntity documento = new DocumentosEntity();
            AttachmentEntity adjunto;

            // setea los datos del documento
            documento.setDocPath(path);
            documento.setTitulo(tituloDoc.getValue());
            documento.setDescripcion(descripcionDoc.getValue());
            documento.setDocumentVersion(1); // cambiar!!!!!!!!!!!!!

            try {
                // guardar el documento
                trackModel.beginTransaction();

                BaseModel.createEntity(documento, false);

                // setea el adjunto
                adjunto = new AttachmentEntity(documento.getIdDocumento(), trackModel.getSelected().getTrackId());
                adjunto.setUsuario(getUserFromSession());
                adjunto.setFecha(new Date());

                // guardar el adjunto
                BaseModel.createEntity(adjunto, false);
                trackModel.commitTransaction();
                trackModel.getSelected().getAttachmentEntityCollection().add(adjunto);
                binder.loadComponent(adjuntos);

                if(envioEmailAdjuntos.isChecked())
                    enviarEmail();
            } catch (Exception ex) {
                try {
                    System.out.println("ERROR: "+ex);
                    ex.printStackTrace();
                    trackModel.rollbackTransaction();
                    showMessage("Ocurrió un error: ", ex);
                } catch (Exception ex1) {
                    Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } else {
            showMessage("archivo no encontrado");
        }
    }

    private String subirDocumento(Media doc) {
        try {
            // inicializacion de varibles
            File archivo;
            String directorio = "/files";
            String realPath = getHttpRequest().getSession().getServletContext().getRealPath(directorio);

            archivo = new File(realPath + "/" + doc.getName());

            FileOutputStream fos = new FileOutputStream(archivo);

            if ("txt".equalsIgnoreCase(doc.getFormat())) {
                fos.write(doc.getStringData().getBytes());
            } else if (doc.isBinary()) {
                InputStream is = doc.getStreamData();
                int b = 0;
                while ((b = is.read()) != -1) {
                    fos.write(b);
                }
            } else {
                fos.write(doc.getByteData());
            }

            fos.flush();
            fos.close();

            showMessage("El documento ha sido recibido exitosamente!!");

            return directorio + "/" + doc.getName();

        } catch (FileNotFoundException ex) {
            showMessage("archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("###################  error de entrada / salida  ####################");
        }
        return null;
    }

    public void refreshLists(){
        prioridadesModel.refreshAll();
        importanciaModel.refreshAll();
    }
}