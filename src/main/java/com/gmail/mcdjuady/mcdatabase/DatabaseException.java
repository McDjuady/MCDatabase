/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase;

/**
 *
 * @author McDjuady
 */
public class DatabaseException extends Exception {
    
    public DatabaseException() {
        super();
    }
    
    public DatabaseException(String message) {
        super(message);
    }
    
    public DatabaseException(String message, Throwable cause) {
        super(message,cause);
    }
    
    public DatabaseException(Throwable cause) {
        super(cause);
    }
    
    protected DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);
    }
    
}
