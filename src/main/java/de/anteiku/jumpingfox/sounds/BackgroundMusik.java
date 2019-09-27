package de.anteiku.jumpingfox.sounds;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class BackgroundMusik implements Sound{

	private Clip clip;
	private int ACTIVE_MUSIK, soundsLength = 3;
	private ArrayList <AudioInputStream> sounds;
	private String name = "jump.wav";
    private static int position = 0;

    private float defaultVolume = 1.0f;
    private float volume = defaultVolume;
    
    
    public BackgroundMusik() {
    	sounds = new ArrayList<>();
		
		loadMusik();
		try {
			clip = AudioSystem.getClip();
			clip.open(sounds.get(0));
			ACTIVE_MUSIK = 0;
			clip.addLineListener(new LineListener() {
				
				@Override
				public void update(LineEvent e) {
				    if(e.getType() == LineEvent.Type.CLOSE) {
						if(ACTIVE_MUSIK + 1 <= soundsLength - 1) {
							ACTIVE_MUSIK++;
						}
						else {
							ACTIVE_MUSIK = 0;
							loadMusik();
						}
						try {
							clip.close();
							clip.open(sounds.get(ACTIVE_MUSIK));
							clip.setFramePosition(0);
							clip.start();
						}
						catch (LineUnavailableException e1) {
							e1.printStackTrace();
						}
						catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
    }
    
    private void loadMusik() {
    	InputStream stream1 = SoundLoader.loadSound("/musik/cavemusik");
		InputStream stream2 = SoundLoader.loadSound("/musik/hillsmusik");
		InputStream stream3 = SoundLoader.loadSound("/musik/plainsmusik");
    	sounds.clear();
    	try {
			sounds.add(AudioSystem.getAudioInputStream(stream1));
			sounds.add(AudioSystem.getAudioInputStream(stream2));
			sounds.add(AudioSystem.getAudioInputStream(stream3));
		}
    	catch (UnsupportedAudioFileException | IOException e) {
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
