package entity;

import java.awt.*;

import render.AnimationManager;
import render.IRenderable;
import render.RenderAnimationHelper;
import utility.ConfigurableOption;
import utility.Resource;

public class Zombie implements IRenderable{
	protected int x;
	protected int y;
	protected int z;
	protected int charWidth,charHeight;
	public int speed;
	public boolean moving; 
	protected boolean destroyed;
	protected boolean destroying;
	private int deadCounter;
	protected AnimationManager zombieAnimation; 

	public Zombie(int speed) {
		this.x = -40;
		this.y = ConfigurableOption.northScreenHeight;
		this.z = 100;
//		this.z = utility.RandomUtility.random(100, 300);
		this.speed = speed;
		this.moving = true;
		this.destroyed = false;
		this.destroying = false;
		
		zombieAnimation = Resource.get("zombie-running");
		zombieAnimation.loop();
		
		this.charHeight = 80;
		this.charWidth = zombieAnimation.getCharWidth(this.charHeight);
	}

	public void update() {
		if(moving){
			x+=speed;
			zombieAnimation.update();
		}
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(!destroying){
			RenderAnimationHelper.draw(g2d, zombieAnimation, x, y, 0,charHeight);
		}else{
			if(deadCounter == 0)
				deadCounter = 150;
			
			if(deadCounter%25<12)
				RenderAnimationHelper.draw(g2d, zombieAnimation, x, y, 0,charHeight);
			
			if(--deadCounter == 0)
				destroyed = true;
		}
	}

	public int getCharWidth() {
		return charWidth;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	public boolean isDestroyed() {
		return this.destroyed;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroying = destroyed;
	}

}
