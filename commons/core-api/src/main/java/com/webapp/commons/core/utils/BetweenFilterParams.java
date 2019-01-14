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
public class BetweenFilterParams extends FilterParams<BetweenFilterParam> {

    protected BetweenFilterParams() {
        super(new ArrayList<BetweenFilterParam>());
    }

    protected BetweenFilterParams(Collection<BetweenFilterParam> params) {
        super(params);
    }

    protected BetweenFilterParams(BetweenFilterParam... params) {
        super(Arrays.asList(params));
    }

    protected BetweenFilterParams(Map<String, Object[]> map) {
        this();
        for (String key : map.keySet()) {
            this.params.add(BetweenFilterParam.from(key, map.get(key)[0],map.get(key)[1]));
        }
    }

    public static BetweenFilterParams from(Collection<BetweenFilterParam> filterParams) {
        return new BetweenFilterParams(filterParams);
    }

    public static BetweenFilterParams from(BetweenFilterParam... filterParams) {
        return new BetweenFilterParams(filterParams);
    }

    public static BetweenFilterParams from(String field, Object value1,Object value2) {
        return new BetweenFilterParams(BetweenFilterParam.from(field, value1,value2));
    }

    public static BetweenFilterParams from(Map<String, Object[]> map) {
        return new BetweenFilterParams(map);
    }

    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (BetweenFilterParam param : this.params) {
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
        for (BetweenFilterParam param : this.params) {
            param.setQueryParam(query);
        }
    }
}
