/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.g2p.tracker.model.entities.BaseEntity;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author Administrador
 */
public class BaseController extends Window implements AfterCompose, Constants {

    private static final long serialVersionUID = -6524216859170169468L;

    //ZK databinder
    protected DataBinder binder;
    protected Session session;
    protected boolean pageProtected;

    public BaseController(boolean pageProtected) {
        this.pageProtected = pageProtected;

        if (this.pageProtected && getSession().getAttribute(USER_ID) == null) {
            Executions.sendRedirect("/");
        }

    }

    //operation transient state
    protected BaseEntity _tmpSelected; //store original selected entity
    protected boolean _create; //when new a entity
    protected boolean editMode; //switch to edit mode when doing editing(new/update)
    protected int _lastSelectedIndex = -1; //last selectedIndex before delete

    @Override
    public void afterCompose() {

        //wire variables
        Components.wireVariables(this, this);

        //auto forward
        Components.addForwards(this, this);

    }

    public boolean is_create() {
        return _create;
    }

    public void set_create(boolean _create) {
        this._create = _create;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public boolean is_edit() {
        return !_create;
    }

    public void setEditMode(boolean _editMode) {
        this.editMode = _editMode;
    }

    public int get_lastSelectedIndex() {
        return _lastSelectedIndex;
    }

    public void set_lastSelectedIndex(int _lastSelectedIndex) {
        this._lastSelectedIndex = _lastSelectedIndex;
    }

    public DataBinder getBinder() {
        return binder;
    }

    public void setBinder(DataBinder binder) {
        this.binder = binder;
    }

    protected void showMessage(String msg) {
        showMessage(msg,null);
    }

    protected void showMessage(String msg, Exception ex) {
        try {
            Messagebox.show(msg + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex1) {
            System.out.println("ERROR MOSTRANDO MENSAJE: " + ex1.getMessage());
            ex1.printStackTrace();
        }
    }

    public Session getSession() {
        if (session == null) {
            session = Sessions.getCurrent();
        }
        return session;
    }

    public boolean isPageProtected() {
        return pageProtected;
    }

    public Integer getUserIdFromSession() {
        return (Integer) getSession().getAttribute(USER_ID);
    }

    public String getUserNameFromSession() {
        return (String) getSession().getAttribute(USER_NAME);
    }

    public void setUserIdInSession(Integer userId) {
        getSession().setAttribute(USER_ID, userId);
    }

    public void setUserNameInSession(String userName) {
        getSession().setAttribute(USER_NAME, userName);
    }

    public HttpServletRequest getHttpRequest() {
        return (HttpServletRequest) getDesktop().getExecution().getNativeRequest();
    }

    public HttpServletResponse getHttpResponse() {
        return (HttpServletResponse) getDesktop().getExecution().getNativeResponse();
    }

    public void gotoHome() {
        ((Include) getDesktop().getAttribute(INCLUDE)).setSrc(HOME_PAGE);
        ((BasePageController) getDesktop().getAttribute(BASE_PAGE_CONTROLLER)).setNavBarItem(HOME_PAGE);
    }
}
