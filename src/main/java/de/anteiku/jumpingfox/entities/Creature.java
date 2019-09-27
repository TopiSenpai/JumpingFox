package de.anteiku.jumpingfox.entities;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.tiles.Tile;
import de.anteiku.jumpingfox.world.World;

public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH = 20;
	public static final int DEFAULT_XSPEED = 4;
	public static final int DEFAULT_XRUNSPEED = 6;
	public static final int DEFAULT_YSPEED = 8;
	public static final int DEFAULT_JUMP_HEIGHT = Constants.DEFAULTHEIGHT * 2;
	
	
	protected boolean inAir = false, isJump = false, jumpRdy = true;
	protected int health, xSpeed, xRunSpeed, ySpeed, xMove, yMove, jumpY, jumpHeight;

	public Creature(World world, int x, int y, int width, int height) {
		super(world, x, y, width, height);
		health = DEFAULT_HEALTH;
		xSpeed = DEFAULT_XSPEED;
		xRunSpeed = DEFAULT_XRUNSPEED;
		ySpeed = DEFAULT_YSPEED;
		jumpHeight = DEFAULT_JUMP_HEIGHT;
	}
	
	public void move() {
		xMove = (int) (xMove * Constants.FRAME_TIME);
		yMove = (int) (yMove * Constants.FRAME_TIME);
		//checkPos();
		moveX();
		moveY();
		if(y > world.getHeight() * Tile.HEIGHT) {
			kill();
		}
	}
	
	public void checkPos(){
		Tile tile = world.getTile(x / Tile.HEIGHT, y / Tile.HEIGHT);
		if(tile != Tile.airTile || !tile.isSolid()){
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.HEIGHT;
			y = ty * Tile.HEIGHT - bounds.height - bounds.y - 1;
		}
	}
	
	public void moveX() {
		if(xMove > 0){//Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)){
				x += xMove;
			}
			else{
				x = tx * Tile.WIDTH - bounds.width - bounds.x - 1;
			}
		}
		else if(xMove < 0){//Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)){
				x += xMove;
			}
			else{
				x = tx * Tile.WIDTH + Tile.WIDTH + bounds.x - 1;
			}
		}
	}
	
	public void moveY() {
		if(isJump) {
			yMove = -ySpeed;
			int ty = (int) (y + yMove) / Tile.HEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.WIDTH, ty)){
				y += yMove;
				if(y <= jumpY - bounds.height) {
					isJump = false;
				}
			}
			else{
				y = ty * Tile.HEIGHT + Tile.HEIGHT - bounds.y + 2;
				isJump = false;
			}
		}
		else {
			yMove = ySpeed;
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.HEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.WIDTH, ty)){
				y += yMove;
				setInAir(true);
			}
			else{
				setInAir(false);
				jumpRdy = true;
				y = ty * Tile.HEIGHT - bounds.height - bounds.y - 1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y){

		return world.getTile(x, y).isSolid();

	}
	
	public void jump() {
		if(jumpRdy && !isInAir()) {
			isJump = true;
			jumpRdy = false;
			jumpY = y - jumpHeight;
		}
	}
	
	
	public boolean isInAir() {
		return inAir;
	}

	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int speed) {
		this.xSpeed = speed;
	}

}
