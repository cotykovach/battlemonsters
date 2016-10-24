package edu.bsu.cs222.BattlinMonsters;

public class Attack {

	private int minDamage;
	private int maxDamage;
	private String attackName;
	
	public Attack(int minDamage, int maxDamage) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	public int attackOpponent(int attackRating, Monster attackingMonster, Monster targetMonster) {
		if (this.attackName.equalsIgnoreCase("Rok You")){
			return attackOpponetRokYou(attackRating);
		}
		else if (this.attackName.equalsIgnoreCase("Toadel Fire")){
			return attackOpponetToadelFire(attackRating, targetMonster);
		}
		else if (this.attackName.equalsIgnoreCase("Leaf Me Alone")){
			return attackOpponetLeafMeAlone(attackRating, attackingMonster, targetMonster);
		}
		else{
		int damageDealt = ((minDamage+attackingMonster.getAttack()) + (int) (Math.random() * ((maxDamage+attackingMonster.getAttack()) - (minDamage+attackingMonster.getAttack())) + 1)) - targetMonster.getDefense();
		if (damageDealt<0){
			damageDealt=0;
		}
		return damageDealt;
		}
	}

	public int attackOpponetToadelFire(int attackRating, Monster targetMonster){
		int ToadelFireDamage=attackRating;
		double percentChance = Math.random();
		if (percentChance < 0.15){
			ToadelFireDamage=attackRating*4;
		}
		else if (percentChance < 0.40){
			ToadelFireDamage=attackRating*3;
		}
		ToadelFireDamage-=targetMonster.getDefense();
		if (ToadelFireDamage<0){
			ToadelFireDamage=0;
		}
		return ToadelFireDamage;
	}
	
	public int attackOpponetRokYou(int attackRating){
		int RokYouDamage=0;
		double percentChance = Math.random();
		if (percentChance < 0.5){
			RokYouDamage=attackRating*5;
		}
		return RokYouDamage;
	}
	
	public int attackOpponetLeafMeAlone(int attackRating, Monster attackingMonster, Monster targetMonster){
		int LeafMeAlone=attackRating;
		double percentChance = Math.random();
		if (percentChance < 0.10){
			attackingMonster.healCurrentHealth(attackRating*3);
		}
		else if (percentChance < 0.30) {
			attackingMonster.healCurrentHealth(attackRating*2);
		}
		else {
			attackingMonster.healCurrentHealth(attackRating);
		}
		LeafMeAlone-=targetMonster.getDefense();
		if (LeafMeAlone<0){
			LeafMeAlone=0;
		}
		return LeafMeAlone;
	}
	
	public int getMinimumDamage(){
		return minDamage;
	}
	
	public int getMaximumDamage(){
		return maxDamage;
	}
	
	public void setAttackName(String attackName){
		this.attackName = attackName;
	}

	public String getName() {
		return this.attackName;
	}
}

