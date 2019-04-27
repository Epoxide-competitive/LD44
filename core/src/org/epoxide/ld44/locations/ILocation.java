package org.epoxide.ld44.locations;

import java.util.Set;

public interface ILocation {

	String getName();
	
	int getMinFloors();
	
	int getMaxFloors();
	
	int getMinRooms(); 
	
	int getMaxRooms();
	
	Set<String> getLocationTags();
}