package LogicGame;

import java.util.*;

import Main.ScreenManager;
import entity.Gateway;
import entity.Player;
import entity.PlayerStatus;
import entity.Zombie;
import render.IRenderable;
import render.RenderableHolder;
import utility.TimeToCounter;
import utility.ConfigurableOption;

public class NorthScreenLogic implements Logic{
	protected int spawnZombieCounter;
	protected boolean firstZombie;
	protected Player player;
	protected PlayerStatus playerStatus;
	protected Gateway gateway1, gateway2, gateway0;
	protected ArrayList<Zombie> zombies;
	private int movingDelayCounter;
	private SouthScreenLogic southScreenLogic;
	public static boolean spawnZombie;
	private List<IRenderable> list;
	private int nextZombieSpeedUp;
	
	public NorthScreenLogic(){
		this.player = new Player();
		this.playerStatus = new PlayerStatus();
		this.gateway1 = new Gateway(ConfigurableOption.xGateway1, ConfigurableOption.yGateway1);
		this.gateway2 = new Gateway(ConfigurableOption.xGateway2, ConfigurableOption.yGateway2);
		this.zombies = new ArrayList<Zombie>();
		this.movingDelayCounter = 0;
		this.spawnZombieCounter = 600;
		this.firstZombie = true;
		nextZombieSpeedUp = ConfigurableOption.nextZombieSpeedUp;
		spawnZombie = true;
		
		list = RenderableHolder.getInstance().getNorthRenderableList();
		RenderableHolder.getInstance().addNorthEntity(player);
		RenderableHolder.getInstance().addNorthEntity(playerStatus);
		RenderableHolder.getInstance().addNorthEntity(gateway1);
		RenderableHolder.getInstance().addNorthEntity(gateway2);
	}
	
	public void logicUpdate() {
		if(player.isDestroyed())
			ScreenManager.changeScreen(ScreenManager.GAMEOVERSCREEN);
		if(player.isDestroying())
			return;
		
		gateway1.update();
		gateway2.update();
		player.update();
		playerStatus.update();
		movingDelayCounter++;
		spawnZombieCounter++;
		
		
		if(firstZombie && spawnZombieCounter<TimeToCounter.getCounter(10000)) return;
		
		if(spawnZombie){
			firstZombie = false;
			spawnZombie = false;
			for(Zombie zombie : zombies){
				if(zombie.speed<zombies.size()){
					zombie.speed++;
				}
			}
			if(spawnZombieCounter >= TimeToCounter.getCounter(10000)){
				spawnZombieCounter = 0;
				Zombie zombie = new Zombie(zombies.size()+nextZombieSpeedUp);
				RenderableHolder.getInstance().addNorthEntity(zombie);
				zombies.add(zombie);
				for(IRenderable rend : list){
					if(rend instanceof Player){
						((Player) rend).zombieIsComming();
						break;
					}
				}
			}
		}
		
		if(movingDelayCounter >= TimeToCounter.getCounter(500)){
			movingDelayCounter = 0;
			for(Zombie zombie : zombies){
				zombie.update();
				if(player.collideWith(zombie)){
					player.setDestroying(true);
					zombie.moving = false;
				}
			}
		}
		
		if(ConfigurableOption.stageNow==4){
			for(Zombie zombie : zombies){
				zombie.setDestroying(true);
				zombie.moving = false;
			}
		}
	}

	public void setSouthScreenLogic(SouthScreenLogic southScreenLogic) {
		this.southScreenLogic = southScreenLogic;
	}
}
