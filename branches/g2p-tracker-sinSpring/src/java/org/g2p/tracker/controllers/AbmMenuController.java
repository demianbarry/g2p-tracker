/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import java.util.Hashtable;
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

    protected Listbox lbGrupos;
    protected Textbox tbDescripcion;

    protected Button btnAlta;
    protected Button btnBaja;
    protected Button btnAceptar;
    protected Button btnCancelar;
    protected Button btnAplicar;
    protected Button btnAsociar;
    protected Button btnDesasociar;
    protected Button btnDesSel;
    protected Button btnSel;

    protected Rows filas;

    //protected Component vistasDetail; //domain object detail
    protected MenuModel menuModel;
    protected RolesModel rolesModel;
    protected WebsiteUserModel usuariosModel;
    
    
    private final int PK, CHECKBOX, NOMBRE, DESCRIPCION, URL, COMBO;

    public AbmMenuController(){
        super(true);

        menuModel = new MenuModel();
        rolesModel = new RolesModel();
        usuariosModel = new WebsiteUserModel();

        PK = 0;
        CHECKBOX = 1;
        NOMBRE = 2;
        DESCRIPCION = 3;
        URL = 4;
        COMBO = 5;
    }

    public void onClick$btnAplicar(){

       // darAlta();
       // darBaja();

       // asociar();

        actualizar();
    }

    public void onClick$btnCancelar(){
        setVisible(false);
    }

    public void onClick$btnAceptar(){
        onClick$btnAplicar();
        onClick$btnCancelar();
    }

    public void onClick$btnAlta(){
        actualizarListaAlta(darAlta(),"alta");
    }

    public void onClick$btnBaja(){
        actualizarListaAlta(darBaja(),"baja");
    }

    public void onClick$btnAsociar(){
        asociar();
    }

    public void onClick$btnDesasociar(){
        desasociar();
    }

    public void onSelect$lbUsuarios(){
        mostrarMenuesUsuario();
        //masterDetail();
    }

    public void onSelect$lbRoles(){
        mostrarMenuesRol();
        //masterDetail();
    }

    public void onClick$btnDesSel(){
        seleccionarTodoListaAlta(false);
    }

    public void onClick$btnSel(){
        seleccionarTodoListaAlta(true);
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

    private List<MenuEntity> darAlta(){

        Iterator itItems;

        itItems = lbPaginas.getSelectedItems().iterator();

        Vector<MenuEntity> menuesAgregados = new Vector<MenuEntity>();

        while (itItems.hasNext()){

            MenuEntity nuevoMenu = new MenuEntity();
            Listitem itemActual = ((Listitem) (itItems.next()));

            nuevoMenu.setNombre(itemActual.getLabel());
            nuevoMenu.setUrl(itemActual.getValue().toString());

            try{
            MenuModel.createEntity(nuevoMenu,true);

            menuesAgregados.add(nuevoMenu);
            } catch (RollbackFailureException ex) {
                showMessage("No se pudo dar de alta el menu", ex);
            } catch (Exception ex) {
                showMessage("Sucedio un error desconocido", ex);
            }

        }

        showMessage("La alta ha sido exitosa");

        return menuesAgregados;

    }

    private List<MenuEntity> darBaja(){
        List<Row> menuesAEliminar = getFilasSeleccionadas();
        Iterator<Row> itFilas;
        final int PK = 0;

        Vector<MenuEntity> menuesEliminados = new Vector<MenuEntity>();
        itFilas = menuesAEliminar.iterator();

        while (itFilas.hasNext()){
            Row filaActual = (Row) itFilas.next();

            Label lblPk = (Label) filaActual.getChildren().get(PK);

            MenuEntity menuActual = (MenuEntity) menuModel.findEntity(Integer.parseInt(lblPk.getValue()));

            try {
                    MenuModel.deleteEntity(menuActual, true);

                    menuesEliminados.add(menuActual);
                    ((Checkbox) filaActual.getChildren().get(CHECKBOX)).setChecked(false);


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

        return menuesEliminados;
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

        List<BaseEntity> menues = menuesAlta();

        for (int i=0;i < menues.size(); i++){
                agregarFila((MenuEntity) menues.get(i));
        }
    }

    private Row agregarFila(MenuEntity menuActual){
        Row filaActual;
        Combobox comboActual;
        List<BaseEntity> menues = menuesAlta();

        filaActual = new Row();
            //    Group grupo = new Group();
            //    itemsActual = grupo.getItems();
                comboActual = new Combobox();
                Comboitem itemSeleccionado = new Comboitem(menuActual.getGrupo());


                // agrega los grupos al combo
                for (int j=0; j < menues.size();j++){
                    String nombre = ((MenuEntity)menues.get(j)).getUrl();

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

               // agregarGrupos(comboActual);
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

                return filaActual;
    }

//    private void agregarGrupos(Combobox combo){
//        List<BaseEntity> menues = menuesAlta();
//        Comboitem itemSeleccionado = new Comboitem();
//
//        // agrega los grupos al combo
//                for (int j=0; j < menues.size();j++){
//                    MenuEntity menuActual = (MenuEntity) menues.get(j);
//                    String nombre = ((MenuEntity)menues.get(j)).getUrl();
//                    itemSeleccionado.setValue(menuActual.getGrupo());
//
//                    Comboitem item = combo.appendItem(nombre);
//
//                    if (menuActual.getGrupo() != null) {
//                        if (menuActual.getGrupo().compareTo(nombre) == 0) {
//                            itemSeleccionado = item;
//                        }
//                    }
//                    else {
//                        itemSeleccionado = null;
//                    }
//                }
//
//                combo.setSelectedItem(itemSeleccionado);
//                combo.setAutocomplete(true);
//    }

    private void eliminarFila(MenuEntity menu){
        List<Row> hijos = filas.getChildren();
        Iterator<Row> itHijos = hijos.iterator();

        while (itHijos.hasNext()){
            Row hijoActual = itHijos.next();

            int hijoPK = Integer.parseInt(((Label)(hijoActual.getChildren().get(0))).getValue());

            if (hijoPK == menu.getMenuId()){
                hijoActual.setVisible(false);
            }
        }
    }

    private void actualizarListaAlta(List<MenuEntity> menues,String accion){

        Iterator<MenuEntity> itMenues = menues.iterator();

        while (itMenues.hasNext()){
            MenuEntity menuActual = itMenues.next();

            if (accion.compareTo("alta") == 0){
            Row fila = agregarFila(menuActual);

            
        }
        else {
            if (accion.compareTo("baja") == 0){
                eliminarFila(menuActual);
            }
        }
        }


    }

    private void asociar(){
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
                    rolActual.setSelected(false);

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

                    AccesoMenuModel.createEntity((BaseEntity) accesoMenu, true);
                    usuarioActual.setSelected(false);

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

            ((Checkbox) menuActual.getChildren().get(CHECKBOX)).setChecked(false);
        }

                showMessage("La asociasión ha sido exitosa");
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
                Hashtable<String,Integer> parametros = new Hashtable<String, Integer>();

                Integer pk = Integer.parseInt((String)(rolActual.getValue()));

                parametros.put("menuId", menuPk);
                parametros.put("rolId", pk);

                List<BaseEntity> amenu = AccesoMenuModel.findEntities("AccesoMenuEntity.findByMenuIdAndRolId", parametros);

                BaseEntity accesoMenu = amenu.get(0);

                try {
                    AccesoMenuModel.deleteEntity(accesoMenu, true);

                    rolActual.setSelected(false);

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
                Hashtable<String,Integer> parametros = new Hashtable<String, Integer>();

                Integer pk = Integer.parseInt((String)(usuarioActual.getValue()));

                parametros.put("menuId", menuPk);
                parametros.put("userId", pk);

                List<BaseEntity> amenu = AccesoMenuModel.findEntities("AccesoMenuEntity.findByMenuIdAndUsuarioId", parametros);

                AccesoMenuEntity accesoMenu = (AccesoMenuEntity) amenu.get(0);
                try {

                    AccesoMenuModel.deleteEntity((BaseEntity) accesoMenu, true);

                    usuarioActual.setSelected(false);

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
            ((Checkbox) menuActual.getChildren().get(CHECKBOX)).setChecked(false);
        }
    }

    private List getFilasSeleccionadas(){
        Iterator itFilas;

        Vector<Row> filasSeleccionadas;

        itFilas = filas.getChildren().iterator();
        filasSeleccionadas = new Vector<Row>();

        while (itFilas.hasNext()){
            Row filaActual = (Row) itFilas.next();

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

    private void actualizar(){

        List<Row> menuesActualizar = getFilasSeleccionadas();
        Iterator<Row> itMenuesSel = menuesActualizar.iterator();

        while (itMenuesSel.hasNext()){
            Row filaActual = itMenuesSel.next();
            MenuEntity menuActual = new MenuEntity();
            String descripcion;
            String grupo;
            int pk;
            String nombre;
            String url;
            Combobox combo;

            combo = (Combobox) filaActual.getChildren().get(COMBO);

            descripcion = ((Textbox)filaActual.getChildren().get(DESCRIPCION)).getText();
            pk = Integer.parseInt(((Label) filaActual.getChildren().get(PK)).getValue());
            nombre = ((Textbox) filaActual.getChildren().get(NOMBRE)).getText();
            url = ((Label) filaActual.getChildren().get(URL)).getValue();


            menuActual.setDescripcion(descripcion);
            menuActual.setMenuId(pk);
            menuActual.setNombre(nombre);
            menuActual.setUrl(url);

            if (combo != null){
                grupo = combo.getSelectedItem().getLabel();
                menuActual.setGrupo(grupo);
            }

            try {

                MenuModel.editEntity((BaseEntity) menuActual, true);

                ((Checkbox) filaActual.getChildren().get(CHECKBOX)).setChecked(false);
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

    private void mostrarMenuesUsuario() {
        try {
            Listitem itemUsuario = lbUsuarios.getSelectedItem();
            int pk = Integer.parseInt(itemUsuario.getValue().toString());
            Hashtable<String, Integer> parametros = new Hashtable<String, Integer>();

            parametros.put("userId", pk);

            List<BaseEntity> menues = AccesoMenuModel.findEntities("AccesoMenuEntity.findByUsuario", parametros);
            Iterator<BaseEntity> itMenues = menues.iterator();

            seleccionarTodoListaAlta(false);

            while (itMenues.hasNext()) {
                BaseEntity menuActual = itMenues.next();
                boolean seguir = true;
                List<Row> rows = ((List<Row>) filas.getChildren());

                for (int i = 0; seguir && i < filas.getChildren().size(); i++) {

                    for (int j = 0; j < ((Row) filas.getChildren().get(i)).getChildren().size(); ++j) {

                        int filaPk = Integer.parseInt(((Label) rows.get(j).getChildren().get(PK)).getValue());

                        if (filaPk == ((Integer) menuActual.getPK()).intValue()) {

                            ((Checkbox) rows.get(j).getChildren().get(CHECKBOX)).setChecked(true);
                            seguir = false;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            seleccionarTodoListaAlta(false);
        }
    }

    private void mostrarMenuesRol() {
        try {
            Listitem itemRol = lbRoles.getSelectedItem();
            int pk = Integer.parseInt(itemRol.getValue().toString());
            Hashtable<String, Integer> parametros = new Hashtable<String, Integer>();

            parametros.put("rolId", pk);

            List<BaseEntity> menues = AccesoMenuModel.findEntities("AccesoMenuEntity.findByRol", parametros);
            Iterator<BaseEntity> itMenues = menues.iterator();

            seleccionarTodoListaAlta(false);

            while (itMenues.hasNext()) {
                BaseEntity menuActual = itMenues.next();
                boolean seguir = true;
                List<Row> rows = ((List<Row>) filas.getChildren());

                for (int i = 0; seguir && i < filas.getChildren().size(); i++) {

                    for (int j = 0; j < ((Row) filas.getChildren().get(i)).getChildren().size(); ++j) {

                        int filaPk = Integer.parseInt(((Label) rows.get(j).getChildren().get(PK)).getValue());

                        if (filaPk == ((Integer) menuActual.getPK()).intValue()) {

                            ((Checkbox) rows.get(j).getChildren().get(CHECKBOX)).setChecked(true);
                            seguir = false;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            seleccionarTodoListaAlta(false);
        }
    }

    public void seleccionarTodoListaAlta(boolean sel){
        List<Row> menues = filas.getChildren();
        Iterator<Row> itMenues = menues.iterator();

        while (itMenues.hasNext()){
            Row filaActual = itMenues.next();

            ((Checkbox) filaActual.getChildren().get(CHECKBOX)).setChecked(sel);
        }

    }

    private void masterDetail(){

        List<Row> menues = intersectar(getMenuesRoles(),getMenuesUsuarios());
        Iterator<Row> itMenues = menues.iterator();

        seleccionarTodoListaAlta(false);

        while (itMenues.hasNext()){
            ((Checkbox) itMenues.next().getChildren().get(CHECKBOX)).setChecked(true);
        }
    }

    public List<Row> intersectar(List<Row> uno, List<Row> otro){
        Vector<Row> salida = new Vector<Row>();
        Iterator<Row> itUno = uno.iterator();
        Iterator<Row> itOtro = otro.iterator();

        while (itUno.hasNext()){
            Row elementoActual = itUno.next();
            int pkUno = Integer.parseInt(((Label) elementoActual.getChildren().get(PK)).getValue());

            while (itOtro.hasNext()){
                Row otroActual = itOtro.next();
                int pkOtro = Integer.parseInt(((Label) otroActual.getChildren().get(PK)).getValue());

                if (pkUno == pkOtro){
                    salida.add(elementoActual);
                }
            }
        }

        return salida;

    }

    public List<Row> unir(List<Row> uno, List<Row> otro){
        Vector<Row> salida;
        Iterator<Row> itUno = uno.iterator();
        Iterator<Row> itOtro = otro.iterator();
        Vector<Row> temp = new Vector<Row>();
        Vector<Row> interseccion = new Vector<Row>();

        interseccion = (Vector<Row>) intersectar(uno, otro);
        Iterator<Row> itInter = interseccion.iterator();

        salida = new Vector<Row>(interseccion);

//        while (itOtro.hasNext()){
//            Row actual = itOtro.next();
//
//            int otroPk = Integer.parseInt(((Label) actual.getChildren().get(PK)).getValue());
//
//            while (itInter.hasNext()){
//                Row interActual = itInter.next();
//
//                int interPk = Integer.parseInt(((Label) interActual.getChildren().get(PK)).getValue());
//
//                if (interPk != otroPk){
//                    salida.add(actual);
//                }
//            }
//        }
//
//        while (itUno.hasNext()){
//            Row actual = itUno.next();
//
//            int otroPk = Integer.parseInt(((Label) actual.getChildren().get(PK)).getValue());
//
//            while (itInter.hasNext()){
//                Row interActual = itInter.next();
//
//                int interPk = Integer.parseInt(((Label) interActual.getChildren().get(PK)).getValue());
//
//                if (interPk != otroPk){
//                    salida.add(actual);
//                }
//            }
//        }

        agregarSiDistinto(itOtro, itInter, salida);
        agregarSiDistinto(itUno, itInter, salida);

        return salida;
    }

    private void agregarSiDistinto(Iterator<Row> itAlgo,Iterator<Row> itInter, Vector<Row> salida){
        // si no hay elementos en comun entonces todos son diferentes
        if (!itInter.hasNext()){
            while (itAlgo.hasNext()){
                Row actual = itAlgo.next();

                salida.add(actual);
            }
        }
        else {
            while (itAlgo.hasNext()){
            Row actual = itAlgo.next();

            int otroPk = Integer.parseInt(((Label) actual.getChildren().get(PK)).getValue());

            while (itInter.hasNext()){
                Row interActual = itInter.next();

                int interPk = Integer.parseInt(((Label) interActual.getChildren().get(PK)).getValue());

                if (interPk != otroPk){
                    salida.add(actual);
                }
            }
        }
        }


        
    }

    private List<Row> getMenuesRoles(){
        Set itemsRol = lbRoles.getSelectedItems();
        Iterator<Listitem> itRoles = itemsRol.iterator();
        List<Row> salida = new Vector<Row>();

        while (itRoles.hasNext()){
            Listitem rolActual = itRoles.next();
            Vector<Row> menuesR = new Vector<Row>();

            int pk = Integer.parseInt(rolActual.getValue().toString());
            Hashtable<String, Integer> parametros = new Hashtable<String, Integer>();

            parametros.put("rolId", pk);

            List<BaseEntity> menues = AccesoMenuModel.findEntities("AccesoMenuEntity.findByRol", parametros);
            Iterator<BaseEntity> itMenues = menues.iterator();

            while (itMenues.hasNext()){
                BaseEntity menuActual = itMenues.next();
                int menuPk = Integer.parseInt(menuActual.getPK().toString());

                Iterator<Row> itRows = filas.getChildren().iterator();

                while (itRows.hasNext()){
                    Row rowActual = itRows.next();
                    int rowPk = Integer.parseInt(((Label) rowActual.getChildren().get(PK)).getValue());

                    if (menuPk == rowPk){
                        menuesR.add(rowActual);
                    }
                }
            }

            salida = unir(salida,menuesR);
        }

        return salida;
    }

    private List<Row> getMenuesUsuarios(){
        Set itemsUsuario = lbUsuarios.getSelectedItems();
        Iterator<Listitem> itUsuarios = itemsUsuario.iterator();
        List<Row> salida = new Vector<Row>();

        while (itUsuarios.hasNext()){
            Listitem rolActual = itUsuarios.next();
            Vector<Row> menuesR = new Vector<Row>();

            int pk = Integer.parseInt(rolActual.getValue().toString());
            Hashtable<String, Integer> parametros = new Hashtable<String, Integer>();

            parametros.put("userId", pk);

            List<BaseEntity> menues = AccesoMenuModel.findEntities("AccesoMenuEntity.findByUsuario", parametros);
            Iterator<BaseEntity> itMenues = menues.iterator();

            while (itMenues.hasNext()){
                BaseEntity menuActual = itMenues.next();
                int menuPk = Integer.parseInt(menuActual.getPK().toString());

                Iterator<Row> itRows = filas.getChildren().iterator();

                while (itRows.hasNext()){
                    Row rowActual = itRows.next();
                    int rowPk = Integer.parseInt(((Label) rowActual.getChildren().get(PK)).getValue());

                    if (menuPk == rowPk){
                        menuesR.add(rowActual);
                    }
                }
            }

            salida = unir(salida,menuesR);
        }

        System.out.println("menues de los usuarios: " + salida);
        return salida;
    }
}
