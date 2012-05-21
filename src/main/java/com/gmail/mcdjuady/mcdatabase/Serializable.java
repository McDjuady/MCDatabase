/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase;

/**
 *
 * @author McDjuady
 */
public interface Serializable {
    
    void save(DatabaseGroup group);
    void load(DatabaseGroup group);
    
}
