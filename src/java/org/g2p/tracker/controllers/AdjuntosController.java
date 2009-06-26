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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.AttachmentEntity;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.DocumentosEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.model.models.DocumentosModel;
import org.zkoss.idom.Attribute;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Html;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

/**
 *
 * @author kristian
 */
public class AdjuntosController extends BaseController implements AfterCompose{
    protected Fileupload subir;
    protected Filedownload descargar;
    protected Listbox adjuntos;
    protected Textbox tituloDoc;
    protected Textbox descripcionDoc;

    public AdjuntosController(){
        super(true);
    }

    public void onCreate$adjuntosWin(Event evento){
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

    }

    public void onUpload$subir(ForwardEvent evento){
        UploadEvent event = (UploadEvent) evento.getOrigin();
        Media doc = event.getMedia();
        

        String path = subirDocumento(doc);

        //guardarAdjunto(path,doc.getFormat());
        guardarAdjunto(path);
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
            //documento.setTipo(tipo);
            documento.setVersion(1); // cambiar!!!!!!!!!!!!!

            try {
                Hashtable<String,Object> parametros = new Hashtable<String, Object>();
            // guardar el documento
                BaseModel.createEntity(documento, true);

            // recuperar el documento guardado
                parametros.put("path", documento.getPath());
                parametros.put("titulo", documento.getTitulo());
                parametros.put("descripcion", documento.getDescripción());
                parametros.put("version", documento.getDocumentVersion());
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

    private void mostrarAdjuntos(){
        // especifico las variables a usar
        Hashtable<String,String> parametros = new Hashtable<String, String>();
        Vector<DocumentosEntity> documentos = new Vector<DocumentosEntity>();
        Iterator<BaseEntity> itAdjPk;

        //obtengo el track actual
        parametros.put("track", "1"); // cambiar por el track correspondiente

        // obtengo el id de los adjuntos del track
        List<BaseEntity> adjuntosPk = BaseModel.findEntities("AttachmentEntity.findAllByTrack", parametros);

        // recupero los datos de los adjuntos del track
        itAdjPk = adjuntosPk.iterator();

        // por cada adjunto
        while (itAdjPk.hasNext()){
            AttachmentEntity docPk = (AttachmentEntity) itAdjPk.next();
            Listitem documento = new Listitem();
            Listcell titulo = new Listcell();
            Listcell descripcion = new Listcell();
            Listcell version = new Listcell();
            Listcell subidoPor = new Listcell();
            Listcell subidoEl = new Listcell();
            Html link = new Html();

            DocumentosEntity docActual = DocumentosModel.findEntityByPK(docPk.getPK());

            // ademas se especifica la posibilidad de descargarlo
            link.setContent("<![CDATA[<a href=\"" + docActual.getPath() + "\" >" + docActual.getTitulo() + "</a>]]>");

            // agrego los datos de cada adjunto en un item propio
            //titulo.setValue(docActual.getTitulo());
            titulo.appendChild(link);
            descripcion.setValue(docActual.getDescripción());
            version.setValue(docActual.getDocumentVersion());
            subidoPor.setValue(docPk.getUsuario());
            subidoEl.setValue(docPk.getFecha());

            
            //Attribute descargar = new Attribute("onClick", "{Filedownload.save(inputstream,\"" + docActual.getTipo() + "\", documento)}");

            // se agrega el item a la lista principal
            documento.appendChild(titulo);
            documento.appendChild(descripcion);
            documento.appendChild(version);
            documento.appendChild(subidoPor);
            documento.appendChild(subidoEl);

            adjuntos.appendChild(documento);
        }
      


    }

}
