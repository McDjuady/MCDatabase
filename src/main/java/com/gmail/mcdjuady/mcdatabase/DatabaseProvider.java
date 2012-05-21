/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase;

import java.util.Map;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author McDjuady
 */
public interface DatabaseProvider {
    
    //plugin var is used to acces config for storage paths or access data
    
    public Database newInstance(Plugin p, String name); //Decides weather to load or create a new one

    public Map<String, Database> startup(Plugin p); //initaly load all databases. Handled here because we wan't to allow databases that aren't stored in the basepath like mysql

    public void delete(Plugin p, String name); //internal cleanup
}
