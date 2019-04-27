package org.epoxide.ld44.entity;

import org.epoxide.ld44.world.World;

public class Entity {

    private int maxHealth;
    private int health;

    private int x;
    private int y;

    private World world;

    public Entity() {

    }

    public void setWorld(World world) {
        this.world = world;
    }
}
