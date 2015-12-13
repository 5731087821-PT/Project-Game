package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import render.RenderableHolder;
import utility.AA;
import utility.ConfigurableOption;
import utility.RandomUtility;

public class RunningBall implements IRenderable{
	protected int x, y;
	protected int xTab, yTab;
	protected int diameter;
	protected int tabDistance;
	protected int direction;
	protected boolean destroyed;
	private int shuffleDirectionDelay;
	
	public RunningBall(int x, int y, int tabDistance){
		this.diameter = ConfigurableOption.spacebarTabHeight;
		this.x = this.xTab = x;
		this.y = this.yTab = y;
		this.tabDistance = tabDistance;
		this.direction = 1;
		this.destroyed = false;
		this.shuffleDirectionDelay = 0;
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
		}else if(ConfigurableOption.stageNow == 2 && shuffleDirectionDelay >= AA.getCounterTime(300)){
			shuffleDirectionDelay = 0;
			if(RandomUtility.random(1, 2)==1){
				this.direction = -1;
			}else{
				this.direction = 1;
			}
		}

		this.x += 3*this.direction;
		shuffleDirectionDelay++;
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

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return destroyed;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}


}
