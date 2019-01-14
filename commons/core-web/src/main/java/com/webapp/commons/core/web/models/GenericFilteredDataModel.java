/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.models;

import com.webapp.commons.core.entities.BaseEntity;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.utils.IListFilter;
import com.webapp.commons.core.utils.SortDirection;
import com.webapp.commons.core.utils.SortParam;
import com.webapp.commons.core.utils.SortParams;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * DataModel de base pour les opérations CRUD.
 *
 * @author komilo
 * @param <E> La classe entité.
 * @param <ID> Type de la clé primaire de l'entité.
 */
public class GenericFilteredDataModel<E extends BaseEntity, ID extends Serializable> extends LazyDataModel<E> {

    private final GenericServiceBeanRemote<E, ID> service;
    private IListFilter listFilter;
    private int rowCount = -1;

    public GenericFilteredDataModel(GenericServiceBeanRemote<E, ID> service, IListFilter listFilter) {
        this.service = service;
        this.listFilter = listFilter;
    }

    @Override
    public int getRowCount() {
//        if (this.rowCount == -1) {
        this.rowCount = this.service.count(this.listFilter).intValue();
//        }
        return this.rowCount;
    }

    @Override
    public Object getRowKey(E object) {
        return this.service.getId(object);
    }

    @Override
    public E getRowData(String rowKey) {
        List<E> list = (List<E>) getWrappedData();
        for (E e : list) {
            ID id = service.getId(e);
            if (String.valueOf(id).equalsIgnoreCase(rowKey)) {
                return e;
            }
        }
        return null;
    }

    public E get(int index) {
        try {
            return ((List<E>) getWrappedData()).get(index);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<E> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        if (filters != null && this.listFilter != null) {
            this.listFilter.setColumnsFilters(filters);
        }
        return this.service.getAll(first, pageSize, this.listFilter,
                this.buidSortParams(sortField, sortOrder));
    }

    @Override
    public List<E> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        if (filters != null && this.listFilter != null) {
            this.listFilter.setColumnsFilters(filters);
        }
        return this.service.getAll(first, pageSize, this.listFilter,
                this.buidSortParams(multiSortMeta));
    }

    private SortParams buidSortParams(String sortField, SortOrder sortOrder) {
        return SortParams.from(sortField, this.getSortDirection(sortOrder));
    }

    private SortParams buidSortParams(List<SortMeta> multiSortMeta) {
        List<SortParam> params = new LinkedList<>();
        for (SortMeta meta : multiSortMeta) {
            params.add(SortParam.from(meta.getSortField(), this.getSortDirection(meta.getSortOrder())));
        }
        return SortParams.from(params);
    }

    private SortDirection getSortDirection(SortOrder sortOrder) {
        switch (sortOrder) {
            case ASCENDING:
                return SortDirection.ASCENDING;
            case DESCENDING:
                return SortDirection.DESCENDING;
            default:
                return SortDirection.UNSORTED;
        }
    }

    public IListFilter getListFilter() {
        return listFilter;
    }

    public void setListFilter(IListFilter listFilter) {
        this.listFilter = listFilter;
    }

}
