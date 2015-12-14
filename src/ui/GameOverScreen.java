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
import utility.ConfigurableOption;

@SuppressWarnings("serial")
public class GameOverScreen extends JComponent{
	
	private BufferedImage[] restartButton = new BufferedImage[2];
	private AnimationManager BG;
	private BufferedImage img;
	private int width,height;

	public GameOverScreen(){
		super();
		BG = Resource.get("babadook");
		BG.loop();
		width = BG.getWidth();
		height = BG.getHeightByWidth(width);
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width, height));
		restartButton[0] = Resource.getImage("button-restart",0);
		restartButton[1] = Resource.getImage("button-restart",1);

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
				g2d,restartButton[1], 
				10, 10, 
				0, 67, 
				RenderHelper.TOP|RenderHelper.LEFT);
		
		BG.update();
	}

	private void drawStartBT(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position){
		RenderHelper.draw(
				null, 
				img, 
				x, y, 
				width, height, 
				position,
				new RenderHelperMouseEvent() {
					@Override
					public void mouseEntered(){
						RenderHelper.draw(g, restartButton[0], x, y, width, height, position);
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
						RenderHelper.draw(g, restartButton[1], x, y, width, height, position);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
		});
		
	}

}
