/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
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
import org.zkoss.zul.Listitem;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
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
       listaDisponibles();
        listaAlta();
        listaRoles();
        listaUsuarios();
    }

    private void darAlta(){

        Iterator itItems;

        itItems = lbPaginas.getSelectedItems().iterator();

        while (itItems.hasNext()){

            MenuEntity nuevoMenu = new MenuEntity();
            Listitem itemActual = ((Listitem) (itItems.next()));

            nuevoMenu.setNombre(itemActual.getLabel());
            nuevoMenu.setUrl(itemActual.getValue().toString());

            try{
            MenuModel.createEntity(nuevoMenu,true);


            } catch (RollbackFailureException ex) {
                showMessage("No se pudo dar de alta el menu", ex);
            } catch (Exception ex) {
                showMessage("Sucedio un error desconocido", ex);
            }

        }

        showMessage("La alta ha sido exitosa");

    }

    private void darBaja(){
        List<Row> menuesAEliminar = getFilasSeleccionadas();
        Iterator<Row> itFilas;
        final int PK = 0;

        itFilas = menuesAEliminar.iterator();

        while (itFilas.hasNext()){
            Row filaActual = (Row) itFilas.next();

            Label lblPk = (Label) filaActual.getChildren().get(PK);

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

        showMessage("La baja ha sido exitosa");
    }

    public List<BaseEntity> menuesAlta(){
        return menuModel.findEntities();
    }

    public void listaDisponibles(){
            final String EXTENSION = ".zul";
            Set dirVistas = getHttpRequest().getSession().getServletContext().getResourcePaths("/");
            String nombre;
            List<BaseEntity> activos;


            //busca las paginas dadas de alta
            activos = menuesAlta();

            // obtiene la lista de pantallas
            Iterator<String> itVistas = dirVistas.iterator();
            Iterator<BaseEntity> itActivos = activos.iterator();

            while (itActivos.hasNext()) {
                MenuEntity activoActual = (MenuEntity) itActivos.next();

                while (itVistas.hasNext()) {
                    String vistaActual = itVistas.next();
                //nombre = vistaActual.substring(0, vistaActual.lastIndexOf(EXTENSION) -1);
                    nombre = vistaActual.substring(1);

                    // si una pagina, ubicada en el disco, no fue dada de alta previamente
                    // se la agrega a la lista como disponible
                    if (vistaActual.endsWith(EXTENSION) &&  activoActual.getUrl().compareTo(nombre) != 0) {
                       Listitem item = lbPaginas.appendItem(nombre, nombre);
                        item.setSelected(false);
                    }
                }
            }
            setFocus(true);
    }

    private void listaAlta(){
        try {

        List<BaseEntity> menues = menuesAlta();
        Row filaActual;
        MenuEntity menuActual;
        Combobox comboActual;


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

                    if (menuActual.getGrupo() != null) {
                        if (menuActual.getGrupo().compareTo(nombre) == 0) {
                            itemSeleccionado = item;
                        }
                    }
                    else {
                        itemSeleccionado = null;
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
        }

        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void vaciarListaAlta(){
        List<Row> items = filas.getGroups();

        Iterator<Row> itRows = items.iterator();

        while (itRows.hasNext()){
            filas.getGroups().remove(itRows.next());
        }
    }

    private void actualizarListaAlta(){
        vaciarListaAlta();
        listaAlta();
    }

    private void asociar(){
        Set<Listitem> roles = lbRoles.getSelectedItems();
        Set<Listitem> usuarios = lbUsuarios.getSelectedItems();
        List<Row> menues = getFilasSeleccionadas();
        final int PK = 0;



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

            String strPk = ((Label)(menuActual.getChildren().get(PK))).getValue();
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

                showMessage("La asociasión ha sido exitosa");
    }

    private void desasociar(){
        Set<Listitem> roles = lbRoles.getSelectedItems();
        Set<Listitem> usuarios = lbUsuarios.getSelectedItems();
        List<Row> menues = getFilasSeleccionadas();
        final int PK = 0;

        Iterator<Row> itMenues = menues.iterator();

        while (itMenues.hasNext()){
            Iterator<Listitem> itRoles = roles.iterator();
            Iterator<Listitem> itUsuarios = usuarios.iterator();
            Row menuActual = itMenues.next();

            String strPk = ((Label)(menuActual.getChildren().get(PK))).getValue();
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

                    showMessage("La desasociasión ha sido exitosa");
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

        itFilas = filas.getChildren().iterator();
        filasSeleccionadas = new Vector<Row>();

        while (itFilas.hasNext()){
            Row filaActual = (Row) itFilas.next();


            //Checkbox item = (Checkbox) filaActual.getGroup().getItems().get(CHECKBOX);
            Checkbox item = (Checkbox) filaActual.getChildren().get(CHECKBOX);

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
