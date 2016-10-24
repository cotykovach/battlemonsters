package edu.bsu.cs222.BattlinMonstersGUI;

import java.awt.AWTError;
import java.awt.Color;
import java.awt.Image;
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
	private JButton jNewGame, jLoadGame, jExitGame;
	private BufferedImage myMonsterPicture;
	
	
	private class HomeGUIActions implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public  HomeGUI(final JFrame gamejframe) throws  AWTError, IOException, ClassNotFoundException {
	
		
		/*myMonsterPicture = ImageIO.read(new File("images\\BattlinMonster_00.jpg"));
		Image scaledMonsterPicture = myMonsterPicture.getScaledInstance(
				258, 319, Image.SCALE_SMOOTH);
		JLabel myMonster = new JLabel(new ImageIcon(scaledMonsterPicture));
		myMonster.setBounds(57, 71, 258, 319);
		add(myMonster);
		
		jMonsterInfo = new JTextPane();
		jMonsterInfo.setForeground(new Color(51, 102, 255));
		jMonsterInfo.setBackground(new Color(153, 204, 255));
		jMonsterInfo.setEditable(false);
		jMonsterInfo.setText("Monster's Ability:\r\n\r\ngood at bite the enermy \r\n\r\n\r\nMonster's Skill:\r\n\r\nAttack  1:   bite \r\n\r\nAttack  2:   still bite\r\n\r\n");
		jMonsterInfo.setBounds(473, 71, 236, 319);
		add(jMonsterInfo);*/

		
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
		jNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamejframe.getContentPane().removeAll();
				try {
					gamejframe.getContentPane().add(new BattleGUI());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (AWTError e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				gamejframe.repaint();
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
		jLoadGame.setRolloverIcon(new ImageIcon(loadGameButtonHighlightImage));
		jLoadGame.setBounds(285, 319, 250, 57);
		
		monsterSelectionBox.setBounds(84, 419, 193, 23);
		monsterSelectionBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		
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
		jExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setLayout(null);
		//add(monsterSelectionBox);
		add(jNewGame);
		add(jLoadGame);
		//add(jHelp);
		add(jExitGame);
		
		BufferedImage backgroundBufferedImage = ImageIO.read(new File(
				"images\\BattlinMonstersHomeBackground.jpg"));
		Image backgroundImage = backgroundBufferedImage.getScaledInstance(
				810, 500, Image.SCALE_SMOOTH);
		JLabel backgroundImageIcon = new JLabel(new ImageIcon(backgroundImage));
		backgroundImageIcon.setBounds(-10, 0, 810, 500);
		add(backgroundImageIcon);
	}
};
	