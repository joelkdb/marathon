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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author komilo
 */
public abstract class GenericDAOBean<E extends Serializable, ID> implements GenericDAOBeanLocal<E, ID> {

    /**
     * Gerer les transactions liés aux methodes
     */

    @PersistenceContext
    protected EntityManager em;

    private final Class<E> entityClass;

    public GenericDAOBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void addOne(E e) {
        this.em.persist(e);
    }

    @Override
    public E updateOne(E e) {
        return this.em.merge(e);
    }

    @Override
    public void deleteOne(E e) {
        this.em.remove(this.em.merge(e));
    }

    @Override
    public void deleteOne(ID id) {
        this.em.remove(this.getOne(id));
    }

    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM " + this.entityClass.getSimpleName();
        Query query = this.em.createQuery(jpql);
        query.executeUpdate();
    }

    @Override
    public E getOne(ID id) {
        return this.em.find(this.entityClass, id);
    }

    @Override
    public List<E> getAll() {
        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e";
        Query query = this.em.createQuery(jpql);
        return query.getResultList();
    }

//    @Override
//    public List<E> getAll(String sortProperty, boolean sortAsc) {
//        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
//                + "ORDER BY e." + sortProperty + " " + (sortAsc ? "ASC" : "DESC");
//        Query query = this.em.createQuery(jpql);
//        return query.getResultList();
//    }
    @Override
    public List<E> getAll(SortParams sortParams) {
        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + sortParams.queryChunk();
        Query query = this.em.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public List<E> getAll(FilterParams filterParams) {
        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + filterParams.queryChunkWithWhere();
        Query query = this.em.createQuery(jpql);
        filterParams.setQueryParams(query);
        return query.getResultList();
    }

    @Override
    public List<E> getAll(SortParams sortParams, FilterParams filterParams) {
        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + (filterParams == null ? "" : filterParams.queryChunkWithWhere())
                + (sortParams == null ? "" : sortParams.queryChunk());
        Query query = this.em.createQuery(jpql);
        if (filterParams != null) {
            filterParams.setQueryParams(query);
        }
        return query.getResultList();
    }

//    @Override
//    public List<E> getAll(int first, int size, String sortProperty, boolean sortAsc) {
//        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
//                + "ORDER BY e." + sortProperty + " " + (sortAsc ? "ASC" : "DESC");
//        Query query = this.em.createQuery(jpql);
//        query.setFirstResult(first);
//        query.setMaxResults(size);
//        return query.getResultList();
//    }
    @Override
    public List<E> getAll(int first, int size, SortParams sortParams) {
        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + sortParams.queryChunk();
        Query query = this.em.createQuery(jpql);
        query.setFirstResult(first);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<E> getAll(int first, int size, SortParams sortParams, FilterParams filterParams) {
        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + filterParams.queryChunkWithWhere()
                + sortParams.queryChunk();
        Query query = this.em.createQuery(jpql);
        filterParams.setQueryParams(query);
        query.setFirstResult(first);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<E> getAll(IListFilter listFilter, SortParams sortParams) {
        return listFilter.getQuery(this.em, sortParams)
                .getResultList();
    }

    @Override
    public List<E> getAll(IListFilter listFilter, SortParams sortParams, Integer max) {
        Query q = listFilter.getQuery(this.em, sortParams);
        if (max != null && max > 0) {
            q.setMaxResults(max);
        }
        return q.getResultList();
    }

    @Override
    public List<E> getAll(int first, int size, IListFilter listFilter, SortParams sortParams) {
        return listFilter.getQuery(this.em, sortParams)
                .setFirstResult(first)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Long count(IListFilter listFilter) {
        return (Long) listFilter.getCountQuery(this.em)
                .getSingleResult();
    }

    @Override
    public Long count() {
        String jpql = "SELECT COUNT(e) FROM " + this.entityClass.getSimpleName() + " e";
        Query query = this.em.createQuery(jpql);
        return (Long) query.getSingleResult();
    }

    @Override
    public boolean exists(ID id) {
        return this.getOne(id) != null;
    }
}
