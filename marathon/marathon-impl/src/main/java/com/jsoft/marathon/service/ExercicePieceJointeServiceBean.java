/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.ExercicePieceJointeDAOBeanLocal;
import com.jsoft.marathon.entities.ExercicePieceJointe;
import com.jsoft.marathon.entities.ExercicePieceJointePK;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.service.GenericServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ExercicePieceJointeServiceBean extends GenericServiceBean<ExercicePieceJointe, ExercicePieceJointePK>
        implements ExercicePieceJointeServiceBeanRemote {

    @EJB
    private ExercicePieceJointeDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<ExercicePieceJointe, ExercicePieceJointePK> getDAO() {
        return this.dao;
    }

    @Override
    public ExercicePieceJointePK getId(ExercicePieceJointe e) {
        return e.getExercicePieceJointePK();
    }

}