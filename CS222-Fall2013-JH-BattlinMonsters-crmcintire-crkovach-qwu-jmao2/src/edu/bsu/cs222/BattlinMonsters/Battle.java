package edu.bsu.cs222.BattlinMonsters;

public class Battle {

	private Monster user, enemy;
	private int userDamageDealt, enemyDamageDealt;
	
	public Battle(Monster user, Monster enemy){
		this.user = user;
		this.enemy = enemy;
	}
	
	public void attackComputer(String chosenAttack){
		userDamageDealt = 0;
		if (chosenAttack.equals("primary")){
			userDamageDealt = user.primaryAttack().attackOpponent(user.getAttack(), user, enemy);
		}else if (chosenAttack.equals("secondary")){
			userDamageDealt = user.secondaryAttack().attackOpponent(user.getAttack(), user, enemy);
		}
		System.out.println(userDamageDealt);
		enemy.decreaseHealth(userDamageDealt);
	}
	
	public void attackUser(String chosenAttack){
		enemyDamageDealt = 0;
		if (chosenAttack.equals("primary")){
			enemyDamageDealt = enemy.primaryAttack().attackOpponent(enemy.getAttack(), enemy, user);
		}else if (chosenAttack.equals("secondary")){
			enemyDamageDealt = enemy.secondaryAttack().attackOpponent(enemy.getAttack(), enemy, user);
		}
		user.decreaseHealth(enemyDamageDealt);
	}	
	
	public int computerHealth(){
		return enemy.getCurrentHealth();
	}
	
	public int userHealth(){
		return user.getCurrentHealth();
	}
	
	public boolean checkEnemyIsAlive(){
		if (enemy.getCurrentHealth() > 0)
			return true; 
		else
			return false;
	}
	
	public boolean checkUserIsAlive(){
		if (user.getCurrentHealth() > 0)
			return true; 
		else
			return false;
	}
	
	public int userDamageDealt(){
		return this.userDamageDealt;
	}
	
	public int enemyDamageDealt(){
		return this.enemyDamageDealt;
	}
	
	public void victory(){
		user.setScore();
	}
	
	public int userScore(){
		return user.getScore();
	}

	public void defeat() {
		
	}
}
