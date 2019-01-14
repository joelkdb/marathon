/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author Persistence
 */
public class OrLikeFilterParams extends FilterParams<LikeFilterParam> {

    protected OrLikeFilterParams() {
        super(new ArrayList<LikeFilterParam>());
    }

    protected OrLikeFilterParams(Collection<LikeFilterParam> params) {
        super(params);
    }

    protected OrLikeFilterParams(LikeFilterParam... params) {
        super(Arrays.asList(params));
    }

    protected OrLikeFilterParams(Map<String, Object> map) {
        this();
        for (String key : map.keySet()) {
            this.params.add(LikeFilterParam.from(key, map.get(key)));
        }
    }

    public static OrLikeFilterParams from(Collection<LikeFilterParam> filterParams) {
        return new OrLikeFilterParams(filterParams);
    }

    public static OrLikeFilterParams from(LikeFilterParam... filterParams) {
        return new OrLikeFilterParams(filterParams);
    }

    public static OrLikeFilterParams from(String field, Object value) {
        return new OrLikeFilterParams(LikeFilterParam.from(field, value));
    }

    public static OrLikeFilterParams from(Map<String, Object> map) {
        return new OrLikeFilterParams(map);
    }

    public OrLikeFilterParams(String field, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (FilterParam param1 : this.params) {
            LikeFilterParam param = new LikeFilterParam(param1.field, param1.value);
            if (param.isValid()) {
                if (i >= 1) {
                    builder.append(" OR ");
                }
                builder.append(param.queryChunk(prefix));
                i++;
            }
        }
        return builder.toString();
    }

    @Override
    public void setQueryParams(Query query) {
        for (FilterParam param1 : this.params) {
            LikeFilterParam param = new LikeFilterParam(param1.field, param1.value);
            param.setQueryParam(query);
        }
    }

}
