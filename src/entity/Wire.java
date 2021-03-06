package entity;

import java.awt.BasicStroke;
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
		this.height = 15;
		this.primaryKey = primaryKey;
		this.color = color;
		this.destroyed = false;
	}
	
	public int getPrimaryKey(){
		return this.primaryKey;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(x, y, width, height); 

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(x-1, y-1, width, height+2); 
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isDestroyed() {
		return this.destroyed;
	}

	@Override
	public void setDestroying(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public void update() {
		
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void updateAnimation() {
		
	}

}
