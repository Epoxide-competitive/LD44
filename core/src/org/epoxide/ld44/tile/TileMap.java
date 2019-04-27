package org.epoxide.ld44.tile;

public class TileMap {
    private final int width;
    private final int height;
    public Tile[][] tiles;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public boolean setTile(int x, int y, Tile tile) {
        if (x < 0 || y < 0 || x > width || y > height)
            return false;
        this.tiles[x][y] = tile;
        return true;
    }

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
