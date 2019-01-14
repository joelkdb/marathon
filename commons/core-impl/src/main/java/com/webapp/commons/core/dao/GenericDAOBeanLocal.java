/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.utils.FilterParams;
import com.webapp.commons.core.utils.IListFilter;
import com.webapp.commons.core.utils.SortParams;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author komilo
 * @param <E> L'entité qui implemente le GéneriqueDaoBean
 * @param <ID> La clé primaire de l'entité
 */
public interface GenericDAOBeanLocal<E extends Serializable, ID> {

    /**
     * Persiste l'objet passé en paramètre.
     *
     * @param e L'objet à persister.
     */
    void addOne(E e);

    E updateOne(E e);

    void deleteOne(E e);

    void deleteOne(ID id);

    void deleteAll();

    E getOne(ID id);

    List<E> getAll();

//    List<E> getAll(String sortProperty, boolean sortAsc);
    List<E> getAll(SortParams sortParams);

    List<E> getAll(SortParams sortParams, FilterParams filterParams);

    List<E> getAll(FilterParams filterParams);

//    List<E> getAll(int first, int size, String sortProperty, boolean sortAsc);
    List<E> getAll(int first, int size, SortParams sortParams);

    List<E> getAll(int first, int size, SortParams sortParams, FilterParams filterParams);

    List<E> getAll(IListFilter listFilter, SortParams sortParams);
    
     List<E> getAll(IListFilter listFilter, SortParams sortParams,Integer max);

    List<E> getAll(int first, int size, IListFilter listFilter, SortParams sortParams);

    Long count();

    Long count(IListFilter listFilter);

    /**
     * Vérifie si l'objet entité dont l'identifiant est passé en paramètre
     * existe dans l'unité de persistence.
     *
     * @param id L'identifiant de l'objet entité à vérifier.
     * @return <code>true</code> si l'objet existe.
     */
    boolean exists(ID id);
}
