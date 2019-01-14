/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

/**
 *
 * @author komilo
 */
public enum SortDirection {
    ASCENDING ("ASC"),
    DESCENDING ("DESC"),
    UNSORTED ("");
    
    private final String queryString;

    private SortDirection(String queryString) {
        this.queryString = queryString;
    }
    
    public String queryString() {
        return this.queryString;
    }
}
