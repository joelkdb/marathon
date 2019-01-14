/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author komilo
 */
public abstract class BaseListFilter implements IListFilter {

    public Map<String, Object> filters;
    private boolean columnFiltering = false;

    @Override
    public Query getQuery(EntityManager em, SortParams sortParams) {
        return this.setParameters(em.createQuery(this.buildBaseJPQL() + sortParams.queryChunk()));
    }

    @Override
    public Query getCountQuery(EntityManager em) {
        return this.setParameters(em.createQuery(this.buildBaseJPQL(true)));
    }

    private String buildBaseJPQL() {
        return this.buildBaseJPQL(false);
    }

    public String buildColumnJPQL(String prefix) {
        columnFiltering = false;
        String s = " ";
        if (filters != null && !filters.isEmpty()) {
            columnFiltering = true;
            s += "AND " + LikeFilterParams.from(filters).queryChunk(prefix);
        }
        return s;
    }

    public abstract String buildBaseJPQL(boolean count);

    public Query setParameters(Query query) {
        if (columnFiltering) {
            LikeFilterParams.from(filters).setQueryParams(query);
        }
        return query;
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Override
    public Map<String, Object> getColumnsFilters() {
        return this.filters;
    }

    @Override
    public void setColumnsFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

}
