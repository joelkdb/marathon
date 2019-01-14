/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author komilo
 */
public interface IListFilter extends Serializable {

    Query getQuery(EntityManager em, SortParams sortParams);

    Query getCountQuery(EntityManager em);

    /**
     * Récupère la liste des paramètres passé au filtre de primes faces sur le
     * data table.
     *
     * @return un map des paramètres
     */
    public Map<String, Object> getColumnsFilters();

    public void setColumnsFilters(Map<String, Object> filters);
}
