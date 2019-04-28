package org.epoxide.ld44.item;

import org.epoxide.ld44.registry.NamedRegistry;

public class Items {
    
    public static final NamedRegistry<Item> REGISTRY = new NamedRegistry<Item>();
    
    public static Item STICK = new Item("ld44:stick");
    public static Item RUSTY_SWORD = new Item("ld44:rusty_sword");
    public static Item LEATHER_SCRAP = new Item("ld44:leather_scrap");
    public static Item LEATHER_CAP = new Item("ld44:leather_cap");
    public static Item LEATHER_PANTS = new Item("ld44:leather_pants");
    public static Item SOUL_WISP = new Item("ld44:soul_wisp");
    public static Item POTION_HEALTH = new Item("ld44:potion_health");
    public static Item POTION_ACTION = new Item("ld44:potion_action");

    public static void register() {
        
        REGISTRY.registerValue(STICK);
        REGISTRY.registerValue(RUSTY_SWORD);
        REGISTRY.registerValue(LEATHER_SCRAP);
        REGISTRY.registerValue(LEATHER_CAP);
        REGISTRY.registerValue(LEATHER_PANTS);
        REGISTRY.registerValue(SOUL_WISP);
        REGISTRY.registerValue(POTION_HEALTH);
        REGISTRY.registerValue(POTION_ACTION);
    }
}