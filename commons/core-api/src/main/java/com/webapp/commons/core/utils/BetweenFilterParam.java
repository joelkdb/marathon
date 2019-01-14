/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import javax.persistence.Query;

/**
 *
 * @author Persistence
 */
public class BetweenFilterParam extends FilterParam {

    protected final Object value1;

    protected final Object value2;

    public BetweenFilterParam(String field, Object value1, Object value2) {
        super(field, null);
        this.value1 = value1;
        this.value2 = value2;
    }

    public static BetweenFilterParam from(String field, Object value1, Object value2) {
        return new BetweenFilterParam(field, value1, value2);
    }

    public String queryChunk() {
        return this.queryChunk(DEFAULT_PREFIX);
    }

    public String queryChunk(String prefix) {
        if (this.isNotValid()) {
            return "";
        }
        return this.getQueryChunk(prefix);
    }

    protected String getQueryChunk(String prefix) {
        return prefix + "." + this.field + "  BETWEEN :" + this.getFieldQueryParameter1() + " AND :" + this.getFieldQueryParameter2();
    }

    public void setQueryParam(Query query) {
        if (this.isValid()) {
            this.setQueryParamValue(query);
        }
    }

    protected void setQueryParamValue(Query query) {
        query.setParameter(this.getFieldQueryParameter1(), this.value1);
        query.setParameter(this.getFieldQueryParameter2(), this.value2);

    }

    protected String getFieldQueryParameter1() {
        return getFieldQueryParameter() + "1";
    }

    protected String getFieldQueryParameter2() {
        return getFieldQueryParameter() + "2";
    }
}
