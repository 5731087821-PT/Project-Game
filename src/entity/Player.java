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
	private int deadCounter;
	public AnimationManager animation;
	public AnimationManager animationWalking;
	public AnimationManager animationStanding;
	private boolean walking;
	private boolean visible;
	private int threadCounter;
	private boolean threadStart;

	public Player() {
		this.x = -40;
		this.y = ConfigurableOption.northScreenHeight;
		this.destroyed = false;
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
		if(ConfigurableOption.stageNow == 0 && x < ConfigurableOption.xGateway1-35){
			this.x+=2;
			setWalking(true);
		}else if(ConfigurableOption.stageNow == 1 && x < ConfigurableOption.xGateway1+30){
			this.x+=2;
			setWalking(true);
		}else if(ConfigurableOption.stageNow == 2 && x <ConfigurableOption.xGateway2 -35){
			this.x+=2;
			setWalking(true);
		}else if(ConfigurableOption.stageNow == 3 && x < ConfigurableOption.xGateway2 + 70){
			this.x+=2;
			setWalking(true);
		}
		else{
			setWalking(false);
		}
	}
	
	public boolean collideWith(Zombie zombie){
		return Math.hypot(this.x-zombie.x, this.y-zombie.y) < this.charWidth/2 + zombie.getCharWidth()/2;
	}
	
	public boolean isDestroyed(){
		return this.destroyed;
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
			
			if(--deadCounter == 0){
				destroyed = true;
				ConfigurableOption.GAMEOver = true;
			}
		}
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
		return this.visible;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroying = destroyed;
	}

	public boolean isDestroying() {
		return this.destroying;
	}

	@Override
	public void updateAnimation() {
			animation.update();
	}
}
