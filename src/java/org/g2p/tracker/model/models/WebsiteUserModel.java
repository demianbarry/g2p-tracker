/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.WebsiteUserEntityJpaController;
import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import org.g2p.tracker.model.entities.WebsiteUserEntity;
import org.zkoss.lang.Strings;

/**
 *
 * @author nacho
 */
public class WebsiteUserModel {

    protected WebsiteUserEntityJpaController websiteUserDAO = new WebsiteUserEntityJpaController();
    protected WebsiteUserEntity selected;
    protected UsuarioRolesEntity rolSelected;
    protected static List<WebsiteUserEntity> all;
    protected String queryString;
    protected String where;
    protected String orderBy;
    protected int offset;
    protected int maxResults;
    protected Map<String, ?> parameters;

    public void setRolSelected(UsuarioRolesEntity rolSelected) {
        this.rolSelected = rolSelected;
    }

    public UsuarioRolesEntity getRolSelected() {
        return rolSelected;
    }

    public Collection<UsuarioRolesEntity> getUsuariosRoles() {
        if (selected != null) {
            return selected.getUsuarioRolesEntityCollection();
        }

        return null;
    }

    public WebsiteUserModel() {
        all = new ArrayList<WebsiteUserEntity>(websiteUserDAO.findWebsiteUserEntityEntities());
    }

    public WebsiteUserEntityJpaController getDAO() {
        return websiteUserDAO;
    }

    public void setDAO(WebsiteUserEntityJpaController websiteUserDAO) {
        this.websiteUserDAO = websiteUserDAO;
    }

    public WebsiteUserEntity getSelected() {
        return this.selected;
    }

    public void setSelected(WebsiteUserEntity todo) {
        this.selected = todo;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public String getQueryString() {
        if (queryString != null) {
            return this.queryString;
        }
        return generateQueryString(this.where, this.orderBy);
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Map<String, ?> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, ?> params) {
        this.parameters = params;
    }

    //-- DB access on the selected bean --//
    public void persist() {
        websiteUserDAO.create(selected);
    }

    public void merge() throws IllegalOrphanException, NonexistentEntityException, Exception {
        websiteUserDAO.edit(selected);
    }

    public void delete() throws IllegalOrphanException, NonexistentEntityException {
        websiteUserDAO.destroy(selected.getUserId());
    }

    public List<WebsiteUserEntity> getAll() {
        return websiteUserDAO.findWebsiteUserEntityEntities();
    }

    //-- overridable --//
    /** Generate query string */
    protected String generateQueryString(String where, String orderBy) {
        final StringBuffer sb = new StringBuffer(256);
        sb.append("FROM " + WebsiteUserEntity.class.getName());
        if (!Strings.isBlank(where)) {
            sb.append(" WHERE " + where);
        }
        if (!Strings.isBlank(orderBy)) {
            sb.append(" ORDER BY " + orderBy);
        }
        return sb.toString();
    }
}
