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
public class EqualsFilterParams extends FilterParams<EqualsFilterParam> {

    protected EqualsFilterParams() {
        super(new ArrayList<EqualsFilterParam>());
    }

    protected EqualsFilterParams(Collection<EqualsFilterParam> params) {
        super(params);
    }

    protected EqualsFilterParams(EqualsFilterParam... params) {
        super(Arrays.asList(params));
    }

    protected EqualsFilterParams(Map<String, Object> map) {
        this();
        for (String key : map.keySet()) {
            this.params.add(EqualsFilterParam.from(key, map.get(key)));
        }
    }

    public static EqualsFilterParams from(Collection<EqualsFilterParam> filterParams) {
        return new EqualsFilterParams(filterParams);
    }

    public static EqualsFilterParams from(EqualsFilterParam... filterParams) {
        return new EqualsFilterParams(filterParams);
    }

    public static EqualsFilterParams from(String field, Object value) {
        return new EqualsFilterParams(EqualsFilterParam.from(field, value));
    }

    public static EqualsFilterParams from(Map<String, Object> map) {
        return new EqualsFilterParams(map);
    }

    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (EqualsFilterParam param : this.params) {
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
        for (EqualsFilterParam param : this.params) {
            param.setQueryParam(query);
        }
    }
}
