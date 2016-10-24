package edu.bsu.cs222.BattlinMonsters;

public class Battle {

	private Monster user;
	private Monster computer;
	
	public Battle(Monster user, Monster computer){
		this.user = user;
		this.computer = computer;
	}
	
	public void attackComputer(String chosenAttack){
		int healthDamage = 0;
		if (chosenAttack.equals("primary")){
			healthDamage = user.usePrimaryAttack().attackOpponent();
		}else if (chosenAttack.equals("secondary")){
			healthDamage = user.useSecondaryAttack().attackOpponent();
		}
		System.out.println(healthDamage);
		computer.decreaseHealth(healthDamage);
	}
	
	public void attackUser(String chosenAttack){
		int healthDamage = 0;
		if (chosenAttack.equals("primary")){
			healthDamage = computer.usePrimaryAttack().attackOpponent();
		}else if (chosenAttack.equals("secondary")){
			healthDamage = computer.useSecondaryAttack().attackOpponent();
		}
		user.decreaseHealth(healthDamage);
	}	
	
	public int computerHealth(){
		return computer.getCurrentHealth();
	}
	
	public int userHealth(){
		return user.getCurrentHealth();
	}
	
	public boolean checkHealth(){
		if (user.getCurrentHealth() > 0 && computer.getCurrentHealth() > 0)
			return true; 
		else if (user.getCurrentHealth() > 0 && computer.getCurrentHealth() == 0){
			user.setScore();
			return false;
		}
		else
			return false;
	}
	
	public int userScore(){
		return user.getScore();
	}
}
