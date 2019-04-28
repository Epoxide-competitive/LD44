package org.epoxide.ld44.inventory;

import org.epoxide.ld44.item.ItemInstance;

public interface IInventory {
    
    int getSize();
    
    ItemInstance getItem(int slot);
    
    void setItem(int slot, ItemInstance item);
}
