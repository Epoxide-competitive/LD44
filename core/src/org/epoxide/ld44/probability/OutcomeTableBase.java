package org.epoxide.ld44.probability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class OutcomeTableBase<T> implements IOutcomeTable<T> {

	private List<IOutcome<T>> outcomes;
	
	public OutcomeTableBase() {
		
		this.outcomes = new ArrayList<IOutcome<T>>();
	}
	
	@Override
	public IOutcome<T> getOutcome(Random random) {
		
		return outcomes.get(random.nextInt(outcomes.size()));
	}

	@Override
	public void addOutcome(IOutcome<T> outcome) {
		
		this.outcomes.add(outcome);
	}

	@Override
	public Collection<IOutcome<T>> getOutcomes() {

		return this.outcomes;
	}
}