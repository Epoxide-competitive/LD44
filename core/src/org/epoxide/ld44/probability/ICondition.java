package org.epoxide.ld44.probability;

/**
 * This interface is used to represent conditions for outcomes in an outcome table.
 */
public interface ICondition {
    
    /**
     * Tests if the condition can happen or not.
     * 
     * @return Whether or not the condition can happen.
     */
    boolean test ();
    
    // TODO this is going to need more params in the future.
}