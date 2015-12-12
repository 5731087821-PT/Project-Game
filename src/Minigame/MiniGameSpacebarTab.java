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
import entity.Gateway;
import entity.Player;
import entity.PlayerStatus;
import entity.RunningBall;
import entity.SpacebarGap;
import entity.Zombie;
import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.RandomUtility;
import utility.Resource;

public class MiniGameSpacebarTab implements IRenderable {
	protected int xTab, yTab;
	protected int distance;
	protected int direction;
	protected int answerCounter;
	protected int disappearCounter;
	protected boolean answer;
	protected PlayerStatus playerStatus;
	protected ArrayList<RunningBall> runningBalls;
	protected ArrayList<SpacebarGap> gaps;
	protected ArrayList<Coin> coins;
	private int threadCounter;
	private boolean threadStart;
	private int spawnDelayCounter;
	private static final int SPAWN_DELAY = 50;
	

	public MiniGameSpacebarTab() {
		this.xTab = ConfigurableOption.xSpacebarTab;
		this.yTab = ConfigurableOption.ySpacebarTab;
		this.distance = ConfigurableOption.tabDistance;
		this.direction = 1;
		this.answerCounter = 0;
		this.spawnDelayCounter = 0;
		this.playerStatus = new PlayerStatus();
		this.coins = new ArrayList<Coin>();
		this.runningBalls = new ArrayList<RunningBall>();
		gaps = new ArrayList<SpacebarGap>();
		this.threadStart = false;
		
		RenderableHolder.getInstance().addNorthEntity(playerStatus);
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

	public void enterSpacebarStageZero() {
		runningBalls.get(0).destroyed = true;
		answerCounter++;

		if (answerCounter >= 3) {
			answerCounter = 0;
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
		return (runningBalls.get(0).getX() + runningBalls.get(0).getDiameter() - gap.getX()) > 10
				&& (gap.getX() + ConfigurableOption.gapWidth - runningBalls.get(0).getX() > 10);
	}
	
	public void zombieAppear(){
		NorthScreenLogic.spawnZombie = true;
		threadCounter = 0;//Count up
		
		if(!threadStart){
			new Thread(new Runnable() {
				public void run() {
					threadStart = true;
					
					Player player = null;
					
					ArrayList<IRenderable> list = (ArrayList<IRenderable>) RenderableHolder.getInstance().getNorthRenderableList();
					for(IRenderable thisOne : list){
						if(thisOne instanceof Player){
							player = (Player) thisOne;
						}
					}

					
					while(true){
						try {
							Thread.sleep(utility.ConfigurableOption.sleepTime);
						} catch (InterruptedException e) {}
						
						if(threadCounter==0){
							AudioClip bgm = Resource.getAudio("zombiedeath");
							bgm.play();	
						}else if(threadCounter==5){
							player.animationCurrent.setFlip(true);
						}else if(threadCounter==30){
							player.animationCurrent.setFlip(false);
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
	public void update() {
		// TODO Auto-generated method stub
		spawnDelayCounter++;
		
		playerStatus.update();
		
		if(spawnDelayCounter >= SPAWN_DELAY && ConfigurableOption.stageNow !=2 && ConfigurableOption.coinCounter < ConfigurableOption.coinLimit){
			if(ConfigurableOption.seedCoin%30==0) ConfigurableOption.seedCoin = 0;
			disappearCounter = RandomUtility.random(200, 500);
			spawnDelayCounter =0;
			Coin coin = new Coin(disappearCounter);
			RenderableHolder.getInstance().addNorthEntity(coin);
			coins.add(coin);
			int randomX = 0;
			
			if (ConfigurableOption.stageNow == 0 && gaps.size() == 0) {
				SpacebarGap gap = new SpacebarGap(1000000, randomX);
				RenderableHolder.getInstance().addSouthEntity(gap);
				gaps.add(gap);
			}else if(ConfigurableOption.stageNow == 1){
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
				ConfigurableOption.coinCounter++;
				SpacebarGap gap = new SpacebarGap(disappearCounter, randomX);
				RenderableHolder.getInstance().addSouthEntity(gap);
				gaps.add(gap);
			}
			
			ConfigurableOption.seedCoin++;
		}
		
		if (runningBalls.size() == 0) {
			RunningBall runningBall = new RunningBall();
			RenderableHolder.getInstance().addSouthEntity(runningBall);
			runningBalls.add(runningBall);
		} else {
			if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)) {
				answer = false;
				for (SpacebarGap gap : gaps) {
					if (enterInGap(gap)) {
						if (ConfigurableOption.stageNow == 0) {
							answer = true;
							enterSpacebarStageZero();
						}else if(ConfigurableOption.stageNow == 1){
							gap.destroyed = true;
							for(Coin coin : coins){
								if(coin.seed == gap.seed){
									coin.destroyed = true;
									break;
								}
							}
							playerStatus.addScore(1);
							answer = true;
							ConfigurableOption.coinCounter--;
						}
					}
				}
				if (!answer) {
					zombieAppear();
					playerStatus.subtractionScore(2);
				}
			}

			runningBalls.get(0).update();
		}

		for (SpacebarGap gap : gaps) {
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
		
		if(ConfigurableOption.stageNow == 2){
			for(Coin coin : coins){
				coin.destroyed = true;
			}
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
