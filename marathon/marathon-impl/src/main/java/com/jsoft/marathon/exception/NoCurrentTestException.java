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
public class NoCurrentTestException extends Exception {

    /**
     * Creates a new instance of <code>NoCurrentTestException</code> without
     * detail message.
     */
    public NoCurrentTestException() {
    }

    /**
     * Constructs an instance of <code>NoCurrentTestException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoCurrentTestException(String msg) {
        super(msg);
    }
}
