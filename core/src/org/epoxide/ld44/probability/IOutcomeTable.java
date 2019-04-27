package org.epoxide.ld44.probability;

import java.util.Collection;
import java.util.Random;

public interface IOutcomeTable<T> {

	IOutcome<T> getOutcome(Random random);
	
	void addOutcome(IOutcome<T> outcome);
	
	Collection<IOutcome<T>> getOutcomes();
}