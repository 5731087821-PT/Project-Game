package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import render.AnimationManager;
import render.IRenderable;
import render.RenderAnimationHelper;
import render.RenderHelper;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.Resource;

public class Player implements IRenderable {
	protected int x;
	protected int y;
	protected int width;
	public boolean destroyed;
	private int doorOpen;
	private int deadCounter;
	public AnimationManager animationCurrent;
	public AnimationManager animationWalking;
	public AnimationManager animationStanding;
	private boolean walking;
	private boolean visible;

	public Player() {
		this.width = 50;
//		this.x = ConfigurableOption.screenWidth-(5*ConfigurableOption.screenWidth/7);
//		this.y = ConfigurableOption.gameScreenHeight-radius*2;
		this.x = ConfigurableOption.screenWidth-(5*ConfigurableOption.screenWidth/7);
		this.y = ConfigurableOption.northScreenHeight;
		this.destroyed = false;
		this.doorOpen = 0;
		this.visible = true;
		animationWalking = Resource.get("batman-walking");
		animationStanding = Resource.get("batman-standing");
		setWalking(true);
	}

	public void setWalking(boolean walking){
		if(!((!this.walking && walking) || (this.walking && !walking))) return;
		
		this.walking = walking;
		if(walking){
			animationCurrent = animationWalking;
		}else{
			animationCurrent = animationStanding;
		}
		animationCurrent.loop();
	}

	public synchronized void update() {
		for(IRenderable renderable : RenderableHolder.getInstance().getNorthRenderableList()){
			if(renderable instanceof Gateway){
				if( ((Gateway) renderable).isGateClose() ){
					continue;
				}else if( ((Gateway) renderable).getX() == ConfigurableOption.xGateway1 && doorOpen !=2 && ((Gateway)renderable).getY() <= -50){
					doorOpen = 1;
				}else if( ((Gateway) renderable).getX() == ConfigurableOption.xGateway2 && ((Gateway)renderable).getY() <= -20){
					doorOpen = 2;
				}
			}
		}
		
		if(doorOpen == 1 && x < ConfigurableOption.xGateway1+30){
			this.x+=2;
			setWalking(true);
		}else if(doorOpen == 2){
			this.x+=3;
			setWalking(true);
		}else{
			setWalking(false);
		}
		if(isVisible())
			animationCurrent.update();
	}
	
	public boolean collideWith(Zombie zombie){
		return Math.hypot(this.x-zombie.x, this.y-zombie.y) < this.width+20;
	}
	
	public boolean isDestroyed(){
		return this.destroyed;
	}
	
	public int getDoorOpen(){
		return this.doorOpen;
	}

	@Override
	public int getZ() {
		return 100;
	}

	@Override
	public synchronized void draw(Graphics2D g2d) {
		
		
		if(!destroyed){
			RenderAnimationHelper.draw(g2d, animationCurrent, x, y, width);
		}else{
			if(deadCounter == 0){
				deadCounter = 150;
			}else{
				deadCounter--;
			}
			
			if(deadCounter%25<12){
//					g2d.setColor(Color.RED);
					RenderAnimationHelper.draw(g2d, animationCurrent, x, y, width);
				}else{
//					g2d.setColor(new Color(255,0,0,0));
				}
//				g2d.fillOval(x, y, radius * 2, radius * 2);
				
			if(deadCounter == 0)
				destroyed = true;
				RenderableHolder.getInstance().getNorthRenderableList().remove(this);
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return this.visible;
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
