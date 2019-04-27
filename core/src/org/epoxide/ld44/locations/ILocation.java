package org.epoxide.ld44.locations;

import java.util.Set;

import org.epoxide.ld44.entity.Entity;
import org.epoxide.ld44.item.Item;
import org.epoxide.ld44.probability.IOutcomeTable;

/**
 * This interface is used to define the backing structure of a new location in the game.
 */
public interface ILocation {
    
    /**
     * Gets the name of the location. This can be used for display name purposes.
     * 
     * @return The display name of the location.
     */
    String getName ();
    
    /**
     * The lowest possible number of floors for this location.
     * 
     * @return The lowest possible number of floors.
     */
    int getMinFloors ();
    
    /**
     * Gets the highest number of floors for the location. Must be greater than or equal too
     * {@link #getMinFloors()}.
     * 
     * @return The highest number of floors to generate.
     */
    int getMaxFloors ();
    
    /**
     * Gets the minimum number of floors to try and generate.
     * 
     * @return The lowest number of room generation attempts.
     */
    int getMinRooms ();
    
    /**
     * Gets the highest number of room generation attempts. Must be greater than or equal too
     * {@link #getMinRooms()}.
     * 
     * @return The highest number of room generation attempts.
     */
    int getMaxRooms ();
    
    /**
     * Gets a list of location tags for the floor. Theses are used to give hints about the
     * environment to other systems.
     * 
     * @param floor The floor to get the tags for. This may be ignored depending on the
     *        location.
     * @return A set of tags that describe the location.
     */
    Set<LocationTag> getTags (int floor);
    
    /**
     * Gets an outcome table for the potential enemies that can spawn.
     * 
     * @return The entity spawn outcome table.
     */
    IOutcomeTable<Entity> getSpawnTable ();
    
    /**
     * Gets an outcome table for the potential items that can spawn.
     * 
     * @return The item loot outcome table.
     */
    IOutcomeTable<Item> getItemTable ();
}