/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;
import java.util.Collection;
import java.util.logging.Level;

/**
 *
 * @author McDjuady
 */
public class DatabaseCollectionBridge implements DatabaseBridge<Collection> {

    @Override
    public void translateAndSave(Collection t, DatabaseGroup group) {
        group.addString("class", t.getClass().getName());
        group.addInt("size", t.size());
        if (t.size() <= 0) {
            return;
        }
        Object[] array = t.toArray();
        for (int i = 0; i < t.size(); i++) {
            DatabaseGroup sub = group.createSubgroup("n"+i);
            //TODO Find solutions to fix unchecked
            DatabaseBridgeFactory.getInstance().translateAndSave(array[i], sub);
        }
    }

    @Override
    public Collection loadAndTranslate(DatabaseGroup group) {
        int size = group.getInt("size");
        String clssName = group.getString("class");
        try {
            Class<?> clss = Class.forName(clssName);
            if (!Collection.class.isAssignableFrom(clss)) {
                return null;
            }
            @SuppressWarnings("unchecked")
            //We need to cast this to Collection<Object>. Else adding Elements is impossible
            Collection<Object> col = (Collection<Object>) ((Class<Collection<?>>) clss).newInstance();
            if (size==0) {
                return col;
            }
            for (int i = 0; i < size; i++) {
                DatabaseGroup sub = group.getSubgroup("n"+i);
                col.add(DatabaseBridgeFactory.getInstance().loadAndTranslate(sub));
            }
            return col;
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (ClassNotFoundException e) {
        }
        return null;
    }

    @Override
    public boolean canTranslate(Object o) {
        if (o instanceof Collection) {
            Collection collection = (Collection) o;
            if (collection.isEmpty()) {
                return true;
            }
            for (Object obj : collection) {
                if (!DatabaseBridgeFactory.getInstance().canTranslate(obj)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        try {
            String className = group.getString("class");
            if (className.isEmpty()) {
                return false;
            }
            Class<?> colClass = Class.forName(className);
            if (!Collection.class.isAssignableFrom(colClass)) {
                return false;
            }
            int size = group.getInt("size");
            if (size <= 0) {
                return true;
            }
            for (int i = 0; i < size; i++) {
                DatabaseGroup sub = group.getSubgroup(String.valueOf(i));
                //we don't translate null entries, so skip them!
                //may change this to return false. Let's see...
                if (sub == null) {
                    continue;
                }
                if (!DatabaseBridgeFactory.getInstance().canTranslate(sub)) {
                    return false;
                }
            }
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}
