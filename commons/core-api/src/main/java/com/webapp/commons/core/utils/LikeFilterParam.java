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
public class LikeFilterParam extends FilterParam {

    protected LikeFilterParam(String field, Object value) {
        super(field, value);
    }

    public static LikeFilterParam from(String field, Object value) {
        return new LikeFilterParam(field, value);
    }

    @Override
    public String queryChunk(String prefix) {
        if (this.isNotValid()) {
            return "";
        }
       
        return getQueryChunk(prefix);
    }

    @Override
    protected String getQueryChunk(String prefix) {
        return "UPPER(" + prefix + "." + this.field + ") LIKE UPPER(:" + this.getFieldQueryParameter() + ")";
    }

    @Override
    public void setQueryParam(Query query) {
        if (this.isValid()) {
            query.setParameter(this.getFieldQueryParameter(), "%" + this.value + "%");
        }
    }
}
