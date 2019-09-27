package de.anteiku.jumpingfox.sounds;

public interface Sound {
	
    void play();
    void pause();
    void resume();
    void stop();
    void resetVolume();
    
    void setVolume(float volume);
    float getVolume();
    
    boolean isPlaying();
	
}
