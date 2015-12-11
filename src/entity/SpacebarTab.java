package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.function.Predicate;

import com.sun.glass.events.KeyEvent;

import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;

public class SpacebarTab implements IRenderable{
	protected int xTab, yTab;
	protected int distance;
	protected int direction;
	protected ArrayList<RunningBall> runningBalls;
	protected ArrayList<SpacebarGap> gaps;
	private static final int SPAWN_DELAY = 50;
	private int spawnDelayCounter;
	
	public SpacebarTab(){
		this.xTab = ConfigurableOption.xSpacebarTab;
		this.yTab = ConfigurableOption.ySpacebarTab;
		this.distance = ConfigurableOption.tabDistance;
		this.direction = 1;
		this.runningBalls = new ArrayList<RunningBall>();
		this.gaps = new ArrayList<SpacebarGap>();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(new Color(192, 192, 192));
		g2d.fillRect(xTab, yTab, ConfigurableOption.tabDistance,ConfigurableOption.spacebarTabHeight);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(xTab-1, yTab-1, ConfigurableOption.tabDistance, 22);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		spawnDelayCounter++;
		
		if(runningBalls.size()==0){
			RunningBall runningBall = new RunningBall();
			RenderableHolder.getInstance().addSouthEntity(runningBall);
			runningBalls.add(runningBall);
		}else{
			if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)) {
				InputUtility.postUpdate();
				for(SpacebarGap gap : gaps){
					if((runningBalls.get(0).getX()+runningBalls.get(0).getDiameter() - gap.getX()) > 10 && (gap.getX() + ConfigurableOption.gapWidth - runningBalls.get(0).getX() > 10) ){
						gap.destroyed = true;
						runningBalls.get(0).destroyed = true;
					}
				}
			}
			
			runningBalls.get(0).update();
		}
		
		for(SpacebarGap gap : gaps){
			gap.update();
		}
		
		gaps.removeIf(new Predicate<SpacebarGap>() {
			@Override
			public boolean test(SpacebarGap gap) {
				return gap.destroyed;
			}
		});
		
		runningBalls.removeIf(new Predicate<RunningBall>() {

			@Override
			public boolean test(RunningBall runningBall) {
				// TODO Auto-generated method stub
				return runningBall.destroyed;
			}
		});
		
		if(ConfigurableOption.stageNow == 0 && gaps.size() == 0){
			SpacebarGap gap = new SpacebarGap();
			RenderableHolder.getInstance().addSouthEntity(gap);
			gaps.add(gap);
		}else if(ConfigurableOption.stageNow == 1 && spawnDelayCounter >= SPAWN_DELAY){
			spawnDelayCounter=0;
			SpacebarGap gap = new SpacebarGap();
			RenderableHolder.getInstance().addSouthEntity(gap);
			gaps.add(gap);
		}
		
		
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
