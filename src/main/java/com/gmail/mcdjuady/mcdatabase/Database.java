/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.mcdjuady.mcdatabase;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author McDjuady
 */
public interface Database {

    //DatabaseGroup readNextGroup();
    Collection<DatabaseGroup> getGroups();

    DatabaseGroup readGroup(String name);

    DatabaseGroup createGroup(String name);

    DatabaseGroup destroyGroup(DatabaseGroup group);

    //copyTo needs to be Synchronized. Else bad things could happen
    void copyTo(Database db);

    void flush();

    void save() throws IOException;

    File getFile();

    void close(); //To make sure no more connections are etablished when deleting

    boolean groupExists(String name);
    //boolean hasNextGroup();
}
