package LogicGame;

import java.util.*;
import entity.Gateway;
import entity.Player;
import entity.PlayerStatus;
import entity.Zombie;
import render.RenderableHolder;
import utility.AA;
import utility.ConfigurableOption;

public class NorthScreenLogic implements Logic{
	protected int spawnZombieCounter;
	protected Player player;
	protected PlayerStatus playerStatus;
	protected Gateway gateway1, gateway2;
	protected ArrayList<Zombie> zombies;
	private int movingDelayCounter;
	private SouthScreenLogic southScreenLogic;
	public static boolean spawnZombie;
	
	public NorthScreenLogic(){
		this.player = new Player();
		this.playerStatus = new PlayerStatus();
		this.gateway1 = new Gateway(ConfigurableOption.xGateway1, ConfigurableOption.yGateway1);
		this.gateway2 = new Gateway(ConfigurableOption.xGateway2, ConfigurableOption.yGateway2);
		this.zombies = new ArrayList<Zombie>();
		this.movingDelayCounter = 0;
		this.spawnZombieCounter = 666;
		spawnZombie = true;
		
		RenderableHolder.getInstance().addNorthEntity(player);
		RenderableHolder.getInstance().addNorthEntity(playerStatus);
		RenderableHolder.getInstance().addNorthEntity(gateway1);
		RenderableHolder.getInstance().addNorthEntity(gateway2);
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		gateway1.update();
		gateway2.update();
		player.update();
		playerStatus.update();
		movingDelayCounter++;
		spawnZombieCounter++;
		
		if(spawnZombie){
			spawnZombie = false;
			for(Zombie zombie : zombies){
				if(zombie.speed<zombies.size()){
					zombie.speed++;
				}
			}
			if(spawnZombieCounter >= AA.getCounterTime(10000)){
				spawnZombieCounter = 0;
				Zombie zombie = new Zombie(zombies.size()+1);
				RenderableHolder.getInstance().addNorthEntity(zombie);
				zombies.add(zombie);
			}
		}
		
		if(movingDelayCounter >= AA.getCounterTime(500)){
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
		
		if(ConfigurableOption.stageNow==4){
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
