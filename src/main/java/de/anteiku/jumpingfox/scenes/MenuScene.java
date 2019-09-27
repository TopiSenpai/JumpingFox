package de.anteiku.jumpingfox.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.graphics.Assets;
import de.anteiku.jumpingfox.graphics.Fonts;
import de.anteiku.jumpingfox.managers.SceneManager;
import de.anteiku.jumpingfox.managers.UIManager;
import de.anteiku.jumpingfox.ui.ClickListener;
import de.anteiku.jumpingfox.ui.TextButton;
import de.anteiku.jumpingfox.utils.Utils;

public class MenuScene implements Scene{

	BufferedImage background;
	UIManager uiManager;
	
	public MenuScene() {
		uiManager = new UIManager();
		background = Utils.fitImageToRect(Assets.background, new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT));
		
		TextButton playButton = new TextButton(Constants.WIDTH / 2 - 300 - 25, Constants.HEIGHT / 2, 300, 100, "PLAY", 
			new ClickListener() {
			
				@Override
				public void onClick() {
					SceneManager.setScene(1);
				}
			});
		TextButton levelsButton = new TextButton(Constants.WIDTH / 2 + 25, Constants.HEIGHT / 2, 300, 100, "LEVELS", 
			new ClickListener() {
			
				@Override
				public void onClick() {
					SceneManager.setScene(3);
				}
			});
		TextButton levelMakerButton = new TextButton(Constants.WIDTH / 2 - 300 - 25, Constants.HEIGHT / 2 + 100 + 50, 650, 100, "LEVEL MAKER", 
			new ClickListener() {
			
				@Override
				public void onClick() {
					SceneManager.setScene(2);
				}
			});
		
		uiManager.add(playButton);
		uiManager.add(levelsButton);
		uiManager.add(levelMakerButton);
	}
	
	@Override
	public void open() {
		
	}

	@Override
	public void close() {
		uiManager.close();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		uiManager.onKeyReleased(e);
	}
	
	@Override
	public void mouseButtonPressed() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		uiManager.onMouseReleased(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		uiManager.onMouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		uiManager.onMouseMoved(e);
	}

	@Override
	public void update() {
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
		g.setFont(Fonts.HEADER);
		Utils.setFontSize(g, 150);
		g.setColor(Color.BLUE);
		Utils.drawText(g, "Jumping Fox", Constants.WIDTH / 2, 200, true);
		g.setFont(Fonts.DEFAULT);
		uiManager.render(g);
	}

	@Override
	public int getId() {
		return 0;
	}

}
