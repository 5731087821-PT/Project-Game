package LogicGame;

import java.util.*;

import Minigame.MiniGameSpacebarTab;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class SouthScreenLogic implements Logic{
	protected MiniGameSpacebarTab miniGameSpacebarTab;
	private NorthScreenLogic northScreenLogic;
	
	public SouthScreenLogic(){
		this.miniGameSpacebarTab = new MiniGameSpacebarTab();
		
		RenderableHolder.getInstance().addSouthEntity(miniGameSpacebarTab);
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		miniGameSpacebarTab.update();
	}

	public void setNorthScreenLogic(NorthScreenLogic northScreenLogic) {
		// TODO Auto-generated method stub
		this.northScreenLogic = northScreenLogic;
		miniGameSpacebarTab.setPlayerStatus(northScreenLogic.playerStatus);
	}
}
