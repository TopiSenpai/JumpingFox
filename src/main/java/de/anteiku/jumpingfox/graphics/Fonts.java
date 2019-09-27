package de.anteiku.jumpingfox.graphics;

import java.awt.Font;

import javax.swing.JLabel;

public class Fonts {

	public static Font HEADER;
	public static Font DEFAULT;
	
	public Fonts() {
		HEADER = FontLoader.loadFont("pixelfont");
		DEFAULT = new JLabel().getFont();
	}
	
}
