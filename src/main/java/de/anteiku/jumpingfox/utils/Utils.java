package de.anteiku.jumpingfox.utils;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.tiles.Tile;

public class Utils {

	public static int calculateTileX(int x, int px) {
		return (px - (Constants.WIDTH / 2 - x) + Tile.WIDTH / 2) / Tile.WIDTH;
	}
	
	public static int calculateTileY(int y, int py) {
		return (py - (Constants.HEIGHT / 2 - y) + Tile.HEIGHT / 2) / Tile.HEIGHT;
	}
	
	public static BufferedImage fitImageToRect(BufferedImage image, Rectangle rect){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage;
        if(width > height){
            float wh = Constants.WIDTH / Constants.HEIGHT;
            nImage = image.getSubimage(0, 0, width, (int) (height * wh));
        }
        else if(width < height){
            float hw = Constants.HEIGHT / Constants.WIDTH;
            nImage = image.getSubimage(0, 0, (int) (width * hw), height);
        }
        else {
        	nImage = image;
        }
        return nImage;
    }
	
	public static int drawText(Graphics g, String text, int x, int y, boolean center){
		FontMetrics fm = g.getFontMetrics(g.getFont());
		y = (y - fm.getHeight() / 2) + fm.getAscent();
		if(center){	
			x = x - fm.stringWidth(text) / 2;
		}

		g.drawString(text, x, y);
		return fm.stringWidth(text);
	}
	
	public static int drawText(Graphics g, String text, int x, int y){
		FontMetrics fm = g.getFontMetrics(g.getFont());
		y = y + fm.getAscent();

		g.drawString(text, x, y);
		return fm.stringWidth(text);
	}
	
	public static void setFontSize(Graphics g, int size) {
		Font font = g.getFont();
		g.setFont(new Font(font.getFontName(), font.getStyle(), size));
	}
	
	
}
