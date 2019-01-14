/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.PieceJointeDAOBeanLocal;
import com.jsoft.marathon.entities.PieceJointe;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.service.GenericServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class PieceJointeServiceBean extends GenericServiceBean<PieceJointe, Long>
        implements PieceJointeServiceBeanRemote {

    @EJB
    private PieceJointeDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<PieceJointe, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(PieceJointe e) {
        return e.getId();
    }

}