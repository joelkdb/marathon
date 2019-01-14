/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.ExercicePieceJointe;
import com.jsoft.marathon.entities.ExercicePieceJointePK;
import com.webapp.commons.core.dao.GenericDAOBean;
import javax.ejb.Stateless;


/**
 *
 * @author joelkdb
 */
@Stateless
public class ExercicePieceJointeDAOBean extends GenericDAOBean<ExercicePieceJointe, ExercicePieceJointePK>
        implements ExercicePieceJointeDAOBeanLocal {

    public ExercicePieceJointeDAOBean() {
        super(ExercicePieceJointe.class);
    }
}
