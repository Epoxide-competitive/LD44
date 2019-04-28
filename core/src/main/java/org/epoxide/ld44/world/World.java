package org.epoxide.ld44.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.epoxide.ld44.entity.Entity;
import org.epoxide.ld44.tile.TileMap;

public abstract class World {

    private static final Comparator<Entity> SPEED_COMPARATOR = new Comparator<Entity>() {
        
        @Override
        public int compare(Entity one, Entity two) {
            
            return Float.compare(two.getSpeed(), one.getSpeed());
        }
    };
    
    private final int floorCount;
    
    private final List<Entity> entities;

    public World(int floorCount) {
        
        this.floorCount = floorCount;
        this.entities = new ArrayList<Entity>();
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void addEntity(Entity entity) {
        
        this.entities.add(entity);
    }
    
    public void update(double delta) {
        
        Collections.sort(entities, SPEED_COMPARATOR);
        
        for (Entity entity : entities) {
            
            entity.update(delta);
        }
    }
    
    public abstract TileMap getTileMap(int floor);

    public abstract void generate();
}