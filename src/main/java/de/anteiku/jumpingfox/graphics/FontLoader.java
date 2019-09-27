package de.anteiku.jumpingfox.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;


public class FontLoader {

	public static Font loadFont(String path) {
		try {
			InputStream res = FontLoader.class.getResourceAsStream("/fonts/" + path + ".ttf");
			if(res != null) {
				Font font = Font.createFont(Font.TRUETYPE_FONT, res);
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
				return font;
			}
			else {
				System.out.println("Failed 2 load Font: /fonts/" + path + ".ttf");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
