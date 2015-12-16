package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import utility.ConfigurableOption;

public class Gateway implements IRenderable{
	protected int x;
	protected int y;
	protected boolean destroyed;
	protected boolean gateClose;
	protected int width,height;

	public Gateway(int x, int y) {
		this.x = x;
		this.y = y;
		this.destroyed = false;
		this.gateClose = true;
		this.height = ConfigurableOption.northScreenHeight;
		this.width = height/13;
	}

	public void update() {
		if(!gateClose && y >= -160){
			y--;
		}
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean isGateClose(){
		return gateClose;
	}
	
	public void setGateClose(Boolean gateClose){
		this.gateClose = gateClose;
	}

	@Override
	public int getZ() {
		return 100;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(187, 187, 187, 250));
		g2d.fillRect(x, y, width, height);

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(width/5));
		g2d.drawRect(x-1, y-1, width, height-2); 
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public void setDestroying(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}
}
