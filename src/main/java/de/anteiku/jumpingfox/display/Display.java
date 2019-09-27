package de.anteiku.jumpingfox.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import de.anteiku.jumpingfox.constants.Constants;
import de.anteiku.jumpingfox.graphics.ImageLoader;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private int width;
	private int height;
	private String title;
	private String text;
	
	public Display(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.text = title + ": " + width + " x " + height;
		createDisplay();
	}
	
	public void createDisplay() {
		frame = new JFrame();
		frame.setTitle(text);
		frame.setIconImage(ImageLoader.loadImage("launchericons/launchericonround"));
		frame.setSize(width, height);
		frame.setResizable(Constants.RESIZEABLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		Dimension dim = new Dimension(width, height);
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public void resize(int width, int height) {
		this.width = width;
		Constants.WIDTH = width;
		Constants.DEFAULTWIDTH = Constants.WIDTH / Constants.SCREENMUTLIPLIER;
		
		this.height = height;
		Constants.HEIGHT = height;
		Constants.DEFAULTHEIGHT = Constants.HEIGHT / Constants.SCREENMUTLIPLIER;
		
		Dimension dim = new Dimension(width, height);
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		frame.setTitle(title + ": " + width + " x " + height);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
}
