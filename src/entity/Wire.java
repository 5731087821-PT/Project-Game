package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;

public class Wire implements IRenderable {
	protected int x, y;
	protected int width, height;
	protected int primaryKey;
	protected Color color;
	protected boolean destroyed;
	
	public Wire(int primaryKey, Color color, int x, int y, int width){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = 10;
		this.primaryKey = primaryKey;
		this.color = color;
		this.destroyed = false;
	}
	
	public int getPrimaryKey(){
		return this.primaryKey;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(color);
		g2d.fillRect(x, y, width, height); 
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return this.destroyed;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		// TODO Auto-generated method stub
		this.destroyed = destroyed;
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

}
