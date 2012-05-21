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
public class DatabaseFloatBridge implements DatabaseBridge<Float> {

    @Override
    public void translateAndSave(Float t, DatabaseGroup group) {
        group.addString("class", "float");
        group.addFloat("floatVal", t);
    }

    @Override
    public Float loadAndTranslate(DatabaseGroup group) {
        return group.getFloat("floatVal");
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof Float);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("float");
    }
    
}
