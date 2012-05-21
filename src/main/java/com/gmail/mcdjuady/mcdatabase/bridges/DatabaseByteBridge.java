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
public class DatabaseByteBridge implements DatabaseBridge<Byte> {

    @Override
    public void translateAndSave(Byte t, DatabaseGroup group) {
        group.addString("class", "Byte");
        group.addInt("byteValue", t);
    }

    @Override
    public Byte loadAndTranslate(DatabaseGroup group) {
        return (byte)group.getInt("byteValue");
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof Byte);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("Byte");
    }
    
}
