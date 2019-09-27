package de.anteiku.jumpingfox.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.anteiku.jumpingfox.constants.Constants;

public class Tile {
	
	public static Tile[] tiles = new Tile[9];
	public static Tile airTile = new AirTile(Tiles.AIR);
	
	public static Tile dirtTile = new DirtTile(Tiles.DIRT);
	public static Tile dirt2Tile = new Dirt2Tile(Tiles.DIRT2);
	public static Tile dirt3Tile = new Dirt3Tile(Tiles.DIRT3);
	
	public static Tile grassTile = new GrassTile(Tiles.GRASS);
	
	public static Tile stoneTile = new StoneTile(Tiles.STONE);
	public static Tile stoneBackgroundTile = new StoneBackgroundTile(Tiles.STONEBACKGROUND);
	
	public static Tile WoodTile = new WoodTile(Tiles.WOOD);
	
	public static Tile barrierTile = new BarrierTile(Tiles.BARRIER);
	
	
	
	public static final int WIDTH = Constants.DEFAULTWIDTH, HEIGHT = Constants.DEFAULTHEIGHT;

	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		if(isAnimated()) {
			
		}
		else {
			if(texture != null) {
				g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
			}
			else {
				g.setColor(Color.red);
				g.fillRect(x, y, WIDTH, HEIGHT);
			}
		}
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public String getName() {
		return this.getClass().getName();
	}
	
	public boolean isAnimated() {
		return false;
	}
	
	public boolean isPlatform() {
		return false;
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public int getId() {
		return id;
	}

}
