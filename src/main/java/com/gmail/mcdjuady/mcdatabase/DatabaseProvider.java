/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase;

import java.util.Map;

/**
 *
 * @author McDjuady
 */
public interface DatabaseProvider {

    public Database newInstance(String name); //Decides weather to load or create a new one

    public Map<String, Database> startup(); //initaly load all databases. Handled here because we wan't to allow databases that aren't stored in the basepath like mysql

    public void delete(String name); //internal cleanup
}
