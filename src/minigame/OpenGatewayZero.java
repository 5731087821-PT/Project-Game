package minigame;

import java.applet.AudioClip;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.function.Predicate;

import com.sun.glass.events.KeyEvent;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Redefinable;

import LogicGame.NorthScreenLogic;
import entity.Coin;
import entity.Gateway;
import entity.Player;
import entity.PlayerStatus;
import entity.RunningBall;
import entity.SpacebarGap;
import entity.Zombie;
import render.IRenderable;
import render.RenderableHolder;
import resource.Resource;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.RandomUtility;

public class OpenGatewayZero implements IRenderable {
	protected int xTab, yTab;
	protected int distance;
	protected int direction;
	protected int comboCounter;
	protected int answerCounter;
	protected boolean answer;
	protected boolean destroyed;
	protected PlayerStatus playerStatus;
	protected RunningBall runningBall;
	protected SpacebarGap gap;
	
	public OpenGatewayZero() {
		this.xTab = ConfigurableOption.xSpacebarTab;
		this.yTab = ConfigurableOption.ySpacebarTab;
		this.distance = ConfigurableOption.tabDistance;
		this.direction = 1;
		this.answerCounter = 0;
		gap = new SpacebarGap(-1, null, 1000000, 0, ConfigurableOption.ySpacebarTab);
		
		RenderableHolder.getInstance().addSouthEntity(gap);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(192, 192, 192));
		g2d.fillRect(xTab, yTab, ConfigurableOption.tabDistance, ConfigurableOption.spacebarTabHeight);

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(xTab - 1, yTab - 1, ConfigurableOption.tabDistance, 22);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	public void enterSpacebar() {
		runningBall.setDestroyed(true);
		answerCounter++;

		if (answerCounter >= 3) {
			for (IRenderable renderable : RenderableHolder.getInstance().getNorthRenderableList()) {
				if (renderable instanceof Gateway && ((Gateway) renderable).getX() == ConfigurableOption.xGateway1) {
					((Gateway) renderable).setGateClose(false);
					ConfigurableOption.stageNow = 1;
					break;
				}
			}
		}
	}

	public boolean enterInGap(SpacebarGap gap) {
		return (runningBall.getX() + runningBall.getDiameter() - gap.getX()) > 10
				&& (gap.getX() + ConfigurableOption.gapWidth - runningBall.getX() > 10);
	}
	
	

	@Override
	public void update() {
		if (runningBall == null) {
			runningBall = new RunningBall(ConfigurableOption.xSpacebarTab, ConfigurableOption.ySpacebarTab, ConfigurableOption.tabDistance);
			RenderableHolder.getInstance().addSouthEntity(runningBall);
		} else {
			if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)) {
				answer = false;
				if (enterInGap(gap)) {
					answer = true;
					enterSpacebar();
				}

				if (!answer) {
					zombieAppear();
				}
			}

			runningBall.update();
		}

		if(runningBall.isDestroyed()){
			runningBall = null;
		}
	}
	
	public void zombieAppear(){
		NorthScreenLogic.spawnZombie = true;

		for(IRenderable rend : RenderableHolder.getInstance().getNorthRenderableList()){
			if(rend instanceof Player){
				((Player) rend).zombieIsComming();
				break;
			}
		}
	}
	
	public SpacebarGap getGap(){
		return this.gap;
	}
	
	public RunningBall getRunningBall(){
		return this.runningBall;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public void updateAnimation() {
		
	}

}
