package de.anteiku.jumpingfox.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import de.anteiku.jumpingfox.animation.Animation;
import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.graphics.Assets;
import de.anteiku.jumpingfox.graphics.Camera;
import de.anteiku.jumpingfox.managers.AnimationManager;
import de.anteiku.jumpingfox.managers.SceneManager;
import de.anteiku.jumpingfox.managers.SoundManager;
import de.anteiku.jumpingfox.sounds.Sounds;
import de.anteiku.jumpingfox.world.World;

public class Player extends Creature {

	
	private boolean up = false, down = false, left = false, right = false, shift = false, space = false;
	private String lastDirection = "left";
	
	private static final int WIDTH = Constants.DEFAULTWIDTH, HEIGHT = Constants.DEFAULTHEIGHT;
	
	private AnimationManager animationManager;
	private Camera camera;
	
	public Player(World world, Camera camera, int x, int y) {
		super(world, x, y, WIDTH, HEIGHT);
		
		this.camera = camera;
		
		Animation idleLeft = new Animation(Assets.player_idle_left, 0.5f);
		Animation idleRight = new Animation(Assets.player_idle_right, 0.5f);
		
		Animation walkLeft = new Animation(Assets.player_walk_left, 0.4f);
		Animation walkRight = new Animation(Assets.player_walk_right, 0.4f);
		
		Animation runLeft = new Animation(Assets.player_walk_left, 0.3f);
		Animation runRight = new Animation(Assets.player_walk_right, 0.3f);
		
		Animation jumpLeft = new Animation(Assets.player_jump_left, 2);
		Animation jumpRight = new Animation(Assets.player_jump_right,2);
		
		Animation fallLeft = new Animation(Assets.player_fall_left, 2);
		Animation fallRight = new Animation(Assets.player_fall_right,2);
		
		Animation waggLeft = new Animation(Assets.player_wagg_left, 0.3f);
		Animation waggRight = new Animation(Assets.player_wagg_right,0.3f);
		
		Animation[] animations = new Animation[] {
			idleLeft, // 0
			idleRight, // 1
			walkLeft, // 2
			walkRight, // 3
			jumpLeft, // 4
			jumpRight, // 5
			fallLeft, // 6
			fallRight, // 7
			waggLeft, // 8
			waggRight, // 9
			runLeft, // 10
			runRight, // 11
		};
		animationManager = new AnimationManager(animations);
		animationManager.playAnimation(0);
	}
	
	public void jump() {
		if(jumpRdy && !isInAir()) {
			SoundManager.playSound(Sounds.JUMP);
		}
		super.jump();
	}

	public void animate() {
		if(isJump) {
			if(lastDirection.equals("left")) {
				animationManager.playAnimation(4);
			}
			else {
				animationManager.playAnimation(5);
			}
		}
		else if(isInAir()) {
			if(lastDirection.equals("left")) {
				animationManager.playAnimation(6);
			}
			else {
				animationManager.playAnimation(7);
			}
		}
		else if(xMove > 0) {
			if(shift) {
				animationManager.playAnimation(11);
			}
			else {
				animationManager.playAnimation(3);
			}
			lastDirection = "right";
		}
		else if(xMove < 0){
			if(shift) {
				animationManager.playAnimation(10);
			}
			else {
				animationManager.playAnimation(2);
			}
			lastDirection = "left";
		}
		else if(down) {
			if(lastDirection.equals("left")) {
				animationManager.playAnimation(8);
			}
			else {
				animationManager.playAnimation(9);
			}
		}
		else {
			if(lastDirection.equals("left")) {
				animationManager.playAnimation(0);
			}
			else {
				animationManager.playAnimation(1);
			}
		}
	}
	
	public void kill() {
		SceneManager.setScene(0);
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
		
		if(space) {
			jump();
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D) {
			right = true;
		}
		
		if(key == KeyEvent.VK_A){
			left = true;
		}
		
		if(key == KeyEvent.VK_W) {
			up = true;
		}
		
		if(key == KeyEvent.VK_S){
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
		animate();
		camera.centerOnPlayer(this);
		
		animationManager.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		//g.fillRect(x + bounds.x, y + bounds.y, bounds.width, bounds.height);
		animationManager.render(g, new Rectangle(x - camera.getxOffset(), y - camera.getyOffset(), width, height));
		
		
	}

}
