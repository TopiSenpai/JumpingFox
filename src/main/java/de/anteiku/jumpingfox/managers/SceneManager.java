package de.anteiku.jumpingfox.managers;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import de.anteiku.jumpingfox.scenes.GameScene;
import de.anteiku.jumpingfox.scenes.LevelMakerScene;
import de.anteiku.jumpingfox.scenes.LevelsScene;
import de.anteiku.jumpingfox.scenes.MenuScene;
import de.anteiku.jumpingfox.scenes.Scene;

public class SceneManager {

	private static int ACTIVE_SCENE = 0;
	private static ArrayList<Scene> SCENES = new ArrayList<Scene>();
	
	public SceneManager(){
		SCENES.add(new MenuScene()); //0
		SCENES.add(new GameScene()); //1
		SCENES.add(new LevelMakerScene()); //2
		SCENES.add(new LevelsScene()); //3
		getActiveScene().open();
	}
	
	
	public static void setScene(int scene) {
		getActiveScene().close();
		//SoundManager.close();
		getScene(scene).open();
		ACTIVE_SCENE = scene;
	}
	
	public static Scene getScene(int scene) {
		return SCENES.get(scene);
	}
	
	public static Scene getActiveScene() {
		return SCENES.get(ACTIVE_SCENE);
	}
	
	public void keyPressed(KeyEvent e) {
		getActiveScene().keyPressed(e);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && ACTIVE_SCENE != 2) {
			setScene(0);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		getActiveScene().keyReleased(e);
	}
	
	public void mousePressed(MouseEvent e) {
		getActiveScene().mousePressed(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		getActiveScene().mouseClicked(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		getActiveScene().mouseReleased(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		getActiveScene().mouseDragged(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		getActiveScene().mouseMoved(e);
	}
	
	public void update() {
		if(MouseManager.isMouseLeft() || MouseManager.isMouseRight() || MouseManager.isMouseWheel()) {
			getActiveScene().mouseButtonPressed();
		}
		getActiveScene().update();
	}
	
	public void render(Graphics g) {
		getActiveScene().render(g);
	}
	
}
