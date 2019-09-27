package de.anteiku.jumpingfox.ui;

import java.awt.Graphics;

import de.anteiku.jumpingfox.graphics.Colors;
import de.anteiku.jumpingfox.managers.SoundManager;
import de.anteiku.jumpingfox.sounds.Sounds;
import de.anteiku.jumpingfox.utils.Utils;

public class TextButton extends UIObject {
	
	private String text;
	private int[] fontSize;
	private ClickListener clicker;

	public TextButton(int x, int y, int width, int height, String text, ClickListener clicker) {
		super(x, y, width, height, true);
		this.text = text;
		this.fontSize = new int[] {height / 4 * 3, (int) (height / 4 * 3.1f)};
		this.clicker = clicker;
	}


	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			g.setColor(Colors.MENUCLOSEHOVER);
			g.fillRoundRect(x, y, width, height, height / 2, height / 2);
			if(isHovering()) {
				g.setColor(Colors.MENUCLOSEHOVER);
				Utils.setFontSize(g, fontSize[1]);
			}
			else {
				g.setColor(Colors.MENUCLOSEBACKGROUND);
				Utils.setFontSize(g, fontSize[0]);
			}
			g.fillRoundRect(x + 2, y + 2, width - 4, height - 4, height / 2, height / 2);
			g.setColor(Colors.LABELTEXT);
			Utils.drawText(g, text, (int) bounds.getCenterX(), (int) bounds.getCenterY(), true);
		}
	}

	@Override
	public void onClick() {
		SoundManager.playSound(Sounds.BUTTONCLICK);
		clicker.onClick();
	}

}
