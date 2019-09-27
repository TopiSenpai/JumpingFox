package de.anteiku.jumpingfox.scenes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.entities.VirtualPlayer;
import de.anteiku.jumpingfox.graphics.Assets;
import de.anteiku.jumpingfox.graphics.Camera;
import de.anteiku.jumpingfox.jumpingfox.GameLoop;
import de.anteiku.jumpingfox.managers.MouseManager;
import de.anteiku.jumpingfox.managers.SceneManager;
import de.anteiku.jumpingfox.managers.UIManager;
import de.anteiku.jumpingfox.tiles.Tile;
import de.anteiku.jumpingfox.ui.TextButton;
import de.anteiku.jumpingfox.utils.Utils;
import de.anteiku.jumpingfox.world.World;

public class LevelMakerScene implements Scene {
	
	private Camera camera;
	private static String levelname;
	private static World world;
	private VirtualPlayer player;
	
	private ArrayList<Rectangle> rects;
	
	private boolean iKey = false;
	
	private boolean showInventory = true, showMenu = false;
	private int focus = 0;
	private TextButton menuButton;
	
	private Tile selectedTile;
	private int tileX, tileY;
	
	private BufferedImage background;
	private UIManager uiManager;
	
	public LevelMakerScene(){
		background = Utils.fitImageToRect(Assets.background, new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT));
		rects = new ArrayList<>();
		world = new World();
		world.generateWorld();
		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");
		levelname = "level_" + ft.format(new Date(System.currentTimeMillis())) + ".de.anteiku.jumpingfox.world";
		uiManager = new UIManager();
		selectedTile = Tile.dirtTile;
	}
	
	public static void loadWorld(String name){
		world.loadWorld(name);
		levelname = name;
	}
	
	public void pause(){
		
	}
	
	private void initInventory(){
		rects.clear();
		int menuWidth = 800;
		int menuHeight = 600;
		int menuX = 200;
		int menuY = 100;
		
		int width = 75;
		int height = 75;
		
		int border = 10;
		
		int countX = menuWidth / (width + border);
		int countY = menuHeight / (height + border);
		
		int i = 1;
		for(int y = 0; y < countY; y++){
			for(int x = 0; x < countX; x++){
				Rectangle rect = new Rectangle(border + menuX + x * (width + border), border + menuY + y * (height + border), width, height);
				rects.add(rect);
				i++;
				if(i > Tile.tiles.length - 1) {
					return;
				}
			}
		}
	}
	
	public void placeTile(int x, int y) {
		int tileX = Utils.calculateTileX(x, player.getX());
		int tileY = Utils.calculateTileY(y, player.getY());
		int tile = selectedTile.getId();
		
		if(MouseManager.isMouseLeft()){
			world.setTile(tileX, tileY, tile);
		}
		else if(MouseManager.isMouseRight()){
			world.setTile(tileX, tileY, Tile.airTile.getId());
		}
		else if(MouseManager.isMouseWheel()){
			selectedTile = world.getTile(tileX, tileY);
		}
	}
	
	@Override
	public void open(){
		camera = new Camera();;
		player = new VirtualPlayer(world, camera, world.getSpawnX(), world.getSpawnY());
		
		initInventory();
	}
	
	@Override
	public void close(){
		world.safeWorld(levelname);
		uiManager.close();
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		player.keyPressed(e);
		int key = e.getKeyCode();
		switch(key){
			case KeyEvent.VK_E:
				iKey = true;
			break;
			case KeyEvent.VK_ESCAPE:
				// showMenu = !showMenu;
				// menuButton.setVisible(showMenu);
				SceneManager.setScene(0);
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		player.keyReleased(e);
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_E){
			iKey = false;
			showInventory = !showInventory;
		}
	}
	
	@Override
	public void mouseButtonPressed(){
		int x = MouseManager.getLastX();
		int y = MouseManager.getLastY();
		
		if(!showInventory) {
			placeTile(x, y);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		int x = MouseManager.getLastX();
		int y = MouseManager.getLastY();
		
		if(showInventory) {
			for(Rectangle rect : rects) {
				if(rect.contains(x, y)) {
					int i = rects.indexOf(rect) + 1;
					selectedTile = Tile.tiles[i];
					return;
				}
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(!showInventory) {
			placeTile(x, y);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		uiManager.onMouseReleased(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(!showInventory) {
			placeTile(x, y);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(showInventory) {
			boolean hover = false;
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
		else {
			placeTile(x, y);
			uiManager.onMouseMoved(e);
		}
	}
	
	@Override
	public void update(){
		world.update();
		player.update();
	}
	
	@Override
	public void render(Graphics g){
		g.drawImage(background, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
		// g.setStroke(new BasicStroke(2));
		for(int x = 1; x <= world.getWidth(); x++){
			g.drawLine(x * Tile.WIDTH - Camera.getxOffset(), 0 * Tile.WIDTH - Camera.getyOffset(), x * Tile.WIDTH - Camera.getxOffset(), world.getHeight() * Tile.HEIGHT - Camera.getyOffset());
		}
		for(int y = 0; y <= world.getHeight(); y++){
			g.drawLine(1 * Tile.WIDTH - Camera.getxOffset(), y * Tile.HEIGHT - Camera.getyOffset(), world.getWidth() * Tile.WIDTH - Camera.getxOffset(), y * Tile.HEIGHT - Camera.getyOffset());
		}
		world.render(g);
		player.render(g);
		if(showInventory) {
			g.setColor(Color.darkGray);
			g.fillRect(200, 100, 800, 600);
			for(Rectangle rect : rects) {
				g.setColor(Color.red);
				g.fillRect(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);
				g.drawImage(Tile.tiles[rects.indexOf(rect) + 1].getTexture(), rect.x, rect.y, rect.width, rect.height, null);
			}
		}
		else if(selectedTile != null){
			int mX = (int) Math.floor(MouseManager.getLastX());
			int mY = (int) Math.floor(MouseManager.getLastY());
			tileX = Utils.calculateTileX(mX, player.getX());
			tileY = Utils.calculateTileY(mY, player.getY());
			Utils.setFontSize(g, 30);
			g.setColor(Color.red);
			g.drawString(tileX + " " + tileY, 0, 70);
			if(!MouseManager.isMouseRight()){
				selectedTile.render(g, tileX * Tile.WIDTH - Camera.getxOffset(), tileY * Tile.HEIGHT - Camera.getyOffset());
			}
			g.drawRect(tileX * Tile.WIDTH - Camera.getxOffset(), tileY * Tile.HEIGHT - Camera.getyOffset(), Tile.WIDTH, Tile.HEIGHT);
		}
		
		uiManager.render(g);
	}
	
	@Override
	public int getId(){
		return 1;
	}
	
}
