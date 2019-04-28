package org.epoxide.ld44.entity;

public class EntityRat extends Entity {

    public EntityRat() {

        super("ld44:rat");
        this.setSpeed((float) (15f * Math.random()));
        this.setHealth((int) (5 * (5 * Math.random())));
        this.setAttack((int) (3 * Math.random()));
    }
    
}
