package entity;

import java.applet.AudioClip;
import java.awt.*;

import render.AnimationManager;
import render.IRenderable;
import render.RenderAnimationHelper;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.Resource;

public class Player implements IRenderable {
	protected int x;
	protected int y;
	protected int charWidth,charHeight;
	protected boolean destroyed;
	protected boolean destroying;
	private int doorOpen;
	private int deadCounter;
	public AnimationManager animation;
	public AnimationManager animationWalking;
	public AnimationManager animationStanding;
	private boolean walking;
	private boolean visible;
	private int threadCounter;
	private boolean threadStart;

	public Player() {
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

		this.charHeight = 120;
		this.charWidth = animation.getCharWidth(this.charHeight);
	}

	public void setWalking(boolean walking){
		if(!(this.walking ^ walking)) return;
		this.walking = walking;
		if(walking){
			animation = animationWalking;
		}else{
			animation = animationStanding;
		}
		animation.loop();
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
	}
	
	public boolean collideWith(Zombie zombie){
		return Math.hypot(this.x-zombie.x, this.y-zombie.y) < this.charWidth/2 + zombie.getCharWidth()/2;
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
			RenderAnimationHelper.draw(g2d, animation, x, y,0, charHeight);
		}else{
			if(deadCounter == 0)
				deadCounter = 150;
			
			if(deadCounter%25<12)
				RenderAnimationHelper.draw(g2d, animation, x, y,0, charHeight);
			
			if(--deadCounter == 0)
				destroyed = true;
		}
		if(ConfigurableOption.PAUSE) return;
			animation.update();
	}
	
	public void zombieIsComming(){
		threadCounter = 0;//Counter - counting up
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
							animation.flip(AnimationManager.FlipToUnUsual);
						}else if(threadCounter==30){
							animation.flip(AnimationManager.FlipToUsual);
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
