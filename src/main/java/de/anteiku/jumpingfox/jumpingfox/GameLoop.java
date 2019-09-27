package de.anteiku.jumpingfox.jumpingfox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.display.Display;
import de.anteiku.jumpingfox.graphics.Assets;
import de.anteiku.jumpingfox.graphics.Colors;
import de.anteiku.jumpingfox.graphics.Fonts;
import de.anteiku.jumpingfox.managers.KeyManager;
import de.anteiku.jumpingfox.managers.MouseManager;
import de.anteiku.jumpingfox.managers.SceneManager;
import de.anteiku.jumpingfox.managers.SoundManager;
import de.anteiku.jumpingfox.managers.WindowManager;
import de.anteiku.jumpingfox.utils.Utils;

public class GameLoop implements Runnable{

	private static Display display;
	
	private Thread thread;
	private BufferStrategy bs;
	
	private boolean running = false;
	
	public int width, height;
	public String title;
	
	private SceneManager sceneManager;
	private SoundManager soundManager;
	private WindowManager windowManager;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	
	public GameLoop(int width, int height, String title) {
		File dir = new File(Constants.SAVEPATH);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		this.width = width;
		this.height = height;
		this.title = title;
		Constants.WIDTH = width;
		Constants.HEIGHT = height;
		
		new Fonts();
		new Colors();
		new Assets();
		
		sceneManager = new SceneManager();
		soundManager = new SoundManager();
		windowManager = new WindowManager(this);
		keyManager = new KeyManager(sceneManager);
		mouseManager = new MouseManager(sceneManager);
	}
	
	public static void setCursor(int cursor) {
		display.getFrame().setCursor(new Cursor(cursor));
	}
	
	public void resize(int width, int height) {
		display.resize(width, height);
	}
	
	private void init() {
		display = new Display(width, height, title);
		//de.anteiku.jumpingfox.display.getFrame().addComponentListener(windowManager);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
	}
	
	public void update() {
		sceneManager.update();
	}
	
	public void render(Graphics g) {
		g.clearRect(0, 0, width, height);
		g.setFont(Fonts.DEFAULT);
		sceneManager.render(g);
		g.setFont(Fonts.DEFAULT);
		Utils.setFontSize(g, 30);
		g.setColor(Color.red);
		g.drawString(String.valueOf(Constants.FPS), 0, 30);
		
	}
	
	@Override
	public void run() {
		
		init();
		
		
		
		double timePerFrame = 1000000000 / Constants.MAX_FPS;
		double delta = 0;
		float frameTime = 0;
		long now;
		long lastFrame = System.nanoTime();
		long timer = 0;
		int currentFPS = 0;
		
		while(running) {
			
			now = System.nanoTime();
			delta += (now - lastFrame) / timePerFrame;
			timer += now - lastFrame;
			frameTime = (now - lastFrame) / 10000000f;
			lastFrame = now;
			Constants.FRAME_TIME = 1;
			//System.out.println(frameTime);
			

			if(delta >= 1){
				update();
				bs = display.getCanvas().getBufferStrategy();
				if(bs == null) {
					display.getCanvas().createBufferStrategy(3);
				}
				else {
					Graphics g = bs.getDrawGraphics();
					render(g);
					bs.show();
					g.dispose();
				}
				currentFPS++;
				delta--;
			}
			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + currentFPS);
				Constants.FPS = currentFPS;
				currentFPS = 0;
				timer = 0;
			}
			
		}	
		stop();
		
	}
	
	public synchronized void start() {
		if(!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop() {
		if(running) {
			running = false;
			try {
				thread.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
