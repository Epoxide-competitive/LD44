package org.epoxide.ld44.item;

public class ItemInstance {
    
    private Item item;
    
    private int amount;

    public Item getItem () {
        
        return item;
    }

    public int getAmount () {
        
        return amount;
    }
    
    public ItemInstance shrink(int toShrink) {
        
        this.amount -= toShrink;
        return this;
    }
}