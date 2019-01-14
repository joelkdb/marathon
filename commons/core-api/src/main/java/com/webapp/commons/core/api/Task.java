/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.api;

/**
 *
 * @author El Phenox
 */

public interface Task {
    String getName();
    String getDescription();
    String getGroup();
    void run();
}
