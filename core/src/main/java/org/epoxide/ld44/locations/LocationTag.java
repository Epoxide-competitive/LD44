package org.epoxide.ld44.locations;

/**
 * Location tags are used to define meta properties about a location. They are intended to
 * provide hints about the environment to other systems in the game. For example rats may have
 * an increased chance to give poison if they are in a {@link #URBAN} location, or bats may
 * have a higher speed in {@link #CAVE} locations.
 */
public enum LocationTag {
    
    DARK,
    LIGHT,
    DUSTY,
    MUSKY,
    WET,
    DRY,
    COLD,
    WARM,
    FOREST,
    SWAMP,
    MOUNTAIN,
    LAKE,
    CAVE,
    DESSERT,
    URBAN
}
