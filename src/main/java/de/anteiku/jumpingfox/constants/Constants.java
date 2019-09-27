package de.anteiku.jumpingfox.constants;

import java.io.File;

public class Constants {

	
	public static boolean RESIZEABLE = true;
	public static int SCREENMUTLIPLIER = 14;
	
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public static int DEFAULTWIDTH = HEIGHT / SCREENMUTLIPLIER;
	public static int DEFAULTHEIGHT = HEIGHT / SCREENMUTLIPLIER;
	
	
	
	
	public static final int MAX_FPS = 80;
	public static int FPS = 0;
	
	public static float FRAME_TIME = 0;
	
	public static String SAVEPATH = System.getProperty("user.home") + File.separatorChar + "My Documents" + File.separatorChar;
	
}
