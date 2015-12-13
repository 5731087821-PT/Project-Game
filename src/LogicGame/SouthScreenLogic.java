package LogicGame;

import java.awt.event.KeyEvent;

import entity.AlphabetBox;
import entity.Coin;
import entity.SpacebarGap;
import entity.Wire;
import minigame.OpenGatewayZero;
import minigame.Passcode;
import minigame.WireCut;
import minigame.GetCoin;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;

public class SouthScreenLogic implements Logic{
	protected OpenGatewayZero openGatewayZero;
	protected GetCoin getCoin;
	protected Passcode passcode;
	protected WireCut wireCut;
	private NorthScreenLogic northScreenLogic;
	private boolean startStage;
	
	public SouthScreenLogic(){
		this.startStage = true;
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		if(RenderableHolder.getInstance().getSouthRenderableList().contains(getCoin)){
			getCoin.setPlayerStatus(northScreenLogic.playerStatus);
		}
		
		if(InputUtility.getKeyTriggered(KeyEvent.VK_ESCAPE)){
			ConfigurableOption.PAUSE = true;
			return;
		}
		
		if(startStage){
			startStage = false;
			if(ConfigurableOption.stageNow == 0){
				this.openGatewayZero = new OpenGatewayZero();
				RenderableHolder.getInstance().addSouthEntity(openGatewayZero);
			}else if(ConfigurableOption.stageNow == 1){
				this.getCoin = new GetCoin();
				RenderableHolder.getInstance().addSouthEntity(getCoin);
			}else if(ConfigurableOption.stageNow == 2){
				this.wireCut = new WireCut();
				RenderableHolder.getInstance().addSouthEntity(wireCut);
			}else if(ConfigurableOption.stageNow ==3){
				this.passcode = new Passcode();
				RenderableHolder.getInstance().addSouthEntity(passcode);
			}
		}
		
		if(ConfigurableOption.stageNow == 1 && RenderableHolder.getInstance().getSouthRenderableList().contains(openGatewayZero)){
			RenderableHolder.getInstance().getSouthRenderableList().remove(openGatewayZero.getGap());
			RenderableHolder.getInstance().getSouthRenderableList().remove(openGatewayZero.getRunningBall());
			RenderableHolder.getInstance().getSouthRenderableList().remove(openGatewayZero);
			openGatewayZero = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow == 2 && RenderableHolder.getInstance().getSouthRenderableList().contains(getCoin)){
			for(Coin coin : getCoin.getCoin()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(coin);
			}
			for(SpacebarGap gap : getCoin.getGap()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(gap);
			}
			RenderableHolder.getInstance().getSouthRenderableList().remove(getCoin.getRunningBall());
			RenderableHolder.getInstance().getSouthRenderableList().remove(getCoin);
			getCoin = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow == 3 && RenderableHolder.getInstance().getSouthRenderableList().contains(wireCut)){
			for(SpacebarGap gap : wireCut.getGap()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(gap);
			}
			for(Wire wire : wireCut.getWire()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(wire);
			}
			RenderableHolder.getInstance().getSouthRenderableList().remove(wireCut.getRunningBall());
			RenderableHolder.getInstance().getSouthRenderableList().remove(wireCut);
			wireCut = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow == 4 && RenderableHolder.getInstance().getSouthRenderableList().contains(passcode)){
			for(AlphabetBox keyBox : passcode.getKeyBox()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(keyBox);
			}
			for(AlphabetBox password : passcode.getPassword()){
				RenderableHolder.getInstance().getSouthRenderableList().remove(password);
			}
			RenderableHolder.getInstance().getSouthRenderableList().remove(passcode);
			passcode = null;
			startStage = true;
		}
		
		if(ConfigurableOption.stageNow==0){
			openGatewayZero.update();
		}else if(ConfigurableOption.stageNow == 1 && getCoin != null){
			getCoin.update();
		}else if(ConfigurableOption.stageNow == 2 && wireCut != null){
			wireCut.update();
		}else if(ConfigurableOption.stageNow == 3 && passcode != null && InputUtility.isMouseLeftClicked()){
			passcode.update();
		}
	}

	public void setNorthScreenLogic(NorthScreenLogic northScreenLogic) {
		// TODO Auto-generated method stub
		this.northScreenLogic = northScreenLogic;
//		spacebarTab.setPlayerStatus(northScreenLogic.playerStatus);
	}
}
