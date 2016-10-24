package edu.bsu.cs222.BattlinMonsters;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	
	

	*/
	

	
	
	
	public static void main(String[] args) throws IOException {
		Attack attack1 = new Attack(5, 10);
		Attack attack2 = new Attack(0, 1);
		Monster user = new Monster("user", 100, 0, attack1, attack2);
		
		Monster computer = new Monster("computer", 100, 0, attack1, attack2);
		Battle battle = new Battle(user, computer);
		while(battle.checkHealth() == true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Attack 1 or 2: ");
			String attack = br.readLine();
			if (attack.equals("1")){
				battle.attackComputer("primary");
			}
			else if (attack.equals("2")){
				battle.attackComputer("secondary");
			}
			System.out.println("Your opponents health is now " + battle.computerHealth());
			int computerAttack = 0 + (int) (Math.random() * (1) + 1);
			if (computerAttack == 0){
				battle.attackUser("secondary");
			}
			else if (computerAttack == 1){
				battle.attackUser("secondary");
			}
			System.out.println("Your health is now " + battle.userHealth());
		}
		System.out.println(battle.userScore());
	}

} 


