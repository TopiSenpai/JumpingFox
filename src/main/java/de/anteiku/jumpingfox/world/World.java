package de.anteiku.jumpingfox.world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.graphics.Camera;
import de.anteiku.jumpingfox.tiles.Tile;
import de.anteiku.jumpingfox.tiles.Tiles;

public class World {
	
	private int width;
	private int height;
	private int spawnX;
	private int spawnY;
	private String sep = ",";

	private int[][] tiles;
	
	
	public Tile getTile(int x, int y) {
		if(x <= 0 || x >= width || y < 0 || y >= height) {
			return Tile.airTile;
		}
		else {
			Tile tile = Tile.tiles[tiles[x][y]];
			if(tile == null) {
				return Tile.airTile;
			}
			else {
				return tile;
			}
		}
	}
	
	public void setTile(int x, int y, int tile) {
		if(x <= 0 || x >= width || y < 0 || y >= height) {
			
		}
		else {
			tiles[x][y] = tile;
		}
	}
	
	public void generateWorld() {
		width = 128;
		height = 32;
		spawnX = 18;
		spawnY = 11;
		tiles = new int[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(y == 12) {
					tiles[x][y] = Tiles.GRASS;
				}
				else if(y > 12 && y <= 15){
					tiles[x][y] = Tiles.DIRT;
				}
				else if(y >= 15){
					tiles[x][y] = Tiles.STONE;
				}
			}
		}
	}
	
	public void loadWorld(String name) {
		File file = new File(Constants.SAVEPATH + "worlds/" + name);
		
		try {
			 FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr);
             
             String line;
        
             while((line = br.readLine()) != null){
            	 String[] props = line.split(sep);
            	 
            	 if(props.length == 4) {
            		 width = Integer.parseInt(props[0]);
            		 height = Integer.parseInt(props[1]);
            		 spawnX = Integer.parseInt(props[2]);
            		 spawnY = Integer.parseInt(props[3]);
            		 tiles = new int[width][height];
            	 }
            	 else {
            		 int x = Integer.parseInt(props[0]);
            		 int y = Integer.parseInt(props[1]);
                	 int tile = Integer.parseInt(props[2]);
                	 tiles[x][y] = tile;
            	 }
             }
             br.close();
             fr.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("loaded de.anteiku.jumpingfox.world: " + name + " succsesfully");
	}
	
	public void safeWorld(String name) {
		File file = new File(Constants.SAVEPATH + "worlds/" + name);
		if(!file.exists()) {
			try {
				if(!file.createNewFile()) {
					System.out.println("Failed");
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter (fw);
            
			bw.write(width + sep + height + sep + spawnX + sep + spawnY);
			bw.newLine();
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					bw.write(x + sep + y + sep + tiles[x][y]);
					bw.newLine();
				}
			}
			bw.flush();
            bw.close();
            fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("safed de.anteiku.jumpingfox.world: " + name + " succsesfully");
	}
	
	public static void renameWorld(String oldName, String name) {
		File file = new File(Constants.SAVEPATH + "worlds/" + oldName);
		if(file.exists()) {
			file.renameTo(new File(Constants.SAVEPATH + "worlds/" + name + ".world"));
		}
//		System.out.println("renamed de.anteiku.jumpingfox.world: " + oldName + " succsesfully too: " + name);
	}
	
	public static void deleteWorld(String name) {
		File file = new File(Constants.SAVEPATH + "worlds/" + name);
		if(file.exists()) {
			file.delete();
		}
//		System.out.println("deleted de.anteiku.jumpingfox.world: " + name + " succsesfully");
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		int renderX = (Constants.WIDTH / Tile.WIDTH) / 2 + 2;
		int renderY = (Constants.HEIGHT / Tile.HEIGHT) / 2 + 2;
		
		for(int y = 0; y < height; y++) {
			int playerY = (Camera.getY()) / Tile.HEIGHT;
			int tileY = y;
			int distY = Math.abs(playerY - tileY);
			if(distY < renderY) {
				for(int x = 0; x < width; x++) {
					int playerX = (Camera.getX()) / Tile.WIDTH;
					int tileX = x;
					int distX = Math.abs(playerX - tileX);
					if(distX < renderX) {
						Tile t = getTile(x, y);
						t.render(g, x * Tile.WIDTH - Camera.getxOffset(), y * Tile.HEIGHT - Camera.getyOffset());
					}
				}
			}
		}
	}
	
	public int getSpawnX() {
		return spawnX * Tile.WIDTH;
	}
	
	public int getSpawnY() {
		return spawnY * Tile.HEIGHT;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
