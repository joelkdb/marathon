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
public class NoCurrentMarathonException extends Exception {

    /**
     * Creates a new instance of <code>NoCurrentMarathonException</code> without
     * detail message.
     */
    public NoCurrentMarathonException() {
    }

    /**
     * Constructs an instance of <code>NoCurrentMarathonException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoCurrentMarathonException(String msg) {
        super(msg);
    }
}
