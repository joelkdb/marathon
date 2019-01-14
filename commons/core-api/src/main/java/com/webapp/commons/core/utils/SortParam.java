/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.io.Serializable;

/**
 *
 * @author komilo
 */
public class SortParam implements Serializable {

    public final static String DEFAULT_PREFIX = "e";

    private final String field;
    private final SortDirection order;

    private SortParam() {
        this.field = null;
        this.order = SortDirection.UNSORTED;
    }

    private SortParam(String field) {
        this.field = field;
        this.order = SortDirection.ASCENDING;
    }

    private SortParam(String field, SortDirection order) {
        this.field = field;
        this.order = order;
    }

    private SortParam(String field, boolean sortAsc) {
        this.field = field;
        this.order = sortAsc ? SortDirection.ASCENDING : SortDirection.DESCENDING;
    }

    public static SortParam unsorted() {
        return new SortParam();
    }

    public static SortParam from(String field) {
        return new SortParam(field);
    }

    public static SortParam from(String field, SortDirection order) {
        return new SortParam(field, order);
    }

    public static SortParam from(String field, boolean sortAsc) {
        return new SortParam(field, sortAsc);
    }

    public String queryChunk() {
        return this.queryChunk(DEFAULT_PREFIX);
    }

    public String queryChunk(String prefix) {
        if (this.order == SortDirection.UNSORTED) {
            return this.order.queryString();
        }
        return prefix + "." + this.field + " " + this.order.queryString();
    }

    public boolean isSorted() {
        return this.field != null && this.order != SortDirection.UNSORTED;
    }

    @Override
    public String toString() {
        return "SortParam{" + "field=" + field + ", order=" + order + '}';
    }
}
