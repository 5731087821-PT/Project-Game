package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import minigame.GetCoin;

import render.IRenderable;
import render.RenderHelper;
import render.RenderableHolder;
import resource.Resource;
import utility.ConfigurableOption;
import utility.RandomUtility;

public class Coin implements IRenderable{
	protected int x;
	protected int y;
	protected int height;
	protected int disappearCounter;
	protected boolean destroyed;
	private int deadCounter;
	public int seed;
	public boolean destroying;
	private BufferedImage coinImage ;
	
	public Coin(int disappearCounter){
		this.height = RandomUtility.random((int) (ConfigurableOption.characterHeight*0.30), (int) (ConfigurableOption.characterHeight*0.55));
		this.x = RandomUtility.random(ConfigurableOption.xGateway1+60, ConfigurableOption.xGateway2-60);
		this.y = ConfigurableOption.northScreenHeight;
		this.disappearCounter = disappearCounter;
		this.destroyed = false;
		this.destroying = false;
		this.seed = ConfigurableOption.seedCoin;
		coinImage = Resource.getImage("coins-stack");
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
		return 10;
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(!destroying){
//		g2d.setColor(Color.YELLOW);
//		g2d.fillOval(x, y, radius*2, radius*2);
		RenderHelper.draw(
				g2d, coinImage, 
				x, y, 
				height, 0, 
				RenderHelper.BOTTOM|RenderHelper.CENTER);
		}else{
			if(deadCounter == 0)
				deadCounter = 150;
			
			if(deadCounter%25<12){
				RenderHelper.draw(
						g2d, coinImage, 
						x, y, 
						0, height, 
						RenderHelper.BOTTOM|RenderHelper.CENTER);
				
			}else{
				
			}
			
			if(--deadCounter == 0){
				destroyed = true;
			}
		}
	}

	public boolean isDestroyed() {
		return this.destroyed;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	public void setDestroying(boolean destroyed) {
		this.destroyed = destroyed;
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
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}
}
