package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Main.ScreenManager;
import render.AnimationManager;
import render.RenderHelper;
import render.RenderHelperMouseEvent;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.Resource;

@SuppressWarnings("serial")
public class IntroScreen extends JComponent{
	
	private BufferedImage[] playButton = new BufferedImage[2];
	private BufferedImage[] continueButton = new BufferedImage[2];
	private BufferedImage[] exitButton = new BufferedImage[2];
	private AnimationManager introBG;
	private BufferedImage img;
	private int width,height;

	public IntroScreen(){
		super();
		introBG = Resource.get("batman-intro");
		introBG.loop();
		width = introBG.getWidth();
		height = ConfigurableOption.screenHeight;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width, height));
		playButton[0] = Resource.getImage("button",0);
		playButton[1] = Resource.getImage("button",1);
		continueButton[0] = Resource.getImage("button",2);
		continueButton[1] = Resource.getImage("button",3);
		exitButton[0] = Resource.getImage("button",4);
		exitButton[1] = Resource.getImage("button",5);

		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		setVisible(true);
//				ScreenManager.resetScreen();
//				ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
//				System.exit(0);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderHelper.addAntiAlising(g2d);
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, width, height);
		img = introBG.getCurrentBufferedImage();
		RenderHelper.draw( 
				g2d, img, 
				width/2, 0, 
				0, height, 
				RenderHelper.TOP|RenderHelper.CENTER);
		drawStartBT(
				g2d,playButton[1], 
				110, 450, 
				150, 0, 
				RenderHelper.TOP|RenderHelper.CENTER);
		
		
		introBG.update();
	}

	private void drawStartBT(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position){
		RenderHelper.draw(
				null, 
				img, 
				x, y, 
				width, height, 
				RenderHelper.TOP|RenderHelper.CENTER,
				new RenderHelperMouseEvent() {
					@Override
					public void mouseEntered(){
						RenderHelper.draw(g, playButton[0], x, y, width, height, position);
					}

					@Override
					public void mouseClicked() {
						ScreenManager.resetScreen();
						ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
					}

					@Override
					public void mousePressed() {
						
					}

					@Override
					public void mouseReleased() {
						
					}

					@Override
					public void mouseExited() {
						RenderHelper.draw(g, playButton[1], x, y, width, height, position);
					}
		});
		
	}

}
