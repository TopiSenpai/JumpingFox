package de.anteiku.jumpingfox.animation;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
    private int frameIndex;
    private float frameTime;
    private long lastFrame;

    private boolean isPlaying = false;

    public Animation(BufferedImage[] frames, float animTime){
        this.frames = frames;
        this.frameIndex = 0;

        this.frameTime = animTime / frames.length;

        this.lastFrame = System.currentTimeMillis();
    }

    public boolean isPlaying(){
        return this.isPlaying;
    }

    public void play(){
        this.isPlaying = true;
        this.frameIndex = 0;
        this.lastFrame = System.currentTimeMillis();
    }

    public void stop(){
        this.isPlaying = false;
        this.frameIndex = 0;
    }

    private void scaleAnim(Rectangle rect){
        float whRatio = (float)(frames[frameIndex].getWidth()) / (frames[frameIndex].getHeight());
        if(rect.width > rect.height){
            rect.x = (rect.x + rect.width) - (int)(rect.height * whRatio);
        }
        else{
            rect.y = (rect.y + rect.height) - (int)(rect.height * (1 / whRatio));
        }
    }

    public void update(){
        if(isPlaying){
            if(System.currentTimeMillis() - lastFrame > frameTime * 1000){
                frameIndex++;
                frameIndex = frameIndex >= frames.length ? 0 : frameIndex;
                lastFrame = System.currentTimeMillis();
            }
        }
    }

    public void render(Graphics g, Rectangle destination){
        if(isPlaying){
            g.drawImage(frames[frameIndex], destination.x, destination.y, destination.width, destination.height, null);
        }
    }
	
}
