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
public class NoLastTestException extends Exception {

    /**
     * Creates a new instance of <code>NoLastTestException</code> without detail
     * message.
     */
    public NoLastTestException() {
    }

    /**
     * Constructs an instance of <code>NoLastTestException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoLastTestException(String msg) {
        super(msg);
    }
}
