package de.anteiku.jumpingfox.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.anteiku.jumpingfox.world.World;

public abstract class Entity {
	
	protected int x, y, width, height;
	protected World world;
	protected Rectangle bounds;
	
	public Entity(World world, int x, int y, int width, int height) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(1, 1, width - 2, height - 2);
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	
	public void kill() {
		
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		bounds.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		bounds.height = height;
	}

}
