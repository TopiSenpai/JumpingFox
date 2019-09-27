package de.anteiku.jumpingfox.managers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private SceneManager sceneManager;
	private static int mouseX, mouseY;
	private static boolean mouseLeft, mouseRight, mouseWheel;
	
	public MouseManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		mouseLeft = false;
		mouseRight = false;
		mouseWheel = false;
	}

	
	public static int getLastX() {
		return mouseX;
	}
	
	public static boolean isMouseLeft() {
		return mouseLeft;
	}

	public static boolean isMouseRight() {
		return mouseRight;
	}
	
	public static boolean isMouseWheel() {
		return mouseWheel;
	}

	public static int getLastY() {
		return mouseY;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		sceneManager.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		sceneManager.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		sceneManager.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		sceneManager.mousePressed(e);
		int button = e.getButton();
		if(button == MouseEvent.BUTTON1) {
			mouseLeft = true;
		}
		else if(button == MouseEvent.BUTTON2) {
			mouseWheel = true;
		}
		else if(button == MouseEvent.BUTTON3) {
			mouseRight = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sceneManager.mouseReleased(e);
		int button = e.getButton();
		if(button == MouseEvent.BUTTON1) {
			mouseLeft = false;
		}
		else if(button == MouseEvent.BUTTON2) {
			mouseWheel = false;
		}
		else if(button == MouseEvent.BUTTON3) {
			mouseRight = false;
		}
	}

}
