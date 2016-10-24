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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import edu.bsu.cs222.BattlinMonsters.Attack;
import edu.bsu.cs222.BattlinMonsters.Battle;
import edu.bsu.cs222.BattlinMonsters.Monster;

public class SelectionGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6529803515165361423L;


	private JButton jPrevious, jNext, jselectMonster;

	public SelectionGUI(final JFrame gamejframe) throws IOException {
		setLayout(null);

		
		
		BufferedImage playerMonsterImage = ImageIO.read(new File(
				"images\\monsters\\BattlinMonster_00.png"));
		final Image scaledPlayerMonsterImage = playerMonsterImage.getScaledInstance(
				224, 287, Image.SCALE_SMOOTH);
		BufferedImage playerMonsterImage2 = ImageIO.read(new File(
				"images\\monsters\\BattlinMonster_02.png"));
		final Image scaledPlayerMonsterImage2 = playerMonsterImage2.getScaledInstance(
				224, 287, Image.SCALE_SMOOTH);
		
		final JLabel picLabel = new JLabel(new ImageIcon(scaledPlayerMonsterImage));
		picLabel.setBounds(135, 55, 224, 287);
		add(picLabel);
		
		BufferedImage descriptionBackground = ImageIO.read(new File(
				"images\\monsterDescriptionBackground.png"));
		final Image scaledDescriptionBackground = descriptionBackground.getScaledInstance(
				675, 350, Image.SCALE_SMOOTH);
		final JLabel descriptionBackgroundLabel = new JLabel(new ImageIcon(scaledDescriptionBackground));
		descriptionBackgroundLabel.setBounds(65, 25, 675, 350);
		add(descriptionBackgroundLabel);
		
		
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
		jNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage));
				descriptionBackgroundLabel.setIcon(new ImageIcon(scaledDescriptionBackground));
			}
		});
		
		
		
		
	
		
		
		BufferedImage skoulDescriptionBackground = ImageIO.read(new File(
				"images\\skoulDescription.png"));
		final Image scaledSkoulDescriptionBackground = skoulDescriptionBackground.getScaledInstance(
				675, 350, Image.SCALE_SMOOTH);
		
		
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
		jPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picLabel.setIcon(new ImageIcon(scaledPlayerMonsterImage2));
				descriptionBackgroundLabel.setIcon(new ImageIcon(scaledSkoulDescriptionBackground));
			}
		});
		
		BufferedImage selectMonsterButtonBufferedImage = ImageIO.read(new File(
				"images\\buttons\\selectMonsterButton.png"));
		Image selectMonsterButtonImage = selectMonsterButtonBufferedImage;
		
		BufferedImage selectMonsterButtonHighlightBufferedImage = ImageIO.read(new File(
				"images\\buttons\\selectMonsterButtonHighlight.png"));
		Image selectMonsterButtonHighlightImage = selectMonsterButtonHighlightBufferedImage;
		
		jselectMonster =new JButton(new ImageIcon(selectMonsterButtonImage));
		jselectMonster.setFocusPainted(false);
		jselectMonster.setMargin(new Insets(0, 0, 0, 0));
		jselectMonster.setBorderPainted(false);
		jselectMonster.setContentAreaFilled(false);
		jselectMonster.setRolloverIcon(new ImageIcon(selectMonsterButtonHighlightImage));
		jselectMonster.setBounds(210, 394, 396, 80);
		jselectMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamejframe.getContentPane().removeAll();
					try {
						gamejframe.getContentPane().add(new BattleGUI());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (AWTError e1) {
						// TODO Auto-generated catch block
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
}
