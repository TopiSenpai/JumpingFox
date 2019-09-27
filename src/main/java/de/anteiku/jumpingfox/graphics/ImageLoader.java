package de.anteiku.jumpingfox.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			InputStream res = ImageLoader.class.getResourceAsStream("/textures/" + path + ".png");
			if(res != null) {
				return ImageIO.read(res);
			}
			else {
				System.out.println("Failed 2 load Image: /textures/" + path + ".png");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
