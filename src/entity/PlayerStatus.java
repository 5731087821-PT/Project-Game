package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import resource.Resource;
import utility.ConfigurableOption;

public class PlayerStatus implements IRenderable{
	private int combo;
	
	public PlayerStatus(){
		this.combo = 1;
	}
	

	public int getCombo(){
		return this.combo;
	}

	
	public void addScore(int coin){
		ConfigurableOption.coins += coin;
	}
	
	public void comboInterrupted(){
		this.combo = 1;
	}
	
	public void addCombo(int combo){
		this.combo += combo;
	}
	
	public void subtractionScore(int coin){
		ConfigurableOption.coins -= coin;
		if(ConfigurableOption.coins<0) ConfigurableOption.coins = 0;
	}
	
	@Override
	public int getZ() {
		return 1000;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(0, 0, ConfigurableOption.screenWidth, ConfigurableOption.statusHeight);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Resource.standardFont);
		g2d.drawString("COIN: "+ConfigurableOption.coins, 10, 32);
		g2d.drawString("COMBO: "+this.combo, 200, 32);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void update() {
		
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
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public void setDestroyed(boolean destroyed) {
		
	}

	@Override
	public void updateAnimation() {
		
	}
	
}