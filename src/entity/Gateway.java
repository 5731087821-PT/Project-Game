package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import utility.ConfigurableOption;

public class Gateway implements IRenderable{
	protected int x;
	protected int y;
	protected boolean gateClose;

	public Gateway(int x, int y) {
		this.x = x;
		this.y = y;
		this.gateClose = true;
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
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(new Color(0, 0, 0, 100));
		g2d.fillRect(x, y, 10, ConfigurableOption.gameScreenHeight);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
}
