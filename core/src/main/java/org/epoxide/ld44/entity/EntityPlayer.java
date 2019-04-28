package org.epoxide.ld44.entity;

import org.epoxide.ld44.inventory.IInventory;
import org.epoxide.ld44.inventory.InventoryBase;

public class EntityPlayer extends Entity {

    private IInventory inventory;
    
    public EntityPlayer () {
        
        super("ld44:player");
        this.inventory = new InventoryBase(7);
        this.setSpeed(25f);
    }
    
    @Override
    public void update(double delta) {
        
    }
}
