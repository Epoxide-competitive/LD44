package org.epoxide.ld44.tile;

import org.epoxide.ld44.registry.NamedRegistry;

public class Tiles {
    
    public static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<Tile>();
    
    public static final Tile STONE = new Tile("ld44:stone");
    public static final Tile STONE_BRICKS = new Tile("ld44:stone_bricks");
    public static final Tile GRASS = new Tile("ld44:grass");
    public static final Tile PATH = new Tile("ld44:path");
    
    
    
    
    public static void register() {
        REGISTRY.registerValue(STONE);
        REGISTRY.registerValue(STONE_BRICKS);
        REGISTRY.registerValue(GRASS);
        REGISTRY.registerValue(PATH);
    
    }
}