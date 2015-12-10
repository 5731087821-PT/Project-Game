package Entity;

import java.awt.*;

import render.IRenderable;
import render.RenderableHolder;

public class Player implements IRenderable {
	protected int x;
	protected int y;
	protected int radius;
	protected boolean destroyed;
	private int doorOpen;
	private int deadCounter;

	public Player() {
		this.x = 210;
		this.y = 120;
		this.radius = 20;
		this.destroyed = false;
		this.doorOpen = 0;
	}

	public void update() {
		for(IRenderable renderable : RenderableHolder.getInstance().getRenderableList()){
			if(renderable instanceof Gateway){
				if( ((Gateway) renderable).isGateClose() ){
					continue;
				}else if( ((Gateway) renderable).getX() == 260 && doorOpen !=2 && ((Gateway)renderable).getY() <= -40){
					doorOpen = 1;
				}else if( ((Gateway) renderable).getX() == 450){
					doorOpen = 2;
				}
			}
		}
		
		if(doorOpen == 1 && x < 300){
			this.x+=2;
		}else if(doorOpen == 2){
			this.x+=3;
		}
		
	}
	
	public boolean collideWith(Zombie zombie){
		return Math.hypot(this.x-zombie.x, this.y-zombie.y) < this.radius+20;
	}
	
	public boolean isDestroyed(){
		return this.destroyed;
	}
	
	public int getDoorOpen(){
		return this.doorOpen;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(!destroyed){
			g2d.setColor(Color.RED);
			g2d.fillOval(x, y, radius * 2, radius * 2);
		}else{
			if(deadCounter == 0){
				deadCounter = 150;
			}else{
				deadCounter--;
			}
			
			if(deadCounter%25<12){
				g2d.setColor(Color.RED);
			}else{
				g2d.setColor(new Color(255,0,0,0));
			}
			g2d.fillOval(x, y, radius * 2, radius * 2);
				
			if(deadCounter == 0)
				RenderableHolder.getInstance().getRenderableList().remove(this);
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
}
