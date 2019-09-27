package de.anteiku.jumpingfox.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import de.anteiku.jumpingfox.graphics.Colors;
import de.anteiku.jumpingfox.utils.Utils;

public class TextInput extends UIObject implements Input{
	
	private String text;
	private int fontsize;
	private String placeholder;
	private int maxChars;
	private long blink, blink2;
	private boolean show;

	public TextInput(int x, int y, int width, int height, int maxChars, String placeholder, String text) {
		super(x, y, width, height, true);
		this.text = text;
		this.fontsize = height / 4 * 3;
		this.maxChars = maxChars;
		this.placeholder = placeholder;
		this.blink = System.currentTimeMillis();
		this.blink2 = System.currentTimeMillis();
		this.show = true;
	}
	
	public String getText() {
		return text;
	}
	
	private boolean checkText() {
		return text.length() + 1 < maxChars;
	}
	
	@Override
	public void onKeyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(key);
		switch(key) {
			case KeyEvent.VK_DELETE:
				if(!text.equals("")) {
					text = "";
				}
				
				break;
			case KeyEvent.VK_ENTER:
				this.setTarget(false);
				break;
			case 8:
				if(!text.equals("")) {
					text = text.substring(0, text.length() - 1);
				}
			
				break;
			case KeyEvent.VK_SPACE:
				if(checkText()) {
					text += " ";
				}
				break;
			case 45:
				if(checkText()) {
					text += "_";
				}
				break;
			default:
				String t = KeyEvent.getKeyText(key);
				if(checkText()) {
					if(t.matches("\\w") && t.length() > 0 && t.length() < 2) {
						if(e.isShiftDown()) {
							text += t.toUpperCase();
						}
						else {
							text += t.toLowerCase();
						}
					}
				}
				break;
		}
	}
	
	public boolean isInput() {
		return true;
	}

	@Override
	public void update() {
		if(blink < System.currentTimeMillis()) {
			show = false;
			blink = System.currentTimeMillis() + 3000;
			blink2 = System.currentTimeMillis() + 1000;
		}
		else if(blink2 < System.currentTimeMillis()){
			show = true;
			blink = System.currentTimeMillis() + 1000;
			blink2 = System.currentTimeMillis() + 3000;
		}
	}

	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			Utils.setFontSize(g, fontsize);
			g.setColor(Colors.MENUCLOSEHOVER);
			g.fillRoundRect(x, y, width, height, height / 2, height / 2);
			
			g.setColor(Colors.MENUBACKGROUND);
			g.fillRoundRect(x + 2, y + 2, width - 4, height - 4, height / 2, height / 2);
			if(isTarget() && show) {
				FontMetrics fm = g.getFontMetrics();
				g.setColor(Color.black);
				g.fillRect(x + fm.stringWidth(text) + 10, y + 4, 3, fm.getHeight() - 6);
			}
			g.setColor(Color.black);
			if(text.equals("")) {
				Utils.drawText(g, placeholder, bounds.x + 10, (int) bounds.getCenterY(), false);
			}
			Utils.drawText(g, text, bounds.x + 10, (int) bounds.getCenterY(), false);
		}
	}

	@Override
	public void onClick() {
		
	}


	

}
