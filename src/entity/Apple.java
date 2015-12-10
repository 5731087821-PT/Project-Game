package entity;

import java.awt.Graphics2D;

import render.IRenderable;
import res.Resource;

public class Apple implements IRenderable{
	protected int x;
	protected int y;
	protected int radius;
	protected int speed;
	protected boolean destroyed = false;
	
	public Apple(){
		radius = 20;
		x=RandomUtility.random(radius, 640-radius);
		y=-radius;
		speed = RandomUtility.random(2, 7);
	}
	public void update(){
		y+=speed;
		if(y-radius > 420) destroyed = true;
	}
	public boolean isDestroyed(){
		return destroyed;
	}
	public boolean collideWith(Player other){
		return Math.hypot(this.getX()-other.getX(), this.getY()-other.getY()) <= this.getRadius()+other.getRadius();
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getRadius() {
		return radius;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -1 * RandomUtility.random(100,200);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(Resource.appleSprite, null, x-radius, y-radius);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return destroyed;
	}

}
