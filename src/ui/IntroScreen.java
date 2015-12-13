package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Main.ScreenManager;
import render.AnimationManager;
import render.RenderHelper;
import utility.ConfigurableOption;
import utility.Resource;

public class IntroScreen extends JComponent{
	
	private static final boolean CONTINUECHOICE = true;
	private static final boolean NEWPLAYCHOICE = false;
	private JButton playButton;
	private JButton continueButton;
	private JButton exitButton;
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
		setVisible(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(false);
		playButton = new JButton("PLAYNEW");
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenManager.resetScreen();
				ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
			}
		});
		continueButton = new JButton("CONTINUE");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
			}
		});
		
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(playButton);
		add(continueButton);
		add(exitButton);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		img = introBG.getCurrentBufferedImage();
		RenderHelper.draw( (Graphics2D)g, img, width/2, 0, 0, height, RenderHelper.TOP|RenderHelper.CENTER);
		introBG.update();
	}
	
	private void showDialog(boolean mode){
		
	}

}
