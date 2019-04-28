package org.epoxide.ld44.probability;

import java.util.Collection;
import java.util.Random;

/**
 * This class is used as a skeleton for defining tables for probability based systems. They can
 * be used for weighted system, such as item drops or potential spawns.
 */
public interface IOutcomeTable<T> {
    
    /**
     * Gets a random outcome.
     * 
     * @param random An instance of random, used to select the outcome.
     * @return The outcome that has been selected.
     */
    IOutcome<T> getOutcome (Random random);
    
    /**
     * Adds an outcome to the table.
     * 
     * @param outcome The outcome to add.
     */
    void addOutcome (IOutcome<T> outcome);
    
    /**
     * Gets a collection of all possible outcomes.
     * 
     * @return A collection of all possible outcomes.
     */
    Collection<IOutcome<T>> getOutcomes ();
}