package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import render.IRenderable;
import render.RenderHelper;
import resource.Resource;
import utility.Debugger;
import utility.TimeToCounter;

public class AlphabetBox implements IRenderable {
	public static int NOTTHING = 0;
	public static int CORRECT = 1;
	public static int INCORECT = -1;
	protected int primaryKey;
	protected int x, y;
	protected int width,height;
	protected int selected;
	protected boolean destroyed;
	private BufferedImage checkMark;
	private int boxReactionCounter;
	
	public AlphabetBox(int primaryKey, int x, int y, int width, int height){
		this.primaryKey = primaryKey;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.selected = NOTTHING;
		this.destroyed = false;
		this.checkMark = Resource.getImage("checkmark");
		this.boxReactionCounter = 0;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
		if(selected == NOTTHING){
			drawDefaultBox(g2d);
		}else if(selected == INCORECT){
			if(boxReactionCounter++ < TimeToCounter.getCounter(200)){
				drawIncorrectBox(g2d);
			}else{
				this.selected = NOTTHING;
				boxReactionCounter = 0;
				drawDefaultBox(g2d);
			}
		}else if(selected == CORRECT){
			if(boxReactionCounter++ < TimeToCounter.getCounter(500)){
				drawCorrectingBox(g2d);
			}else{
				drawCorrectedBox(g2d);
				RenderHelper.draw(g2d, checkMark, x+2, y+2, width-3, height-3, RenderHelper.LEFT|RenderHelper.TOP);
			}
		}
	}

	private void drawCorrectedBox(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x+1, y+1, width-2, height-2);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(x, y, width, height);
	}
	private void drawCorrectingBox(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(x-1, y-1, width, height-2);
	}

	private void drawIncorrectBox(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(x-1, y-1, width, height-2);
	}

	public void drawDefaultBox(Graphics2D g2d){
		g2d.setColor(new Color(primaryKey, primaryKey, primaryKey));
		g2d.fillRect(x+1, y+1, width, height-2);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(x, y, width, height);
	}
	
	public void setSelected(int selected){
		this.selected = selected;
	}
	
	public int isSelected(){
		return selected;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public void setDestroying(boolean destroyed) {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getZ() {
		return 0;
	}
	
	@Override
	public void updateAnimation(){
		
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getPrimaryKey(){
		return this.primaryKey;
	}

}
