package edu.bsu.cs222.BattlinMonstersGUI;

import java.awt.AWTError;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextPane;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JLayeredPane;

import edu.bsu.cs222.BattlinMonsters.Attack;
import edu.bsu.cs222.BattlinMonsters.Battle;
import edu.bsu.cs222.BattlinMonsters.Monster;

public class BattleGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6529803515165361423L;

	private JTextPane txtpnScore;
	private JTextPane txtpnBattleLog;
	private JTextField txtInstructionOfAttack;
	private JLayeredPane layeredPane;
	private JButton useAttackButton, attack_1Button, attack_2Button;
	private BufferedImage healthBarImage, enemyMonsterImage;
	private Image rescaledHealthBarImage;
	private ImageIcon[] healthBarImageIcon = new ImageIcon[21];
	private String selectedAttack = "primary", playerHealthText,
			enemyHealthText, playerMonsterName, enemyMonsterName, playerScore,
			attackInfo;
	private Battle currentBattle;
	private JLabel playerHealthTextLabel, enemyHealthTextLabel,
			playerHealthBarLabel, enemyHealthBarLabel, playerMonsterNameLabel,
			enemyMonsterNameLabel, playerScoreLabel;
	private Monster currentPlayer, currentEnemy;
	private double percentHealth;

	public void setHealthBar(double percent, Monster damagedMonster) {
		int healthBarNumber = (int) (percent / 5);
		if (damagedMonster == this.currentPlayer) {
			playerHealthBarLabel.setIcon(healthBarImageIcon[healthBarNumber]);
		} else if (damagedMonster == this.currentEnemy) {
			enemyHealthBarLabel.setIcon(healthBarImageIcon[healthBarNumber]);
		}
	}

	public BattleGUI() throws AWTError, IOException, ClassNotFoundException {
		setLayout(null);
		Attack attack1 = new Attack(4, 10);
		Attack attack2 = new Attack(-1, 15);
		Monster player = new Monster("Akron", 255, 0, attack1, attack2);

		int monsterRandomizer = (int) (Math.random() * (2) + 1);
		System.out.println(monsterRandomizer);
		if (monsterRandomizer == 1) {
			Monster enemy = new Monster("Evil Akron", 100, 0, attack1, attack2);
			Battle newBattle = new Battle(player, enemy);
			this.currentPlayer = player;
			this.currentEnemy = enemy;
			this.currentBattle = newBattle;
		} else {
			Monster enemy = new Monster("Coty, the Destroyer", 135, 0, attack1,
					attack2);
			Battle newBattle = new Battle(player, enemy);

			this.currentPlayer = player;
			this.currentEnemy = enemy;
			this.currentBattle = newBattle;
		}

		int f = 0;
		for (int i = 0; i <= 100; i = i + 5) {
			healthBarImage = ImageIO.read(new File("images\\health\\HealthBar"
					+ i + ".jpg"));
			rescaledHealthBarImage = (healthBarImage.getScaledInstance(250, 25,
					Image.SCALE_SMOOTH));
			healthBarImageIcon[f] = new ImageIcon(rescaledHealthBarImage);
			f++;
		}

		playerMonsterName = (currentPlayer.getName());
		playerMonsterNameLabel = new JLabel(playerMonsterName);
		playerMonsterNameLabel.setFont(playerMonsterNameLabel.getFont()
				.deriveFont(Font.BOLD, 22));
		playerMonsterNameLabel.setForeground(Color.BLACK);
		playerMonsterNameLabel.setHorizontalAlignment(JLabel.CENTER);
		playerMonsterNameLabel.setBounds(30, 5, 240, 25);
		add(playerMonsterNameLabel);

		playerHealthText = (currentPlayer.getCurrentHealth() + "/" + currentPlayer
				.getMaxHealth());
		playerHealthTextLabel = new JLabel(playerHealthText);
		playerHealthTextLabel.setFont(playerHealthTextLabel.getFont()
				.deriveFont(Font.BOLD, 15));
		playerHealthTextLabel.setForeground(Color.BLACK);
		playerHealthTextLabel.setHorizontalAlignment(JLabel.CENTER);

		playerHealthBarLabel = new JLabel(healthBarImageIcon[20]);
		playerHealthBarLabel.setLayout(new BorderLayout());
		playerHealthBarLabel.setBounds(30, 35, 240, 21);
		add(playerHealthBarLabel);
		playerHealthBarLabel.add(playerHealthTextLabel);

		enemyHealthText = (currentEnemy.getCurrentHealth() + "/" + currentEnemy
				.getMaxHealth());

		BufferedImage enemyMonsterNameBufferedImage = ImageIO.read(new File(
				"images\\MonsterNameLabel.png"));
		Image enemyMonsterNameImage = enemyMonsterNameBufferedImage
				.getScaledInstance(250, 35, Image.SCALE_SMOOTH);
		JLabel playerMonsterNameLabel = new JLabel(new ImageIcon(
				enemyMonsterNameImage));
		playerMonsterNameLabel.setBounds(25, 0, 250, 35);

		enemyMonsterName = (currentEnemy.getName());
		enemyMonsterNameLabel = new JLabel(enemyMonsterName);
		enemyMonsterNameLabel.setFont(enemyMonsterNameLabel.getFont()
				.deriveFont(Font.BOLD, 22));
		enemyMonsterNameLabel.setForeground(Color.BLACK);
		enemyMonsterNameLabel.setHorizontalAlignment(JLabel.CENTER);
		enemyMonsterNameLabel.setBounds(530, 5, 240, 25);
		add(enemyMonsterNameLabel);

		JLabel enemyMonsterNameLabel = new JLabel(new ImageIcon(
				enemyMonsterNameImage));
		enemyMonsterNameLabel.setBounds(525, 0, 250, 35);
		add(playerMonsterNameLabel);
		add(enemyMonsterNameLabel);

		enemyHealthTextLabel = new JLabel(enemyHealthText);
		enemyHealthTextLabel.setFont(enemyHealthTextLabel.getFont().deriveFont(
				Font.BOLD, 15));
		enemyHealthTextLabel.setForeground(Color.BLACK);
		enemyHealthTextLabel.setHorizontalAlignment(JLabel.CENTER);

		enemyHealthBarLabel = new JLabel(healthBarImageIcon[20]);
		enemyHealthBarLabel.setLayout(new BorderLayout());
		enemyHealthBarLabel.setBounds(530, 35, 240, 21);
		add(enemyHealthBarLabel);
		enemyHealthBarLabel.add(enemyHealthTextLabel);

		txtpnBattleLog = new JTextPane();
		txtpnBattleLog.setFont(new Font("YouYuan", Font.PLAIN, 11));
		txtpnBattleLog.setText("Battle Log");
		txtpnBattleLog.setEditable(false);
		txtpnBattleLog.setBounds(258, 87, 279, 203);
		add(txtpnBattleLog);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(57, 392, 660, 250);
		add(layeredPane);

		useAttackButton = new JButton("USE");

		useAttackButton.setBounds(620, 392, 120, 90);
		useAttackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentBattle.checkHealth() == true) {
					currentBattle.attackComputer(selectedAttack);
					int computerAttack = 0 + (int) (Math.random() * (1) + 1);
					if (computerAttack == 0) {
						currentBattle.attackUser("secondary");
					} else if (computerAttack == 1) {
						currentBattle.attackUser("secondary");
					}
					playerHealthText = (currentPlayer.getCurrentHealth() + "/" + currentPlayer
							.getMaxHealth());
					enemyHealthText = (currentEnemy.getCurrentHealth() + "/" + currentEnemy
							.getMaxHealth());
					playerHealthTextLabel.setText(playerHealthText);
					enemyHealthTextLabel.setText(enemyHealthText);
					if (currentPlayer.getCurrentHealth()
							/ currentPlayer.getMaxHealth() < 1) {
						percentHealth = (double) currentPlayer
								.getCurrentHealth()
								/ (double) currentPlayer.getMaxHealth();
						percentHealth = Math.round((Math
								.round(percentHealth * 100) + 4) / 5 * 5);
						setHealthBar(percentHealth, currentPlayer);
						percentHealth = (double) currentEnemy
								.getCurrentHealth()
								/ (double) currentEnemy.getMaxHealth();
						percentHealth = Math.round((Math
								.round(percentHealth * 100) + 4) / 5 * 5);
						setHealthBar(percentHealth, currentEnemy);
					}
					if (currentBattle.checkHealth() == false) {
						playerScoreLabel.setText("" + currentPlayer.getScore());
						useAttackButton.setEnabled(false);
					}
				}
			}
		});

		add(useAttackButton);

		attackInfo = "Deals damage from 5 to 10.";
		txtInstructionOfAttack = new JTextField(attackInfo);
		txtInstructionOfAttack.setBounds(10, 0, 550, 90);
		layeredPane.add(txtInstructionOfAttack);

		txtInstructionOfAttack.setEditable(false);
		txtInstructionOfAttack.setColumns(10);

		BufferedImage attack_1ButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\attack_1Button.png"));
		final Image attack_1ButtonImage = attack_1ButtonBufferedImage
				.getScaledInstance(120, 30, Image.SCALE_SMOOTH);

		BufferedImage attack_1ButtonHighlightBufferedImage = ImageIO
				.read(new File("images\\buttons\\attack_1ButtonHighlight.png"));
		final Image attack_1ButtonHighlightImage = attack_1ButtonHighlightBufferedImage
				.getScaledInstance(120, 30, Image.SCALE_SMOOTH);

		BufferedImage attack_2ButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\attack_2Button.png"));
		final Image attack_2ButtonImage = attack_2ButtonBufferedImage
				.getScaledInstance(120, 30, Image.SCALE_SMOOTH);

		BufferedImage attack_2ButtonHighlightBufferedImage = ImageIO
				.read(new File("images\\buttons\\attack_2ButtonHighlight.png"));
		final Image attack_2ButtonHighlightImage = attack_2ButtonHighlightBufferedImage
				.getScaledInstance(120, 30, Image.SCALE_SMOOTH);

		attack_1Button = new JButton(
				new ImageIcon(attack_1ButtonHighlightImage));
		attack_1Button.setRolloverIcon(new ImageIcon(
				attack_1ButtonHighlightImage));
		attack_1Button.setBounds(23, 352, 120, 30);

		attack_1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attackInfo = "Deals damage from 5 to 10.";
				txtInstructionOfAttack.setText(attackInfo);
				attack_1Button.setIcon(new ImageIcon(
						attack_1ButtonHighlightImage));
				attack_2Button.setIcon(new ImageIcon(attack_2ButtonImage));
				selectedAttack = "primary";
				System.out.println("Primary attack chosen!");

			}
		});
		add(attack_1Button);

		attack_2Button = new JButton(new ImageIcon(attack_2ButtonImage));
		attack_2Button.setRolloverIcon(new ImageIcon(
				attack_2ButtonHighlightImage));
		attack_2Button.setBounds(143, 352, 120, 30);
		attack_2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attackInfo = "Deals damage from 1 to 15.";
				txtInstructionOfAttack.setText(attackInfo);
				attack_1Button.setIcon(new ImageIcon(attack_1ButtonImage));
				attack_2Button.setIcon(new ImageIcon(
						attack_2ButtonHighlightImage));
				selectedAttack = "secondary";
				System.out.println("Secondary attack chosen!");
			}
		});
		add(attack_2Button);

		BufferedImage playerMonsterImage = ImageIO.read(new File(
				"images\\BattlinMonster_00.png"));
		Image scaledPlayerMonsterImage = playerMonsterImage.getScaledInstance(
				184, 247, Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(scaledPlayerMonsterImage));
		picLabel.setBounds(57, 74, 184, 247);
		add(picLabel);

		if ((currentEnemy.getName().equals("Evil Akron"))) {
			enemyMonsterImage = ImageIO.read(new File(
					"images\\BattlinMonster_01.png"));
		} else if ((currentEnemy.getName().equals("Coty, the Destroyer"))) {
			enemyMonsterImage = ImageIO.read(new File(
					"images\\BattlinMonster_99.png"));
		}

		Image scaledEnemyMonsterImage = enemyMonsterImage.getScaledInstance(
				184, 247, Image.SCALE_SMOOTH);
		JLabel picLabel2 = new JLabel(new ImageIcon(scaledEnemyMonsterImage));
		picLabel2.setBounds(570, 74, 184, 247);
		add(picLabel2);

		BufferedImage saveButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\saveButton.png"));
		Image saveButtonImage = saveButtonBufferedImage.getScaledInstance(120,
				30, Image.SCALE_SMOOTH);

		BufferedImage saveButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\saveButtonHighlight.png"));
		Image saveButtonHighlightImage = saveButtonHighlightBufferedImage
				.getScaledInstance(120, 30, Image.SCALE_SMOOTH);

		JButton btnSave = new JButton(new ImageIcon(saveButtonImage));
		btnSave.setBounds(545, 352, 120, 30);
		btnSave.setRolloverIcon(new ImageIcon(saveButtonHighlightImage));
		add(btnSave);

		BufferedImage returnButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\returnButton.png"));
		Image returnButtonImage = returnButtonBufferedImage.getScaledInstance(
				120, 30, Image.SCALE_SMOOTH);

		BufferedImage returnButtonHighlightBufferedImage = ImageIO
				.read(new File("images\\buttons\\returnButtonHighlight.png"));
		Image returnButtonHighlightImage = returnButtonHighlightBufferedImage
				.getScaledInstance(120, 30, Image.SCALE_SMOOTH);

		JButton btnReturn = new JButton(new ImageIcon(returnButtonImage));
		btnReturn.setBounds(664, 352, 120, 30);
		btnReturn.setRolloverIcon(new ImageIcon(returnButtonHighlightImage));
		add(btnReturn);

		playerScore = ("" + currentPlayer.getScore());
		playerScoreLabel = new JLabel(playerScore);
		playerScoreLabel.setFont(enemyMonsterNameLabel.getFont().deriveFont(
				Font.BOLD, 22));
		playerScoreLabel.setForeground(Color.BLACK);
		playerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		playerScoreLabel.setBounds(355, -15, 75, 103);
		add(playerScoreLabel);

		BufferedImage scoreBufferedImage = ImageIO.read(new File(
				"images\\ScoreImage.png"));
		Image scoreImage = scoreBufferedImage.getScaledInstance(75, 103,
				Image.SCALE_SMOOTH);
		JLabel scoreImageIcon = new JLabel(new ImageIcon(scoreImage));
		scoreImageIcon.setBounds(355, -5, 75, 103);
		add(scoreImageIcon);

		BufferedImage monsterZoneBufferedImage = ImageIO.read(new File(
				"images\\MonsterZone.png"));
		Image monsterPlayerZone = monsterZoneBufferedImage.getScaledInstance(
				175, 55, Image.SCALE_SMOOTH);
		JLabel monsterPlayerZoneLabel = new JLabel(new ImageIcon(
				monsterPlayerZone));
		monsterPlayerZoneLabel.setBounds(55, 265, 175, 55);
		add(monsterPlayerZoneLabel);

		JLabel monsterEnemyZoneLabel = new JLabel(new ImageIcon(
				monsterPlayerZone));
		monsterEnemyZoneLabel.setBounds(565, 265, 175, 55);
		add(monsterEnemyZoneLabel);

		BufferedImage backgroundBufferedImage = ImageIO.read(new File(
				"images\\BattlinMonstersBackground.jpg"));
		Image backgroundImage = backgroundBufferedImage.getScaledInstance(1000,
				500, Image.SCALE_SMOOTH);
		JLabel backgroundImageIcon = new JLabel(new ImageIcon(backgroundImage));
		backgroundImageIcon.setBounds(0, 0, 1000, 500);
		add(backgroundImageIcon);

	}
}
