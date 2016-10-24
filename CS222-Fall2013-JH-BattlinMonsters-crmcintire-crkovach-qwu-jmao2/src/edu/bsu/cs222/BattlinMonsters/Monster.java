 package edu.bsu.cs222.BattlinMonsters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Monster {

	private BufferedImageCreator createdImage = null;
	private String name;
	private int currentHealth;
	private int maxHealth;
	private int attackRating;
	private int defenseRating;
	private int score;
	private Attack primaryAttack;
	private Attack secondaryAttack;

	public Monster(BufferedImageCreator createdImage, String name, int maxHealth, int attack, int defense, Attack primaryAttack, Attack secondaryAttack)
			throws IOException {
		this.createdImage = createdImage;
		this.name = name;
		this.attackRating = attack;
		this.defenseRating = defense;
		this.currentHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.primaryAttack = primaryAttack;
		this.secondaryAttack = secondaryAttack;
	}

	
	public BufferedImageCreator getCreatedImage() {
		return createdImage;
	}
	
	public BufferedImage monsterPicture() throws IOException {
		return createdImage.getBufferedImage();
	}

	public int getMinimumDamage(String selectedAttack) {
		if (selectedAttack.equalsIgnoreCase("primary")){
			return primaryAttack.getMinimumDamage();
		}
		return secondaryAttack.getMinimumDamage();
	}
	
	public int getMaximumDamage(String selectedAttack) {
		if (selectedAttack.equalsIgnoreCase("primary")){
			return primaryAttack.getMaximumDamage();
		}
		return secondaryAttack.getMaximumDamage();
	}
	
	public String monsterName() {
		return name;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getScore() {
		return score;
	}

	public Attack primaryAttack() {
		return primaryAttack;
	}

	public Attack secondaryAttack() {
		return secondaryAttack;
	}

	public void decreaseHealth(int damage) {
		if ((currentHealth - damage) > 0)
			currentHealth -= damage;
		else
			currentHealth = 0;
		System.out.println("health of monster: " + currentHealth);
	}

	public void setScore() {
		score++;
		increaseMaxHealth();
		increaseDefenseRating();
		increaseAttackRating();
	}
	
	public void increaseMaxHealth(){
		this.currentHealth=currentHealth+5;
		this.maxHealth=maxHealth+5;
	}
	
	public void increaseDefenseRating(){
		this.defenseRating++;
	}
	
	public void increaseAttackRating(){
		this.attackRating++;
	}
	
	public void healCurrentHealth(int incomingHealthIncrease){
		if (this.currentHealth+incomingHealthIncrease>this.maxHealth){
			this.currentHealth=this.maxHealth;
		}
		else {
		this.currentHealth = this.currentHealth+incomingHealthIncrease;
		}
	}
	
	public void setCurrentHealth(int currentHealth){
		this.currentHealth = currentHealth;
	}

	public void loadUserScore(int userScore) {
		this.score = userScore;
	}

	public int getAttack() {
		return this.attackRating;
	}
	
	public int getDefense() {
		return this.defenseRating;
	}
}
