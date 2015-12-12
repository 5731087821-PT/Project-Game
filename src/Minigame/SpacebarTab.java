package Minigame;

import java.applet.AudioClip;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.function.Predicate;

import com.sun.glass.events.KeyEvent;

import LogicGame.NorthScreenLogic;
import entity.Coin;
import entity.Player;
import entity.PlayerStatus;
import entity.RunningBall;
import entity.SpacebarGap;
import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.RandomUtility;
import utility.Resource;

public class SpacebarTab implements IRenderable {
	protected int xTab, yTab;
	protected int distance;
	protected int direction;
	protected int comboCounter;
	protected int disappearCounter;
	protected boolean answer;
	protected PlayerStatus playerStatus;
	protected RunningBall runningBall;
	protected ArrayList<SpacebarGap> gaps;
	protected ArrayList<Coin> coins;
	private int spawnDelayCounter;
	private static final int SPAWN_DELAY = 50;
	

	public SpacebarTab() {
		this.xTab = ConfigurableOption.xSpacebarTab;
		this.yTab = ConfigurableOption.ySpacebarTab;
		this.distance = ConfigurableOption.tabDistance;
		this.direction = 1;
		this.comboCounter = 1;
		this.spawnDelayCounter = 0;
		this.runningBall = new RunningBall();
		this.coins = new ArrayList<Coin>();
		gaps = new ArrayList<SpacebarGap>();
		
		RenderableHolder.getInstance().addSouthEntity(this.runningBall);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(new Color(192, 192, 192));
		g2d.fillRect(xTab, yTab, ConfigurableOption.tabDistance, ConfigurableOption.spacebarTabHeight);

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(xTab - 1, yTab - 1, ConfigurableOption.tabDistance, 22);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean enterInGap(SpacebarGap gap) {
		return (runningBall.getX() + runningBall.getDiameter() - gap.getX()) > 10
				&& (gap.getX() + ConfigurableOption.gapWidth - runningBall.getX() > 10);
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		spawnDelayCounter++;
		runningBall.update();
		
		if(spawnDelayCounter >= SPAWN_DELAY && ConfigurableOption.stageNow !=2 && ConfigurableOption.coinCounter < ConfigurableOption.coinLimit){
			if(ConfigurableOption.seedCoin%30==0) ConfigurableOption.seedCoin = 0;
			disappearCounter = RandomUtility.random(200, 500);
			spawnDelayCounter =0;
			int randomX = 0;
			
			boolean overlap = false;
			do{
				randomX = RandomUtility.random(ConfigurableOption.xSpacebarTab,
							ConfigurableOption.xSpacebarTab+ConfigurableOption.tabDistance-ConfigurableOption.gapWidth);
				overlap = false;
				for(SpacebarGap gap : gaps){
					if(randomX+ConfigurableOption.gapWidth - gap.getX() >= 0 && gap.getX()+ConfigurableOption.gapWidth - randomX >= 0){
						overlap = true;
						break;
					}
				}
			}while(overlap);
			Coin coin = new Coin(disappearCounter);
			RenderableHolder.getInstance().addNorthEntity(coin);
			coins.add(coin);
			SpacebarGap gap = new SpacebarGap(disappearCounter, randomX);
			RenderableHolder.getInstance().addSouthEntity(gap);
			gaps.add(gap);
			ConfigurableOption.coinCounter++;
			ConfigurableOption.seedCoin++;
		}
		
		if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)) {
			answer = false;
			for (SpacebarGap gap : gaps) {
				if (enterInGap(gap)) {
					gap.destroyed = true;
					for(Coin coin : coins){
						if(coin.seed == gap.seed){
							coin.destroyed = true;
							break;
						}
					}
					playerStatus.addScore(playerStatus.getCombo());
					playerStatus.addCombo(1);
					answer = true;
					ConfigurableOption.coinCounter--;
				}
			}
			if (!answer) {
				zombieAppear();
				playerStatus.subtractionScore(2);
				playerStatus.comboInterrupted(); ;
			}
		}	

		for (SpacebarGap gap : gaps) {
			gap.update();
		}
		
		for(Coin coin : coins){
			coin.update();
		}
		
		for(SpacebarGap gap : gaps){
			gap.update();
		}
		
		coins.removeIf(new Predicate<Coin>() {
			@Override
			public boolean test(Coin coin) {
				return coin.isDestroyed();
			}
		});
		
		gaps.removeIf(new Predicate<SpacebarGap>() {
			@Override
			public boolean test(SpacebarGap gap) {
				return gap.destroyed;
			}
		});
	
	}
	
	public void setPlayerStatus(PlayerStatus playerStatus){
		this.playerStatus = playerStatus;
	}
	
	public ArrayList<Coin> getCoin(){
		return coins;
	}
	
	public ArrayList<SpacebarGap> getGap(){
		return gaps;
	}
	
	public RunningBall getRunningBall(){
		return this.runningBall;
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
