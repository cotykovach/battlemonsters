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

public class TutorialGUI extends JPanel {

	private static final long serialVersionUID = 6529803515165361423L;

	private JButton jNext, jMainMenu;
	private int tutorialPage = 0;
	private Clip clip;
	
	public TutorialGUI(final JFrame gamejframe) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		setLayout(null);
		File soundFile = new File("sounds\\prepareMusic.wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.loop(clip.LOOP_CONTINUOUSLY);

		BufferedImage tutorialPageOneBackground = ImageIO.read(new File(
				"images\\tutorial_0.png"));
		final Image scaledTutorialPageOneBackgroundBackground = tutorialPageOneBackground
				.getScaledInstance(810, 500, Image.SCALE_SMOOTH);
		final JLabel tutorialBackgroundLabel = new JLabel(new ImageIcon(
				scaledTutorialPageOneBackgroundBackground));
		tutorialBackgroundLabel.setBounds(0, 0, 810, 500);
		

		BufferedImage nextButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\nextButton.png"));
		Image nextButtonImage = nextButtonBufferedImage.getScaledInstance(155, 55, Image.SCALE_SMOOTH);

		BufferedImage nextButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\nextButtonHighlight.png"));
		Image nextButtonHighlightImage = nextButtonHighlightBufferedImage.getScaledInstance(155, 55, Image.SCALE_SMOOTH);

		jNext = new JButton(new ImageIcon(nextButtonImage));
		buttonBuilder(jNext);
		jNext.setRolloverIcon(new ImageIcon(nextButtonHighlightImage));
		jNext.setBounds(675, 425, 155, 55);
		

		BufferedImage tutorialPageTwoBackground = ImageIO.read(new File(
				"images\\tutorial_1.png"));
		final Image scaledTutorialPageTwoBackgroundBackground = tutorialPageTwoBackground
				.getScaledInstance(810, 500, Image.SCALE_SMOOTH);

		BufferedImage tutorialPageThreeBackground = ImageIO.read(new File(
				"images\\tutorial_2.png"));
		final Image scaledTutorialPageThreeBackgroundBackground = tutorialPageThreeBackground
				.getScaledInstance(810, 500, Image.SCALE_SMOOTH);

		jNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tutorialPage == 2){
					tutorialPage=0;
				}
				else {
					tutorialPage++;
				}
				
				if (tutorialPage == 0){
					tutorialBackgroundLabel.setIcon(new ImageIcon(
							scaledTutorialPageOneBackgroundBackground));
					}
				else if (tutorialPage == 1){
					tutorialBackgroundLabel.setIcon(new ImageIcon(
							scaledTutorialPageTwoBackgroundBackground));
				}
				else {tutorialPage = 2;
				remove(jNext);
				tutorialBackgroundLabel.setIcon(new ImageIcon(
						scaledTutorialPageThreeBackgroundBackground));
				}
				
			}
		});
		
		BufferedImage selectMonsterButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\mainMenuButton.png"));
		Image mainMenuButtonImage = selectMonsterButtonBufferedImage
				.getScaledInstance(225, 55, Image.SCALE_SMOOTH);

		BufferedImage mainMenuButtonHighlightBufferedImage = ImageIO
				.read(new File(
						"images\\buttons\\mainMenuButtonHighlight.png"));
		Image mainMenuButtonHighlightImage = mainMenuButtonHighlightBufferedImage
				.getScaledInstance(225, 55, Image.SCALE_SMOOTH);

		jMainMenu = new JButton(new ImageIcon(mainMenuButtonImage));
		buttonBuilder(jMainMenu);
		jMainMenu.setRolloverIcon(new ImageIcon(
				mainMenuButtonHighlightImage));
		jMainMenu.setBounds(300, 425, 225, 55);
		jMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.stop();
				gamejframe.getContentPane().removeAll();
				try {
					gamejframe.getContentPane().add(
							new HomeGUI(gamejframe));
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

		add(jMainMenu);
		add(jNext);
		add(tutorialBackgroundLabel);
	}

	public void buttonBuilder(JButton button) {
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
	}
}
