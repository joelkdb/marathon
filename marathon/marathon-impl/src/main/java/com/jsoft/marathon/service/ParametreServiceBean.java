/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.ParametreDAOBeanLocal;
import com.jsoft.marathon.entities.Parametre;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.service.GenericServiceBean;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ParametreServiceBean extends GenericServiceBean<Parametre, String>
        implements ParametreServiceBeanRemote {

    @EJB
    private ParametreDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Parametre, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Parametre e) {
        return e.getCode();
    }

    @Override
    public List<String> getLines(String texte) throws Exception {
        List<String> lines = Arrays.asList(texte.split(this.dao.getOne("LINESEP").getValeur()));
        return lines;
    }

    @Override
    public String getValeurParametre(String key) throws Exception {
        return this.dao.getOne(key).getValeur();
    }

}
