/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

/**
 *
 * @author McDjuady
 */
import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;
import java.util.logging.Level;
import org.bukkit.Bukkit;

public class DatabaseEnumBridge implements DatabaseBridge<Enum<?>> {

    @Override
    public boolean canTranslate(Object obj) {
        return (obj instanceof Enum);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("Enum");
    }

    @Override
    public Enum<?> loadAndTranslate(DatabaseGroup group) {
        String name;
        name = group.getString("enumClass");
        if (name.isEmpty()) {
            return null;
        }

        try {
            Class<?> clss = Class.forName(name);
            if (!Enum.class.isAssignableFrom(clss)) {
                return null;
            }
            return Enum.valueOf(((Class<? extends Enum>) clss).asSubclass(Enum.class), group.getString("val"));
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().log(Level.SEVERE, "[MCDatabase] Failed to load class "+name+" from database!");
            return null;
        }
    }

    public void translateAndSave(Enum<?> e, DatabaseGroup group) {
        group.addString("class", "Enum");
        group.addString("enumClass", e.getClass().getName());
        group.addString("val", e.toString());
    }
}
