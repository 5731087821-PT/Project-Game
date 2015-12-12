package ui;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import LogicGame.Logic;
import LogicGame.NorthScreenLogic;
import LogicGame.SouthScreenLogic;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.Resource;;

@SuppressWarnings("serial")
public class ScreenManager extends JFrame{
	
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int GAMESCREEN = 3;
	public static final int ATTACKSCREEN = 4;
	public static final int WINNINGSCREEN = 5;

	private AudioClip bgm;
	
	private NorthScreen northScreen;
	private SouthScreen southScreen;
	private NorthScreenLogic northScreenLogic;
	private SouthScreenLogic southScreenLogic;
	
	private JPanel panel;
	
	private static ArrayList<JComponent> currentScreen = new ArrayList<>();
	private static ArrayList<Logic> currentLogic = new ArrayList<>();
	
	public ScreenManager(){
		
		new Resource();		
		northScreen = new NorthScreen();
		southScreen = new SouthScreen();
		northScreenLogic = new NorthScreenLogic();
		southScreenLogic = new SouthScreenLogic();

		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight));
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));southScreenLogic = new SouthScreenLogic();
		
		northScreenLogic.setSouthScreenLogic(southScreenLogic);
		southScreenLogic.setNorthScreenLogic(northScreenLogic);
		
		
		bgm = Resource.getAudio("gamebgm");
		changeScreen(GAMESCREEN);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addListener(this);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		
		while(true){
			try{
				Thread.sleep(ConfigurableOption.sleepTime);
			}catch (InterruptedException e){}
			

			for(JComponent part:currentScreen){
				part.repaint();
			}
			
			for(Logic part:currentLogic){
				part.logicUpdate();
			}
			
			InputUtility.postUpdate();
			
			this.requestFocus();
		}
	}
	

	public void addListener(JFrame frame){
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(!InputUtility.getKeyPressed(e.getKeyCode()) || InputUtility.getKeyTriggered(e.getKeyCode())){
					InputUtility.setKeyPressed(e.getKeyCode(), true);
					InputUtility.setKeyTriggered(e.getKeyCode(), true);
				}
			}
		});
		frame.setFocusable(true);
	}
	
	public void changeScreen(int screen){
		
		bgm.stop();
		
		for(JComponent component:currentScreen){
			panel.remove(component);
		}
		currentScreen.clear();
		currentLogic.clear();
		
		System.out.print("Change screen to ");
		switch (screen) {
			case GAMESCREEN:{
				System.out.println("Game Screen");
				currentScreen.add(northScreen);
				currentScreen.add(southScreen);
				currentLogic.add(northScreenLogic);
				currentLogic.add(southScreenLogic);
				this.add(northScreen, BorderLayout.NORTH);
				this.add(southScreen, BorderLayout.SOUTH);
				bgm = Resource.getAudio("gamebgm");
				bgm.play();
				break;
			}case INTROSCREEN:{
			
				break;
			}default:{
				throw new RuntimeException("Error Screen not found : "+screen);
			}
		}

		for(JComponent comp : currentScreen){
			panel.add(comp);
		}
		this.add(panel);
		this.pack();
		this.setResizable(false);
		
	}
}
