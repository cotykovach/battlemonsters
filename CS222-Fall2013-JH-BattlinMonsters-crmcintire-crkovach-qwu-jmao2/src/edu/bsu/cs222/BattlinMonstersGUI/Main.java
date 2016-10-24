package edu.bsu.cs222.BattlinMonstersGUI;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Main {
		public static JFrame gamejframe = new JFrame("Battlin' Monsters!");
	public static void main(String[] args) throws IOException, ClassNotFoundException {
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



