package de.anteiku.jumpingfox.ui;

import java.awt.Graphics;

import de.anteiku.jumpingfox.graphics.Assets;
import de.anteiku.jumpingfox.graphics.Colors;

public class CloseMenu extends UIObject {
	
	private ClickListener clicker;
	
	public CloseMenu(MenuUI menu, ClickListener clicker) {
		super(menu.x + menu.width - menu.labelHeight + 2, menu.y + 4, menu.labelHeight - 8, menu.labelHeight - 6, true);
		this.clicker = clicker;
	}


	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			if(isHovering()) {
				g.setColor(Colors.MENUCLOSEHOVER);
			}
			else {
				g.setColor(Colors.MENUCLOSEBACKGROUND);
			}
			g.fillRoundRect(x, y, width, height, height / 2, height / 2);
			g.drawImage(Assets.closeIcon, x + 2, y + 2, width - 4, height - 4, null);
		}
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
