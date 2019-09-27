package de.anteiku.jumpingfox.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage background;
	
	
	public static BufferedImage playIcon;
	public static BufferedImage closeIcon;
	public static BufferedImage searchIcon;
	public static BufferedImage editIcon;
	public static BufferedImage deleteIcon;
	//public static BufferedImage IconWhite;
	
	
	public static BufferedImage grass;
	public static BufferedImage dirt;
	public static BufferedImage dirt2;
	public static BufferedImage dirt3;
	
	public static BufferedImage stone;
	public static BufferedImage stoneBackground;
	
	public static BufferedImage wood;
	
	public static BufferedImage box;
	public static BufferedImage barrier;
		
	public static BufferedImage[] player_idle_left;
	public static BufferedImage[] player_idle_right;
	
	
	public static BufferedImage[] player_walk_left;
	public static BufferedImage[] player_walk_right;
	
	
	public static BufferedImage[] player_jump_left;
	public static BufferedImage[] player_jump_right;
	
	
	public static BufferedImage[] player_fall_left;
	public static BufferedImage[] player_fall_right;
	
	
	public static BufferedImage[] player_wagg_left;
	public static BufferedImage[] player_wagg_right;
	
	public Assets() {
		background = ImageLoader.loadImage("backgrounds/background_01");
		
		playIcon = ImageLoader.loadImage("icons/play");
		closeIcon = ImageLoader.loadImage("icons/white/close");
		searchIcon = ImageLoader.loadImage("icons/search");
		editIcon = ImageLoader.loadImage("icons/edit");
		deleteIcon = ImageLoader.loadImage("icons/delete");
		//closeIcon = ImageLoader.loadImage("icons/");
		
		
		grass = ImageLoader.loadImage("tiles/grass_01");
		dirt = ImageLoader.loadImage("tiles/dirt_01");
		dirt2 = ImageLoader.loadImage("tiles/dirt_06");
		dirt3 = ImageLoader.loadImage("tiles/dirt_08");
		
		stone = ImageLoader.loadImage("tiles/stone_02");
		stoneBackground = ImageLoader.loadImage("tiles/stone_01");
		
		wood = ImageLoader.loadImage("tiles/wood_01");
		
		box = ImageLoader.loadImage("entities/box_01");
		barrier = ImageLoader.loadImage("tiles/barrier");
			
		player_idle_left = new BufferedImage[] {
			ImageLoader.loadImage("fox/idle/fox_idle_left_01"),
			ImageLoader.loadImage("fox/idle/fox_idle_left_02"),
			ImageLoader.loadImage("fox/idle/fox_idle_left_01"),
			ImageLoader.loadImage("fox/idle/fox_idle_left_03"),
		};
		player_idle_right = new BufferedImage[] {
			ImageLoader.loadImage("fox/idle/fox_idle_right_01"),
			ImageLoader.loadImage("fox/idle/fox_idle_right_02"),
			ImageLoader.loadImage("fox/idle/fox_idle_right_01"),
			ImageLoader.loadImage("fox/idle/fox_idle_right_03"),
		};
		
		
		player_walk_left = new BufferedImage[] {
			ImageLoader.loadImage("fox/walk/fox_walk_left_01"),
			ImageLoader.loadImage("fox/walk/fox_walk_left_02"),
			ImageLoader.loadImage("fox/walk/fox_walk_left_03"),
			ImageLoader.loadImage("fox/walk/fox_walk_left_04"),
			ImageLoader.loadImage("fox/walk/fox_walk_left_05"),
			ImageLoader.loadImage("fox/walk/fox_walk_left_06"),
		};
		player_walk_right = new BufferedImage[] {
			ImageLoader.loadImage("fox/walk/fox_walk_right_01"),
			ImageLoader.loadImage("fox/walk/fox_walk_right_02"),
			ImageLoader.loadImage("fox/walk/fox_walk_right_03"),
			ImageLoader.loadImage("fox/walk/fox_walk_right_04"),
			ImageLoader.loadImage("fox/walk/fox_walk_right_05"),
			ImageLoader.loadImage("fox/walk/fox_walk_right_06"),
		};
		
		
		player_jump_left = new BufferedImage[] {
			ImageLoader.loadImage("fox/jump/fox_jump_left")
		};
		player_jump_right = new BufferedImage[] {
			ImageLoader.loadImage("fox/jump/fox_jump_right")
		};
		
		
		player_fall_left = new BufferedImage[] {
			ImageLoader.loadImage("fox/fall/fox_fall_left")
		};
		player_fall_right = new BufferedImage[] {
			ImageLoader.loadImage("fox/fall/fox_fall_right")
		};
		
		
		player_wagg_left = new BufferedImage[] {
			ImageLoader.loadImage("fox/wagg/fox_wagg_left_01"),
			ImageLoader.loadImage("fox/wagg/fox_wagg_left_02")
		};
		player_wagg_right = new BufferedImage[] {
			ImageLoader.loadImage("fox/wagg/fox_wagg_right_01"),
			ImageLoader.loadImage("fox/wagg/fox_wagg_right_02")
		};
	}
	
}
