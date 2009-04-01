package org.g2p.tracker.model.models;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import javax.persistence.EntityNotFoundException;
import org.g2p.tracker.model.daos.RolesDAO;
import org.g2p.tracker.model.entities.RolesEntity;
import org.zkoss.lang.Strings;

public class RolesModel {
	@Resource
	protected RolesDAO rolesDAO;

	protected RolesEntity selected;
	protected String queryString;
	protected String where;
	protected String orderBy;
	protected int offset;
	protected int maxResults;
	protected Map<String, ?> parameters;

	public RolesDAO getDAO() {
		return rolesDAO;
	}

	public void setDAO(RolesDAO rolesDAO) {
		this.rolesDAO = this.rolesDAO;
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

	public Map<String,?> getParameters() {
		return this.parameters;
	}

	public void setParameters(Map<String,?> params) {
		this.parameters = params;
	}

	//-- DB access on the selected bean --//
	public void persist() {
		rolesDAO.persist(selected);
	}

	public void merge() throws EntityNotFoundException {
		rolesDAO.merge(selected);
	}

	public void delete() throws EntityNotFoundException {
		rolesDAO.delete(selected);
	}

	public List<RolesEntity> getAll() {
		return rolesDAO.find(getQueryString(), this.offset, this.maxResults, getParameters());
	}

	//-- overridable --//
	/** Generate query string */
	protected String generateQueryString(String where, String orderBy) {
		final StringBuffer sb = new StringBuffer(256);
		sb.append("FROM " + RolesEntity.class.getName());
		if (!Strings.isBlank(where)) {
			sb.append(" WHERE "+where);
		}
		if (!Strings.isBlank(orderBy)) {
			sb.append(" ORDER BY "+orderBy);
		}
		return sb.toString();
	}
}
