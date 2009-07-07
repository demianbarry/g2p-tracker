/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.AttachmentEntity;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.DocumentosEntity;
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
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
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
import org.zkoss.zul.Fileupload;
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

    private void enviarEmail() throws MessagingException, IOException {
        String emailRemitente;
        String contraseniaRemitente;
        String asunto;
        String contenido;
        String[] emailDestinatarios;

        // recupero los datos, para el envio del email, desde el archivo de properties
        Properties email = getEmailData();
        emailRemitente = email.getProperty("email");
        contraseniaRemitente = email.getProperty("password");
        
        // obtengo el track actual
        TracksEntity trackActual = trackModel.getSelected();

        // obtengo los emails de los trabajadores
        Set<WebsiteUsersEntity> trabajadores = trackActual.getWebsiteUsersEntityCollection();
        Iterator<WebsiteUsersEntity> worker = trabajadores.iterator();
        emailDestinatarios = new String[trabajadores.size()];

        for (int i = 0;worker.hasNext();i++){
            emailDestinatarios[i] = worker.next().getEmail();
        }

        // especifico el asunto y el mensaje
        asunto = "Se ha comentado el track " + trackActual.getTitulo();
        contenido = getUserNameFromSession() + " ha comentado el track " + trackActual.getTitulo();

        // envio el email
        BaseEntity.enviarEmail(contenido, asunto, emailRemitente, contraseniaRemitente, emailDestinatarios);

//        Properties conf = new Properties();
//        String contenido;
//
//        // nombre del host de correo
//        conf.put("mail.smtp.host", "smtp.gmail.com");
//
//        // TLS si está disponible
//        conf.setProperty("mail.smtp.starttls.enable", "true");
//
//        // Puerto de gmail para envio de correos
//        conf.setProperty("mail.smtp.port", "587");
//
//        // Nombre del usuario
//        conf.setProperty("mail.smtp.user", emailRemitente);
//
//        // Si requiere o no usuario y password para conectarse.
//        conf.setProperty("mail.smtp.auth", "true");
//
//        // cambiar por el track actual!!!!!!!!!!!!
//        TracksEntity trackActual = trackModel.getSelected();
//
//        Session sesion = Session.getDefaultInstance(conf);
//        Message mensaje = new MimeMessage(sesion);
//
//        contenido = getUserNameFromSession() + " ha comentado el track " + trackActual.getTitulo();
//
//        try {
//            InternetAddress de = new InternetAddress(emailRemitente);
//
//            InternetAddress a[] = new InternetAddress[trackActual.getWebsiteUsersEntityCollection().size()+1];
//            a[0] = (new InternetAddress((trackActual.getUserIdOwner().getEmail())));
//
//            Iterator worker = trackActual.getWebsiteUsersEntityCollection().iterator();
//
//            String userEmail = null;
//            int i = 1;
//            while (i < a.length) {
//                userEmail = ((WebsiteUsersEntity) worker.next()).getEmail();
//                if (userEmail != null && userEmail.length() > 0) {
//                    a[i] = (new InternetAddress(userEmail));
//                }
//                i++;
//            }
//
//            mensaje.setFrom(de);
//            mensaje.setRecipients(Message.RecipientType.TO, a);
//            mensaje.setSubject("Se ha comentado el track " + trackActual.getTitulo());
//            mensaje.setContent(contenido, "text/plain");
//            mensaje.setSentDate(new Date());
//
//            Transport t = sesion.getTransport("smtp");
//            t.connect(emailRemitente, contraseniaRemitente);
//            t.sendMessage(mensaje, mensaje.getAllRecipients());
//            t.close();
//
//
//        } catch (AddressException ex) {
//            Logger.getLogger(DetallesController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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

    /*
     * Agregado por cristian
     *
     */

    protected Fileupload subir;
    protected Listbox adjuntos;
    protected Textbox tituloDoc;
    protected Textbox descripcionDoc;
    protected Checkbox envioEmail;

    public void onUpload$subir(ForwardEvent evento){
        try {
            UploadEvent event = (UploadEvent) evento.getOrigin();
            Media doc = event.getMedia();
            Properties email = getEmailData();
            String[] emailDestinatarios;

            String path = subirDocumento(doc);
            guardarAdjunto(path);

            if (!envioEmail.isChecked()){
                return;
            }

            String asunto = "Se ha agregado un adjunto";
            String contenido = getUserNameFromSession() + " ha adjuntado un documento a la tarea " + trackModel.getSelected().getTitulo();
            String contrasenia = email.getProperty("password");
            String remitente = email.getProperty("email");
            
            Set<WebsiteUsersEntity> workers = trackModel.getSelected().getWebsiteUsersEntityCollection();
            Iterator<WebsiteUsersEntity> itWorkers = workers.iterator();
            emailDestinatarios = new String[workers.size()];
            
            for (int i = 0; itWorkers.hasNext(); i++){
                emailDestinatarios[i] = itWorkers.next().getEmail();
            }
            
            BaseEntity.enviarEmail(contenido, asunto, remitente, contrasenia, emailDestinatarios);
            //mostrarAdjuntos();
        } catch (MessagingException ex) {
            System.out.println("No se pudo enviar el email");
        }
        catch (IOException ex) {
            System.out.println("No se pudo leer el archivo mail.properties");
        }
        //mostrarAdjuntos();
    }

    //private void guardarAdjunto(String path,String tipo){
    private void guardarAdjunto(String path){
        if (path != null){
            DocumentosEntity documento = new DocumentosEntity();
            AttachmentEntity adjunto;

            // setea los datos del documento
            documento.setPath(path);
            documento.setTitulo(tituloDoc.getText());
            documento.setDescripción(descripcionDoc.getText());
            documento.setVersion(1); // cambiar!!!!!!!!!!!!!

            try {
                Hashtable<String,String> parametros = new Hashtable<String, String>();
            // guardar el documento
                BaseModel.createEntity(documento, true);

            // recuperar el documento guardado
                parametros.put("path", documento.getPath());
                parametros.put("titulo", documento.getTitulo());
                parametros.put("descripcion", documento.getDescripción());
                parametros.put("version", new Double(documento.getDocumentVersion()).toString());
                List<BaseEntity> listDocs = BaseModel.findEntities("DocumentosEntity.findDocument", parametros);

                documento.setIdDocumento(((DocumentosEntity) listDocs.get(0)).getIdDocumento());

            // setea el adjunto
            adjunto = new AttachmentEntity(documento.getIdDocumento(), 1); //CAMBIAR!!!!!!!!!!!!
            adjunto.setUsuario(getUserFromSession());
            adjunto.setFecha(new Date());

                // guardar el adjunto
                BaseModel.createEntity(adjunto, true);


            } catch (RollbackFailureException ex) {
                showMessage("No se guardar el adjunto", ex);
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
        else {
            showMessage("archivo no encontrado");
        }
    }

    private String subirDocumento(Media doc){
        try {

            // inicializacion de varibles


            File archivo;
            String directorio = "/files";

            String realPath = getHttpRequest().getSession().getServletContext().getRealPath(directorio);

            archivo = new File(realPath + "/" + doc.getName());

            // si es texto, lo manipula con herramientas especializadas
            if (doc.getFormat().equalsIgnoreCase("txt")) {
                String texto = doc.getStringData();
                FileWriter fw = new FileWriter(archivo);

                fw.write(texto);
                fw.close();
            } else { // si no, no
                OutputStream os = new FileOutputStream(archivo);
                InputStream is = doc.getStreamData();


                byte[] dato = new byte[5];
                int leidos;

                // copia el documento
                while ((leidos = is.read(dato)) > 0) {
                    os.write(dato, 0, leidos);
                }

                is.close();
                os.close();
            }






            showMessage("El documento ha sido recibido exitosamente!!");

            return directorio + "/" + doc.getName();

        }
        catch (FileNotFoundException ex) {
            showMessage("archivo no encontrado");
        }
        catch (IOException ex) {
            System.out.println("###################  error de entrada / salida  ####################");
        }

        return null;
    }

    private Properties getEmailData() throws IOException{
        // recupero los datos, para el envio del email, desde el archivo de properties
        InputStream is = (getHttpRequest().getSession().getServletContext().getResourceAsStream("/WEB-INF/mail.properties"));
        Properties email = new Properties();
        email.load(is);

        return email;
    }

}
