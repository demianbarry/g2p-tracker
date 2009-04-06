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

@Scope("idspace")
@Repository
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


}
