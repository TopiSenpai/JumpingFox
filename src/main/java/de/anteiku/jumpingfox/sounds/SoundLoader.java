package de.anteiku.jumpingfox.sounds;

import java.io.BufferedInputStream;

public class SoundLoader {

	public static BufferedInputStream loadSound(String path) {
		BufferedInputStream res = new BufferedInputStream(SoundLoader.class.getResourceAsStream("/sounds/" + path + ".wav"));
		if(res != null) {
			return res;
		}
		else {
			System.out.println("Failed 2 load Sound: /de.anteiku.jumpingfox.sounds/" + path + ".wav");
			return null;
		}
	}
	
}
