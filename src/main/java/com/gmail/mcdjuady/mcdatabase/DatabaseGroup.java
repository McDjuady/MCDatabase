/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase;

import java.util.Collection;

/**
 *
 * @author McDjuady
 */
public interface DatabaseGroup {
    
    String getString(String name);
    int getInt(String name);
    float getFloat(String name);
    double getDouble(String name);
    boolean getBoolean(String name);
    byte getByte(String name);
    DatabaseGroup getSubgroup(String name);
    
    String addString(String name, String val);
    int addInt(String name, int val);
    float addFloat(String name, float val);
    double addDouble(String name, double val);
    boolean addBoolean(String name, boolean val);
    byte addByte(String name, byte val);
    DatabaseGroup createSubgroup(String name);
    
    Object destroyEntry(String name);
    DatabaseGroup destroySubgroup(String name);
    
    //DatabaseGroup getNextSubgroup();
    Collection<DatabaseGroup> getSubgroups();
    
    String getName();
    boolean hasSubgroup(String name);
    //boolean hasNextSubgroup();
    
    void copyTo(DatabaseGroup group);
    
}
