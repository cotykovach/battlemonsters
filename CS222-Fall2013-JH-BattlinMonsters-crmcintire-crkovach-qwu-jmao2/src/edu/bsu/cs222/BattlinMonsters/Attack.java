package edu.bsu.cs222.BattlinMonsters;

public class Attack {

	private int minDamage;
	private int maxDamage;

	public Attack(int minDamage, int maxDamage) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	public int attackOpponent() {
		return minDamage + (int) (Math.random() * (maxDamage - minDamage) + 1);
	}
}

