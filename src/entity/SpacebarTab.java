package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import utility.ConfigurableOption;

public class SpacebarTab implements IRenderable{
	private int x, y;
	private int xTab, yTab;
	private int distance;
	private int direction;
	
	public SpacebarTab(){
		this.x = this.xTab = ConfigurableOption.xSpacebarTab;
		this.y = this.yTab = ConfigurableOption.ySpacebarTab;
		this.distance = ConfigurableOption.tabDistance;
		this.direction = 1;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.BLACK);
		g2d.fillRect(xTab, yTab, ConfigurableOption.tabDistance, 20);
		
		g2d.setColor(Color.ORANGE);
		g2d.fillOval(x, y, 20, 20);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(x>=ConfigurableOption.tabDistance+ConfigurableOption.xSpacebarTab){
			this.direction = -1;
		}else if (x<=ConfigurableOption.xSpacebarTab){
			this.direction = 1;
		}
		
		x+=this.direction;
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
