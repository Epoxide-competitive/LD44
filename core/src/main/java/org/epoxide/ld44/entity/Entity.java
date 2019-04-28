package org.epoxide.ld44.entity;

import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.world.World;

public class Entity {

    protected int maxHealth;
    protected int health;

    protected int x;
    protected int y;

    private World world;
    private int floor;

    public Entity() {

    }

    public void update(double delta) {

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public TileMap getTileMap() {
        return this.world.getTileMap(this.floor);
    }
}
