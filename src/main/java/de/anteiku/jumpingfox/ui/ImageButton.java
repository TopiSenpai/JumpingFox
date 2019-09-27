package de.anteiku.jumpingfox.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.anteiku.jumpingfox.utils.Utils;

public class ImageButton extends UIObject {
	
	private BufferedImage[] images;
	private ClickListener clicker;

	public ImageButton(int x, int y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height, true);
		this.images = new BufferedImage[2];
		this.images[0] = Utils.fitImageToRect(images[0], new Rectangle((int) x, (int) y, width, height));
		this.images[1] = Utils.fitImageToRect(images[1], new Rectangle((int) x, (int) y, width, height));
		this.clicker = clicker;
	}


	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			if(isHovering()) {
				g.drawImage(images[1], (int) x, (int) y, width, height, null);
			}
			else {
				g.drawImage(images[0], (int) x, (int) y, width, height, null);
			}	
		}
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
