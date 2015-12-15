package LogicGame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Main.ScreenManager;
import entity.AlphabetBox;
import entity.Coin;
import entity.SpacebarGap;
import entity.Wire;
import minigame.OpenGatewayZero;
import minigame.Passcode;
import minigame.WireCut;
import minigame.GetCoin;
import render.IRenderable;
import render.RenderableHolder;
import resource.Resource;
import utility.ConfigurableOption;
import utility.Debugger;
import utility.InputUtility;

public class SouthScreenLogic implements Logic{
	protected OpenGatewayZero openGatewayZero;
	protected GetCoin getCoin;
	protected Passcode passcode;
	protected WireCut wireCut;
	private NorthScreenLogic northScreenLogic;
	private boolean startStage;
	private List<IRenderable> list;
	
	public SouthScreenLogic(){
		this.startStage = true;
		list = RenderableHolder.getInstance().getSouthRenderableList();
	}
	
	public void logicUpdate() {
		if(InputUtility.getKeyTriggered(KeyEvent.VK_ESCAPE)){
			ConfigurableOption.PAUSE = true;
			ScreenManager.changeScreen(ScreenManager.PAUSESCREEN);
			return;
		}
		
		if(startStage){
			startStage = false;
			if(ConfigurableOption.stageNow == 0){
//				Resource.getAudio("startgame").play();
				this.openGatewayZero = new OpenGatewayZero();
				RenderableHolder.getInstance().addSouthEntity(openGatewayZero);
			}else if(ConfigurableOption.stageNow == 1){
				Resource.getAudio("dooropen").play();
				this.getCoin = new GetCoin();
				getCoin.setPlayerStatus(northScreenLogic.playerStatus);
				RenderableHolder.getInstance().addSouthEntity(getCoin);
			}else if(ConfigurableOption.stageNow == 2){
				Resource.getAudio("dooropen").play();
				this.wireCut = new WireCut();
				RenderableHolder.getInstance().addSouthEntity(wireCut);
			}else if(ConfigurableOption.stageNow ==3){
				Resource.getAudio("dooropen").play();
				this.passcode = new Passcode();
				RenderableHolder.getInstance().addSouthEntity(passcode);
			}else if(ConfigurableOption.stageNow ==4){
				Resource.getAudio("dooropen").play();
			}
		}
		
		if(ConfigurableOption.stageNow == 1 && list.contains(openGatewayZero)){
			list.remove(openGatewayZero.getGap());
			list.remove(openGatewayZero.getRunningBall());
			list.remove(openGatewayZero);
			openGatewayZero = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow == 2 && list.contains(getCoin)){
			for(Coin coin : getCoin.getCoin()){
				list.remove(coin);
			}
			for(SpacebarGap gap : getCoin.getGap()){
				list.remove(gap);
			}
			list.remove(getCoin.getRunningBall());
			list.remove(getCoin);
			getCoin = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow == 3 && list.contains(wireCut)){
			for(SpacebarGap gap : wireCut.getGap()){
				list.remove(gap);
			}
			for(Wire wire : wireCut.getWire()){
				list.remove(wire);
			}
			list.remove(wireCut.getRunningBall());
			list.remove(wireCut);
			wireCut = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow == 4 && list.contains(passcode)){
			for(AlphabetBox keyBox : passcode.getKeyBox()){
				list.remove(keyBox);
			}
			for(AlphabetBox password : passcode.getPassword()){
				list.remove(password);
			}
			list.remove(passcode);
			passcode = null;
			startStage = true;
		}else if(ConfigurableOption.stageNow >= ConfigurableOption.ENDSTAGE){
			list.clear();
			
			openGatewayZero = null;
			getCoin = null;
			wireCut = null;
			passcode = null;
		}
		
		if(ConfigurableOption.stageNow==0 && openGatewayZero != null){
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
		this.northScreenLogic = northScreenLogic;
	}
}
