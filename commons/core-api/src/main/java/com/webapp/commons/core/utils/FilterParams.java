/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author komilo
 */
public abstract class FilterParams<F extends FilterParam> implements Serializable {

    protected List<F> params;

    protected FilterParams() {
        this.params = new ArrayList<>();
    }

    protected FilterParams(Collection<F> params) {
        this();
        this.params.addAll(params);
    }

    protected FilterParams(F... params) {
        this();
        this.params.addAll(Arrays.asList(params));
    }

   

//    public static FilterParams from(Collection<FilterParam> filterParams) {
//        return new FilterParams(filterParams);
//    }
//
//    public static FilterParams from(FilterParam... filterParams) {
//        return new FilterParams(filterParams);
//    }
//
//    public static FilterParams from(String field, Object value) {
//        return new FilterParams(FilterParam.from(field, value));
//    }
//
//    public static FilterParams from(Map<String, Object> map) {
//        return new FilterParams(map);
//    }
    public String queryChunk() {
        return this.queryChunk(F.DEFAULT_PREFIX);
    }

    public abstract String queryChunk(String prefix);

//    public String queryChunkLike(String prefix) {
//        StringBuilder builder = new StringBuilder();
//        int i = 0;
//        for (FilterParam param : this.params) {
//            if (param.isValid()) {
//                if (i >= 1) {
//                    builder.append(" AND ");
//                }
//                builder.append(param.queryChunkLike(prefix));
//                i++;
//            }
//        }
//        return builder.toString();
//    }
    public String queryChunkWithWhere() {
        return this.queryChunkWithWhere(F.DEFAULT_PREFIX);
    }

    public String queryChunkWithWhere(String prefix) {
        String chunk = this.queryChunk(prefix);
        return chunk.isEmpty() ? chunk : " WHERE " + chunk;
    }

    public abstract void setQueryParams(Query query);
//    {
//        for (F param : this.params) {
//            param.setQueryParam(query); 
//        }
//    }

    public List<F> getParams() {
        return params;
    }

    public boolean isEmpty() {
        return params == null || params.isEmpty();
    }

}
