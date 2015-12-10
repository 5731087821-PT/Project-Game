package LogicGame;

import java.util.ArrayList;
import java.util.List;

import render.RenderableHolder;
import res.*;

public class GameLogic {
	private static GameLogic instance;	
	public static GameLogic getInstance() {
		return instance;
	}
	
	private Player player;
	private PlayerStatus playerStatus;
	private List<Apple> apples;
	
	private static final int SPAWN_DELAY = 100;
	private int spawnDelayCounter = 0;
	
	public GameLogic(){	
		
		player = new Player();
		playerStatus = new PlayerStatus();
		apples = new ArrayList<Apple>();
		
		RenderableHolder.getInstance().add(player);
		RenderableHolder.getInstance().add(playerStatus);
		
	}
	
	public void logicUpdate(){
		//Preventing thread interference
		synchronized(RenderableHolder.getInstance()){
			player.update();
			for (int i = 0; i < apples.size(); i++) {
				Apple apple = apples.get(i);
				if (apple.collideWith(player)) {
					apple.destroyed = true;
					playerStatus.addScore(1);
					Resource.coinSound.play();

					RenderableHolder.getInstance().getRenderableList().remove(apple);
					apples.remove(i);
					
					i--;
					continue;
				}

				if (!apple.isDestroyed()) {
					apple.update();
				} else {
					RenderableHolder.getInstance().getRenderableList().remove(apple);
					playerStatus.substractionScore(1);
					apples.remove(i);
				}
			}	
			
			spawnDelayCounter++;
			if (spawnDelayCounter >= SPAWN_DELAY) {
				spawnDelayCounter = 0;
				Apple apple = new Apple();
				apples.add(apple);
				RenderableHolder.getInstance().add(apple);
			}
			
		}
	}
}
