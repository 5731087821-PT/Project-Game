package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class SpacebarGap implements IRenderable{
	protected int x;
	protected int width;
	protected int disappearCounter;
	public boolean destroyed;
	public int seed;
	
	public SpacebarGap(int disappearCounter, int x){
		width = ConfigurableOption.gapWidth;
		if(ConfigurableOption.stageNow == 0){
			this.x = ConfigurableOption.xSpacebarTab+ConfigurableOption.tabDistance-120;
		}else if(ConfigurableOption.stageNow == 1){
			this.x = x;
		}
		
		this.disappearCounter = disappearCounter;
		this.destroyed = false;
		this.seed = ConfigurableOption.seedCoin;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(disappearCounter > 500){
			g2d.setColor(new Color(245, 245, 245));
		}else{
			g2d.setColor(new Color(245, 245, 245, disappearCounter/2));
		}
		g2d.fillRoundRect(x, ConfigurableOption.ySpacebarTab, width, 20, 6, 6);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		disappearCounter--;
		if(disappearCounter<=0) this.destroyed = true;
		if(destroyed){
			RenderableHolder.getInstance().getSouthRenderableList().remove(this);
			ConfigurableOption.coinCounter--;
		}
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}

	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return destroyed;
	}
	
}
