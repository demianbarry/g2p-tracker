/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import com.jhlabs.vecmath.Vector3f;
import java.util.List;
import java.util.Vector;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
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

    public DetallesController(){
        super(true);
    }
    
    public void onCreate$detallesWin(Event evento){
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);
        //binder.loadComponent(vistasDetail);

    }

    public void onClick$btnSubmit(){
        Textbox tbShow = new Textbox();
        tbShow.setReadonly(true);
        tbShow.setMultiline(true);

        tbShow.setText(tbComentario.getText());
        //vboxShowComments.appendChild(tbShow);
        //pgPaginado.smartUpdate(SSO, SSO);

        Row comentario = new Row();
        comentario.appendChild(tbShow);

        List<Component> hijos = comentario.getChildren();
        //new Vector<Component>(hijos);

        filas.appendChild(comentario);
    }
}
