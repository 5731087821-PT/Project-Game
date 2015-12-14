package minigame;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import LogicGame.NorthScreenLogic;
import entity.AlphabetBox;
import entity.Gateway;
import entity.Player;
import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;

public class Passcode implements IRenderable {
	protected ArrayList<AlphabetBox> keyBoxs;
	protected ArrayList<AlphabetBox> passwords;
	protected ArrayList<Integer> randPrimaryKey;
	protected int width,height;
	protected int passwordCounter;
	
	public Passcode(){
		this.keyBoxs = new ArrayList<AlphabetBox>();
		this.passwords = new ArrayList<AlphabetBox>();
		this.randPrimaryKey = new ArrayList<Integer>();
		this.width = this.height = 50;
		this.passwordCounter = 0;
		
		passwords.add(new AlphabetBox(5*12, (ConfigurableOption.screenWidth/2) - 30*2, 30, 25, 25));
		passwords.add(new AlphabetBox(10*12, (ConfigurableOption.screenWidth/2) - 30, 30, 25, 25));
		passwords.add(new AlphabetBox(15*12, (ConfigurableOption.screenWidth/2), 30, 25, 25));
		passwords.add(new AlphabetBox(20*12, (ConfigurableOption.screenWidth/2) + 30, 30, 25, 25));
		
		for(int i = 1 ; i<=20 ; i++){
			randPrimaryKey.add(i);
		}
		Collections.shuffle(randPrimaryKey);
		
		for(int i = 0, c = 0 ; i < 5 ; i++){
			for(int j = 0 ; j < 4 ; j++){
				keyBoxs.add(new AlphabetBox(randPrimaryKey.get(c++)*12, (ConfigurableOption.screenWidth/2)-(int)(2.5*width)+(i*width), 100+j*height, width, height));
			}
		}
		
		for(AlphabetBox keyBox : keyBoxs){
			RenderableHolder.getInstance().addSouthEntity(keyBox);
		}
		
		for(AlphabetBox password : passwords){
			RenderableHolder.getInstance().addSouthEntity(password);
		}
	}
	

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isInPressAreaX(){
		return  ( ( (InputUtility.getMouseXSouth() - ((ConfigurableOption.screenWidth/2)-(int)(2.5*width)) ) >= 0 )
				&& ( ( (ConfigurableOption.screenWidth/2)-(int)(2.5*width) + (5*width) ) - InputUtility.getMouseXSouth()  >=0 ) );
	}
	
	public boolean isInPressAreaY(){
		return (InputUtility.getMouseYSouth() - 100 >=0 ) && (100+height*4 - InputUtility.getMouseYSouth() >=0);
	}
	
	public boolean isInPressBoxAreaX(AlphabetBox keyBox){
		return InputUtility.getMouseXSouth() - keyBox.getX() >=0 && keyBox.getX()+keyBox.getWidth() - InputUtility.getMouseXSouth() >=0;
	}
	
	public boolean isInPressBoxAreaY(AlphabetBox keyBox){
		return InputUtility.getMouseYSouth() - keyBox.getY() >=0 && keyBox.getY()+keyBox.getHeight() - InputUtility.getMouseYSouth() >=0;
	}
	
	public void zombieAppear(){
		NorthScreenLogic.spawnZombie = true;
		
		for(IRenderable rend : RenderableHolder.getInstance().getNorthRenderableList()){
			if(rend instanceof Player){
				((Player) rend).zombieIsComming();
				break;
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(!isInPressAreaX() || !isInPressAreaY()) return;
			
		if(passwordCounter<3){
			for(AlphabetBox keyBox : keyBoxs){
				if(isInPressBoxAreaX(keyBox) && isInPressBoxAreaY(keyBox)){
					if(keyBox.getPrimaryKey() == passwords.get(passwordCounter).getPrimaryKey()){
						passwords.get(passwordCounter).setSelected(true);
						keyBox.setSelected(true);
						passwordCounter++;
						break;
					}else{
						zombieAppear();
					}
				}
			}
		}else{
			//Finish Stage 2
			for (IRenderable renderable : RenderableHolder.getInstance().getNorthRenderableList()) {
				if (renderable instanceof Gateway && ((Gateway) renderable).getX() == ConfigurableOption.xGateway2) {
					((Gateway) renderable).setGateClose(false);
					ConfigurableOption.stageNow = 4;
					break;
				}
			}
		}
			
		
	}
	
	public ArrayList<AlphabetBox> getKeyBox(){
		return keyBoxs;
	}
	
	public ArrayList<AlphabetBox> getPassword(){
		return passwords;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		
	}
	
}
