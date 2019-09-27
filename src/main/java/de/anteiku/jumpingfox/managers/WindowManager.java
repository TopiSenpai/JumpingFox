package de.anteiku.jumpingfox.managers;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import de.anteiku.jumpingfox.jumpingfox.GameLoop;
import de.anteiku.jumpingfox.scenes.GameScene;
import de.anteiku.jumpingfox.scenes.Scenes;

public class WindowManager implements ComponentListener{
	
	private GameLoop game;
	
	
	public WindowManager(GameLoop game) {
		this.game = game;
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
		if(SceneManager.getActiveScene().getId() == Scenes.GAMESCENE) {
			((GameScene) SceneManager.getActiveScene()).pause();
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		game.resize(e.getComponent().getWidth(), e.getComponent().getHeight());
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}

}
