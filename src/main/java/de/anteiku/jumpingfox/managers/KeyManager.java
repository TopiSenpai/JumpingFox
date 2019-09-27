package de.anteiku.jumpingfox.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	private SceneManager sceneManager;
	
	
	public KeyManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		sceneManager.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		sceneManager.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
