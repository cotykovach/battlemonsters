package edu.bsu.cs222.BattlinMonstersGUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
		public static JFrame gamejframe = new JFrame("Battlin' Monsters!");
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
		logger.info("Main panel");
		gamejframe.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\BattlinMonsterLogo.png"));
		
		double ratio=1.6;
		double length=800;
		double height=length/ratio;
		double locationX = length/10;
		double locationY = locationX/ratio;
		
		gamejframe.getContentPane().setPreferredSize(new Dimension((int) length,(int) height));
		gamejframe.setBounds((int)locationX,(int)locationY,(int)length,(int)height);
		gamejframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamejframe.setVisible(true);
		gamejframe.setResizable(false);
		gamejframe.getContentPane().add(new HomeGUI(gamejframe));
		gamejframe.pack();
	}	
}



