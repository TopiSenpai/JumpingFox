package de.anteiku.jumpingfox.sounds;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ButtonClickSound implements Sound{

	private Clip clip;
	private AudioInputStream sound;
	private String name = "buttonclick.wav";
    private static int position = 0;

    private float defaultVolume = 1.0f;
    private float volume = defaultVolume;
    
    
    public ButtonClickSound() {
		BufferedInputStream stream = SoundLoader.loadSound("buttonclick");
		try {
			sound = AudioSystem.getAudioInputStream(stream);
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void setVolume(float volume){
        this.volume = volume;
    }

    @Override
    public float getVolume(){
        return this.volume;
    }

    @Override
    public void resetVolume(){
        this.volume = defaultVolume;
        setVolume(this.volume);
    }

    @Override
    public void play(){
    	clip.setFramePosition(0);
		clip.start();
    }

    @Override
    public void pause(){
    	position = clip.getFramePosition();
		clip.stop();
    }

    @Override
    public void resume(){
    	clip.setFramePosition(position);
		clip.start();
    }

    @Override
    public void stop(){
    	position = 0;
        clip.stop();
    }

	@Override
	public boolean isPlaying() {
		return clip.isRunning();
	}
	
}
