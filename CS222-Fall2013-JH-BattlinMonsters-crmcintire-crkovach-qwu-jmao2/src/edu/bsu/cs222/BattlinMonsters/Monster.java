package edu.bsu.cs222.BattlinMonsters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster {

	private BufferedImage monsterPicture = null;
	private String name;
	private int currentHealth;
	private int maxHealth;
	private int score;
	private Attack primaryAttack;
	private Attack secondaryAttack;

	public Monster(File monsterPictureFile, String name, int maxHealth, int score, Attack primaryAttack, Attack secondaryAttack) throws IOException {
		this.monsterPicture = ImageIO.read(monsterPictureFile);
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.score = score;
		this.primaryAttack = primaryAttack;
		this.secondaryAttack = secondaryAttack;
	}

	public BufferedImage monsterPicture() {
		return monsterPicture;
	}

	public String monsterName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public Attack usePrimaryAttack(){
		return primaryAttack;
	}
	
	public Attack useSecondaryAttack(){
		return secondaryAttack;
	}

	public void decreaseHealth(int damage) {
		if ((currentHealth - damage) > 0)
			currentHealth -= damage;
		else
			currentHealth = 0;
		System.out.println("health of monster: " + currentHealth);
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(){
		score++;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}
}
