package de.anteiku.jumpingfox.scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Scene {

	void open();
	void close();
	
	void keyPressed(KeyEvent e);
	void keyReleased(KeyEvent e);
	
	void mouseButtonPressed();
	void mouseClicked(MouseEvent e);
	void mousePressed(MouseEvent e);
	void mouseReleased(MouseEvent e);
	void mouseDragged(MouseEvent e);
	void mouseMoved(MouseEvent e);
	
	void update();
	void render(Graphics g);
	
	int getId();
	
	
}
