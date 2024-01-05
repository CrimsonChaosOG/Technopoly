package com.technopoly.data.effects;

public class ActiveEffect {
	
	private Effect effect;
	private int remainingTurns;
	
	public ActiveEffect(Effect e, int t) {
		effect = e;
		remainingTurns = t;
	}
	
	public Effect GetEffect() {
		return effect;
	}
	
	public void SetRemainingTurns(int amt) {
		remainingTurns = amt;
	}
	
	public int GetRemainingTurns() {
		return remainingTurns;
	}

}
