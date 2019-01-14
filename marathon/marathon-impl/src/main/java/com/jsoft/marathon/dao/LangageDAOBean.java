/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.Langage;
import com.webapp.commons.core.dao.GenericDAOBean;
import javax.ejb.Stateless;


/**
 *
 * @author joelkdb
 */
@Stateless
public class LangageDAOBean extends GenericDAOBean<Langage, Long>
        implements LangageDAOBeanLocal {

    public LangageDAOBean() {
        super(Langage.class);
    }
}
