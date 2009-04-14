/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.daos;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.g2p.tracker.model.entities.MenuEntity;
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
public class MenuDAO {

    @Resource
    protected BasicDAO basicDAO;

    public BasicDAO getMenuDao() {
        return basicDAO;
    }

    public void setMenuDao(BasicDAO basicDao) {
        this.basicDAO = basicDao;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<MenuEntity> find(String queryString, int offset, int maxResults, Map<String, ?> params) {
                List<?> all = this.basicDAO.find(queryString, offset, maxResults, params);
                return (List<MenuEntity>) all;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void persist(MenuEntity menu) {
        this.basicDAO.persist(menu);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(MenuEntity menu) throws EntityNotFoundException {
        this.basicDAO.remove(MenuEntity.class, menu.getMenuId());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void merge(MenuEntity menu) throws EntityNotFoundException {
        this.basicDAO.merge(menu);
    }
}
