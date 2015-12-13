package LogicGame;

import java.awt.event.KeyEvent;

import entity.Coin;
import entity.SpacebarGap;
import minigame.OpenGatewayZero;
import minigame.SpacebarTab;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;

public class SouthScreenLogic implements Logic{
	protected OpenGatewayZero openGatewayZero;
	protected SpacebarTab spacebarTab;
	private NorthScreenLogic northScreenLogic;
	private boolean startStage;
	
	public SouthScreenLogic(){
		this.startStage = true;
	}
	
	public void logicUpdate() {
		if(InputUtility.getKeyTriggered(KeyEvent.VK_ESCAPE)||InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)){
			ConfigurableOption.PAUSE = true;
			return;
		}
		
		if(startStage){
			startStage = false;
			if(ConfigurableOption.stageNow == 0){
				this.openGatewayZero = new OpenGatewayZero();
				RenderableHolder.getInstance().addSouthEntity(openGatewayZero);
			}else if(ConfigurableOption.stageNow == 1){
				this.spacebarTab = new SpacebarTab();
				RenderableHolder.getInstance().addSouthEntity(spacebarTab);
			}
		}
		if(RenderableHolder.getInstance().getSouthRenderableList().contains(spacebarTab)){
			spacebarTab.setPlayerStatus(northScreenLogic.playerStatus);
		}
		
		if(ConfigurableOption.stageNow == 1 && RenderableHolder.getInstance().getSouthRenderableList().contains(openGatewayZero)){
			RenderableHolder.getInstance().getSouthRenderableList().remove(openGatewayZero.getGap());
			RenderableHolder.getInstance().getSouthRenderableList().remove(openGatewayZero.getRunningBall());
			RenderableHolder.getInstance().getSouthRenderableList().remove(openGatewayZero);
			openGatewayZero = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow == 2 && RenderableHolder.getInstance().getSouthRenderableList().contains(spacebarTab)){
			for(Coin coin : spacebarTab.getCoin()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(coin);
			}
			for(SpacebarGap gap : spacebarTab.getGap()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(gap);
			}
			RenderableHolder.getInstance().getSouthRenderableList().remove(spacebarTab.getRunningBall());
			RenderableHolder.getInstance().getSouthRenderableList().remove(spacebarTab);
		}
		
		if(ConfigurableOption.stageNow==0){
			openGatewayZero.update();
		}else if(ConfigurableOption.stageNow == 1 && spacebarTab != null){
			spacebarTab.update();
		}
	}

	public void setNorthScreenLogic(NorthScreenLogic northScreenLogic) {
		// TODO Auto-generated method stub
		this.northScreenLogic = northScreenLogic;
		//spacebarTab.setPlayerStatus(northScreenLogic.playerStatus);
	}
}
