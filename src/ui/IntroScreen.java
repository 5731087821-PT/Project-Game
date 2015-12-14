package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import Main.ScreenManager;
import render.AnimationManager;
import render.RenderHelper;
import render.RenderHelperMouseEvent;
import resource.Resource;
import score.HighScoreUtility;
import utility.ConfigurableOption;

@SuppressWarnings("serial")
public class IntroScreen extends JComponent{
	
	private BufferedImage[] playButton = new BufferedImage[2];
	private BufferedImage[] rankButton = new BufferedImage[2];
	private AnimationManager BG;
	private BufferedImage img;
	private int width,height;

	public IntroScreen(){
		super();
		BG = Resource.get("batman-intro");
		BG.loop();
		width = BG.getWidth();
//		height = BG.getHeightByWidth(width) + 60;
		height = 560;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width, height));
		playButton[0] = Resource.getImage("button-start",0);
		playButton[1] = Resource.getImage("button-start",1);
		playButton[0] = Resource.getImage("button1",0);
		playButton[1] = Resource.getImage("button1",1);
		rankButton[0] = Resource.getImage("button1",4);
		rankButton[1] = Resource.getImage("button1",5);

		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		setVisible(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderHelper.addAntiAlising(g2d);
		img = BG.getCurrentBufferedImage();
		RenderHelper.draw( 
				g2d, img, 
				width/2, 0, 
				0, height, 
				RenderHelper.TOP|RenderHelper.CENTER);
		drawStartBT(
				g2d, 
				35, height-32, 
				0, 80, 
				RenderHelper.BOTTOM|RenderHelper.LEFT);
//		drawRankBT(
//				g2d, 
//				110, 400, 
//				150, 0, 
//				RenderHelper.TOP|RenderHelper.CENTER);
		
		BG.update();
	}

	private void drawStartBT(Graphics2D g,int x, int y, int width, int height, int position){
		RenderHelper.draw(
				null, 
				playButton[1], 
				x, y, 
				width, height, 
				position,
				new RenderHelperMouseEvent() {
					@Override
					public void mouseEntered(){
						RenderHelper.draw(g, playButton[0], x, y, width, height, position);
						setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseClicked() {
						ScreenManager.resetScreen();
						ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
					}

					@Override
					public void mousePressed() {}

					@Override
					public void mouseReleased() {}

					@Override
					public void mouseExited() {
						RenderHelper.draw(g, playButton[1], x, y, width, height, position);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
		});
		
	}

	private void drawRankBT(Graphics2D g, int x, int y, int width, int height, int position){
		RenderHelper.draw(
				null, 
				rankButton[0], 
				x, y, 
				width, height, 
				position,
				new RenderHelperMouseEvent() {
					@Override
					public void mouseEntered(){
						RenderHelper.draw(g, rankButton[0], x, y, width, height, position);
						setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseClicked() {
						HighScoreUtility.displayTop10();
					}

					@Override
					public void mousePressed() {}

					@Override
					public void mouseReleased() {}

					@Override
					public void mouseExited() {
						RenderHelper.draw(g, rankButton[1], x, y, width, height, position);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
		});
		
	}
}
