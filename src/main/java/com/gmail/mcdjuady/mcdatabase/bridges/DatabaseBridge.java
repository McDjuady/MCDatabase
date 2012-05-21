/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;

/**
 *
 * @author McDjuady
 */
public interface DatabaseBridge<T> {
    
    void translateAndSave(T t, DatabaseGroup group);
    T loadAndTranslate(DatabaseGroup group);
    
    boolean canTranslate(Object o);
    boolean canTranslate(DatabaseGroup group);
    
}
