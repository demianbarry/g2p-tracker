package org.g2p.tracker.model.models;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.g2p.tracker.model.daos.AccesoMenuDAO;
import org.g2p.tracker.model.entities.AccesoMenuEntity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.lang.Strings;
import org.zkoss.spring.jpa.EntityNotFoundException;

@Scope("idspace")
@Component
public class AccesoMenuModel {

	@Resource
	protected AccesoMenuDAO accesoMenuDAO;

	protected AccesoMenuEntity selected;
	protected String queryString;
	protected String where;
	protected String orderBy;
	protected int offset;
	protected int maxResults;
	protected Map<String, ?> parameters;

	public AccesoMenuDAO getDAO() {
		return accesoMenuDAO;
	}

	public void setDAO(AccesoMenuDAO accesoAccesoMenuDAO) {
		this.accesoMenuDAO = accesoAccesoMenuDAO;
	}

	public AccesoMenuEntity getSelected() {
		return this.selected;
	}

	public void setSelected(AccesoMenuEntity todo) {
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

	public Map<String,?> getParameters() {
		return this.parameters;
	}

	public void setParameters(Map<String,?> params) {
		this.parameters = params;
	}

	//-- DB access on the selected bean --//
	public void persist() {
		accesoMenuDAO.persist(selected);
	}

	public void merge() throws EntityNotFoundException {
		accesoMenuDAO.merge(selected);
	}

	public void delete() throws EntityNotFoundException {
		accesoMenuDAO.delete(selected);
	}

	public List<AccesoMenuEntity> getAll() {
		return accesoMenuDAO.find(getQueryString(), this.offset, this.maxResults, getParameters());
	}

	//-- overridable --//
	/** Generate query string */
	protected String generateQueryString(String where, String orderBy) {
		final StringBuffer sb = new StringBuffer(256);
		sb.append("FROM " + AccesoMenuEntity.class.getName());
		if (!Strings.isBlank(where)) {
			sb.append(" WHERE "+where);
		}
		if (!Strings.isBlank(orderBy)) {
			sb.append(" ORDER BY "+orderBy);
		}
		return sb.toString();
	}
}
