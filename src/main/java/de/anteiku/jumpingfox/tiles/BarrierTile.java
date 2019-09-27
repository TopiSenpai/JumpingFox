package de.anteiku.jumpingfox.tiles;

import java.awt.Graphics;

import de.anteiku.jumpingfox.graphics.Assets;

public class BarrierTile extends Tile{
	
	public BarrierTile(int id) {
		super(Assets.barrier, id);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
	}

}
