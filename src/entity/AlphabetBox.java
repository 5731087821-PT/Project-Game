package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;

public class AlphabetBox implements IRenderable {
	protected int primaryKey;
	protected int x, y;
	protected int width,height;
	protected boolean selected;
	protected boolean destroyed;
	
	public AlphabetBox(int primaryKey, int x, int y, int width, int height){
		this.primaryKey = primaryKey;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.selected = false;
		this.destroyed = false;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(!selected){
			g2d.setColor(new Color(primaryKey, primaryKey, primaryKey));
		}else{
			g2d.setColor(Color.GREEN);
		}
		g2d.fillRect(x, y, width, height);
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
	}
	
	public boolean isSelected(){
		return selected;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getPrimaryKey(){
		return this.primaryKey;
	}

}
