package de.anteiku.jumpingfox.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected int x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean target;

	protected boolean hovering = false, clickable, visible = true;

	
	public UIObject(int x, int y, int width, int height, boolean clickable){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.clickable = clickable;
		this.target = false;
		
		bounds = new Rectangle(x, y, width, height);

	}

	public abstract void update();
	public abstract void render(Graphics g);
	public abstract void onClick();

	
	public void onMouseMoved(MouseEvent e){
		if(isVisible()) {
			if(bounds.contains(e.getX(), e.getY())) {
				hovering = true;
			}
			else {
				hovering = false;			
			}
		}
	}

	public void onMouseReleased(MouseEvent e){
		if(isVisible()) {
			if(hovering) {
				onClick();
			}
		}	
	}
	
	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
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
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

	public boolean isInput() {
		return false;
	}

	public boolean isTarget() {
		return target;
	}
	
	public void setTarget(boolean target) {
		this.target = target;
	}
	
}
