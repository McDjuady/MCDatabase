/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

/**
 *
 * @author McDjuady
 */
public class DatabaseBridgeFactory {

    private static DatabaseBridgeFactory instance;
    private List<Class<? extends DatabaseBridge>> bridgeClasses;
    private List<DatabaseBridge> bridges;

    public static DatabaseBridgeFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseBridgeFactory();
        }
        return instance;
    }

    private DatabaseBridgeFactory() {
        Bukkit.getLogger().info("[MCDatabase] Loading Utils...");
        bridgeClasses = new ArrayList<Class<? extends DatabaseBridge>>();
        bridges = new ArrayList<DatabaseBridge>();
        this.registerBridge(DatabaseBlockFaceBridge.class);
        this.registerBridge(DatabaseBooleanBridge.class);
        this.registerBridge(DatabaseCollectionBridge.class);
        this.registerBridge(DatabaseDoubleBridge.class);
        this.registerBridge(DatabaseEnumBridge.class);
        this.registerBridge(DatabaseFloatBridge.class);
        this.registerBridge(DatabaseIntegerBridge.class);
        this.registerBridge(DatabaseLocationBridge.class);
        this.registerBridge(DatabaseMapBridge.class);
        this.registerBridge(DatabaseMaterialBridge.class);
        this.registerBridge(DatabaseSerializableBridge.class);
        this.registerBridge(DatabaseStringBridge.class);
        this.registerBridge(DatabaseByteBridge.class);
        Bukkit.getLogger().info("[MCDatabase] Done!");
    }

    public final void registerBridge(Class<? extends DatabaseBridge> bridge) {
        if (bridge == null || bridgeClasses.contains(bridge)) {
            return;
        }
        Bukkit.getLogger().info("[MCDatabase] Registering " + bridge.getSimpleName());
        try {
            DatabaseBridge bridgeInstance = bridge.newInstance();
            bridges.add(bridgeInstance);
            bridgeClasses.add(bridge);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseBridgeFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseBridgeFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean canTranslate(Object o) {
        for (DatabaseBridge bridge : bridges) {
            if (bridge.canTranslate(o)) {
                return true;
            }
        }
        return false;
    }

    public boolean canTranslate(DatabaseGroup group) {
        return !(getBridge(group) == null);
    }

    public <T> void translateAndSave(T o, DatabaseGroup group) {
        DatabaseBridge<T> bridge = getBridge(o);
        if (bridge == null) {
            Bukkit.getLogger().log(Level.SEVERE, "[MCDatabase] Failed to save object ("+o.toString()+") to group {"+group.getName()+"}! No suitable bridge!");
            return;
        }
        bridge.translateAndSave(o, group);
    }

    public <T> T loadAndTranslate(DatabaseGroup group) {
        if (group == null) {
            return null;
        }
        DatabaseBridge<T> bridge = (DatabaseBridge<T>) getBridge(group);
        if (bridge == null) {
            Bukkit.getLogger().log(Level.SEVERE, "[MCDatabase] Failed to load object from group "+group.getName()+"! No suitable bridge!");
            return null;
        }
        T o = bridge.loadAndTranslate(group);
        if (o == null) {
            Bukkit.getLogger().log(Level.SEVERE, "[MCDatabase] Failed to load object from group "+group.getName()+"! Bridge returned null!");
        }
        return o;
    }

    private <T> DatabaseBridge<T> getBridge(T o) {
        for (DatabaseBridge bridge : bridges) {
            if (bridge.canTranslate(o)) {
                return (DatabaseBridge<T>) bridge;
            }
        }
        return null;
    }

    private DatabaseBridge getBridge(DatabaseGroup group) {
        if (group == null) {
            return null;
        }
        for (DatabaseBridge bridge : bridges) {
            if (bridge.canTranslate(group)) {
                return bridge;
            }
        }
        return null;
    }
}
