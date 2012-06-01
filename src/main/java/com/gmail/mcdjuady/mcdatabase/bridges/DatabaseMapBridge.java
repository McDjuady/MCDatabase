/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McDjuady
 */
public class DatabaseMapBridge implements DatabaseBridge<Map> {

    @Override
    public void translateAndSave(Map t, DatabaseGroup group) {
        group.addString("class", t.getClass().getName());
        Collection<?> keys = t.keySet();
        Collection<?> values = t.values();
        if (!DatabaseBridgeFactory.getInstance().canTranslate(keys)) {
            return;
        }
        DatabaseGroup keySub = group.createSubgroup("keys");
        DatabaseBridgeFactory.getInstance().translateAndSave(keys, keySub);
        DatabaseGroup valSub = group.createSubgroup("values");
        DatabaseBridgeFactory.getInstance().translateAndSave(values, valSub);
    }

    @Override
    public Map loadAndTranslate(DatabaseGroup group) {
        String clss = group.getString("class");
        Map m = null;
        //Create the Map
        try {
            Class<?> mapClass = Class.forName(clss);
            if (!Map.class.isAssignableFrom(mapClass)) {
                return null;
            }
            m = ((Class<? extends Map>) mapClass).newInstance();
        } catch (InstantiationException ex) {
            //log
            return null;
        } catch (IllegalAccessException ex) {
            //log
            return null;
        } catch (ClassNotFoundException e) {
            //log
            return null;
        }
        DatabaseGroup keySub = group.getSubgroup("keys");
        DatabaseGroup valSub = group.getSubgroup("values");
        if (keySub == null || valSub == null) {
            return m;
        }
        if (!DatabaseBridgeFactory.getInstance().canTranslate(keySub)) {
            return m;
        }
        Collection<?> keys = DatabaseBridgeFactory.getInstance().loadAndTranslate(keySub);
        Collection<?> values = DatabaseBridgeFactory.getInstance().loadAndTranslate(valSub);
        Object[] keyArray = keys.toArray();
        Object[] valueArray = values.toArray();
        int size = Math.min(keys.size(), values.size());
        for (int i = 0; i < size; i++) {
            m.put(keyArray[i], valueArray[i]);
        }
        return m;
    }

    @Override
    public boolean canTranslate(Object o) {
        if (o instanceof Map) {
            Map map = (Map) o;
            Collection keys = map.keySet();
            if (!DatabaseBridgeFactory.getInstance().canTranslate(keys)) {
                return false;
            }
            Collection values = map.values();
            if (!DatabaseBridgeFactory.getInstance().canTranslate(values)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        String clss = group.getString("class");
        try {
            Class<?> tclass = Class.forName(clss);
            return Map.class.isAssignableFrom(tclass) && DatabaseBridgeFactory.getInstance().canTranslate(group.getSubgroup("keys")) && DatabaseBridgeFactory.getInstance().canTranslate(group.getSubgroup("values"));
        } catch (ClassNotFoundException ex) {
            return false;
        }
        
    }
}
