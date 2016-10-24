package edu.bsu.cs222.BattlinMonsters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

public class Save {

	public void saveGame(Monster user, Monster enemy) {
		JFileChooser chooser = new JFileChooser();
		String userHomeFolder = System.getProperty("user.home");
		chooser.setCurrentDirectory(new File(userHomeFolder + "\\Desktop"));
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(chooser.getSelectedFile() + ".xml"));
				out.write("<xml>");
				out.newLine();
				out.write("<userImagePath>" + user.getCreatedImage().getImagePath() + "</userImagePath>");
				out.newLine();
				out.write("<userName>" + user.monsterName() + "</userName>");
				out.newLine();
				out.write("<userCurrentHealth>" + user.getCurrentHealth() + "</userCurrentHealth>");
				out.newLine();
				out.write("<userMaximumHealth>" + user.getMaxHealth() + "</userMaximumHealth>");
				out.newLine();
				out.write("<userScore>" + user.getScore() + "</userScore>");
				out.newLine();
				out.write("<userAttackRating>" + user.getAttack() + "</userAttackRating>");
				out.newLine();
				out.write("<userDefenseRating>" + user.getDefense() + "</userDefenseRating>");
				out.newLine();
				out.write("<userAttack1MinDamage>" + user.primaryAttack().getMinimumDamage() + "</userAttack1MinDamage>");
				out.newLine();
				out.write("<userAttack1MaxDamage>" + user.primaryAttack().getMaximumDamage() + "</userAttack1MaxDamage>");
				out.newLine();
				out.write("<userAttack1Name>" + user.primaryAttack().getName() + "</userAttack1Name>");
				out.newLine();
				out.write("<userAttack2MinDamage>" + user.secondaryAttack().getMinimumDamage() + "</userAttack2MinDamage>");
				out.newLine();
				out.write("<userAttack2MaxDamage>" + user.secondaryAttack().getMinimumDamage() + "</userAttack2MaxDamage>");
				out.newLine();
				out.write("<userAttack2Name>" + user.secondaryAttack().getName() + "</userAttack2Name>");
				out.newLine();
				out.write("<enemyImagePath>" + enemy.getCreatedImage().getImagePath() + "</enemyImagePath>");
				out.newLine();
				out.write("<enemyName>" + enemy.monsterName() + "</enemyName>");
				out.newLine();
				out.write("<enemyCurrentHealth>" + enemy.getCurrentHealth() + "</enemyCurrentHealth>");
				out.newLine();
				out.write("<enemyMaximumHealth>" + enemy.getMaxHealth() + "</enemyMaximumHealth>");
				out.newLine();
				out.write("<enemyAttackRating>" + enemy.getAttack() + "</enemyAttackRating>");
				out.newLine();
				out.write("<enemyDefenseRating>" + enemy.getDefense() + "</enemyDefenseRating>");
				out.newLine();
				out.write("<enemyAttack1MinDamage>" + enemy.primaryAttack().getMinimumDamage() + "</enemyAttack1MinDamage>");
				out.newLine();
				out.write("<enemyAttack1MaxDamage>" + enemy.primaryAttack().getMaximumDamage() + "</enemyAttack1MaxDamage>");
				out.newLine();
				out.write("<enemyAttack1Name>" + user.primaryAttack().getName() + "</enemyAttack1Name>");
				out.newLine();
				out.write("<enemyAttack2MinDamage>" + enemy.secondaryAttack().getMinimumDamage() + "</enemyAttack2MinDamage>");
				out.newLine();
				out.write("<enemyAttack2MaxDamage>" + enemy.secondaryAttack().getMinimumDamage() + "</enemyAttack2MaxDamage>");
				out.newLine();
				out.write("<enemyAttack2Name>" + user.secondaryAttack().getName() + "</enemyAttack2Name>");
				out.newLine();
				out.write("</xml>");
				out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
