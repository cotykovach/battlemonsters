package edu.bsu.cs222.BattlinMonstersGUI;

import java.awt.AWTError;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class HomeGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412131820687681043L;
	
	private JLabel jBattlinMonster;
	private JTextPane jMonsterInfo;
	private String[] monsterSelection = { "Akron"};
	private JComboBox monsterSelectionBox = new JComboBox(monsterSelection);
	private JButton jNewGame, jLoadGame, jExitGame, jNext, jPrevious;
	private BufferedImage myMonsterPicture;
	
	
	private class HomeGUIActions implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public  HomeGUI(final JFrame gamejframe) throws  AWTError, IOException, ClassNotFoundException {
	
		BufferedImage newGameButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\newGameButton.png"));
		Image newGameButtonImage = newGameButtonBufferedImage.getScaledInstance(
				250, 62, Image.SCALE_SMOOTH);
		
		BufferedImage newGameButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\newGameButtonHighlight.png"));
		Image newGameButtonHighlightImage = newGameButtonHighlightBufferedImage.getScaledInstance(
				250, 62, Image.SCALE_SMOOTH);
		
		jNewGame = new JButton(new ImageIcon(newGameButtonImage));
		jNewGame.setRolloverIcon(new ImageIcon(newGameButtonHighlightImage));
		jNewGame.setBounds(285, 249, 250, 57);
		jNewGame.setFocusPainted(false);
		jNewGame.setMargin(new Insets(0, 0, 0, 0));
		jNewGame.setBorderPainted(false);
		jNewGame.setContentAreaFilled(false);
		jNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamejframe.getContentPane().removeAll();
					try {
						gamejframe.getContentPane().add(new SelectionGUI(gamejframe));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						gamejframe.repaint();
						gamejframe.setVisible(true);
			}
		});

		
		
		BufferedImage loadGameButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\loadGameButton.png"));
		Image loadGameButtonImage = loadGameButtonBufferedImage.getScaledInstance(
				250, 62, Image.SCALE_SMOOTH);
		
		BufferedImage loadGameButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\loadGameButtonHighlight.png"));
		Image loadGameButtonHighlightImage = loadGameButtonHighlightBufferedImage.getScaledInstance(
				250, 62, Image.SCALE_SMOOTH);
		
		jLoadGame =new JButton(new ImageIcon(loadGameButtonImage));
		jLoadGame.setFocusPainted(false);
		jLoadGame.setMargin(new Insets(0, 0, 0, 0));
		jLoadGame.setBorderPainted(false);
		jLoadGame.setContentAreaFilled(false);
		jLoadGame.setRolloverIcon(new ImageIcon(loadGameButtonHighlightImage));
		jLoadGame.setBounds(285, 319, 250, 57);
		

		
		
		
		BufferedImage exitGameButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\exitGameButton.png"));
		Image exitGameButtonImage = exitGameButtonBufferedImage.getScaledInstance(
				250, 62, Image.SCALE_SMOOTH);
		
		BufferedImage exitGameButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\exitGameButtonHighlight.png"));
		Image exitGameButtonHighlightImage = exitGameButtonHighlightBufferedImage.getScaledInstance(
				250, 62, Image.SCALE_SMOOTH);
		
		jExitGame =new JButton(new ImageIcon(exitGameButtonImage));
		jExitGame.setRolloverIcon(new ImageIcon(exitGameButtonHighlightImage));
		jExitGame.setBounds(285, 389, 250, 57);
		jExitGame.setFocusPainted(false);
		jExitGame.setMargin(new Insets(0, 0, 0, 0));
		jExitGame.setBorderPainted(false);
		jExitGame.setContentAreaFilled(false);
		jExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		BufferedImage nextButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\nextButton.png"));
		Image nextButtonImage = nextButtonBufferedImage;
		
		BufferedImage nextButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\nextButtonHighlight.png"));
		Image nextButtonHighlightImage = nextButtonHighlightBufferedImage;
		
		jNext =new JButton(new ImageIcon(nextButtonImage));
		jNext.setFocusPainted(false);
		jNext.setMargin(new Insets(0, 0, 0, 0));
		jNext.setBorderPainted(false);
		jNext.setContentAreaFilled(false);
		
		jNext.setRolloverIcon(new ImageIcon(nextButtonHighlightImage));
		jNext.setBounds(675, 389, 120, 92);
		
		
		BufferedImage previousButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\previousButton.png"));
		Image previousButtonImage = previousButtonBufferedImage;
		
		BufferedImage previousButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\previousButtonHighlight.png"));
		Image previousButtonHighlightImage = previousButtonHighlightBufferedImage;
		
		jPrevious =new JButton(new ImageIcon(previousButtonImage));
		jPrevious.setFocusPainted(false);
		jPrevious.setMargin(new Insets(0, 0, 0, 0));
		jPrevious.setBorderPainted(false);
		jPrevious.setContentAreaFilled(false);
		jPrevious.setRolloverIcon(new ImageIcon(previousButtonHighlightImage));
		jPrevious.setBounds(0, 389, 120, 92);
		
		setLayout(null);
		//add(monsterSelectionBox);
		add(jNewGame);
		add(jLoadGame);
		//add(jHelp);
		add(jExitGame);
		//add(jNext);
		//add(jPrevious);
		
		BufferedImage backgroundBufferedImage = ImageIO.read(new File(
				"images\\BattlinMonstersHomeBackground.jpg"));
		Image backgroundImage = backgroundBufferedImage.getScaledInstance(
				810, 500, Image.SCALE_SMOOTH);
		JLabel backgroundImageIcon = new JLabel(new ImageIcon(backgroundImage));
		backgroundImageIcon.setBounds(-10, 0, 810, 500);
		add(backgroundImageIcon);
	}
};
	