package de.anteiku.jumpingfox.scenes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.graphics.Assets;
import de.anteiku.jumpingfox.graphics.Colors;
import de.anteiku.jumpingfox.jumpingfox.GameLoop;
import de.anteiku.jumpingfox.managers.SceneManager;
import de.anteiku.jumpingfox.managers.UIManager;
import de.anteiku.jumpingfox.ui.ClickListener;
import de.anteiku.jumpingfox.ui.CloseMenu;
import de.anteiku.jumpingfox.ui.ImageButton;
import de.anteiku.jumpingfox.ui.MenuUI;
import de.anteiku.jumpingfox.ui.TextInput;
import de.anteiku.jumpingfox.utils.Utils;
import de.anteiku.jumpingfox.world.World;

public class LevelsScene implements Scene{

	private BufferedImage background;
	private UIManager uiManager;
	
	private TextInput searchInput;
	private String search;
	
	private int x = Constants.WIDTH / 8, y = Constants.HEIGHT / 8, width = Constants.WIDTH / 8 * 6, height = Constants.HEIGHT / 8 * 6, levelHeight = Constants.HEIGHT / 16;
	
	private ArrayList <String> worlds;
	private ArrayList <Rectangle> rects;
	
	public LevelsScene() {
		uiManager = new UIManager();
		background = Utils.fitImageToRect(Assets.background, new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT));
		
		MenuUI menu = new MenuUI(x, y, width, height, "Levels");
		CloseMenu menuClose = new CloseMenu(menu, new ClickListener() {
			
			@Override
			public void onClick() {
				SceneManager.setScene(0);
			}
		});
		searchInput = new TextInput(x + width - width / 4, y + 50 + 4, width / 4 - 4, 40, 15, "search...", "");
		ImageButton searchButton = new ImageButton(searchInput.getX() - 40 - 2, y + 50 + 4, 40, 40, new BufferedImage[] {Assets.searchIcon, Assets.searchIcon}, new ClickListener() {
			
			@Override
			public void onClick() {
				search();
			}
		});
		
		uiManager.add(menu);
		uiManager.add(menuClose);
		uiManager.add(searchInput);
		uiManager.add(searchButton);
		
		search = "";
		
		worlds = new ArrayList<>();
		rects = new ArrayList<>();
		
		loadWorlds();
	}
	
	public void search() {
		search = searchInput.getText();
	}
	
	private void loadWorlds() {
		worlds = new ArrayList<>();
		rects = new ArrayList<>();
		int y = this.y + searchInput.getHeight() + 50 + 4;
		
		File folder = new File(Constants.SAVEPATH + "worlds");
		if(!folder.exists()){
			folder.mkdirs();
		}
		for (File file : folder.listFiles()) {
		    if(file.isFile()) {
		    	String name = file.getName().substring(0, file.getName().length() - 6);
		    	if(search.equals("")) {
		    		worlds.add(name);
			        Rectangle rect = new Rectangle(x + 4, y + 2, width - 8, levelHeight - 4);
			        rects.add(rect);
			        y += levelHeight;
		    	}
		    	else {
			    	if(name.contains(search)) {
			    		worlds.add(name);
				        Rectangle rect = new Rectangle(x + 4, y + 2, width - 8, levelHeight - 4);
				        rects.add(rect);
				        y += levelHeight;
			    	}
		    	}
		    }
		}
	}
	
	@Override
	public void open() {
		loadWorlds();
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
		search();
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
		int x = e.getX();
		int y = e.getY();
		for(Rectangle rect : rects) {
			if(rect.contains(x, y)) {
				int i = rects.indexOf(rect);
				int width = rect.height - 4;
				int height = rect.height - 4;
				int yc = rect.y + 2;
				
				Rectangle del = new Rectangle(rect.x + rect.width - width + 4, yc, width, height);
				Rectangle edit = new Rectangle(rect.x + rect.width - (width * 2) + 6, yc, width, height);
				
				if(del.contains(x, y)) {
					World.deleteWorld(worlds.get(i) + ".world");
				}
				else if(edit.contains(x, y)) {
					LevelMakerScene.loadWorld(worlds.get(rects.indexOf(rect)) + ".world");
					SceneManager.setScene(Scenes.LEVELMAKERSCENE);
				}
				else {
					GameScene.loadWorld(worlds.get(rects.indexOf(rect)) + ".world");
					SceneManager.setScene(Scenes.GAMESCENE);
				}
				
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		uiManager.onMouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		boolean hover = false;
		int x = e.getX();
		int y = e.getY();
		if(rects.size() > 0) {
			for(Rectangle rect : rects) {
				if(rect.contains(x, y)) {
					hover = true;
				}
			}
			if(hover) {
				GameLoop.setCursor(Cursor.HAND_CURSOR);
			}
			else {
				uiManager.onMouseMoved(e);
			}
		}
	}

	@Override
	public void update() {
		loadWorlds();
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
		uiManager.render(g);
		int y = this.y + 50 + 4;
		int it = 1;
		Utils.setFontSize(g, levelHeight / 4 * 3);
		for(String world : worlds) {
			int i = worlds.indexOf(world);
			Rectangle rect = rects.get(i);
			int width = rect.height - 4;
			int height = rect.height - 4;
			int yc = rect.y + 2;
			
			Rectangle play = new Rectangle(rect.x + 4, yc, width, height);
			Rectangle del = new Rectangle(rect.x + rect.width - width - 2, yc, width, height);
			Rectangle edit = new Rectangle(rect.x + rect.width - (width * 2) - 6, yc, width, height);
			
			g.setColor(Colors.MENULEVELBACKGROUND);
			g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, levelHeight / 2,  levelHeight / 2);
			g.setColor(Color.black);
			Utils.drawText(g, it + " - " + world, play.x + play.width + 10, (int) rect.getCenterY(), false);
			g.drawImage(Assets.playIcon, play.x, play.y, play.width, play.height, null);
			g.drawImage(Assets.deleteIcon, del.x, del.y, del.width, del.height, null);
			g.drawImage(Assets.editIcon, edit.x, edit.y, edit.width, edit.height, null);
			
			y += levelHeight;
			it++;
		}
	}

	@Override
	public int getId() {
		return 3;
	}

}
