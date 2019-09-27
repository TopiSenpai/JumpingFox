package de.anteiku.jumpingfox.managers;

import java.util.ArrayList;

import de.anteiku.jumpingfox.sounds.BackgroundMusik;
import de.anteiku.jumpingfox.sounds.ButtonClickSound;
import de.anteiku.jumpingfox.sounds.JumpSound;
import de.anteiku.jumpingfox.sounds.Sound;

public class SoundManager {
	
	private static ArrayList<Sound> SOUNDS = new ArrayList<Sound>();
	
	
	public SoundManager(){
		SOUNDS.add(new ButtonClickSound()); //0
		SOUNDS.add(new JumpSound()); //1
		SOUNDS.add(new BackgroundMusik()); //2
	}
	
	public static void close() {
		for(Sound sound : SOUNDS) {
			sound.stop();
		}
	}
	
	public static void playSound(int sound) {
		SOUNDS.get(sound).play();
	}
	
	public static void stopSound(int sound) {
		SOUNDS.get(sound).stop();
	}
	
	public static void pauseSound(int sound) {
		SOUNDS.get(sound).pause();
	}
	
	public static void resumeSound(int sound) {
		SOUNDS.get(sound).resume();
	}
	
	public static boolean isSoundPlaying(int sound) {
		return SOUNDS.get(sound).isPlaying();
	}
	
}
