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
public class LikeFilterParams extends FilterParams<LikeFilterParam> {

    protected LikeFilterParams() {
        super(new ArrayList<LikeFilterParam>());
    }

    protected LikeFilterParams(Collection<LikeFilterParam> params) {
        super(params);
    }

    protected LikeFilterParams(LikeFilterParam... params) {
        super(Arrays.asList(params));
    }

    protected LikeFilterParams(Map<String, Object> map) {
        this();
        for (String key : map.keySet()) {
            this.params.add(LikeFilterParam.from(key, map.get(key)));
        }
    }

    public static LikeFilterParams from(Collection<LikeFilterParam> filterParams) {
        return new LikeFilterParams(filterParams);
    }

    public static LikeFilterParams from(LikeFilterParam... filterParams) {
        return new LikeFilterParams(filterParams);
    }

    public static LikeFilterParams from(String field, Object value) {
        return new LikeFilterParams(LikeFilterParam.from(field, value));
    }

    public static LikeFilterParams from(Map<String, Object> map) {
        return new LikeFilterParams(map);
    }

    @Override
    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (FilterParam param1 : this.params) {
            LikeFilterParam param = new LikeFilterParam(param1.field, param1.value);
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
            LikeFilterParam param = new LikeFilterParam(param1.field, param1.value);
            param.setQueryParam(query);
        }
    }

}
