package de.anteiku.jumpingfox.ui;

import java.awt.Graphics;

import de.anteiku.jumpingfox.graphics.Colors;
import de.anteiku.jumpingfox.utils.Utils;

public class MenuUI extends UIObject {
	
	private String label;
	private int fontSize;
	public int labelHeight = 50;

	public MenuUI(int x, int y, int width, int height, String label) {
		super(x, y, width, height, false);
		this.label = label;
		this.fontSize = labelHeight / 4 * 3;
	}


	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			g.setColor(Colors.LABELBACKGROUND);
			g.fillRect(x, y + labelHeight, width, height - labelHeight);
			g.setColor(Colors.MENUBACKGROUND);
			g.fillRect(x + 2, y + labelHeight + 2, width - 4, height - labelHeight - 4);
			g.setColor(Colors.LABELBACKGROUND);
			
			g.fillRoundRect(x, y, width, labelHeight, labelHeight / 2, labelHeight / 2);
			g.fillRect(x, y + labelHeight / 2, width, labelHeight / 2);
			Utils.setFontSize(g, fontSize);
			g.setColor(Colors.LABELTEXT);
			Utils.drawText(g, label, x + width / 2, y + labelHeight / 2, true);
		}
	}

	@Override
	public void onClick() {
		
	}

}
