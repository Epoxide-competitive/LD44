package org.epoxide.ld44.registry;

/**
 * This interface is used to mark an object type as being registerable in one of the game's
 * named registries.
 */
public interface IRegisterable<T> {
    
    /**
     * Sets the identifier for the registerable object.
     *
     * @param identifier The identifier to use.
     * @return The object having it's identifier set, for convenience.
     */
    T setIdentifier (Identifier identifier);
    
    /**
     * Gets the identifier for the registerable object.
     *
     * @return The identifier for the object.
     */
    Identifier getIdentifier ();
}