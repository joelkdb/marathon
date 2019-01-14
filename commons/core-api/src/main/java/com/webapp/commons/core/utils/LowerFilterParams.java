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
public class LowerFilterParams extends FilterParams<LowerFilterParam> {

    protected LowerFilterParams() {
        super(new ArrayList<LowerFilterParam>());
    }

    protected LowerFilterParams(Collection<LowerFilterParam> params) {
        super(params);
    }

    protected LowerFilterParams(LowerFilterParam... params) {
        super(Arrays.asList(params));
    }

    protected LowerFilterParams(Map<String, Object> map) {
        this();
        for (String key : map.keySet()) {
            this.params.add(LowerFilterParam.from(key, map.get(key)));
        }
    }

    public static LowerFilterParams from(Collection<LowerFilterParam> filterParams) {
        return new LowerFilterParams(filterParams);
    }

    public static LowerFilterParams from(LowerFilterParam... filterParams) {
        return new LowerFilterParams(filterParams);
    }

    public static LowerFilterParams from(String field, Object value) {
        return new LowerFilterParams(LowerFilterParam.from(field, value));
    }

    public static LowerFilterParams from(Map<String, Object> map) {
        return new LowerFilterParams(map);
    }

    @Override
    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (FilterParam param1 : this.params) {
            LowerFilterParam param = new LowerFilterParam(param1.field, param1.value);
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
