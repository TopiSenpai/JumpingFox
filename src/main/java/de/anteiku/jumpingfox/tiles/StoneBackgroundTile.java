package de.anteiku.jumpingfox.tiles;

import de.anteiku.jumpingfox.graphics.Assets;

public class StoneBackgroundTile extends Tile{

	public StoneBackgroundTile(int id) {
		super(Assets.stoneBackground, id);
	}
	
	public boolean isSolid() {
		return false;
	}

}
