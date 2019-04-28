package org.epoxide.ld44.item;

import org.epoxide.ld44.registry.Identifier;
import org.epoxide.ld44.registry.Registerable;

public class Item extends Registerable<Item> {
    
    public Item (String identifier) {
        
        this.setIdentifier(new Identifier(identifier));
    }
}