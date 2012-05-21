/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

/**
 *
 * @author McDjuady
 */
public class NoSuitableBridgeException extends Exception{
     public NoSuitableBridgeException() {
        super();
    }
    
    public NoSuitableBridgeException(String message) {
        super(message);
    }
    
    public NoSuitableBridgeException(String message, Throwable cause) {
        super(message,cause);
    }
    
    public NoSuitableBridgeException(Throwable cause) {
        super(cause);
    }
    
    protected NoSuitableBridgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
