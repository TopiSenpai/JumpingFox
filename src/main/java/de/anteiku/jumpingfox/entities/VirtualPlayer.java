package de.anteiku.jumpingfox.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.graphics.Camera;
import de.anteiku.jumpingfox.managers.SceneManager;
import de.anteiku.jumpingfox.world.World;

public class VirtualPlayer extends Creature {

	private int xSpeed = 5;
	private int xRunSpeed = 7;
	private int ySpeed = 5;
	private int yRunSpeed = 7;
	
	private boolean up = false, down = false, left = false, right = false, shift = false, space = false;
	
	private static final int WIDTH = Constants.DEFAULTWIDTH, HEIGHT = Constants.DEFAULTHEIGHT;
	
	private Camera camera;
	
	public VirtualPlayer(World world, Camera camera, int x, int y) {
		super(world, x, y, WIDTH, HEIGHT);
		//bounds = new Rectangle(0, 0, width, height);
		this.camera = camera;
	}

	
	public void kill() {
		SceneManager.setScene(0);
	}
	
	public void move() {
		moveX();
		moveY();
	}
	
	public void moveX() {
		x += xMove;
	}
	
	public void moveY() {
		y += yMove;
	}

	public void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(right) {
			if(shift) {
				xMove = xRunSpeed;
			}
			else {
				xMove = xSpeed;
			}
		}
		
		if(left) {
			if(shift) {
				xMove = -xRunSpeed;
			}
			else {
				xMove = -xSpeed;
			}
		}
		
		if(up) {
			if(shift) {
				yMove = -yRunSpeed;
			}
			else {
				yMove = -ySpeed;
			}
		}
		
		if(down) {
			if(shift) {
				yMove = yRunSpeed;
			}
			else {
				yMove = ySpeed;
			}
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			right = true;
		}
		
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			left = true;
		}
		
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			up = true;
		}
		
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			down = true;
		}
		
		if(key == KeyEvent.VK_SHIFT) {
			shift = true;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			space = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D) {
			right = false;
		}
		
		if(key == KeyEvent.VK_A){
			left = false;
		}
		
		if(key == KeyEvent.VK_W) {
			up = false;
		}
		
		if(key == KeyEvent.VK_S){
			down = false;
		}
		
		if(key == KeyEvent.VK_SHIFT) {
			shift = false;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			space = false;
		}
		
	}
	
	@Override
	public void update() {
		getInput();
		move();
		camera.centerOnPlayer(this);
		}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect(x + bounds.x - camera.getxOffset(), y + bounds.y - camera.getyOffset(), bounds.width, bounds.height);
	}

}
