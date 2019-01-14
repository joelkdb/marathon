/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.filters;

import com.webapp.commons.core.utils.BaseListFilter;
import javax.persistence.Query;

/**
 *
 * @author Shell
 */
public class RoleFilter extends BaseListFilter {

    private Integer maxResult = null;
    private String label = null;

    @Override
    public String buildBaseJPQL(boolean count) {
        String jpql = "SELECT " + (count ? "COUNT(e)" : "e") + " FROM Role e "
                + "WHERE 1 = 1 "
                + (this.isEmpty(this.label) ? " " : "AND UPPER(e.code) LIKE UPPER(:code) ");
        return jpql;
    }

    @Override
    public Query setParameters(Query query) {
        if (!isEmpty(label)) {
            query.setParameter("label", label);
        }
        if (this.maxResult != null && this.maxResult > 0) {
            query.setMaxResults(this.maxResult);
        }
        return query;
    }

    public Integer getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
