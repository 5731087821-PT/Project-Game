package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import input.InputUtility;
import render.IRenderable;

public class Player implements IRenderable{
	protected int x;
	protected int y;
	protected int radius;
	
	public Player(){
		this.radius = 20;
		this.x = 40;
		this.y = 480-60-radius;
	}
	
	public void update(){

		if(InputUtility.getKeyPressed(KeyEvent.VK_LEFT)){
			x-=5;
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)){
			x+=5;
		}
		
		if(x<radius) x=radius;
		else if(x>640-radius) x=640-radius;
		
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.BLUE);
		g2d.fillArc(x-radius, y-radius, radius*2, radius*2, 0, 360);
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
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
}
