package org.epoxide.ld44;

import org.epoxide.ld44.locations.ILocation;
import org.epoxide.ld44.locations.LocationBase;
import org.epoxide.ld44.locations.LocationTag;

public class Content {
    
    public final ILocation sparseWoods;
    
    public Content() {
        
        // Locations
        sparseWoods = new LocationBase("Sparse Woods", 3, 3, 5, 5, LocationTag.FOREST);
        
        
    }
}
