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
public class DatabaseIntegerBridge implements DatabaseBridge<Integer>{

    @Override
    public void translateAndSave(Integer t, DatabaseGroup group) {
        group.addString("class", "integer");
        group.addInt("intValue", t);
    }

    @Override
    public Integer loadAndTranslate(DatabaseGroup group) {
        return group.getInt("intValue");
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof Integer);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("integer");
    }
    
}
