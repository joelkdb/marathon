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
public class InsufficientJokersNumberException extends Exception {

    /**
     * Creates a new instance of <code>InsufficientJokersNumberException</code>
     * without detail message.
     */
    public InsufficientJokersNumberException() {
    }

    /**
     * Constructs an instance of <code>InsufficientJokersNumberException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InsufficientJokersNumberException(String msg) {
        super(msg);
    }
}
