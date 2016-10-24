package edu.bsu.cs222.BattlinMonstersGUI;

import java.awt.AWTError;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.bsu.cs222.BattlinMonsters.Battle;
import edu.bsu.cs222.BattlinMonsters.EnemyMonsterGenerator;
import edu.bsu.cs222.BattlinMonsters.Save;
import edu.bsu.cs222.BattlinMonsters.Monster;

public class BattleGUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6529803515165361423L;

	private JButton useAttackButton, attack_1Button, attack_2Button,
			saveButton, battleExitButton;
	private BufferedImage healthBarImage, enemyMonsterImage,
			playerMonsterImage, battleLog;
	private Image rescaledHealthBarImage, scaledPlayerMonsterImage,
			scaledEnemyMonsterImage;
	private ImageIcon[] healthBarImageIcon = new ImageIcon[21];
	private String selectedAttack = "primary", playerHealthText,
			enemyHealthText, playerMonsterName, enemyMonsterName, playerScore,
			attackInfo, attackName;
	private Battle currentBattle;
	private JLabel playerHealthTextLabel, enemyHealthTextLabel,
			playerHealthBarLabel, enemyHealthBarLabel, playerMonsterNameLabel,
			enemyMonsterNameLabel, playerScoreLabel, playerMonsterPictureLabel,
			enemyMonsterPictureLabel, txtInstructionOfAttack, txtAttackName;
	private JLabel[] battleLogLabel = new JLabel[19];
	private Monster currentPlayer, currentEnemy;
	private double percentHealth;
	private BufferedImage monsterNameBackgroundBufferedImage;
	private JFrame gamejframe;
	private Clip clip;

	public BattleGUI(final JFrame gamejframe, Monster playerMonster,
			Monster enemyMonster) throws AWTError, IOException,
			ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {

		File soundFile = new File("sounds\\prepareMusic.wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.loop(clip.LOOP_CONTINUOUSLY);
		
		Battle newBattle = new Battle(playerMonster, enemyMonster);
		this.gamejframe = gamejframe;
		this.currentPlayer = playerMonster;
		this.currentEnemy = enemyMonster;
		this.currentBattle = newBattle;
		setLayout(null);
		loadHealthBarImages();

		playerMonsterImage = currentPlayer.monsterPicture();
		enemyMonsterImage = currentEnemy.monsterPicture();

		playerMonsterName = (currentPlayer.monsterName());
		playerMonsterNameLabel = new JLabel(playerMonsterName);
		setNameLabel(playerMonsterNameLabel);

		enemyMonsterName = (currentEnemy.monsterName());
		enemyMonsterNameLabel = new JLabel(enemyMonsterName);
		setNameLabel(enemyMonsterNameLabel);

		playerHealthTextLabel = new JLabel();
		playerHealthBarLabel = new JLabel(healthBarImageIcon[20]);
		setInitialHealthTextAndBar(currentPlayer, playerHealthText,
				playerHealthTextLabel, playerHealthBarLabel);

		enemyHealthTextLabel = new JLabel();
		enemyHealthBarLabel = new JLabel(healthBarImageIcon[20]);
		setInitialHealthTextAndBar(currentEnemy, enemyHealthText,
				enemyHealthTextLabel, enemyHealthBarLabel);

		monsterNameBackgroundBufferedImage = ImageIO.read(new File(
				"images\\MonsterNameLabel.png"));
		Image monsterNameBackgroundImage = monsterNameBackgroundBufferedImage
				.getScaledInstance(250, 35, Image.SCALE_SMOOTH);
		JLabel playerMonsterNameLabel = new JLabel(new ImageIcon(
				monsterNameBackgroundImage));
		playerMonsterNameLabel.setBounds(5, 0, 250, 35);
		JLabel enemyMonsterNameLabel = new JLabel(new ImageIcon(
				monsterNameBackgroundImage));
		enemyMonsterNameLabel.setBounds(545, 0, 250, 35);
		add(playerMonsterNameLabel);
		add(enemyMonsterNameLabel);

		scaledPlayerMonsterImage = playerMonsterImage.getScaledInstance(184,
				247, Image.SCALE_SMOOTH);
		playerMonsterPictureLabel = new JLabel(new ImageIcon(
				scaledPlayerMonsterImage));
		playerMonsterPictureLabel.setBounds(43, 74, 184, 247);
		add(playerMonsterPictureLabel);

		scaledEnemyMonsterImage = enemyMonsterImage.getScaledInstance(184, 247,
				Image.SCALE_SMOOTH);
		enemyMonsterPictureLabel = new JLabel(new ImageIcon(
				scaledEnemyMonsterImage));
		enemyMonsterPictureLabel.setBounds(585, 74, 184, 247);
		add(enemyMonsterPictureLabel);

		playerScore = ("" + currentPlayer.getScore());
		playerScoreLabel = new JLabel(playerScore);
		playerScoreLabel.setFont(playerScoreLabel.getFont().deriveFont(
				Font.BOLD, 18));
		playerScoreLabel.setForeground(Color.BLACK);
		playerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		playerScoreLabel.setBounds(363, -18, 75, 103);
		add(playerScoreLabel);

		BufferedImage scoreBufferedImage = ImageIO.read(new File(
				"images\\ScoreImage.png"));
		Image scoreImage = scoreBufferedImage.getScaledInstance(100, 85,
				Image.SCALE_SMOOTH);
		JLabel scoreImageIcon = new JLabel(new ImageIcon(scoreImage));
		scoreImageIcon.setBounds(350, 0, 100, 85);
		add(scoreImageIcon);

		attackName = "Basic Attack";
		attackInfo = "Deals damage from "
				+ (currentPlayer.getMinimumDamage("primary") + currentPlayer
						.getAttack())
				+ " to "
				+ (currentPlayer.getMaximumDamage("primary") + currentPlayer
						.getAttack()) + ".";

		txtAttackName = new JLabel(attackName);
		txtAttackName.setBounds(30, 365, 550, 90);
		txtAttackName
				.setFont(txtAttackName.getFont().deriveFont(Font.BOLD, 16));
		txtAttackName.setForeground(Color.BLACK);
		txtAttackName.setHorizontalAlignment(JLabel.LEFT);
		add(txtAttackName);

		txtInstructionOfAttack = new JLabel(attackInfo);
		txtInstructionOfAttack.setBounds(30, 395, 550, 90);
		txtInstructionOfAttack.setFont(txtInstructionOfAttack.getFont()
				.deriveFont(Font.ITALIC, 13));
		txtInstructionOfAttack.setForeground(Color.BLACK);
		txtInstructionOfAttack.setHorizontalAlignment(JLabel.LEFT);
		add(txtInstructionOfAttack);

		useAttackButton = new JButton();
		buttonBuilder(useAttackButton, "useAttackButton");
		useAttackButton.setBounds(610, 386, 170, 100);

		attack_1Button = new JButton();
		attack_1Button.setBounds(13, 352, 120, 30);
		buttonBuilder(attack_1Button, "attack_1Button");

		attack_2Button = new JButton();
		attack_2Button.setBounds(133, 352, 120, 30);
		buttonBuilder(attack_2Button, "attack_2Button");

		saveButton = new JButton();
		saveButton.setBounds(545, 352, 120, 30);
		buttonBuilder(saveButton, "saveButton");

		battleExitButton = new JButton();
		battleExitButton.setBounds(664, 352, 120, 30);
		buttonBuilder(battleExitButton, "battleExitButton");
		battleExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.stop();
				gamejframe.getContentPane().removeAll();
				try {
					gamejframe.getContentPane().add(new HomeGUI(gamejframe));
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (AWTError e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
				gamejframe.pack();
			}
		});

		for (int i = 0; i < battleLogLabel.length - 1; i++) {
			battleLogLabel[i] = new JLabel();
			battleLogLabel[i].setFont(battleLogLabel[0].getFont().deriveFont(
					Font.BOLD, 12));
			battleLogLabel[i].setForeground(Color.BLACK);
			battleLogLabel[i].setHorizontalAlignment(JLabel.LEFT);
			battleLogLabel[i].setBounds(265, -10 + (i * 12), 310, 270);
			add(battleLogLabel[i]);
		}

		battleLog = ImageIO.read(new File("images\\battleLog.png"));
		JLabel battleLogLabel = new JLabel(new ImageIcon(battleLog));
		battleLogLabel.setBounds(244, 79, 310, 275);
		add(battleLogLabel);

		BufferedImage monsterZoneBufferedImage = ImageIO.read(new File(
				"images\\MonsterZone.png"));
		Image monsterPlayerZone = monsterZoneBufferedImage.getScaledInstance(
				175, 55, Image.SCALE_SMOOTH);
		JLabel monsterPlayerZoneLabel = new JLabel(new ImageIcon(
				monsterPlayerZone));
		monsterPlayerZoneLabel.setBounds(40, 265, 175, 55);
		add(monsterPlayerZoneLabel);

		JLabel monsterEnemyZoneLabel = new JLabel(new ImageIcon(
				monsterPlayerZone));
		monsterEnemyZoneLabel.setBounds(580, 265, 175, 55);
		add(monsterEnemyZoneLabel);

		BufferedImage attackInfobackgroundBuffered = ImageIO.read(new File(
				"images\\attackInfoBackground.png"));
		Image attackInfoBackgroundImage = attackInfobackgroundBuffered
				.getScaledInstance(800, 120, Image.SCALE_SMOOTH);
		JLabel attackInfoBackgroundLabel = new JLabel(new ImageIcon(
				attackInfoBackgroundImage));
		attackInfoBackgroundLabel.setBounds(0, 380, 800, 120);
		add(attackInfoBackgroundLabel);

		BufferedImage backgroundBufferedImage = ImageIO.read(new File(
				"images\\BattlinMonstersBackground.jpg"));
		Image backgroundImage = backgroundBufferedImage.getScaledInstance(1000,
				500, Image.SCALE_SMOOTH);
		JLabel backgroundImageIcon = new JLabel(new ImageIcon(backgroundImage));
		backgroundImageIcon.setBounds(0, 0, 1000, 500);
		add(backgroundImageIcon);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == useAttackButton)
			useAttackButtonAction();
		else if (source == attack_1Button)
			try {
				attack_1ButtonAction();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (source == attack_2Button)
			try {
				attack_2ButtonAction();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (source == saveButton) {
			Save s = new Save();
			s.saveGame(currentPlayer, currentEnemy);
		}
	}

	private void useAttackButtonAction() {
		if (currentBattle.checkEnemyIsAlive() == true
				&& currentBattle.checkUserIsAlive() == true) {
			playerAttack();
			if (currentBattle.checkEnemyIsAlive() == true) {
				enemyAttack();
			}
		}
	}

	private void playerAttack() {
		int tempHealthCheck = currentPlayer.getCurrentHealth();
		currentBattle.attackComputer(selectedAttack);
		if (tempHealthCheck != currentPlayer.getCurrentHealth()) {
			setBattleLog(currentPlayer.monsterName()
					+ " succesfully heals for "
					+ (currentPlayer.getCurrentHealth() - tempHealthCheck)
					+ ".");
			setHealthText("currentPlayer");
			setHealthBar(currentPlayer);
		}
		useAttackButton.setEnabled(false);
		Timer playerAttack = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setBattleLog("You attack for "
						+ currentBattle.userDamageDealt() + " damage.");
				setHealthText("currentEnemy");
				setHealthBar(currentEnemy);

				BufferedImage battlinMonsterHurtImage = null;
				try {
					battlinMonsterHurtImage = ImageIO
							.read(new File("images\\monsters\\"
									+ currentEnemy.monsterName().replaceAll(
											"\\s+", "") + "_recieveDamage.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				scaledEnemyMonsterImage = battlinMonsterHurtImage
						.getScaledInstance(184, 247, Image.SCALE_SMOOTH);
				enemyMonsterPictureLabel.setIcon(new ImageIcon(
						scaledEnemyMonsterImage));
				enemyMonsterPictureLabel.setLocation(
						enemyMonsterPictureLabel.getX() + 10,
						enemyMonsterPictureLabel.getY());
				Timer recieveDamageAnimation = new Timer(0,
						new ActionListener() {
							public void actionPerformed(ActionEvent evt) {

								scaledEnemyMonsterImage = enemyMonsterImage
										.getScaledInstance(184, 247,
												Image.SCALE_SMOOTH);
								enemyMonsterPictureLabel.setIcon(new ImageIcon(
										scaledEnemyMonsterImage));
								enemyMonsterPictureLabel.setLocation(
										enemyMonsterPictureLabel.getX() - 10,
										enemyMonsterPictureLabel.getY());

								if (currentBattle.checkEnemyIsAlive() == false) {
									currentBattle.victory();
									setHealthText("currentPlayer");
									setHealthBar(currentPlayer);
									playerScoreLabel.setText(""
											+ currentPlayer.getScore());
									useAttackButton.setEnabled(false);
									setBattleLog("You have defeated "
											+ currentEnemy.monsterName() + ".");
									try {
										nextMonster();
									} catch (IOException e) {
										e.printStackTrace();
									}
									if (selectedAttack
											.equalsIgnoreCase("primary")) {
										try {
											attack_1ButtonAction();
										} catch (IOException e) {
											e.printStackTrace();
										}
									} else {
										try {
											attack_2ButtonAction();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									setBattleLog("A "
											+ currentEnemy.monsterName()
											+ " appears.");
								}
							}
						});
				recieveDamageAnimation.setInitialDelay(150);
				recieveDamageAnimation.setRepeats(false);
				recieveDamageAnimation.start();

			}
		});
		playerAttack.setInitialDelay(3000);
		playerAttack.setRepeats(false);
		playerAttack.start();
	}

	private void enemyAttack() {
		enemyAttackResponse();
		Timer enemyAttack = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				setBattleLog(currentEnemy.monsterName() + " attacks for "
						+ currentBattle.enemyDamageDealt() + " damage.");
				setHealthText("currentPlayer");
				setHealthBar(currentPlayer);
				if (currentBattle.checkUserIsAlive() == false) {
					currentBattle.defeat();
					useAttackButton.setEnabled(false);
					setBattleLog("You have been defeated by "
							+ currentEnemy.monsterName() + ".");

				} else {
					useAttackButton.setEnabled(true);
				}

				BufferedImage battlinMonsterHurtImage = null;
				try {
					battlinMonsterHurtImage = ImageIO
							.read(new File("images\\monsters\\"
									+ currentPlayer.monsterName().replaceAll(
											"\\s+", "") + "_recieveDamage.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				scaledPlayerMonsterImage = battlinMonsterHurtImage
						.getScaledInstance(184, 247, Image.SCALE_SMOOTH);
				playerMonsterPictureLabel.setIcon(new ImageIcon(
						scaledPlayerMonsterImage));
				playerMonsterPictureLabel.setLocation(
						playerMonsterPictureLabel.getX() - 10,
						playerMonsterPictureLabel.getY());
				Timer recieveDamageAnimation = new Timer(0,
						new ActionListener() {
							public void actionPerformed(ActionEvent evt) {

								scaledPlayerMonsterImage = playerMonsterImage
										.getScaledInstance(184, 247,
												Image.SCALE_SMOOTH);
								playerMonsterPictureLabel
										.setIcon(new ImageIcon(
												scaledPlayerMonsterImage));
								playerMonsterPictureLabel.setLocation(
										playerMonsterPictureLabel.getX() + 10,
										playerMonsterPictureLabel.getY());
								if (currentBattle.checkUserIsAlive() == false) {
								gameOverGUI();
								}
							}
						});
				recieveDamageAnimation.setInitialDelay(150);
				recieveDamageAnimation.setRepeats(false);
				recieveDamageAnimation.start();
			}

			private void gameOverGUI() {
				clip.stop();
				gamejframe.getContentPane().removeAll();
				try {
					gamejframe.getContentPane().add(new GameOverGUI(gamejframe));
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (AWTError e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
				gamejframe.pack();
			}
		});
		enemyAttack.setInitialDelay(6000);
		enemyAttack.setRepeats(false);
		enemyAttack.start();
	}

	protected void nextMonster() throws IOException {
		EnemyMonsterGenerator enemyMonsterGenerator = new EnemyMonsterGenerator();
		Monster generatedMonster = enemyMonsterGenerator
				.randomGenerateMonster(currentPlayer.getScore());
		this.currentEnemy = generatedMonster;
		currentBattle = new Battle(currentPlayer, currentEnemy);
		enemyMonsterImage = currentEnemy.monsterPicture();
		scaledEnemyMonsterImage = enemyMonsterImage.getScaledInstance(184, 247,
				Image.SCALE_SMOOTH);
		enemyMonsterPictureLabel
				.setIcon((new ImageIcon(scaledEnemyMonsterImage)));
		enemyMonsterName = (currentEnemy.monsterName());
		enemyMonsterNameLabel.setText(enemyMonsterName);
		setHealthText(enemyHealthText);
		setHealthBar(currentEnemy);
		useAttackButton.setEnabled(true);
	}

	private void enemyAttackResponse() {
		int computerAttack = 0 + (int) (Math.random() * (1) + 1);
		if (computerAttack == 0) {
			currentBattle.attackUser("secondary");
		} else if (computerAttack == 1) {
			currentBattle.attackUser("secondary");
		}
	}

	private void attack_1ButtonAction() throws IOException {
		attackName = "Basic Attack";
		attackInfo = "Deals damage from "
				+ (currentPlayer.getMinimumDamage("primary") + currentPlayer
						.getAttack())
				+ " to "
				+ (currentPlayer.getMaximumDamage("primary") + currentPlayer
						.getAttack()) + ".";
		txtAttackName.setText(attackName);
		txtInstructionOfAttack.setText(attackInfo);
		selectedAttackButton(attack_2Button, attack_1Button, "attack_2Button");
		selectedAttack = "primary";
	}

	private void attack_2ButtonAction() throws IOException {
		if (currentPlayer.monsterName().equals("Rokhan")) {
			attackName = "Rok You";
			attackInfo = "Has a 50% chance to deal "
					+ currentPlayer.getAttack() * 5 + " otherwise deals "
					+ currentPlayer.getMaximumDamage("secondary")
					+ " damage. Ignores defense.";
		} else if (currentPlayer.monsterName().equals("Fraemog")) {
			attackName = "Toadel Fire";
			attackInfo = "Has a 15% / 25% / 60% chance to deal "
					+ currentPlayer.getAttack() * 4 + " damage / "
					+ currentPlayer.getAttack() * 3 + " damage / "
					+ currentPlayer.getAttack() + " damage respectively.";
		} else {
			attackName = "Leaf Me Alone";
			attackInfo = "Deals "
					+ currentPlayer.getAttack()
					+ " damage, and has a 10%/20%/70% chance to heal Akron for "
					+ currentPlayer.getAttack() * 3 + " / "
					+ currentPlayer.getAttack() * 2 + " / "
					+ currentPlayer.getAttack() + " respectively.";
		}
		txtAttackName.setText(attackName);
		txtInstructionOfAttack.setText(attackInfo);
		selectedAttackButton(attack_1Button, attack_2Button, "attack_1Button");
		selectedAttack = "secondary";
	}

	public void selectedAttackButton(JButton deselectedAttack,
			JButton selectedAttack, String deselectedButtonName)
			throws IOException {
		BufferedImage buttonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\" + deselectedButtonName + ".png"));
		Image buttonImage = buttonBufferedImage.getScaledInstance(120, 30,
				Image.SCALE_SMOOTH);
		deselectedAttack.setIcon(new ImageIcon(buttonImage));
		selectedAttack.setIcon(selectedAttack.getRolloverIcon());
	}

	private void loadHealthBarImages() throws IOException {
		for (int i = 0; i <= 100; i = i + 5) {
			healthBarImage = ImageIO.read(new File("images\\health\\HealthBar"
					+ i + ".jpg"));
			rescaledHealthBarImage = (healthBarImage.getScaledInstance(250, 25,
					Image.SCALE_SMOOTH));
			healthBarImageIcon[i / 5] = new ImageIcon(rescaledHealthBarImage);
		}
	}

	private void setInitialHealthTextAndBar(Monster healthTextMonster,
			String healthTextOwner, JLabel healthTextLabelOwner,
			JLabel healthBarLabelOwner) {
		healthTextOwner = (healthTextMonster.getCurrentHealth() + "/" + healthTextMonster
				.getMaxHealth());
		healthTextLabelOwner.setFont(healthTextLabelOwner.getFont().deriveFont(
				Font.BOLD, 15));
		healthTextLabelOwner.setForeground(Color.BLACK);
		healthTextLabelOwner.setHorizontalAlignment(JLabel.CENTER);
		healthBarLabelOwner.setLayout(new BorderLayout());
		if (healthTextMonster == this.currentPlayer)
			healthBarLabelOwner.setBounds(10, 35, 240, 21);
		else
			healthBarLabelOwner.setBounds(550, 35, 240, 21);
		healthTextLabelOwner.setText(healthTextOwner);
		add(healthBarLabelOwner);
		healthBarLabelOwner.add(healthTextLabelOwner);
		setHealthBar(healthTextMonster);
	}

	private void setHealthText(String adjustedHealth) {
		if (adjustedHealth.equalsIgnoreCase("currentPlayer")) {
			playerHealthText = (currentPlayer.getCurrentHealth() + "/" + currentPlayer
					.getMaxHealth());
			playerHealthTextLabel.setText(playerHealthText);
		} else {
			enemyHealthText = (currentEnemy.getCurrentHealth() + "/" + currentEnemy
					.getMaxHealth());
			enemyHealthTextLabel.setText(enemyHealthText);
		}
	}

	public void setHealthBar(Monster damagedMonster) {
		percentHealth = (double) damagedMonster.getCurrentHealth()
				/ (double) damagedMonster.getMaxHealth();
		percentHealth = Math
				.round((Math.round(percentHealth * 100) + 4) / 5 * 5);
		int healthBarNumber = (int) (percentHealth / 5);
		if (damagedMonster == currentPlayer) {
			playerHealthBarLabel.setIcon(healthBarImageIcon[healthBarNumber]);
		} else
			enemyHealthBarLabel.setIcon(healthBarImageIcon[healthBarNumber]);
	}

	private void setBattleLog(String incomingBattleLogMessage) {
		for (int i = 0; i < battleLogLabel.length - 1; i++) {
			if (i == 17) {
				battleLogLabel[i].setText(incomingBattleLogMessage);
			} else {
				battleLogLabel[i].setText(battleLogLabel[i + 1].getText());
			}
		}
	}

	private void setNameLabel(JLabel monsterToBeNamed) {
		monsterToBeNamed.setFont(monsterToBeNamed.getFont().deriveFont(
				Font.BOLD, 22));
		monsterToBeNamed.setForeground(Color.BLACK);
		monsterToBeNamed.setHorizontalAlignment(JLabel.CENTER);
		if (monsterToBeNamed == this.playerMonsterNameLabel)
			monsterToBeNamed.setBounds(10, 5, 240, 25);
		else
			monsterToBeNamed.setBounds(550, 5, 240, 25);
		add(monsterToBeNamed);
	}

	public void buttonBuilder(JButton button, String buttonName)
			throws IOException {
		BufferedImage buttonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\" + buttonName + ".png"));
		Image buttonImage = buttonBufferedImage.getScaledInstance(120, 30,
				Image.SCALE_SMOOTH);
		BufferedImage buttonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\" + buttonName + "Highlight.png"));
		Image buttonHighlightImage = buttonHighlightBufferedImage
				.getScaledInstance(120, 30, Image.SCALE_SMOOTH);

		if (buttonName.equalsIgnoreCase("useAttackButton")) {
			buttonImage = buttonBufferedImage.getScaledInstance(170, 90,
					Image.SCALE_SMOOTH);
			buttonHighlightImage = buttonHighlightBufferedImage
					.getScaledInstance(170, 90, Image.SCALE_SMOOTH);
			button.setDisabledIcon(new ImageIcon(buttonHighlightImage));
		}

		button.setIcon(new ImageIcon(buttonImage));
		button.setRolloverIcon(new ImageIcon(buttonHighlightImage));
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		if (buttonName.equalsIgnoreCase("attack_1Button")) {
			button.setIcon(new ImageIcon(buttonHighlightImage));
		}
		button.addActionListener(this);
		add(button);
	}
}
