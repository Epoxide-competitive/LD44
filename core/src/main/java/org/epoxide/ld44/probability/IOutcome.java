package org.epoxide.ld44.probability;

import java.util.Collection;

/**
 * This interface is used to represent an outcome of a weighted outcome table.
 */
public interface IOutcome<T> {
    
    /**
     * Gets the result of this outcome.
     * 
     * @return The result of the outcome.
     */
    T getResult ();
    
    /**
     * Gets the weight of this outcome.
     * 
     * @return The weight of this outcome.
     */
    int getWeight ();
    
    /**
     * Gets a collection of conditions which must be met for this outcome to happen.
     * 
     * @return A collection of conditions.
     */
    Collection<ICondition> getConditions ();
}