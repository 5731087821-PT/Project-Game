package entity;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	protected boolean destroyed;
	protected boolean destroying;
	private int doorOpen;
	private int deadCounter;
	public AnimationManager animationCurrent;
	public AnimationManager animationWalking;
	public AnimationManager animationStanding;
	private boolean walking;
	private boolean visible;
	private int threadCounter;
	private boolean threadStart;

	public Player() {
		this.width = 50;
//		this.x = ConfigurableOption.screenWidth-(5*ConfigurableOption.screenWidth/7);
//		this.y = ConfigurableOption.gameScreenHeight-radius*2;
		this.x = ConfigurableOption.screenWidth-(5*ConfigurableOption.screenWidth/7);
		this.y = ConfigurableOption.northScreenHeight;
		this.destroyed = false;
		this.doorOpen = 0;
		this.visible = true;
		this.deadCounter = 0;
		this.threadCounter = 0;
		this.destroyed = false;
		this.destroying = false;
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
		
		
		if(!destroying){
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

			if(deadCounter == 0){
				destroyed = true;
			}
		}
	}
	
	public void zombieIsComming(){
		threadCounter = 0;//Count up
		
		if(!threadStart){
			new Thread(new Runnable() {
				public void run() {
					threadStart = true;
					
					while(true){
						try {
							Thread.sleep(utility.ConfigurableOption.sleepTime);
						} catch (InterruptedException e) {}
						
						if(threadCounter==0){
							AudioClip zombie = Resource.getAudio("zombiedeath");
							zombie.play();	
						}else if(threadCounter==5){
							animationCurrent.flip(AnimationManager.FLIP);
						}else if(threadCounter==30){
							animationCurrent.flip(AnimationManager.FLIP);
							threadStart = false;
							break;
						}
						
						threadCounter++;
					}
					
				}
			}).start();
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

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroying = destroyed;
	}

	public boolean isDestroying() {
		return this.destroying;
	}
}
