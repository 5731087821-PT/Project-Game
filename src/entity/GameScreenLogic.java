package entity;

import java.util.*;
import java.util.function.Predicate;

import render.RenderableHolder;
import render.Resource;
import utility.ConfigurableOption;

public class GameScreenLogic {
	protected Player player;
	protected PlayerStatus playerStatus;
	protected Gateway gateIn, gateOut;
	protected ArrayList<Zombie> zombies;
	protected ArrayList<Coin> coins;
	private static final int SPAWN_DELAY = 50;
	private static final int MOVING_DELAY = 20;
	private int spawnDelayCounter;
	private int movingDelayCounter;
	private Resource resource;
	public static boolean spawnZombie;
	
	public GameScreenLogic(){
		this.player = new Player();
		this.playerStatus = new PlayerStatus();
		this.gateIn = new Gateway(ConfigurableOption.xGateway1, ConfigurableOption.yGateway1);
		this.gateOut = new Gateway(ConfigurableOption.xGateway2, ConfigurableOption.yGateway2);
		this.zombies = new ArrayList<Zombie>();
		this.coins = new ArrayList<Coin>();
		this.spawnDelayCounter = 0;
		this.movingDelayCounter = 0;
		this.resource = new Resource();
		this.spawnZombie = true;
		
		RenderableHolder.getInstance().addNorthEntity(player);
		RenderableHolder.getInstance().addNorthEntity(playerStatus);
		RenderableHolder.getInstance().addNorthEntity(gateIn);
		RenderableHolder.getInstance().addNorthEntity(gateOut);
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		gateIn.update();
		gateOut.update();
		player.update();
		spawnDelayCounter++;
		movingDelayCounter++;
		
		if(spawnZombie){
			this.spawnZombie = false;
			for(Zombie zombie : zombies){
				if(zombie.speed<zombies.size()){
					zombie.speed++;
				}
			}
			Zombie zombie = new Zombie(zombies.size()+1);
			RenderableHolder.getInstance().addNorthEntity(zombie);
			zombies.add(zombie);
		}
		
		if(spawnDelayCounter >= SPAWN_DELAY && player.getDoorOpen()!=2){
			spawnDelayCounter =0;
			Coin coin = new Coin();
			RenderableHolder.getInstance().addNorthEntity(coin);
			coins.add(coin);
		}
		
		if(movingDelayCounter >= MOVING_DELAY){
			movingDelayCounter = 0;
			for(Zombie zombie : zombies){
				zombie.update();
				if(player.collideWith(zombie)){
					player.destroyed= true;
				}
				
				if(player.destroyed){
					zombie.moving = false;
				}
			}
		}
		
		
		
		if(player.getDoorOpen()==2){
			for(Coin coin : coins){
				coin.destroyed = true;
			}
			for(Zombie zombie : zombies){
				zombie.destroyed = true;
				zombie.moving = false;
			}
		}
		
		for(Coin coin : coins){
			coin.update();
		}
		
		coins.removeIf(new Predicate<Coin>() {
			@Override
			public boolean test(Coin coin) {
				return coin.isDestroyed();
			}
		});
	}
}
