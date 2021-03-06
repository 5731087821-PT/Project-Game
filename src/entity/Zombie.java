package entity;

import java.awt.*;

import render.AnimationManager;
import render.IRenderable;
import render.RenderAnimationHelper;
import resource.Resource;
import utility.ConfigurableOption;
import utility.RandomUtility;

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
	protected AnimationManager animation; 

	public Zombie(int speed) {
		this.y = ConfigurableOption.northScreenHeight;
		this.z = RandomUtility.random(100, 300);
		this.speed = speed;
		this.moving = true;
		this.destroyed = false;
		this.destroying = false;
		String[] zombieName = {"zombie-ballon",
							"zombie-helmet",
							"zombie-imps",
							"zombie-moonwalk",
							"zombie-running"};
		
		animation = Resource.get(zombieName[RandomUtility.random(0, 4)]);
		animation.loop();
		
		this.charHeight = ConfigurableOption.characterHeight;
		this.charWidth = animation.getCharWidthByHeight(this.charHeight);
		this.x = 0-charWidth/2;
	}

	public void update() {
		if(moving){
			x+=speed;
		}
	}

	@Override
	public int getZ() {
		return this.z;
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(!destroying){
			RenderAnimationHelper.draw(g2d, animation, x, y, 0,charHeight);
		}else{
			if(deadCounter == 0)
				deadCounter = 150;
			
			if(deadCounter%25<12)
				RenderAnimationHelper.draw(g2d, animation, x, y, 0,charHeight);
			
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
	public void setDestroying(boolean destroyed) {
		this.destroying = destroyed;
	}

	@Override
	public void updateAnimation() {
//		if(ConfigurableOption.PAUSE||ConfigurableOption.gameOver) return;
			animation.update();
	}

}
