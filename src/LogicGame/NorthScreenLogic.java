package LogicGame;

import java.util.*;
import java.util.function.Predicate;

import entity.Coin;
import entity.Gateway;
import entity.Player;
import entity.PlayerStatus;
import entity.Zombie;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class NorthScreenLogic implements Logic{
	protected Player player;
	protected PlayerStatus playerStatus;
	protected Gateway gateIn, gateOut;
	protected ArrayList<Zombie> zombies;
	private static final int MOVING_DELAY = 20;
	private int movingDelayCounter;
	private SouthScreenLogic southScreenLogic;
	public static boolean spawnZombie;
	
	public NorthScreenLogic(){
		this.player = new Player();
		this.playerStatus = new PlayerStatus();
		this.gateIn = new Gateway(ConfigurableOption.xGateway1, ConfigurableOption.yGateway1);
		this.gateOut = new Gateway(ConfigurableOption.xGateway2, ConfigurableOption.yGateway2);
		this.zombies = new ArrayList<Zombie>();
		this.movingDelayCounter = 0;
		spawnZombie = true;
		
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
		playerStatus.update();
		movingDelayCounter++;
		
		if(spawnZombie){
			spawnZombie = false;
			for(Zombie zombie : zombies){
				if(zombie.speed<zombies.size()){
					zombie.speed++;
				}
			}
			Zombie zombie = new Zombie(zombies.size()+1);
			RenderableHolder.getInstance().addNorthEntity(zombie);
			zombies.add(zombie);
		}
		
		if(movingDelayCounter >= MOVING_DELAY){
			movingDelayCounter = 0;
			for(Zombie zombie : zombies){
				zombie.update();
				if(player.collideWith(zombie)){
					player.setDestroyed(true);
				}
				
				if(player.isDestroying()){
					zombie.moving = false;
				}
			}
		}
		
		
		
		if(player.getDoorOpen()==2){
			for(Zombie zombie : zombies){
				zombie.setDestroyed(true);
				zombie.moving = false;
			}
		}
	}

	public void setSouthScreenLogic(SouthScreenLogic southScreenLogic) {
		// TODO Auto-generated method stub
		this.southScreenLogic = southScreenLogic;
	}
}
