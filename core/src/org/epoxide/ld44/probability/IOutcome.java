package org.epoxide.ld44.probability;

import java.util.Collection;

public interface IOutcome<T> {

	T getResult();
	
	int getWeight();
	
	Collection<ICondition> getConditions();
}