package edu.bsu.cs222.BattlinMonsters;

import java.io.IOException;

import edu.bsu.cs222.BattlinMonsters.Monster;

public class PlayerMonsterGenerator {
	private Attack attack1 = new Attack(5, 10);
	
	public Monster selectedMonsterGenerator(int monsterSelected)
			throws IOException {
		attack1.setAttackName("Basic Attack");
		if (monsterSelected == 0) {
			return generateAkron();
		}
		if (monsterSelected == 1) {
			return generateFraemog();
		} else
			return generateRokhan();
	}
	
	public Monster generateAkron() throws IOException {
		String akronFilePath = "images\\monsters\\BattlinMonster_00.png";
		BufferedImageCreator akronImage = new BufferedImageCreator(
				akronFilePath);
		Attack attack2 = new Attack(2, 4);
		attack2.setAttackName("Leaf me Alone");
		Monster selectedMonster = new Monster(akronImage, "Akron", 4, 4, 3, attack1, attack2);
		return selectedMonster;
	} 	

	public Monster generateFraemog() throws IOException {
		String skoulFilePath = "images\\monsters\\BattlinMonster_08.png";
		BufferedImageCreator skoulImage = new BufferedImageCreator(
				skoulFilePath);
		Attack attack2 = new Attack(5, 5);
		attack2.setAttackName("Toadel Fire");
		Monster selectedMonster = new Monster(skoulImage, "Fraemog", 55, 5, 2, attack1, attack2);
		return selectedMonster;
	}
	
	public Monster generateRokhan() throws IOException {
		String skoulFilePath = "images\\monsters\\BattlinMonster_07.png";
		BufferedImageCreator skoulImage = new BufferedImageCreator(
				skoulFilePath);
		Attack attack2 = new Attack(0, 0);
		attack2.setAttackName("Rok You");
		Monster selectedMonster = new Monster(skoulImage, "Rokhan", 85, 3, 4, attack1, attack2);
		return selectedMonster;
	}

}
