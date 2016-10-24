package edu.bsu.cs222.BattlinMonsters;

import java.io.IOException;

import edu.bsu.cs222.BattlinMonsters.Monster;

public class EnemyMonsterGenerator {
	private int currentPlayerScore;
	
	public Monster randomGenerateMonster(int currentPlayerScore) throws IOException {
		this.currentPlayerScore = currentPlayerScore;
		if (currentPlayerScore <= 3) {
			return generateRandomSkoul();
		}
		if (currentPlayerScore == 4) {
			return generateMossSpirit();
		}
		if (currentPlayerScore == 5) {
			return generateWoodSpirit();
		} 
		if (currentPlayerScore == 6) {
			return generateEvilAkron();
		} 
		if (currentPlayerScore == 7) {
			return generateCorruptoad();
		} 
		if (currentPlayerScore == 8) {
			return generateFrostRokhan();
		} 
		else
			return generateCoty();
	}

	Attack attack1 = new Attack(5, 10);
	Attack attack2 = new Attack(5, 10);

	public Monster generateRandomSkoul() throws IOException {
		double percentChance = Math.random();
		String randomSkoulPath;
		String skoulName;
		if (percentChance < 0.33){
			randomSkoulPath = "images\\monsters\\BattlinMonster_02.png";
			skoulName="Skoul";
		}
		else if (percentChance < 0.66){
			randomSkoulPath = "images\\monsters\\BattlinMonster_03.png";
			skoulName="Poison Skoul";
		}
		else {
			randomSkoulPath = "images\\monsters\\BattlinMonster_04.png";
			skoulName="Fury Skoul";
		}
		BufferedImageCreator skoulImage = new BufferedImageCreator(
				randomSkoulPath);
		attack1.setAttackName("Basic Attack");
		attack2.setAttackName("Skoul Punch");
		Monster enemy = new Monster(skoulImage, skoulName, 30+(5*currentPlayerScore), currentPlayerScore, currentPlayerScore,
				attack1, attack2);
		return enemy;
		} 	
	
	public Monster generateMossSpirit() throws IOException {
		String mossSpiritFilePath = "images\\monsters\\BattlinMonster_05.png";
		BufferedImageCreator mossSpiritImage = new BufferedImageCreator(
				mossSpiritFilePath);
		attack1.setAttackName("Basic Attack");
		attack2.setAttackName("Mossacre");
		Monster enemy = new Monster(mossSpiritImage, "Moss Spirit", 55, currentPlayerScore, currentPlayerScore,
				attack1, attack2);
		return enemy;
		} 	
	
	public Monster generateWoodSpirit() throws IOException {
		String woodSpiritFilePath = "images\\monsters\\BattlinMonster_6.png";
		BufferedImageCreator woodSpiritImage = new BufferedImageCreator(
				woodSpiritFilePath);
		attack1.setAttackName("Basic Attack");
		attack2.setAttackName("Woodlash");
		Monster enemy = new Monster(woodSpiritImage, "Wood Spirit", 65, currentPlayerScore, currentPlayerScore,
				attack1, attack2);
		return enemy;
		} 	
	
	public Monster generateEvilAkron() throws IOException {
		String evilAkronFilePath = "images\\monsters\\BattlinMonster_01.png";
		BufferedImageCreator evilAkronImage = new BufferedImageCreator(
				evilAkronFilePath);
		attack1.setAttackName("Basic Attack");
		attack2.setAttackName("Evil Seed");
		Monster enemy = new Monster(evilAkronImage, "Evil Akron", 70, currentPlayerScore, currentPlayerScore,
				attack1, attack2);
		return enemy;
		} 	
	
	public Monster generateCorruptoad() throws IOException {
		String corruptoadFilePath = "images\\monsters\\BattlinMonster_01.png";
		BufferedImageCreator corruptoadImage = new BufferedImageCreator(
				corruptoadFilePath);
		attack1.setAttackName("Basic Attack");
		attack2.setAttackName("Corruption");
		Monster enemy = new Monster(corruptoadImage, "Corruptoad", 85, currentPlayerScore, currentPlayerScore,
				attack1, attack2);
		return enemy;
		} 	
	
	public Monster generateFrostRokhan() throws IOException {
		String frostRokhanFilePath = "images\\monsters\\BattlinMonster_01.png";
		BufferedImageCreator frostRokhanImage = new BufferedImageCreator(
				frostRokhanFilePath);
		attack1.setAttackName("Basic Attack");
		attack2.setAttackName("Frosty Punch");
		Monster enemy = new Monster(frostRokhanImage, "Frost Rokhan", 95, currentPlayerScore, currentPlayerScore,
				attack1, attack2);
		return enemy;
		} 	
	
	public Monster generateCoty() throws IOException {
		String cotyFilePath = "images\\monsters\\BattlinMonster_99.png";
		BufferedImageCreator cotyImage = new BufferedImageCreator(
				cotyFilePath);
		attack1.setAttackName("Basic Attack");
		attack2.setAttackName("Doom");
		Monster enemy = new Monster(cotyImage, "Coty the Destroyer", (80+(5*currentPlayerScore)), 2+currentPlayerScore, currentPlayerScore,
				attack1, attack2);
		return enemy;
		} 	
}
