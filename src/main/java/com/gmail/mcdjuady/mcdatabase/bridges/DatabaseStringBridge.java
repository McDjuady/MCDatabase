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
public class DatabaseStringBridge implements DatabaseBridge<String> {

    @Override
    public void translateAndSave(String t, DatabaseGroup group) {
        group.addString("class", "String");
        group.addString("stringVal", t);
    }

    @Override
    public String loadAndTranslate(DatabaseGroup group) {
        if (!group.getString("class").equals("String")) //Don't return null! An emty String is better!
        {
            return "";
        }
        return group.getString("stringVal");
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof String);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("String");
    }
}
