package edu.bsu.cs222.BattlinMonsters;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Load {

	private String content;
	private String userImagePath;
	private String userName;
	private int userCurrentHealth;
	private int userMaximumHealth;
	private int userScore;
	private int userAttackRating;
	private int userDefenseRating;
	private int userAttack1MinDamage;
	private int userAttack1MaxDamage;
	private String userAttack1Name;
	private int userAttack2MinDamage;
	private int userAttack2MaxDamage;
	private String userAttack2Name;
	private Monster userMonster;
	private String enemyImagePath;
	private String enemyName;
	private int enemyCurrentHealth;
	private int enemyMaximumHealth;
	private int enemyAttackRating;
	private int enemyDefenseRating;
	private int enemyAttack1MinDamage;
	private int enemyAttack1MaxDamage;
	private String enemyAttack1Name;
	private int enemyAttack2MinDamage;
	private int enemyAttack2MaxDamage;
	private String enemyAttack2Name;
	private Monster enemyMonster;

	public void parseFile(File fileToParse) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					if (qName.equalsIgnoreCase("userImagePath")){
						userImagePath = content;
					}
					else if (qName.equalsIgnoreCase("userName"))
						userName = content;
					else if (qName.equalsIgnoreCase("userCurrentHealth"))
						userCurrentHealth = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userMaximumHealth"))
						userMaximumHealth = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userAttackRating"))
						userAttackRating = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userDefenseRating"))
						userDefenseRating = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userScore"))
						userScore = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userAttack1MinDamage"))
						userAttack1MinDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userAttack1MaxDamage"))
						userAttack1MaxDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userAttack1Name"))
						userAttack1Name = content;
					else if (qName.equalsIgnoreCase("userAttack2MinDamage"))
						userAttack2MinDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userAttack2MaxDamge"))
						userAttack2MaxDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("userAttack2Name"))
						userAttack2Name = content;
					else if (qName.equalsIgnoreCase("enemyImagePath"))
						enemyImagePath = content;
					else if (qName.equalsIgnoreCase("enemyName"))
						enemyName = content;
					else if (qName.equalsIgnoreCase("enemyCurrentHealth"))
						enemyCurrentHealth = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyMaximumHealth"))
						enemyMaximumHealth = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyAttackRating"))
						enemyAttackRating = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyDefenseRating"))
						enemyDefenseRating = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyAttack1MinDamage"))
						enemyAttack1MinDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyAttack1MaxDamage"))
						enemyAttack1MaxDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyAttack1Name"))
						enemyAttack1Name = content;
					else if (qName.equalsIgnoreCase("enemyAttack2MinDamage"))
						enemyAttack2MinDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyAttack2MaxDamge"))
						enemyAttack2MaxDamage = Integer.parseInt(content);
					else if (qName.equalsIgnoreCase("enemyAttack2Name")){
						enemyAttack2Name = content;
					}
					try {
						createUserMonster();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						createEnemyMonster();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}

				public void characters(char[] ch, int start, int length)
						throws SAXException {
					content = new String(ch, start, length).trim().replace(
							"\n", "");
				}
			};

			saxParser.parse(fileToParse, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createUserMonster() throws IOException {
		BufferedImageCreator image = new BufferedImageCreator(userImagePath);
		Attack userAttack1 = new Attack(userAttack1MinDamage,
				userAttack1MaxDamage);
		Attack userAttack2 = new Attack(userAttack2MinDamage,
				userAttack2MaxDamage);
		userAttack1.setAttackName(userAttack1Name);
		userAttack2.setAttackName(userAttack2Name);
		userMonster = new Monster(image, userName, userMaximumHealth, userAttackRating, userDefenseRating,
				userAttack1, userAttack2);
		userMonster.setCurrentHealth(userCurrentHealth);
		userMonster.loadUserScore(userScore);
	}

	private void createEnemyMonster() throws IOException {
		BufferedImageCreator image = new BufferedImageCreator(enemyImagePath);
		Attack enemyAttack1 = new Attack(enemyAttack1MinDamage,
				enemyAttack1MaxDamage);
		Attack enemyAttack2 = new Attack(enemyAttack2MinDamage,
				enemyAttack2MaxDamage);
		enemyAttack1.setAttackName(enemyAttack1Name);
		enemyAttack2.setAttackName(enemyAttack2Name);
		enemyMonster = new Monster(image, enemyName, enemyMaximumHealth, enemyAttackRating, enemyDefenseRating, enemyAttack1, enemyAttack2);
		enemyMonster.setCurrentHealth(enemyCurrentHealth);
	}

	public Monster loadUserMonster() {
		return userMonster;
	}

	public Monster loadEnemyMonster() {
		return enemyMonster;
	}
}
