package edu.bsu.cs222.BattlinMonsters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageCreator {

	private String imagePath;
	
	public BufferedImageCreator(String imageFileName) throws IOException{
		this.imagePath = imageFileName;
	}
	
	public String getImagePath(){
		return imagePath;
	}
	
	public BufferedImage getBufferedImage() throws IOException{
		BufferedImage image = ImageIO.read(new File(imagePath));
		return image;
	}
}
