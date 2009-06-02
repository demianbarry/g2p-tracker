/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.AccesoMenuEntity;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.MenuEntity;
import org.g2p.tracker.model.entities.RolesEntity;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.models.AccesoMenuModel;
import org.g2p.tracker.model.models.MenuModel;
import org.g2p.tracker.model.models.RolesModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkoss.idom.Item;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Group;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

/**
 *
 * @author kristian
 */
public class AbmMenuController extends BaseController implements AfterCompose{

    protected Listbox lbPaginas;
    protected Listbox lbMenues;
    protected Listbox lbUsuarios;
    protected Listbox lbRoles;
 //   protected Listbox filas;

    protected Listbox lbGrupos;
    protected Textbox tbDescripcion;

    protected Button btnAlta;
    protected Button btnBaja;
    protected Button btnAceptar;
    protected Button btnCancelar;
    protected Button btnAplicar;
    protected Button btnAsociar;
    protected Button btnDesasociar;

    protected Rows filas;

    //protected Component vistasDetail; //domain object detail
    protected MenuModel menuModel;
    protected RolesModel rolesModel;
    protected WebsiteUserModel usuariosModel;

    public AbmMenuController(){
        super(true);

//        lbPaginas = new Listbox();
//        lbMenues = new Listbox();
//        filas = new Rows();
//        lbRoles = new Listbox();
//        lbUsuarios = new Listbox();
//
//        btnAlta = new Button();
//        btnBaja = new Button();
//        btnAceptar = new Button();
//        btnCancelar = new Button();
//        btnAplicar = new Button();
//        btnAsociar =  new Button();
//        btnDesasociar = new Button();

        menuModel = new MenuModel();
        rolesModel = new RolesModel();
        usuariosModel = new WebsiteUserModel();
    }

    public void onClick$btnAplicar(){

        darAlta();
        darBaja();

        asociar();
    }

    public void onClick$btnCancelar(){
        setVisible(false);
    }

    public void onClick$btnAceptar(){
        onClick$btnAplicar();
        onClick$btnCancelar();
    }

    public void onClick$btnAlta(){
        darAlta();
        actualizarListaAlta();
    }

    public void onClick$btnBaja(){
        darBaja();
        actualizarListaAlta();
    }

    public void onClick$btnAsociar(){
        asociar();
    }

    public void onClick$btnDesasociar(){
        desasociar();
    }

    public void onCreate$abmMenuWin(Event evento){
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);
        //binder.loadComponent(vistasDetail);

        pantallaInit();
    }

    public void pantallaInit(){
    //   listaDisponibles();
        listaAlta();
        listaRoles();
        listaUsuarios();
    }

    private void darAlta(){

        Iterator itItems;

        itItems = lbPaginas.getSelectedItems().iterator();
        MenuModel mnModel = new MenuModel();

        while (itItems.hasNext()){
            
            MenuEntity nuevoMenu = new MenuEntity();
            Listitem itemActual = ((Listitem) (itItems.next()));

            nuevoMenu.setNombre(itemActual.getLabel());
            nuevoMenu.setUrl(itemActual.getValue().toString());

            mnModel.setSelected(nuevoMenu);
            
            try{
            MenuModel.createEntity(nuevoMenu,true);


            } catch (RollbackFailureException ex) {
                showMessage("No se pudo dar de baja el menu", ex);
            } catch (Exception ex) {
                showMessage("Sucedio un error desconocido", ex);
            }
            
        }
        

    }

    private void darBaja(){
        List<Row> menuesAEliminar = getFilasSeleccionadas();
        Iterator<Row> itFilas;
        final int PK = 0;

        itFilas = menuesAEliminar.iterator();

        while (itFilas.hasNext()){
            Row filaActual = (Row) itFilas.next();

            Label lblPk = (Label) filaActual.getGroup().getItems().get(PK);

            MenuEntity menuActual = (MenuEntity) menuModel.findEntity(Integer.parseInt(lblPk.getValue()));

            try {
                    MenuModel.deleteEntity(menuActual, true);



                } catch (RollbackFailureException ex) {
                        showMessage("No se pudo dar de baja el menu", ex);
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
    }

    public List<BaseEntity> menuesAlta(){
        return menuModel.findEntities();
    }

    public void listaDisponibles(){
        try {
            final String EXTENSION = ".zul";

            if (getHttpRequest().getSession().getServletContext().getResource("/") == null){
                System.out.println("##############  ES NULO   ##############");
                
            }
            else{
                System.out.println("##############  todo bien   ##############");
            }

           // System.err.println("##############  " + getHttpRequest().getContextPath() + "##############");
            File dirVistas = new File(getHttpRequest().getSession().getServletContext().getResource("/").getFile());
            File[] vistas;
            String nombre;
            List<BaseEntity> activos;
            if (dirVistas.list().length > 0){
       //     if (dirVistas.isDirectory()) {
                // obtiene la lista de pantallas
                vistas = dirVistas.listFiles(new FileFilter() {

                    @Override
                    public boolean accept(File archivo) {
                        return archivo.getName().endsWith(EXTENSION);
                    }
                });
                //busca las paginas dadas de alta
                activos = menuesAlta();
                // agrega las pantallas al componente
                for (int i = 0; i < vistas.length; i++) {
                    for (int j = 0; j < activos.size(); j++) {
                        // si una pagina, ubicada en el disco, no fue dada de alta previamente
                        // se la agrega a la lista como disponible
                        if (((MenuEntity) activos.get(j)).getNombre().compareTo(vistas[i].getName()) != 0) {
                            nombre = vistas[i].getName().substring(0, vistas[i].getName().lastIndexOf(EXTENSION));
                            lbPaginas.appendItem(nombre, vistas[i].getAbsolutePath());
                        }
                    }
                }
            }
            setFocus(true);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AbmMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // aca hay un nullPointerException
    private void listaAlta(){
//        List<BaseEntity> menues = menuesAlta();
//
//        for (int i=0;i < menues.size(); i++){
//            Listitem filaActual = new Listitem();
//            MenuEntity menuActual = (MenuEntity) menues.get(i);
//            Combobox comboActual = new Combobox();
//            Comboitem itemSeleccionado = new Comboitem();
//
//            // agrega los grupos al combo
//            for (int j = 0; j < menues.size(); j++) {
//                String nombre = ((MenuEntity) menues.get(j)).getNombre();
//
//                comboActual.appendItem(nombre);
//            }
//            itemSeleccionado.setContent(menuActual.getGrupo());
//            comboActual.setSelectedItem(itemSeleccionado);
//
//            filaActual.setCheckable(true);
//
//            // agrega la primary key a la fila (pero no se muestra)
//            Listcell lclPk = new Listcell(menuActual.getPK().toString());
//            lclPk.setVisible(false);
//
//            // nombre del menu (editable)
//            filaActual.appendChild(new Listcell(menuActual.getNombre()));
//
//            // descripcion (editable)
//            filaActual.appendChild(new Listcell(menuActual.getDescripcion()));
//
//            // url del menu
//            filaActual.appendChild(new Listcell(menuActual.getUrl()));
//
//            // grupo del menu (elegible)
//            filaActual.appendChild(new Listcell(menuActual.getGrupo()));
//        }
        try {

        List<BaseEntity> menues = menuesAlta();
        Row filaActual;
        List itemsActual;
        MenuEntity menuActual;
        Combobox comboActual;
        List gruposComponentes = filas.getGroups();


        for (int i=0;i < menues.size(); i++){

                filaActual = new Row();
            //    Group grupo = new Group();
            //    itemsActual = grupo.getItems();
                menuActual = (MenuEntity) menues.get(i);
                comboActual = new Combobox();
                Comboitem itemSeleccionado = new Comboitem(menuActual.getGrupo());
                

                // agrega los grupos al combo
                for (int j=0; j < menues.size();j++){
                    String nombre = ((MenuEntity)menues.get(j)).getNombre();

                    Comboitem item = comboActual.appendItem(nombre);

                    if (menuActual.getGrupo().compareTo(nombre) == 0){
                        itemSeleccionado = item;
                    }
                }
            //    itemSeleccionado.(menuActual.getGrupo());
                comboActual.setSelectedItem(itemSeleccionado);
                comboActual.setAutocomplete(true);

                // agrega la primary key a la fila (pero no se muestra)
                Label lblPk = new Label(menuActual.getPK().toString());
                lblPk.setVisible(false);

                filaActual.appendChild(lblPk);

                // seleccionar menu? si/no
                filaActual.appendChild(new Checkbox());

                // nombre del menu (editable)
                filaActual.appendChild(new Textbox(menuActual.getNombre()));

                // descripcion (editable)
                filaActual.appendChild(new Textbox(menuActual.getDescripcion()));

                // url del menu
                filaActual.appendChild(new Label(menuActual.getUrl()));

                // grupo del menu (elegible)
                filaActual.appendChild(comboActual);

                filas.appendChild(filaActual);

//                itemsActual.add(lblPk);
//
//                // seleccionar menu? si/no
//                itemsActual.add(new Checkbox());
//                // nombre del menu (editable)
//                itemsActual.add(new Textbox(menuActual.getNombre()));
//                // descripcion (editable)
//                itemsActual.add(new Textbox(menuActual.getDescripcion()));
//                // url del menu
//                itemsActual.add(new Label(menuActual.getUrl()));
//                // grupo del menu (elegible)
//                itemsActual.add(comboActual);
//
//
//                //filas.getGroups().add(filaActual);
//                gruposComponentes.add(grupo);
        }

        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void vaciarListaAlta(){
        filas.getGroups().clear();
    }

    private void actualizarListaAlta(){
        vaciarListaAlta();
        listaAlta();
    }

    private void asociar(){
        Set<Listitem> roles = lbRoles.getSelectedItems();
        Set<Listitem> usuarios = lbUsuarios.getSelectedItems();
        List<Row> menues = getFilasSeleccionadas();


        

        Iterator<Row> itMenues = menues.iterator();

        System.out.println("#### Roles:" + roles.size() + "#####");
                System.out.println("#### Usuarios:" + usuarios.size() + "#####");

                System.out.println("#### hay menues seleccionados:" + itMenues.hasNext() + "#####");

        while (itMenues.hasNext()){
            Iterator<Listitem> itRoles = roles.iterator();
            Iterator<Listitem> itUsuarios = usuarios.iterator();
            Row menuActual = itMenues.next();


            System.out.println("#### hay usuarios seleccionados:" + itUsuarios.hasNext() + "#####");
            System.out.println("#### hay roles seleccionados:" + itRoles.hasNext() + "#####");

            String strPk = ((Label)(menuActual.getGroup().getItems().get(0))).getValue();
            int menuPk = Integer.parseInt(strPk);

            // TODO
            // select rol_id from acceso_menu where menu_id = menuPk
            // de los seleccionados, des-seleccionar los que ya se encuentran
            // asociados
            //AccesoMenuModel.findEntities("select object(o) from ", );


            // TODO
            // select user_id from acceso_menu where menu_id = menuPk
            // de los seleccionados, des-seleccionar los que ya se encuentran
            // asociados

            // al menu actual le asocia los roles
            //for (int i = 0;i < roles.size();++i){
            while (itRoles.hasNext()){
                Listitem rolActual = (Listitem) itRoles.next();
                //Listitem rolActual = roles.

                Integer pk = Integer.parseInt((String)(rolActual.getValue()));

                AccesoMenuEntity accesoMenu = new AccesoMenuEntity();
                accesoMenu.setRolId(new RolesEntity(pk.intValue()));

                // ver si es correcto 
                accesoMenu.setMenuId(new MenuEntity(menuPk));
                try {
                    
                    AccesoMenuModel.createEntity((BaseEntity) accesoMenu, true);

                    
                 } catch (RollbackFailureException ex) {
                        showMessage("No se pudo dar de baja el menu", ex);
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

            // al menu actual le asocia los usuarios
            while (itUsuarios.hasNext()){
                Listitem usuarioActual = (Listitem) itUsuarios.next();

                System.out.println("####" + (String)(usuarioActual.getValue()) + "#####");

                Integer pk = Integer.parseInt((String)(usuarioActual.getValue()));

                AccesoMenuEntity accesoMenu = new AccesoMenuEntity();
                accesoMenu.setUserId(new WebsiteUsersEntity(pk.intValue()));


                // ver si es correcto
                accesoMenu.setMenuId(new MenuEntity(menuPk));
                try {

                    AccesoMenuModel.createEntity((BaseEntity) accesoMenu, true);


                 } catch (RollbackFailureException ex) {
                        showMessage("No se pudo dar de baja el menu", ex);
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
        }
    }

    private void desasociar(){
        Set<Listitem> roles = lbRoles.getSelectedItems();
        Set<Listitem> usuarios = lbUsuarios.getSelectedItems();
        List<Row> menues = getFilasSeleccionadas();


        Iterator<Row> itMenues = menues.iterator();

        while (itMenues.hasNext()){
            Iterator<Listitem> itRoles = roles.iterator();
            Iterator<Listitem> itUsuarios = usuarios.iterator();
            Row menuActual = itMenues.next();

            String strPk = ((Label)(menuActual.getGroup().getItems().get(0))).getValue();
            int menuPk = Integer.parseInt(strPk);

            // TODO
            // select rol_id from acceso_menu where menu_id = menuPk
            // de los seleccionados, des-seleccionar los que no se encuentran
            // asociados
            //AccesoMenuModel.findEntities("select object(o) from ", );


            // TODO
            // select user_id from acceso_menu where menu_id = menuPk
            // de los seleccionados, des-seleccionar los que no se encuentran
            // asociados

            // al menu actual le asocia los roles
            while (itRoles.hasNext()){
                Listitem rolActual = (Listitem) itRoles.next();

                Integer pk = Integer.parseInt((String)(rolActual.getValue()));

                AccesoMenuEntity accesoMenu = new AccesoMenuEntity();
                accesoMenu.setRolId(new RolesEntity(pk.intValue()));


                // ver si es correcto
                accesoMenu.setMenuId(new MenuEntity(menuPk));
                try {
                    AccesoMenuModel.deleteEntity((BaseEntity) accesoMenu, true);


                } catch (RollbackFailureException ex) {
                        showMessage("No se pudo dar de baja el menu", ex);
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

            // al menu actual le asocia los usuarios
            while (itUsuarios.hasNext()){
                Listitem usuarioActual = (Listitem) itUsuarios.next();

                Integer pk = Integer.parseInt((String)(usuarioActual.getValue()));

                AccesoMenuEntity accesoMenu = new AccesoMenuEntity();
                accesoMenu.setUserId(new WebsiteUsersEntity(pk.intValue()));


                // ver si es correcto
                accesoMenu.setMenuId(new MenuEntity(menuPk));
                try {

                    AccesoMenuModel.deleteEntity((BaseEntity) accesoMenu, true);


                 } catch (RollbackFailureException ex) {
                        showMessage("No se pudo dar de baja el menu", ex);
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
        }
    }

    private List getFilasSeleccionadas(){
        Iterator itFilas;
        final int CHECKBOX = 1;

        Vector<Row> filasSeleccionadas;

        itFilas = filas.getGroups().iterator();
        filasSeleccionadas = new Vector<Row>();

        while (itFilas.hasNext()){
            Row filaActual = (Row) itFilas.next();

            Checkbox item = (Checkbox) filaActual.getGroup().getItems().get(CHECKBOX);

            if (item.isChecked()){
                filasSeleccionadas.add(filaActual);
            }
        }

        return filasSeleccionadas;
    }

    private void listaUsuarios(){
        WebsiteUserModel userModel = new WebsiteUserModel();

        List<BaseEntity> usuarios = userModel.findEntities();
        Iterator<BaseEntity> itUsuarios;

        itUsuarios = usuarios.iterator();

        while (itUsuarios.hasNext()){
            WebsiteUsersEntity usuarioActual = (WebsiteUsersEntity) itUsuarios.next();

            lbUsuarios.appendItem(usuarioActual.getApellidoNombre(), usuarioActual.getPK().toString());
        }

    }

    private void listaRoles(){
        RolesModel rolModel = new RolesModel();

        List<BaseEntity> roles = rolModel.findEntities();
        Iterator<BaseEntity> itRoles;

        itRoles = roles.iterator();

        while (itRoles.hasNext()){
            RolesEntity rolActual = (RolesEntity) itRoles.next();

            lbRoles.appendItem(rolActual.getNombre(), rolActual.getPK().toString());
        }
    }
}
