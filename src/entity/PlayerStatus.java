package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import utility.Resource;

public class PlayerStatus implements IRenderable {
	private int score;
	
	public PlayerStatus() {
		this.score = 0;
	}
	
	public void addScore(int s) {
		this.score += s;
	}
	
	public void substractionScore(int s) {
		this.score -= s;
		if(this.score < 0) this.score = 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 420, 640, 60); 
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(Resource.standardFont);
		g2d.drawString("SCORE: " + score, 10, 460);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 3;
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

