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

/**
 *
 * @author komilo
 */
public class SortParams implements Serializable {

    private final List<SortParam> params;

    private SortParams() {
        this.params = new ArrayList<>();
    }

    private SortParams(Collection<SortParam> params) {
        this();
        this.params.addAll(params);
    }

    private SortParams(SortParam... sortParams) {
        this();
        this.params.addAll(Arrays.asList(sortParams));
    }

    private SortParams(SortParam sortParam) {
        this();
        this.params.add(sortParam);
    }

    public static SortParams from(Collection<SortParam> sortParams) {
        return new SortParams(sortParams);
    }
    public static SortParams from(SortParam... sortParams) {
        return new SortParams(sortParams);
    }
    public static SortParams from(SortParam sortParam) {
        return new SortParams(sortParam);
    }
    public static SortParams from(String field, SortDirection order) {
        return new SortParams(SortParam.from(field, order));
    }
    public static SortParams from(String field, boolean sortAsc) {
        return new SortParams(SortParam.from(field, sortAsc));
    }

    public String queryChunk() {
        return this.queryChunk(SortParam.DEFAULT_PREFIX);
    }

    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (SortParam param : this.params) {
            if (param.isSorted()) {
                if (i >= 1) {
                    builder.append(", ");
                }
                builder.append(param.queryChunk(prefix));
                i++;
            }
        }
        String chunk = builder.toString();
        return chunk.isEmpty() ? chunk : " ORDER BY " + chunk;
    }
}
