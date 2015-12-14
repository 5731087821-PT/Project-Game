package ui;

import java.awt.Color;
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
import utility.ConfigurableOption;
import utility.Resource;

@SuppressWarnings("serial")
public class PauseScreen extends JComponent{
	
	private BufferedImage[] continueButton = new BufferedImage[2];
	private BufferedImage[] exitButton = new BufferedImage[2];
	private AnimationManager introBG;
	private BufferedImage img;
	private int width,height;

	public PauseScreen(){
		super();
		introBG = Resource.get("batman-intro");
		introBG.loop();
		width = introBG.getWidth();
		height = ConfigurableOption.screenHeight;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width, height));
		continueButton[0] = Resource.getImage("button2",0);
		continueButton[1] = Resource.getImage("button2",1);
		exitButton[0] = Resource.getImage("button1",2);
		exitButton[1] = Resource.getImage("button1",3);

		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		setVisible(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderHelper.addAntiAlising(g2d);
		img = introBG.getCurrentBufferedImage();
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
		introBG.update();
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
						RenderHelper.draw(g, continueButton[0], x, y, width, height, position);
					}

					@Override
					public void mouseClicked() {
						ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
					}

					@Override
					public void mousePressed() {}

					@Override
					public void mouseReleased() {
						
					}

					@Override
					public void mouseExited() {
						RenderHelper.draw(g, continueButton[1], x, y, width, height, position);
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
					public void mouseReleased() {
						
					}

					@Override
					public void mouseExited() {
						RenderHelper.draw(g, exitButton[1], x, y, width, height, position);
					}
		});
	}
}
