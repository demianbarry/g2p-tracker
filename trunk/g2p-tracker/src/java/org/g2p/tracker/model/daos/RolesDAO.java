package org.g2p.tracker.model.daos;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import org.g2p.tracker.model.entities.RolesEntity;
import org.hibernate.Session;


public class RolesDAO {
	@Resource
	protected Session session;

	public Session getBasicDao() {
		return session;
	}

	public void setBasicDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<RolesEntity> find(String queryString, int offset, int maxResults, Map<String,?> params) {
		List<?> all = this.session.createQuery("RolesEntity.findAll").list();
		return (List<RolesEntity>) all;
	}

	public void persist(RolesEntity rol){
		this.session.persist(rol);
	}

	public void delete(RolesEntity rol) throws EntityNotFoundException {
		this.session.delete(rol);
	}

	public void merge(RolesEntity rol) throws EntityNotFoundException {
		this.session.merge(rol);
	}
}
