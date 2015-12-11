package entity;

import java.util.*;
import java.util.function.Predicate;

import render.RenderableHolder;
import render.Resource;

public class GameLogic {
	protected Zombie zombie;
	protected Player player;
	protected Gateway gateIn, gateOut;
	protected ArrayList<Coin> coins;
	private static final int SPAWN_DELAY = 50;
	private static final int MOVING_DELAY = 20;
	private int spawnDelayCounter;
	private int movingDelayCounter;
	private Resource resource;
	
	public GameLogic(){
		this.zombie = new Zombie();
		this.player = new Player();
		this.gateIn = new Gateway(260, 0);
		this.gateOut = new Gateway(450, 0);
		this.coins = new ArrayList<Coin>();
		this.spawnDelayCounter = 0;
		this.movingDelayCounter = 0;
		this.resource = new Resource();
		
		RenderableHolder.getInstance().add(zombie);
		RenderableHolder.getInstance().add(player);
		RenderableHolder.getInstance().add(gateIn);
		RenderableHolder.getInstance().add(gateOut);
		
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		gateIn.update();
		gateOut.update();
		player.update();
		spawnDelayCounter++;
		movingDelayCounter++;
		
		if(spawnDelayCounter >= SPAWN_DELAY && player.getDoorOpen()!=2){
			spawnDelayCounter =0;
			Coin coin = new Coin();
			RenderableHolder.getInstance().add(coin);
			coins.add(coin);
		}
		
		if(movingDelayCounter >= MOVING_DELAY){
			movingDelayCounter = 0;
			zombie.update();
		}
		
		if(player.collideWith(zombie)){
			player.destroyed= true;
			zombie.moving = false;
		}
		
		if(player.getDoorOpen()==2){
			zombie.moving = false;
			zombie.destroyed = true;
			for(int i = 0 ; i< coins.size();i++){
				coins.get(i).destroyed = true;
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
