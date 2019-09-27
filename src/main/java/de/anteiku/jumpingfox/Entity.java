package de.anteiku.jumpingfox;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public abstract class Entity {
	
	protected int x, y, width, height;
	protected BufferedImage texture;
	
	protected Entity(int x, int y, int width, int height, String path) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		texture = loadTexture(path);
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}

	public BufferedImage getTexture(){
		return texture;
	}

	public void setTexture(BufferedImage texture){
		this.texture = texture;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	private BufferedImage loadTexture(String path) {
		try {
			InputStream res = Entity.class.getResourceAsStream("/textures/" + path + ".png");
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
