/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.daos;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.g2p.tracker.model.entities.AccesoMenuEntity;
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
public class AccesoMenuDAO {

    @Resource
    protected BasicDAO basicDAO;

    public BasicDAO getAccesoMenuDao() {
        return basicDAO;
    }

    public void setAccesoMenuDao(BasicDAO basicDao) {
        this.basicDAO = basicDao;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<AccesoMenuEntity> find(String queryString, int offset, int maxResults, Map<String, ?> params) {
                List<?> all = this.basicDAO.find(queryString, offset, maxResults, params);
                return (List<AccesoMenuEntity>) all;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void persist(AccesoMenuEntity accesoMenu) {
        this.basicDAO.persist(accesoMenu);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(AccesoMenuEntity accesoMenu) throws EntityNotFoundException {
        this.basicDAO.remove(AccesoMenuEntity.class, accesoMenu.getAccesoMenuId());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void merge(AccesoMenuEntity accesoMenu) throws EntityNotFoundException {
        this.basicDAO.merge(accesoMenu);
    }
}
