/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jsoft.marathon.exception;

/**
 *
 * @author joelkdb
 */
public class DisqualifiedTeamException extends Exception {

    /**
     * Creates a new instance of <code>DisqualifiedTeamException</code> without
     * detail message.
     */
    public DisqualifiedTeamException() {
    }

    /**
     * Constructs an instance of <code>DisqualifiedTeamException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DisqualifiedTeamException(String msg) {
        super(msg);
    }
}
