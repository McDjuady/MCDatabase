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
public class DatabaseBooleanBridge implements DatabaseBridge<Boolean>{

    @Override
    public void translateAndSave(Boolean t, DatabaseGroup group) {
        group.addString("class", "boolean");
        group.addBoolean("booleanVal", t);
    }

    @Override
    public Boolean loadAndTranslate(DatabaseGroup group) {
        return group.getBoolean("booleanVal");
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof Boolean);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("boolean");
    }
    
}
