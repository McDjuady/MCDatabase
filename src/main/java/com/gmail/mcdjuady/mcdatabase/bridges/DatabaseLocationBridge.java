/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase.bridges;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 *
 * @author McDjuady
 */
public class DatabaseLocationBridge implements DatabaseBridge<Location> {

    @Override
    public void translateAndSave(Location loc, DatabaseGroup group) {
        group.addString("class", "Location");
        group.addDouble("x", loc.getX());
        group.addDouble("y", loc.getY());
        group.addDouble("z", loc.getZ());
        group.addFloat("pitch", loc.getPitch());
        group.addFloat("yaw", loc.getYaw());
        group.addString("world", loc.getWorld().getName());
    }

    @Override
    public Location loadAndTranslate(DatabaseGroup group) {
        if (!(group.getString("class").equals("Location"))) {
            return null;
        }
        String world = group.getString("world");
        if (Bukkit.getWorld(world) == null) {
            return null;
        }
        double x = group.getDouble("x");
        double y = group.getDouble("y");
        double z = group.getDouble("z");
        float pitch = group.getFloat("pitch");
        float yaw = group.getFloat("yaw");
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    @Override
    public boolean canTranslate(Object o) {
        return (o instanceof Location);
    }

    @Override
    public boolean canTranslate(DatabaseGroup group) {
        return group.getString("class").equals("Location");
    }
}
