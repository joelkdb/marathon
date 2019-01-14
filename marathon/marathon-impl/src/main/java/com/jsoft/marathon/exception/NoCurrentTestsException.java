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
public class NoCurrentTestsException extends Exception {

    /**
     * Creates a new instance of <code>NoCurrentTestException</code> without
     * detail message.
     */
    public NoCurrentTestsException() {
    }

    /**
     * Constructs an instance of <code>NoCurrentTestException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoCurrentTestsException(String msg) {
        super(msg);
    }
}
