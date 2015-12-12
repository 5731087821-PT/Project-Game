package LogicGame;

import java.util.*;
import java.util.function.Predicate;

import render.RenderableHolder;
import utility.ConfigurableOption;

public class SouthScreenLogic implements Logic{
	protected MiniGameSpacebarTab spacebarTab;
	
	public SouthScreenLogic(){
		this.spacebarTab = new MiniGameSpacebarTab();
		
		RenderableHolder.getInstance().addSouthEntity(spacebarTab);
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		spacebarTab.update();
	}
}
