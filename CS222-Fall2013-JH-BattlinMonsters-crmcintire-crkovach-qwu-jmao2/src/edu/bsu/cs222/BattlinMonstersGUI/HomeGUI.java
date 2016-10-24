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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.bsu.cs222.BattlinMonsters.Load;
import edu.bsu.cs222.BattlinMonsters.Monster;

public class HomeGUI extends JPanel {

	private static final long serialVersionUID = -2412131820687681043L;
	private JButton newGameButton, loadGameButton, exitGameButton,
			tutorialButton;
	private Clip clip;
	public HomeGUI(final JFrame gamejframe) throws AWTError, IOException,
			ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException  {
		Logger logger = (Logger) LoggerFactory.getLogger(HomeGUI.class);
		logger.info("Home panel");
		newGameButton = new JButton();
		newGameButton.setBounds(285, 149, 250, 57);
		buttonBuilder(newGameButton, "newGameButton");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.stop();
				gamejframe.getContentPane().removeAll();
				try {
					gamejframe.getContentPane().add(
							new MonsterSelectionGUI(gamejframe));
				} catch (AWTError e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
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
		File soundFile = new File("sounds\\mainMenuMusic.wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.loop(clip.LOOP_CONTINUOUSLY);
		loadGameButton = new JButton();
		loadGameButton.setBounds(285, 229, 250, 57);
		buttonBuilder(loadGameButton, "loadGameButton");
		loadGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				String userHomeFolder = System.getProperty("user.home");
				chooser.setCurrentDirectory(new File(userHomeFolder
						+ "\\Desktop"));
				int retrival = chooser.showOpenDialog(null);
				File fileToOpen = null;
				if (retrival == JFileChooser.APPROVE_OPTION) {
					fileToOpen = chooser.getSelectedFile();
				}
				Load load = new Load();
				load.parseFile(fileToOpen);
				Monster user = load.loadUserMonster();
				Monster enemy = load.loadEnemyMonster();
				gamejframe.getContentPane().removeAll();
				try {
					clip.stop();
					gamejframe.getContentPane().add(
							new BattleGUI(gamejframe, user, enemy));
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
				gamejframe.repaint();
				gamejframe.setVisible(true);
			}
		});

		tutorialButton = new JButton();
		tutorialButton.setBounds(285, 309, 250, 57);
		buttonBuilder(tutorialButton, "tutorialButton");
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.stop();
				try {
					gamejframe.getContentPane().removeAll();
					gamejframe.getContentPane().add(
							new TutorialGUI(gamejframe));
				} catch (AWTError e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				gamejframe.repaint();
				gamejframe.setVisible(true);
			}
		});

		exitGameButton = new JButton();
		exitGameButton.setBounds(285, 389, 250, 57);
		buttonBuilder(exitGameButton, "exitGameButton");
		exitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		setLayout(null);
		ImageIcon gifImage = new ImageIcon("images\\mainMenuTest.gif");
        JLabel background = new JLabel(gifImage);
		background.setBounds(-10, 0, 810, 500);
		add(background);
	}

	public void buttonBuilder(JButton button, String buttonName)
			throws IOException {
		BufferedImage buttonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\" + buttonName + ".png"));
		Image buttonImage = buttonBufferedImage.getScaledInstance(250, 62,
				Image.SCALE_SMOOTH);
		BufferedImage buttonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\" + buttonName + "Highlight.png"));
		Image buttonHighlightImage = buttonHighlightBufferedImage
				.getScaledInstance(250, 62, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(buttonImage));
		button.setRolloverIcon(new ImageIcon(buttonHighlightImage));
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		add(button);
	}

};