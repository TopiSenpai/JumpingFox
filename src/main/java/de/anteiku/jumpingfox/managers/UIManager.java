package de.anteiku.jumpingfox.managers;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import de.anteiku.jumpingfox.jumpingfox.GameLoop;
import de.anteiku.jumpingfox.ui.TextInput;
import de.anteiku.jumpingfox.ui.UIObject;

public class UIManager {
	
	private ArrayList<UIObject> objects;

	
	public UIManager(){
		objects = new ArrayList<UIObject>();
	}
	
	public void onKeyReleased(KeyEvent e) {
		for(UIObject o : objects) {
			if(o.isInput() && o.isTarget()) {
				((TextInput) o).onKeyReleased(e);
			}	
		}
	}
	
	public void onMouseDragged(MouseEvent e) {
		boolean hover = false;
		UIObject hoverO = null;
		for(UIObject o : objects) {
			o.onMouseMoved(e);
			if(o.isHovering() && o.isClickable()) {
				hover = true;
				hoverO = o;
			}	
		}
		if(hover) {
			if(hoverO.isInput()) {
				GameLoop.setCursor(Cursor.TEXT_CURSOR);
			}
			else {
				GameLoop.setCursor(Cursor.HAND_CURSOR);
			}
		}
		else {
			GameLoop.setCursor(Cursor.DEFAULT_CURSOR);
		}
	}
	
	public void onMouseMoved(MouseEvent e){
		boolean hover = false;
		UIObject hoverO = null;
		for(UIObject o : objects) {
			o.onMouseMoved(e);
			if(o.isHovering() && o.isClickable()) {
				hover = true;
				hoverO = o;
			}	
		}
		if(hover) {
			if(hoverO.isInput()) {
				GameLoop.setCursor(Cursor.TEXT_CURSOR);
			}
			else {
				GameLoop.setCursor(Cursor.HAND_CURSOR);
			}
		}
		else {
			GameLoop.setCursor(Cursor.DEFAULT_CURSOR);
		}
	}

	public void onMouseReleased(MouseEvent e){
		UIObject last = null;
		for(UIObject o : objects) {
			o.onMouseReleased(e);
			if(o.isHovering()) {
				last = o;
			}
			else {
				o.setTarget(false);
			}
		}
		if(last != null) {
			last.setTarget(true);
		}
	}

	public void update(){
		for(UIObject o : objects) {
			o.update();
		}
	}

	public void render(Graphics g){
		for(UIObject o : objects) {
			o.render(g);
		}
	}
	
	public void close() {
		for(UIObject o : objects) {
			o.setHovering(false);
		}
		GameLoop.setCursor(Cursor.DEFAULT_CURSOR);
	}
	
	public void add(UIObject o){
		objects.add(o);
	}

	public void remove(UIObject o){
		objects.remove(o);
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
