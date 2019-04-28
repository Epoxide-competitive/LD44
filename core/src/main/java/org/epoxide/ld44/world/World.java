package org.epoxide.ld44.world;

import org.epoxide.ld44.tile.TileMap;

public abstract class World {

    private final int floorCount;

    public World(int floorCount) {
        this.floorCount = floorCount;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public abstract TileMap getTileMap(int floor);

    public abstract void generate();
}