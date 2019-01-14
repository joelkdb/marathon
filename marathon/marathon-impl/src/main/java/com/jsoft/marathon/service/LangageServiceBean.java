/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.LangageDAOBeanLocal;
import com.jsoft.marathon.entities.Langage;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.service.GenericServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class LangageServiceBean extends GenericServiceBean<Langage, Long>
        implements LangageServiceBeanRemote {

    @EJB
    private LangageDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Langage, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Langage e) {
        return e.getId();
    }

}