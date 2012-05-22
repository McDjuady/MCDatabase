/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;
import com.gmail.mcdjuady.mcdatabase.Serializable;
import java.util.logging.Level;
import org.bukkit.Bukkit;

/**
 *
 * @author McDjuady
 */
public class DatabaseSerializableBridge implements DatabaseBridge<Serializable> {

    @Override
    public void translateAndSave(Serializable t, DatabaseGroup group) {
        group.addString("class", t.getClass().getName());
        DatabaseGroup sub = group.createSubgroup("data");
        if (sub != null) {
            t.save(sub);
        }
    }

    @Override
    public Serializable loadAndTranslate(DatabaseGroup group) {
        String serClass = group.getString("class");
        try {
            if (serClass == null || serClass.isEmpty()) {
                return null;
            }
            Class<?> cls = Class.forName(serClass);
            if (!Serializable.class.isAssignableFrom(cls)) {
                return null;
            }
            Serializable ser = cls.asSubclass(Serializable.class).newInstance();
            if (ser != null) {
                ser.load(group);
            }
            return ser;
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (ClassNotFoundException ex) {
        } finally {
            Bukkit.getLogger().log(Level.SEVERE, "[MCDatabase] Failed to load class "+serClass+" from database!");
            return null;
        } 
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof Serializable);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        try {
            String className = group.getString("class");
            Class<?> serClass = Class.forName(className);
            if (Serializable.class.isAssignableFrom(serClass)) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}
