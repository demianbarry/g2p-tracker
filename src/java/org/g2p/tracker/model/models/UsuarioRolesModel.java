/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.exceptions.PreexistingEntityException;
import org.g2p.tracker.model.daos.UsuarioRolesEntityJpaController;
import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import org.zkoss.lang.Strings;


/**
 *
 * @author nacho
 */

public class UsuarioRolesModel {

    protected UsuarioRolesEntityJpaController usuarioRolesDAO;

	protected UsuarioRolesEntity selected;
	protected String queryString;
	protected String where;
	protected String orderBy;
	protected int offset;
	protected int maxResults;
	protected Map<String, ?> parameters;

	public UsuarioRolesEntityJpaController getDAO() {
		return usuarioRolesDAO;
	}

	public void setDAO(UsuarioRolesEntityJpaController usuarioRolesDAO) {
		this.usuarioRolesDAO = usuarioRolesDAO;
	}

	public UsuarioRolesEntity getSelected() {
		return this.selected;
	}

	public void setSelected(UsuarioRolesEntity todo) {
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
	public void persist() throws PreexistingEntityException, Exception {
		usuarioRolesDAO.create(selected);
	}

	public void merge() throws EntityNotFoundException, NonexistentEntityException, Exception {
		usuarioRolesDAO.edit(selected);
	}

	public void delete() throws EntityNotFoundException, NonexistentEntityException {
		usuarioRolesDAO.destroy(selected.getUsuarioRolesEntityPK());
	}

	public List<UsuarioRolesEntity> getAll() {
		return usuarioRolesDAO.findUsuarioRolesEntityEntities();
	}

	//-- overridable --//
	/** Generate query string */
	protected String generateQueryString(String where, String orderBy) {
		final StringBuffer sb = new StringBuffer(256);
		sb.append("FROM " + UsuarioRolesEntity.class.getName());
		if (!Strings.isBlank(where)) {
			sb.append(" WHERE "+where);
		}
		if (!Strings.isBlank(orderBy)) {
			sb.append(" ORDER BY "+orderBy);
		}
		return sb.toString();
	}

}
