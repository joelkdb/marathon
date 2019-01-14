/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author komilo
 */
public class FilterParam implements Serializable {

    public final static String DEFAULT_PREFIX = "e";
    
    protected final String field;
    protected final Object value;

    protected FilterParam(String field, Object value) {
        this.field = field;
        this.value = value;
    }


    public boolean isValid() {
        return this.field != null && (!this.field.isEmpty());
    }

    public boolean isNotValid() {
        return this.field == null || this.field.isEmpty();
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
        return prefix + "." + this.field + " = :" + this.getFieldQueryParameter();
    }

    public void setQueryParam(Query query) {
        if (this.isValid()) {
            this.setQueryParamValue(query);
        }
    }
    
    protected void setQueryParamValue(Query query) {
        query.setParameter(this.getFieldQueryParameter(), this.value);
    }
    
    protected String getFieldQueryParameter() {
        return this.field == null ? "" : this.field.replace('.', '_');
    }

    @Override
    public String toString() {
        return "FilterParam{" + "field=" + field + ", value=" + value + '}';
    }
}
