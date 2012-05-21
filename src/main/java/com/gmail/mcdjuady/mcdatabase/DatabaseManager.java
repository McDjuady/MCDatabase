/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 * @author McDjuady
 */
public class DatabaseManager {

    private static String global = "__GLOBAL__";
    private Map<String, Database> dbs;
    private DatabaseProvider factory;

    private void searchForDB() throws DatabaseException {
        if (!Bukkit.getServicesManager().isProvidedFor(DatabaseProvider.class)) {
            Bukkit.getLogger().severe("[MCDatabase] No Database Service found. Disabling!");
            throw new DatabaseException("No ServiceProvider found for Service "+DatabaseProvider.class.getSimpleName());
            //return;
        }
        RegisteredServiceProvider<DatabaseProvider> dbFactory = Bukkit.getServicesManager().getRegistration(DatabaseProvider.class);
        factory = dbFactory.getProvider();
    }

    public void startup() throws DatabaseException {
        if (factory == null) {
            searchForDB();
        }
        if (factory == null) {
            return;
        }
        dbs = new ConcurrentHashMap<String, Database>();
        dbs.putAll(factory.startup());
    }

    public void shutdown() {
        if (dbs==null) {
            return;
        }
        for (Database db : dbs.values()) {
            try {
                db.save();
            } catch (IOException ex) {
                Bukkit.getLogger().log(Level.SEVERE, "[MCDatabase] Error! Failed to save Database {0} \n{1}", new Object[]{db.toString(), ex.getLocalizedMessage()});
            }
            db.close();
        }
    }

    public Database getDatabase() {
        return dbs.get(global);
    }

    public Database getDatabase(String name) {
        return dbs.get(name);
    }

    public boolean databaseExists(String name) {
        return dbs.containsKey(name);
    }

    public Database createDatabase(String name) {
        Database db = factory.newInstance(name);
        dbs.put(name, db);
        return db;
    }

    public void deleteDatabase(String name) {
        Database db = dbs.remove(name);
        if (db == null) {
            return;
        }
        db.close();
        factory.delete(name);
    }
}
