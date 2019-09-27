package de.anteiku.jumpingfox.tiles;

import java.awt.Graphics;

import de.anteiku.jumpingfox.graphics.Assets;

public class AirTile extends Tile{
	
	public AirTile(int id) {
		super(Assets.box, id);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void render(Graphics g, int x, int y) {
		//g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
		//System.out.println("Air gets rendered");
	}

}
