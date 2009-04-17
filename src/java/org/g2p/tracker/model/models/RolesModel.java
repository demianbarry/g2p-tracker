package org.g2p.tracker.model.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.RolesEntityJpaController;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.RolesEntity;
import org.zkoss.lang.Strings;

public class RolesModel {

    protected RolesEntityJpaController rolesDAO = new RolesEntityJpaController();
    protected RolesEntity selected;
    protected static List<RolesEntity> all;
    protected String queryString;
    protected String where;
    protected String orderBy;
    protected int offset;
    protected int maxResults;
    protected Map<String, ?> parameters;

    public RolesModel() {
        all = new ArrayList<RolesEntity>(rolesDAO.findRolesEntityEntities());
    }

    public RolesEntityJpaController getDAO() {
        return rolesDAO;
    }

    public void setDAO(RolesEntityJpaController rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    public RolesEntity getSelected() {
        return this.selected;
    }

    public void setSelected(RolesEntity todo) {
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
        rolesDAO.create(selected);
    }

    public void merge() throws EntityNotFoundException, IllegalOrphanException, NonexistentEntityException, Exception {
            rolesDAO.edit(selected);
    }

    public void delete() throws EntityNotFoundException, IllegalOrphanException, NonexistentEntityException {
        rolesDAO.destroy(selected.getRolId());
    }

    public void setAll(List<RolesEntity> allEntities) {
        all = allEntities;
    }

    public List<RolesEntity> getAll() {
        return rolesDAO.findRolesEntityEntities();
    }

    //-- overridable --//
    /** Generate query string */
    protected String generateQueryString(String where, String orderBy) {
        final StringBuffer sb = new StringBuffer(256);
        sb.append("FROM " + RolesEntity.class.getName());
        if (!Strings.isBlank(where)) {
            sb.append(" WHERE " + where);
        }
        if (!Strings.isBlank(orderBy)) {
            sb.append(" ORDER BY " + orderBy);
        }
        return sb.toString();
    }
}
