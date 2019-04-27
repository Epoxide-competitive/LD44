package org.epoxide.ld44.tile;

import org.epoxide.ld44.registry.NamedRegistry;

public class Tiles {
    public static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<Tile>();

    public static final Tile STONE = new Tile("ld44:stone");

    public static void register() {
        REGISTRY.registerValue(STONE);
    }
}