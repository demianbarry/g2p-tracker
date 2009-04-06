/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.daos;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.spring.jpa.BasicDAO;
import org.zkoss.spring.jpa.EntityNotFoundException;
import org.springframework.context.annotation.*;


/**
 *
 * @author nacho
 */
@Scope("idspace")
@Repository
public class UsuarioRolesDAO {

    @Resource
    protected BasicDAO basicDAO;

    public BasicDAO getUsuarioRolesDao() {
        return basicDAO;
    }

    public void setUsuarioRolesDao(BasicDAO basicDao) {
        this.basicDAO = basicDao;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<UsuarioRolesEntity> find(String queryString, int offset, int maxResults, Map<String, ?> params) {
                List<?> all = this.basicDAO.find(queryString, offset, maxResults, params);
                return (List<UsuarioRolesEntity>) all;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void persist(UsuarioRolesEntity UserRol) {
        this.basicDAO.persist(UserRol);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(UsuarioRolesEntity UserRol) throws EntityNotFoundException {
        this.basicDAO.remove(UsuarioRolesEntity.class, UserRol.getUsuarioRolesEntityPK());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void merge(UsuarioRolesEntity UserRol) throws EntityNotFoundException {
        this.basicDAO.merge(UserRol);
    }


}
