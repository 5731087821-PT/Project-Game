package Main;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import LogicGame.Logic;
import LogicGame.NorthScreenLogic;
import LogicGame.SouthScreenLogic;
import render.RenderableHolder;
import ui.IntroScreen;
import ui.NorthScreen;
import ui.SouthScreen;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.Resource;;

@SuppressWarnings("serial")
public class ScreenManager{
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int GAMESCREEN = 3;
	public static final int ATTACKSCREEN = 4;
	public static final int WINNINGSCREEN = 5;

	private static AudioClip bgm;
	
	private static NorthScreen northScreen;
	private static SouthScreen southScreen;
	private static NorthScreenLogic northScreenLogic;
	private static SouthScreenLogic southScreenLogic;
	private static IntroScreen introScreen;
	private static JFrame frame;
	
	private static JPanel panel;
	
	private static ArrayList<JComponent> currentScreen = new ArrayList<>();
	private static ArrayList<Logic> currentLogic = new ArrayList<>();
	
	public static void resetScreen(){
		RenderableHolder.getInstance().clear();
		
		northScreen = new NorthScreen();
		southScreen = new SouthScreen();
		northScreenLogic = new NorthScreenLogic();
		southScreenLogic = new SouthScreenLogic();
		introScreen = new IntroScreen();
		
		northScreenLogic.setSouthScreenLogic(southScreenLogic);
		southScreenLogic.setNorthScreenLogic(northScreenLogic);
	}
	
	public ScreenManager(){
		new Resource();		
		resetScreen();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		addListener(frame);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight));
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		bgm = Resource.getAudio("gamebgm");
		changeScreen(INTROSCREEN);

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		while(true){
			try{
				Thread.sleep(ConfigurableOption.sleepTime);
			}catch (InterruptedException e){}

			for(JComponent component:currentScreen){
				component.repaint();
			}
			if(!ConfigurableOption.PAUSE)
				for(Logic component:currentLogic){
					component.logicUpdate();
				}
			InputUtility.postUpdate();
			frame.requestFocus();
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
		
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				InputUtility.setMouseLeftDown(false);
				InputUtility.setMouseLeftTriggered(false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				InputUtility.setMouseLeftTriggered(true);
				InputUtility.setMouseLeftDown(true);
				InputUtility.setMouseX(e.getX());
				InputUtility.setMouseY(e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		frame.setFocusable(true);
	}
	
	public static void changeScreen(int screen){
		bgm.stop();
		panel.removeAll();
		currentScreen.clear();
		currentLogic.clear();
		System.out.print("Change screen to ");
		switch (screen) {
			case INTROSCREEN:{
				System.out.println("Intro Screen");
				currentScreen.add(introScreen);
				bgm = Resource.getAudio("intro");
				bgm.play();
				break;
			}case GAMESCREEN:{
				System.out.println("Game Screen");
				currentScreen.add(northScreen);
				currentScreen.add(southScreen);
				currentLogic.add(northScreenLogic);
				currentLogic.add(southScreenLogic);
				bgm = Resource.getAudio("gamebgm");
				bgm.play();
				break;
			}default:{
				throw new RuntimeException("Error Screen not found : "+screen);
			}
		}
		for(JComponent comp : currentScreen){
			panel.add(comp);
		}
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
}
