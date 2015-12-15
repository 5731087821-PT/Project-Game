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
public class PauseScreen extends JComponent{
	
	private BufferedImage[] continueButton = new BufferedImage[2];
	private BufferedImage[] exitButton = new BufferedImage[2];
	private AnimationManager BG;
	private BufferedImage img;
	private int width,height;

	public PauseScreen(){
		super();
		BG = Resource.get("batman-intro");
		BG.loop();
		width = BG.getWidth();
		height = ConfigurableOption.screenHeight;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width, height));

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
		drawExitBT(
				g2d,exitButton[1], 
				25, 410, 
				0, 70, 
				RenderHelper.LEFT|RenderHelper.MIDDLE);
		
		drawContinueBT(
				g2d,continueButton[1], 
				25, 495, 
				0, 80, 
				RenderHelper.LEFT|RenderHelper.MIDDLE);
		BG.update();
	}

	private void drawContinueBT(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position){
		RenderHelper.draw(
				null, 
				img, 
				x, y, 
				width, height, 
				position,
				new RenderHelperMouseEvent() {
					@Override
					public void mouseEntered(){
						Resource.getAudio("punch").play();
						RenderHelper.draw(g, continueButton[0], x, y, width, height, position);
					}

					@Override
					public void mouseClicked() {
						ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
					}

					@Override
					public void mousePressed() {}

					@Override
					public void mouseReleased() {}

					@Override
					public void mouseExited() {
						RenderHelper.draw(g, continueButton[1], x, y, width, height, position);
					}
		});
	}
	private void drawReStartBT(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position){
		RenderHelper.draw(
				null, 
				img, 
				x, y, 
				width, height, 
				position,
				new RenderHelperMouseEvent() {
					@Override
					public void mouseEntered(){
						Resource.getAudio("punch").play();
						RenderHelper.draw(g, Resource.restartButton[0], x, y, width, height, position);
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
						RenderHelper.draw(g, Resource.restartButton[1], x, y, width, height, position);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
		});
	}

	private void drawExitBT(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position){
		RenderHelper.draw(
				null, 
				img, 
				x, y, 
				width, height, 
				position,
				new RenderHelperMouseEvent() {
					@Override
					public void mouseEntered(){
						RenderHelper.draw(g, exitButton[0], x, y, width, height, position);
					}

					@Override
					public void mouseClicked() {
						System.exit(0);
						System.out.println("OK");
					}

					@Override
					public void mousePressed() {}

					@Override
					public void mouseReleased() {}

					@Override
					public void mouseExited() {
						RenderHelper.draw(g, exitButton[1], x, y, width, height, position);
					}
		});
	}
}
