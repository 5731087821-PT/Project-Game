package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import minigame.GetCoin;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.RandomUtility;

public class Coin implements IRenderable{
	protected int x;
	protected int y;
	protected int radius;
	protected int disappearCounter;
	protected boolean destroyed;
	private int deadCounter;
	public int seed;
	public boolean destroying;
	
	public Coin(int disappearCounter){
		this.radius = 10;
		this.x = RandomUtility.random(275, 425);
		this.y = ConfigurableOption.northScreenHeight-radius*2;
		this.disappearCounter = disappearCounter;
		this.destroyed = false;
		this.destroying = false;
		this.seed = ConfigurableOption.seedCoin;
	}
	
	public void update(){
		disappearCounter--;
		if(disappearCounter<=0) this.destroyed = true;
		if(destroyed){
			RenderableHolder.getInstance().getNorthRenderableList().remove(this);
		}
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(!destroying){
		g2d.setColor(Color.YELLOW);
		g2d.fillOval(x, y, radius*2, radius*2);
		}else{
			if(deadCounter == 0)
				deadCounter = 150;
			
			if(deadCounter%25<12){
				g2d.setColor(Color.YELLOW);
			}else{
				g2d.setColor(new Color(255,255,0,0));
			}
			g2d.fillOval(x, y, radius * 2, radius * 2);
			
			if(--deadCounter == 0){
				destroyed = true;
			}
		}
	}

	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return this.destroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
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
}
