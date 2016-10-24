package edu.bsu.cs222.BattlinMonstersGUI;

import java.awt.AWTError;
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

import edu.bsu.cs222.BattlinMonsters.Monster;
import edu.bsu.cs222.BattlinMonsters.PlayerMonsterGenerator;
import edu.bsu.cs222.BattlinMonsters.EnemyMonsterGenerator;

public class MonsterSelectionGUI extends JPanel {

	private static final long serialVersionUID = 6529803515165361423L;

	private JButton jPrevious, jNext, jselectMonster;
	private int monsterSelected = 0;
	private Clip clip;
	
	public MonsterSelectionGUI(final JFrame gamejframe) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		setLayout(null);
		File soundFile = new File("sounds\\prepareMusic.wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.loop(clip.LOOP_CONTINUOUSLY);
		
		
		BufferedImage playerMonsterImage = ImageIO.read(new File(
				"images\\monsters\\BattlinMonster_00.png"));
		final Image scaledPlayerMonsterImage = playerMonsterImage
				.getScaledInstance(224, 287, Image.SCALE_SMOOTH);
		BufferedImage playerMonsterImage2 = ImageIO.read(new File(
				"images\\monsters\\BattlinMonster_08.png"));
		final Image scaledPlayerMonsterImage2 = playerMonsterImage2
				.getScaledInstance(224, 287, Image.SCALE_SMOOTH);
		BufferedImage playerMonsterImage3 = ImageIO.read(new File(
				"images\\monsters\\BattlinMonster_07.png"));
		final Image scaledPlayerMonsterImage3 = playerMonsterImage3
				.getScaledInstance(224, 287, Image.SCALE_SMOOTH);
		

		final JLabel picLabel = new JLabel(new ImageIcon(
				scaledPlayerMonsterImage));
		picLabel.setBounds(135, 55, 224, 287);
		add(picLabel);

		BufferedImage descriptionBackground = ImageIO.read(new File(
				"images\\akronDescription.png"));
		final Image scaledDescriptionBackground = descriptionBackground
				.getScaledInstance(675, 350, Image.SCALE_SMOOTH);
		final JLabel descriptionBackgroundLabel = new JLabel(new ImageIcon(
				scaledDescriptionBackground));
		descriptionBackgroundLabel.setBounds(65, 25, 675, 350);
		add(descriptionBackgroundLabel);

		BufferedImage nextButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\nextButton.png"));
		Image nextButtonImage = nextButtonBufferedImage;

		BufferedImage nextButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\nextButtonHighlight.png"));
		Image nextButtonHighlightImage = nextButtonHighlightBufferedImage;

		jNext = new JButton(new ImageIcon(nextButtonImage));
		buttonBuilder(jNext);
		jNext.setRolloverIcon(new ImageIcon(nextButtonHighlightImage));
		jNext.setBounds(675, 389, 120, 92);
		

		BufferedImage skoulDescriptionBackground = ImageIO.read(new File(
				"images\\fraemogDescription.png"));
		final Image scaledSkoulDescriptionBackground = skoulDescriptionBackground
				.getScaledInstance(675, 350, Image.SCALE_SMOOTH);

		BufferedImage woodSpiritDescriptionBackground = ImageIO.read(new File(
				"images\\rokhanDescription.png"));
		final Image scaledWoodSpiritDescriptionBackground = woodSpiritDescriptionBackground
				.getScaledInstance(675, 350, Image.SCALE_SMOOTH);
		
		BufferedImage previousButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\previousButton.png"));
		Image previousButtonImage = previousButtonBufferedImage;

		BufferedImage previousButtonHighlightBufferedImage = ImageIO
				.read(new File("images\\buttons\\previousButtonHighlight.png"));
		Image previousButtonHighlightImage = previousButtonHighlightBufferedImage;

		jPrevious = new JButton(new ImageIcon(previousButtonImage));
		buttonBuilder(jPrevious);
		jPrevious.setRolloverIcon(new ImageIcon(previousButtonHighlightImage));
		jPrevious.setBounds(0, 389, 120, 92);
		jPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (monsterSelected == 0){
					monsterSelected=2;
				}
				else {
					monsterSelected--;
				}
			
				if (monsterSelected == 0){
					picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage));
					descriptionBackgroundLabel.setIcon(new ImageIcon(
							scaledDescriptionBackground));
					}
				else if (monsterSelected == 1){
					picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage2));
					descriptionBackgroundLabel.setIcon(new ImageIcon(
							scaledSkoulDescriptionBackground));
				}
				else {monsterSelected = 2;
				picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage3));
				descriptionBackgroundLabel.setIcon(new ImageIcon(
						scaledWoodSpiritDescriptionBackground));
				}
				
			}
		});

		jNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (monsterSelected == 2){
					monsterSelected=0;
				}
				else {
					monsterSelected++;
				}
				
				if (monsterSelected == 0){
					picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage));
					descriptionBackgroundLabel.setIcon(new ImageIcon(
							scaledDescriptionBackground));
					}
				else if (monsterSelected == 1){
					picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage2));
					descriptionBackgroundLabel.setIcon(new ImageIcon(
							scaledSkoulDescriptionBackground));
				}
				else {monsterSelected = 2;
				picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage3));
				descriptionBackgroundLabel.setIcon(new ImageIcon(
						scaledWoodSpiritDescriptionBackground));
				}
				
			}
		});
		
		BufferedImage selectMonsterButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\selectMonsterButton.png"));
		Image selectMonsterButtonImage = selectMonsterButtonBufferedImage;

		BufferedImage selectMonsterButtonHighlightBufferedImage = ImageIO
				.read(new File(
						"images\\buttons\\selectMonsterButtonHighlight.png"));
		Image selectMonsterButtonHighlightImage = selectMonsterButtonHighlightBufferedImage;

		jselectMonster = new JButton(new ImageIcon(selectMonsterButtonImage));
		buttonBuilder(jselectMonster);
		jselectMonster.setRolloverIcon(new ImageIcon(
				selectMonsterButtonHighlightImage));
		jselectMonster.setBounds(210, 394, 396, 80);
		jselectMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamejframe.getContentPane().removeAll();
				try {
					clip.stop();
					PlayerMonsterGenerator playerMonsterGenerator = new PlayerMonsterGenerator();
					Monster playerMonster = playerMonsterGenerator
							.selectedMonsterGenerator(monsterSelected);
					EnemyMonsterGenerator enemyMonsterGenerator = new EnemyMonsterGenerator();
					Monster enemyMonster = enemyMonsterGenerator.randomGenerateMonster(0);
					gamejframe.getContentPane().add(
							new BattleGUI(gamejframe, playerMonster, enemyMonster));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (AWTError e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
				gamejframe.repaint();
				gamejframe.setVisible(true);
			}
		});

		add(jselectMonster);
		add(jNext);
		add(jPrevious);

		BufferedImage backgroundBufferedImage = ImageIO.read(new File(
				"images\\BattlinMonstersBackground.jpg"));
		Image backgroundImage = backgroundBufferedImage.getScaledInstance(1000,
				500, Image.SCALE_SMOOTH);
		JLabel backgroundImageIcon = new JLabel(new ImageIcon(backgroundImage));
		backgroundImageIcon.setBounds(0, 0, 1000, 500);
		add(backgroundImageIcon);

	}

	public void buttonBuilder(JButton button) {
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
	}
}
