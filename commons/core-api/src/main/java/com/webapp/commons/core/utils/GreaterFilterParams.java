/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author Persistence
 */
public class GreaterFilterParams extends FilterParams<GreaterFilterParam> {

    protected GreaterFilterParams() {
        super(new ArrayList<GreaterFilterParam>());
    }

    protected GreaterFilterParams(Collection<GreaterFilterParam> params) {
        super(params);
    }

    protected GreaterFilterParams(GreaterFilterParam... params) {
        super(Arrays.asList(params));
    }

    protected GreaterFilterParams(Map<String, Object> map) {
        this();
        for (String key : map.keySet()) {
            this.params.add(GreaterFilterParam.from(key, map.get(key)));
        }
    }

    public static GreaterFilterParams from(Collection<GreaterFilterParam> filterParams) {
        return new GreaterFilterParams(filterParams);
    }

    public static GreaterFilterParams from(GreaterFilterParam... filterParams) {
        return new GreaterFilterParams(filterParams);
    }

    public static GreaterFilterParams from(String field, Object value) {
        return new GreaterFilterParams(GreaterFilterParam.from(field, value));
    }

    public static GreaterFilterParams from(Map<String, Object> map) {
        return new GreaterFilterParams(map);
    }

    @Override
    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (FilterParam param1 : this.params) {
            GreaterFilterParam param = new GreaterFilterParam(param1.field, param1.value);
            if (param.isValid()) {
                if (i >= 1) {
                    builder.append(" AND ");
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
            GreaterFilterParam param = new GreaterFilterParam(param1.field, param1.value);
            param.setQueryParam(query);
        }
    }

}
