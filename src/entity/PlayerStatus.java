package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import utility.ConfigurableOption;
import utility.Resource;

public class PlayerStatus implements IRenderable{
	private int coins;
	private int combo;
	
	public PlayerStatus(){
		this.coins = 0;
		this.combo = 1;
	}
	
	public int getCoin(){
		return this.coins;
	}

	public int getCombo(){
		return this.combo;
	}

	public void setCoin(int coin){
		this.coins = coin;
	}
	
	public void addScore(int coin){
		this.coins += coin;
	}
	
	public void comboInterrupted(){
		this.combo = 1;
	}
	
	public void addCombo(int combo){
		this.combo += combo;
	}
	
	public void subtractionScore(int coin){
		this.coins -= coin;
		if(coins<0) coins = 0;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(0, 0, ConfigurableOption.screenWidth, 40);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Resource.standardFont);
		g2d.drawString("COIN: "+this.coins, 10, 32);
		g2d.drawString("COMBO: "+this.combo, 200, 32);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}