package de.anteiku.jumpingfox.scenes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.entities.Player;
import de.anteiku.jumpingfox.graphics.Assets;
import de.anteiku.jumpingfox.graphics.Camera;
import de.anteiku.jumpingfox.utils.Utils;
import de.anteiku.jumpingfox.world.World;

public class GameScene implements Scene {

	private Camera camera;
	private static World world;
	private Player player;
	
	private BufferedImage background;
	
	public GameScene() {
		background = Utils.fitImageToRect(Assets.background, new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT));
		world = new World();
		world.generateWorld();
	}
	
	public static void loadWorld(String name) {
		world.loadWorld(name);
	}
	
	public void pause() {
		
	}
	
	@Override
	public void open() {
		camera = new Camera();
		player = new Player(world, camera, world.getSpawnX(), world.getSpawnY());
	}

	@Override
	public void close() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
	
	@Override
	public void mouseButtonPressed() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void update() {
		world.update();
		player.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
		world.render(g);
		player.render(g);
	}

	@Override
	public int getId() {
		return 1;
	}

}
