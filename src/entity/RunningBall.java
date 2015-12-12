package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class RunningBall implements IRenderable{
	protected int x, y;
	protected int xTab, yTab;
	protected int diameter;
	protected int distance;
	protected int direction;
	public boolean destroyed;
	
	public RunningBall(){
		this.diameter = ConfigurableOption.spacebarTabHeight;
		this.x = this.xTab = ConfigurableOption.xSpacebarTab;
		this.y = this.yTab = ConfigurableOption.ySpacebarTab;
		this.distance = ConfigurableOption.tabDistance;
		this.direction = 1;
		this.destroyed = false;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.ORANGE);
		g2d.fillOval(x, y, diameter, diameter);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(destroyed){
			RenderableHolder.getInstance().getSouthRenderableList().remove(this);
		}
		
		if(x>=ConfigurableOption.tabDistance+ConfigurableOption.xSpacebarTab-20){
			this.direction = -1;
		}else if (x<=ConfigurableOption.xSpacebarTab){
			this.direction = 1;
		}
		
		x+=3*this.direction;
		
	}
	public int getDiameter(){
		return diameter;
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

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 10;
	}


}
