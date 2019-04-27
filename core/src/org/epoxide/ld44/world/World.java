package org.epoxide.ld44.world;

import org.epoxide.ld44.tile.TileMap;

public abstract class World {

    private final int floorCount;
    private TileMap[] tileMaps;

    public World(int floorCount, int width, int height) {
        this.floorCount = floorCount;
        this.tileMaps = new TileMap[floorCount];

        for (int i = 0; i < floorCount; i++) {
            this.tileMaps[i] = new TileMap(width, height);
        }
    }

    public int getFloorCount() {
        return floorCount;
    }

    public TileMap[] getTileMaps() {
        return tileMaps;
    }

    public abstract void generate();
}