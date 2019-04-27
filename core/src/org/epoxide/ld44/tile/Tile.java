package org.epoxide.ld44.tile;

import org.epoxide.ld44.registry.Identifier;
import org.epoxide.ld44.registry.Registerable;

public class Tile extends Registerable<Tile> {

    public Tile(String id) {

        this.setIdentifier(new Identifier(id));
    }
}