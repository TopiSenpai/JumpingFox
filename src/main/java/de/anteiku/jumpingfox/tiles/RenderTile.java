package de.anteiku.jumpingfox.tiles;

import java.awt.Color;
import java.awt.Graphics;

import de.anteiku.jumpingfox.graphics.Assets;

public class RenderTile extends Tile{
	
	public RenderTile(int id) {
		super(Assets.box, id);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void render(Graphics g, int x, int y) {
		g.setColor(Color.magenta);
		g.drawRect(x, y, WIDTH, HEIGHT);
	}

}
