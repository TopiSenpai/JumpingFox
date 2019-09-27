package de.anteiku.jumpingfox;

import java.awt.*;

public class Player extends Entity{

	public static final int WIDTH = 75;
	public static final int HEIGHT = 200;
	public static final String PATH = "player";
	
	protected Player(int x, int y){
		super(x, y, WIDTH, HEIGHT, PATH);
	}

	@Override
	public void update(){
		//TODO MOVEMENT
	}

	@Override
	public void render(Graphics g){
		//TODO DRAW PLAYER TO CANVAS
	}
	
}
