package org.g2p.tracker.model.daos;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.g2p.tracker.model.entities.RolesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.spring.jpa.BasicDAO;
import org.zkoss.spring.jpa.EntityNotFoundException;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.Isolation;
import org.zkoss.lang.Strings;

@Scope("idspace")
@Repository()
public class RolesDAO {

    @Resource
    protected BasicDAO basicDAO;

    public BasicDAO getRolesDao() {
        return basicDAO;
    }

    public void setRolesDao(BasicDAO basicDao) {
        this.basicDAO = basicDao;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<RolesEntity> find(String queryString, int offset, int maxResults, Map<String, ?> params) {
        List<?> all = this.basicDAO.find(queryString, offset, maxResults, params);
        return (List<RolesEntity>) all;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void persist(RolesEntity rol) {
        this.basicDAO.persist(rol);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(RolesEntity rol) throws EntityNotFoundException {
        this.basicDAO.remove(RolesEntity.class, rol.getRolId());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void merge(RolesEntity rol) throws EntityNotFoundException {
        this.basicDAO.merge(rol);
    }
    /*protected RolesEntity selected;
    protected String queryString;
    protected String where;
    protected String orderBy;
    protected int offset;
    protected int maxResults;
    protected Map<String, ?> parameters;

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

    public List<RolesEntity> getAll() {
        return find(getQueryString(), this.offset, this.maxResults, getParameters());
    }

    //-- overridable --//
    /** Generate query string */
    /*protected String generateQueryString(String where, String orderBy) {
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

    	//-- DB access on the selected bean --//
	public void persist() {
		persist(selected);
	}

	public void merge() throws EntityNotFoundException {
		merge(selected);
	}

	public void delete() throws EntityNotFoundException {
		delete(selected);
	}

    public RolesDAO getDAO() {
		return this;
	}*/

}
