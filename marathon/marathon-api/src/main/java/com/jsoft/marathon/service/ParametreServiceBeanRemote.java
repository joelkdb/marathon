/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.Parametre;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface ParametreServiceBeanRemote extends GenericServiceBeanRemote<Parametre, String> {

    /**
     * Permet de récupérer les lignes d'un texte à partir d'un texte
     *
     * @param texte
     * @return
     * @throws java.lang.Exception
     */
    public List<String> getLines(String texte) throws Exception;

    /**
     * Permet de récupérer n'importe quelle valeur d'un paramètre à partir de sa
     * clé
     *
     * @param key la clé du paramètre
     * @return
     * @throws Exception
     */
    public String getValeurParametre(String key) throws Exception;
}
