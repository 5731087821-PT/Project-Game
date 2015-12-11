package entity;

import java.util.*;
import java.util.function.Predicate;

import render.RenderableHolder;
import render.Resource;
import utility.ConfigurableOption;

public class MainGameLogic {
	protected SpacebarTab spacebarTab;
	
	public MainGameLogic(){
		this.spacebarTab = new SpacebarTab();
		
		RenderableHolder.getInstance().addSouthEntity(spacebarTab);
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		spacebarTab.update();
	}
}
