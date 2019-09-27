package de.anteiku.jumpingfox.graphics;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.entities.Entity;

public class Camera {

	private static int xOffset = 0, yOffset = 0, x = 0, y = 0;;
	
	public void centerOnPlayer(Entity entity) {
		xOffset = entity.getX() - Constants.WIDTH / 2 + entity.getWidth() / 2;
		yOffset = entity.getY() - Constants.HEIGHT / 2 + entity.getHeight() / 2;
		x = entity.getX();
		y = entity.getY();
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static int getxOffset() {
		return xOffset;
	}

	public static int getyOffset() {
		return yOffset;
	}
	
}
