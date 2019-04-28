package org.epoxide.ld44.inventory;

import org.epoxide.ld44.item.ItemInstance;

public class InventoryBase implements IInventory {

    private final int size;
    private ItemInstance[] contents;
    
    public InventoryBase(int size) {
        
        this.size = size;
        contents = new ItemInstance[size];
    }
    
    @Override
    public int getSize () {
        
        return this.size;
    }

    @Override
    public ItemInstance getItem (int slot) {
        
        return null;
    } 
    
    public void setItem(int slot, ItemInstance item) {
        
        contents[slot] = item;
    }
}