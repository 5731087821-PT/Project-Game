package ui;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;


import LogicGame.Logic;
import LogicGame.NorthScreenLogic;
import LogicGame.SouthScreenLogic;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.Resource;;

@SuppressWarnings("serial")
public class GameWindow extends JFrame{
	
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int GAMESCREEN = 3;
	public static final int ATTACKSCREEN = 4;
	public static final int WINNINGSCREEN = 5;

	private AudioClip bgm;
	
	private NorthScreen northScreen;
	private SouthScreen southScreen;
	private JPanel southPanelTester;
	private NorthScreenLogic northScreenLogic;
	private SouthScreenLogic southGameLogic;
	
	private static ArrayList<JComponent> currentScreen = new ArrayList<>();
	private static ArrayList<Logic> currentLogic = new ArrayList<>();
	
	public GameWindow(){
		new Resource();

		northScreen = new NorthScreen();
		southScreen = new SouthScreen();
		southPanelTester = new SouthPanelTester(southScreen);
		northScreenLogic = new NorthScreenLogic();
		southGameLogic = new SouthScreenLogic();
		
		this.setPreferredSize(new Dimension(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addListener(this);
		
		bgm = Resource.getAudio("gamebgm");
		changeScreen(GAMESCREEN);
		
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
			this.remove(component);
		}
		currentScreen.clear();
		currentLogic.clear();
		
		System.out.print("Change screen to ");
		switch (screen) {
			case GAMESCREEN:{
				System.out.println("Game Screen");
				currentScreen.add(northScreen);
				currentScreen.add(southPanelTester);
				currentLogic.add(northScreenLogic);
				currentLogic.add(southGameLogic);
				this.add(northScreen, BorderLayout.NORTH);
				this.add(southPanelTester, BorderLayout.CENTER);
				bgm = Resource.getAudio("gamebgm");
				bgm.play();
				break;
			}case INTROSCREEN:{
			
				break;
			}default:{
				throw new RuntimeException("Error Screen not found : "+screen);
			}
		}
		
		this.pack();
		
	}
}
