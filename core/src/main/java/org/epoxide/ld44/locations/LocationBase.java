package org.epoxide.ld44.locations;

import java.util.HashSet;
import java.util.Set;

import org.epoxide.ld44.entity.Entity;
import org.epoxide.ld44.item.Item;
import org.epoxide.ld44.probability.IOutcomeTable;

public class LocationBase implements ILocation {

    private final String name;
    private final int minFloor;
    private final int maxFloor;
    private final int minRoom;
    private final int maxRoom;    
    private final Set<LocationTag> tags;
    
    
    public LocationBase(String name, int minFloor, int maxFloor, int minRoom, int maxRoom, LocationTag... locTags) {
        
        this.name = name;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.minRoom = minRoom;
        this.maxRoom = maxRoom;
        this.tags = new HashSet<LocationTag>();
        
        for (LocationTag locTag : locTags) {
            
            this.tags.add(locTag);
        }
    }

    @Override
    public String getName () {
        
        return this.name;
    }

    @Override
    public int getMinFloors () {
        
        return this.minFloor;
    }

    @Override
    public int getMaxFloors () {
        
        return this.maxFloor;
    }

    @Override
    public int getMinRooms () {
        
        return this.minRoom;
    }

    @Override
    public int getMaxRooms () {
        
        return this.maxRoom;
    }

    @Override
    public Set<LocationTag> getTags (int floor) {

        return this.tags;
    }

    @Override
    public IOutcomeTable<Entity> getSpawnTable () {
        
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public IOutcomeTable<Item> getItemTable () {
        
        throw new RuntimeException("Not Yet Implemented");
    }
}