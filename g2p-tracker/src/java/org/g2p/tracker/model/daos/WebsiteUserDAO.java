/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.daos;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.g2p.tracker.model.entities.WebsiteUserEntity;
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
public class WebsiteUserDAO {

    @Resource
    protected BasicDAO basicDAO;

    public BasicDAO getWebsiteUserDao() {
        return basicDAO;
    }

    public void setWebsiteUserDao(BasicDAO basicDao) {
        this.basicDAO = basicDao;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<WebsiteUserEntity> find(String queryString, int offset, int maxResults, Map<String, ?> params) {
                List<?> all = this.basicDAO.find(queryString, offset, maxResults, params);
                return (List<WebsiteUserEntity>) all;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void persist(WebsiteUserEntity user) {
        this.basicDAO.persist(user);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WebsiteUserEntity user) throws EntityNotFoundException {
        this.basicDAO.remove(WebsiteUserEntity.class, user.getUserId());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void merge(WebsiteUserEntity user) throws EntityNotFoundException {
        this.basicDAO.merge(user);
    }

}
