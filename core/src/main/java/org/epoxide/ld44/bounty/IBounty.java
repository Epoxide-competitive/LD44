package org.epoxide.ld44.bounty;

import org.epoxide.ld44.Tier;
import org.epoxide.ld44.locations.ILocation;

/**
 * This interface is used to outline bounties that the player can do.
 */
public interface IBounty {
    
    /**
     * Gets the description of what the player has to do in order to complete this bounty.
     * 
     * @return The description of the bounty.
     */
    String getDescription ();
    
    /**
     * Gets the tier of bounty that this is. Higher tier bounties are harder to do.
     * 
     * @return The tier of the bounty.
     */
    Tier getTier ();
    
    /**
     * Gets the location that this bounty is for.
     * 
     * @return The location the bounty is for.
     */
    ILocation getLocation ();
}