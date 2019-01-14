/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import javax.ejb.Remote;

/**
 *
 * @author persistence
 */
@Remote
public interface LogServiceBeanRemote {

    void trace(String message);

    void trace(String message, String categoryCode);

    void debug(String message);

    void debug(String message, String categoryCode);

    void info(String message);

    void info(String message, String categoryCode);

    void warn(String message);

    void warn(String message, String categoryCode);

    void error(String message);

    void error(String message, String categoryCode);
}
