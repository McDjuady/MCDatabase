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
public class DatabaseDoubleBridge implements DatabaseBridge<Double>{

    @Override
    public void translateAndSave(Double t, DatabaseGroup group) {
        group.addString("class", "double");
        group.addDouble("doubleVal", t);
    }

    @Override
    public Double loadAndTranslate(DatabaseGroup group) {
        return group.getDouble("doubleVal");
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof Double);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("double");
    }
    
}
