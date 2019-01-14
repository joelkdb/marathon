/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.utils.FilterParams;
import com.webapp.commons.core.utils.IListFilter;
import com.webapp.commons.core.utils.SortParams;


/**
 *
 * @author persitence
 * @param <E> l'entité implementant le generique
 * @param <ID> son identifiant
 */
public abstract class GenericServiceBean<E extends Serializable, ID>
        implements GenericServiceBeanRemote<E, ID> {

    @EJB
    protected LogServiceBeanRemote logService;

    @Resource
    protected EJBContext context;

    protected abstract GenericDAOBeanLocal<E, ID> getDAO();

    protected final Logger logger;

    public GenericServiceBean() {
        this.logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public void addOne(E e) {
        this.getDAO().addOne(e);
    }

    @Override
    public void addAll(Collection<E> es) {
        for (E e : es) {
            this.getDAO().addOne(e);
        }
    }

    @Override
    public E updateOne(E e) {
        return this.getDAO().updateOne(e);
    }

    @Override
    public Collection<E> updateAll(Collection<E> es) {
        Collection<E> c = new ArrayList<>();
        for (E e : es) {
            this.getDAO().updateOne(e);
            c.add(e);
        }
        return c;
    }

    @Override
    public void deleteOne(E e) {
        this.getDAO().deleteOne(e);
    }

    @Override
    public void deleteAll(Collection<E> es) {
        for (E e : es) {
            this.getDAO().deleteOne(e);
        }
    }

    @Override
    public void deleteOne(ID id) {
        E e = this.getDAO().getOne(id);
        this.getDAO().deleteOne(e);
    }

    @Override
    public void deleteOne(Collection<ID> ids) {
        for (ID id : ids) {
            this.getDAO().deleteOne(id);
        }
    }

    @Override
    public void deleteAll() {
        this.getDAO().deleteAll();
    }

    @Override
    public E getOne(ID id) {
        return this.getDAO().getOne(id);
    }

    @Override
    public List<E> getAll(Collection<ID> ids) {
        List<E> list = new LinkedList<>();
        for (ID id : ids) {
            list.add(this.getDAO().getOne(id));
        }
        return list;
    }

    @Override
    public List<E> getAll() {
        return this.getDAO().getAll();
    }

    @Override
    public List<E> getAll(String sortProperty, boolean sortAsc) {
        return this.getDAO().getAll(SortParams.from(sortProperty, sortAsc));
    }

    @Override
    public List<E> getAll(SortParams sortParams) {
        return this.getDAO().getAll(sortParams);
    }
    
    @Override
    public List<E> getAll(SortParams sortParams, FilterParams filterParams) {
        return this.getDAO().getAll(sortParams, filterParams);
    }

    @Override
    public List<E> getAll(int first, int size, String sortProperty, boolean sortAsc) {
        return this.getDAO().getAll(first, size, SortParams.from(sortProperty, sortAsc));
    }

    @Override
    public List<E> getAll(int first, int size, SortParams sortParams) {
        return this.getDAO().getAll(first, size, sortParams);
    }
    
    @Override
    public List<E> getAll(FilterParams filterParams) {
        return this.getDAO().getAll(filterParams);
    }

    @Override
    public List<E> getAll(int first, int size, SortParams sortParams, FilterParams filterParams) {
        return this.getDAO().getAll(first, size, sortParams, filterParams);
    }

    @Override
    public List<E> getAll(IListFilter listFilter) {
        return this.getDAO().getAll(listFilter, SortParams.from());
    }

    @Override
    public List<E> getAll(IListFilter listFilter, Integer max) {
        return this.getDAO().getAll(listFilter, SortParams.from(), max);
    }

    @Override
    public List<E> getAll(IListFilter listFilter, SortParams sortParams) {
        return this.getDAO().getAll(listFilter, sortParams);
    }

    @Override
    public List<E> getAll(int first, int size, IListFilter listFilter) {
        return this.getDAO().getAll(first, size, listFilter, SortParams.from());
    }

    @Override
    public List<E> getAll(int first, int size, IListFilter listFilter, SortParams sortParams) {
        return this.getDAO().getAll(first, size, listFilter, sortParams);
    }

    @Override
    public Long count() {
        return this.getDAO().count();
    }

    @Override
    public Long count(IListFilter listFilter) {
        return this.getDAO().count(listFilter);
    }

    @Override
    public boolean exists(E e) {
        return this.getDAO().getOne(this.getId(e)) != null;
    }

    @Override
    public boolean exists(ID id) {
        return this.getDAO().getOne(id) != null;
    }

    public EJBContext getContext() {
        return context;
    }

    public void setContext(EJBContext context) {
        this.context = context;
    }
    
    
}
