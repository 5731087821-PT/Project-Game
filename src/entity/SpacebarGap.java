package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class SpacebarGap implements IRenderable{
	protected int x, y;
	protected int width;
	protected int disappearCounter;
	protected int primaryKey;
	protected boolean destroyed;
	protected Color color;
	public int seed;
	
	public SpacebarGap(int primaryKey, Color color,int disappearCounter, int x, int y){
		width = ConfigurableOption.gapWidth;
		if(ConfigurableOption.stageNow == 0){
			this.x = ConfigurableOption.xSpacebarTab+ConfigurableOption.tabDistance-120;
		}else{
			this.x = x;
		}
		
		this.y = y;
		this.primaryKey = primaryKey;
		this.color = color;
		this.disappearCounter = disappearCounter;
		this.destroyed = false;
		this.seed = ConfigurableOption.seedCoin;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		if(primaryKey<0){
			if(disappearCounter > 500){
				g2d.setColor(new Color(245, 245, 245));
			}else{
				g2d.setColor(new Color(245, 245, 245, disappearCounter/2));
			}
		}else{
			g2d.setColor(color);
		}
		g2d.fillRoundRect(x, y, width, 20, 6, 6);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void update() {
		disappearCounter--;
		if(disappearCounter<=0) this.destroyed = true;
		if(destroyed){
			RenderableHolder.getInstance().getSouthRenderableList().remove(this);
			ConfigurableOption.coinCounter--;
		}
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getZ() {
		return 1;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	
	public int getPrimaryKey(){
		return this.primaryKey;
	}

	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}
	
}
