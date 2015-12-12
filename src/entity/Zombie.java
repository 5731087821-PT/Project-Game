package entity;

import java.awt.*;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class Zombie implements IRenderable{
	protected int x;
	protected int y;
	public int speed;
	public boolean moving; 
	protected boolean destroyed;
	protected boolean destroying;
	private int deadCounter;

	public Zombie(int speed) {
		this.x = -40;
		this.y = ConfigurableOption.northScreenHeight-40;
		this.speed = speed;
		this.moving = true;
		this.destroyed = false;
		this.destroying = false;
	}

	public void update() {
		if(moving){
			x+=speed;
		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(!destroying){
			g2d.setColor(Color.DARK_GRAY);
			g2d.fillRect(x, y, 40, 40);
		}else{
			if(deadCounter == 0){
				deadCounter = 150;
			}else{
				deadCounter--;
			}
			
			if(deadCounter%25<12){
				g2d.setColor(Color.DARK_GRAY);
			}else{
				g2d.setColor(new Color(169,169,169,0));
			}
			g2d.fillRect(x, y, 40, 40);
			
			if(deadCounter == 0){
				destroyed = true;
			}
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
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

	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return this.destroyed;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroying = destroyed;
	}

}
