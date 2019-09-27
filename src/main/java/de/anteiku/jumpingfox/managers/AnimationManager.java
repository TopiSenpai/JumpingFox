package de.anteiku.jumpingfox.managers;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.anteiku.jumpingfox.animation.Animation;

public class AnimationManager {

	private Animation[] animations;
    private int animationIndex = 0;


    public AnimationManager(Animation[] animations){
        this.animations = animations;
    }

    public void playAnimation(int index){
        for(int i = 0; i < animations.length; i++){
            if(i == index){
                if(!animations[index].isPlaying()){
                    animations[i].play();
                }
            }
            else{
                animations[i].stop();
            }
        }
        this.animationIndex = index;
    }

    public int getAnimation(){
        return this.animationIndex;
    }

    public void update(){
        if(animations[animationIndex].isPlaying()){
            animations[animationIndex].update();
        }
    }

    public void render(Graphics g, Rectangle rect){
        if(animations[animationIndex].isPlaying()){
            animations[animationIndex].render(g, rect);
        }
    }

}
