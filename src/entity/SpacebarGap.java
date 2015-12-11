package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;

public class SpacebarGap implements IRenderable{
	protected int x;
	protected int width;
	protected boolean destroyed;
	
	public SpacebarGap(){
		width = ConfigurableOption.gapWidth;
		if(ConfigurableOption.stageNow == 0){
			x = ConfigurableOption.xSpacebarTab+ConfigurableOption.tabDistance-120;
		}else if(ConfigurableOption.stageNow == 1){
			x = RandomUtility.random(ConfigurableOption.xSpacebarTab, ConfigurableOption.xSpacebarTab+ConfigurableOption.tabDistance-width);
		}
		
		this.destroyed = false;
	}
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(new Color(245, 245, 245));
		g2d.fillRoundRect(x, ConfigurableOption.ySpacebarTab, width, 20, 6, 6);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(destroyed){
			RenderableHolder.getInstance().getSouthRenderableList().remove(this);
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
	
}
