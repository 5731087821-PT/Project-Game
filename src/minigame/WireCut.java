package minigame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.function.Predicate;

import com.sun.glass.events.KeyEvent;

import LogicGame.NorthScreenLogic;
import entity.Gateway;
import entity.RunningBall;
import entity.SpacebarGap;
import entity.Wire;
import render.IRenderable;
import render.RenderableHolder;
import resource.Resource;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.RandomUtility;

public class WireCut implements IRenderable {
	protected int x, y;
	protected int width, height;
	protected int xTab, yTab;
	protected int tabDistance;
	protected int direction;
	protected int randomX;
	protected RunningBall runningBall;
	protected ArrayList<SpacebarGap> gaps;
	protected ArrayList<Wire> wires;
	
	
	public WireCut(){
		this.wires = new ArrayList<Wire>();
		
		this.width = ConfigurableOption.wireFrameWidth;
		this.height = ConfigurableOption.wireFrameHeight;
		this.x = (ConfigurableOption.screenWidth/2) - (width/2) ;
		this.y = 30;
		this.xTab = x;
		this.yTab = 30+11*height/9;
		this.tabDistance = width;
		this.runningBall = new RunningBall(x, 30+11*height/9, width);
		this.gaps = new ArrayList<SpacebarGap>();
		
		gaps.add(new SpacebarGap(0, new Color(128, 0, 128), 1000000, randGapX(), 30+11*height/9));
		gaps.add(new SpacebarGap(1, Color.BLUE, 1000000, randGapX(), 30+11*height/9));
		gaps.add(new SpacebarGap(2, Color.RED, 1000000, randGapX(), 30+11*height/9));
		gaps.add(new SpacebarGap(3, Color.YELLOW, 1000000, randGapX(), 30+11*height/9));
		
		wires.add(new Wire(0, new Color(128, 0, 128), x+width/10, 30+height/9, 4*width/5));
		wires.add(new Wire(1, Color.BLUE, x+width/10, 30+3*height/9, 4*width/5));
		wires.add(new Wire(2, Color.RED, x+width/10, 30+5*height/9, 4*width/5));
		wires.add(new Wire(3, Color.YELLOW, x+width/10, 30+7*height/9, 4*width/5));
		
		RenderableHolder.getInstance().addSouthEntity(this.runningBall);
		
		for(SpacebarGap gap : gaps){
			RenderableHolder.getInstance().addSouthEntity(gap);
		}
		
		for(Wire wire : wires){
			RenderableHolder.getInstance().addSouthEntity(wire);
		}
	}
	
	public int randGapX(){
		boolean overlap = false;
		do{
			randomX = RandomUtility.random(x+width/10,
						ConfigurableOption.xSpacebarTab+ConfigurableOption.tabDistance-ConfigurableOption.gapWidth);
			overlap = false;
			for(SpacebarGap gap : gaps){
				if(randomX+ConfigurableOption.gapWidth - gap.getX() >= 0 && gap.getX()+ConfigurableOption.gapWidth - randomX >= 0){
					overlap = true;
					break;
				}
			}
		}while(overlap);
		
		return randomX;
	}
	
	public boolean enterInGap(SpacebarGap gap) {
		return (runningBall.getX() + runningBall.getDiameter() - gap.getX()) > 10
				&& (gap.getX() + ConfigurableOption.gapWidth - runningBall.getX() > 10);
	}
	
	public void zombieAppear(){
		NorthScreenLogic.spawnZombie = true;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(x, y, width, height);
		
		g2d.setColor(new Color(192, 192, 192));
		g2d.fillRect(xTab, yTab, tabDistance, ConfigurableOption.spacebarTabHeight);

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(xTab - 1, yTab - 1, tabDistance, 22);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public void setDestroying(boolean destroyed) {
		
	}

	@Override
	public void update() {
		runningBall.update();
		
		if(wires.size()==0){
			for (IRenderable renderable : RenderableHolder.getInstance().getNorthRenderableList()) {
				if (renderable instanceof Gateway && ((Gateway) renderable).getX() == ConfigurableOption.xGateway2) {
					((Gateway) renderable).setGateClose(false);
					ConfigurableOption.stageNow++;
					break;
				}
			}
		}
		
		if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)) {
			boolean answer = false;
			for (SpacebarGap gap : gaps) {
				if (enterInGap(gap)) {
					gap.setDestroying(true);
					for(Wire wire : wires){
						if(wire.getPrimaryKey() == gap.getPrimaryKey()){
							wire.setDestroying(true);
							break;
						}
					}
					answer = true;
					Resource.getAudio("gotitem").play();
				}
			}
			if (!answer) {
				Resource.getAudio("bump").play();
				zombieAppear();
			}
		}
		
		for (SpacebarGap gap : gaps) {
			if(gap.isDestroyed()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(gap);
			}
		}
		
		for (Wire wire : wires) {
			if(wire.isDestroyed()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(wire);
			}
		}
		
		gaps.removeIf(new Predicate<SpacebarGap>() {
			@Override
			public boolean test(SpacebarGap gap) {
				return gap.isDestroyed();
			}
		});
		
		wires.removeIf(new Predicate<Wire>() {
			@Override
			public boolean test(Wire wire) {
				return wire.isDestroyed();
			}
		});
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
	public int getZ() {
		return 0;
	}
	
	public RunningBall getRunningBall(){
		return this.runningBall;
	}
	
	public ArrayList<SpacebarGap> getGap(){
		return gaps;
	}
	
	public ArrayList<Wire> getWire(){
		return wires;
	}

	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}

}
