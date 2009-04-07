/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.g2p.tracker.model.daos.WebsiteUserDAO;
import org.g2p.tracker.model.entities.WebsiteUserEntity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.lang.Strings;
import org.zkoss.spring.jpa.EntityNotFoundException;

/**
 *
 * @author nacho
 */
@Scope("idspace")
@Component
public class WebsiteUserModel {
    @Resource
	protected WebsiteUserDAO websiteUserDAO;

	protected WebsiteUserEntity selected;
	protected String queryString;
	protected String where;
	protected String orderBy;
	protected int offset;
	protected int maxResults;
	protected Map<String, ?> parameters;

	public WebsiteUserDAO getDAO() {
		return websiteUserDAO;
	}

	public void setDAO(WebsiteUserDAO websiteUserDAO) {
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

	public Map<String,?> getParameters() {
		return this.parameters;
	}

	public void setParameters(Map<String,?> params) {
		this.parameters = params;
	}

	//-- DB access on the selected bean --//
	public void persist() {
		websiteUserDAO.persist(selected);
	}

	public void merge() throws EntityNotFoundException {
		websiteUserDAO.merge(selected);
	}

	public void delete() throws EntityNotFoundException {
		websiteUserDAO.delete(selected);
	}

	public List<WebsiteUserEntity> getAll() {
		return websiteUserDAO.find(getQueryString(), this.offset, this.maxResults, getParameters());
	}

	//-- overridable --//
	/** Generate query string */
	protected String generateQueryString(String where, String orderBy) {
		final StringBuffer sb = new StringBuffer(256);
		sb.append("FROM " + WebsiteUserEntity.class.getName());
		if (!Strings.isBlank(where)) {
			sb.append(" WHERE "+where);
		}
		if (!Strings.isBlank(orderBy)) {
			sb.append(" ORDER BY "+orderBy);
		}
		return sb.toString();
	}
}
