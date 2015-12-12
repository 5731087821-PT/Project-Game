package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.RandomUtility;

public class Coin implements IRenderable{
	protected int x;
	protected int y;
	protected int radius;
	protected int disappearCounter;
	public boolean destroyed;
	private int deadCounter;
	
	public Coin(){
		this.radius = 10;
		this.x = RandomUtility.random(275, 425);
		this.y = ConfigurableOption.northScreenHeight-radius*2;
		this.disappearCounter = RandomUtility.random(100, 300);
		this.destroyed = false;
	}
	
	public void update(){
		disappearCounter--;
		if(disappearCounter<=0) this.destroyed = true;
		
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(!destroyed){
		g2d.setColor(Color.YELLOW);
		g2d.fillOval(x, y, radius*2, radius*2);
		}else{
			if(deadCounter == 0){
				deadCounter = 150;
			}else{
				deadCounter--;
			}
			
			if(deadCounter%25<12){
				g2d.setColor(Color.YELLOW);
			}else{
				g2d.setColor(new Color(255,255,0,0));
			}
			g2d.fillOval(x, y, radius * 2, radius * 2);
			
			if(deadCounter == 0)
				destroyed = true;
//				RenderableHolder.getInstance().getNorthRenderableList().remove(this);
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
