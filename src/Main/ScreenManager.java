package Main;

import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
		
		northScreenLogic.setSouthScreenLogic(southScreenLogic);
		southScreenLogic.setNorthScreenLogic(northScreenLogic);
	}
	
	public ScreenManager(){
		new Resource();		
		introScreen = new IntroScreen();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Resource.getImage("icon"));
		frame.setTitle("Zombie Escape");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addListener(panel);
		
		bgm = Resource.getAudio("gamebgm");
		changeScreen(INTROSCREEN);

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		while(true){
			try{
				Thread.sleep(ConfigurableOption.sleepTime);
			}catch (InterruptedException e){}

			for(JComponent component:currentScreen)
				component.repaint();
			
			if(!ConfigurableOption.PAUSE)
				for(Logic component:currentLogic)
					component.logicUpdate();
			
			InputUtility.postUpdate();
			panel.requestFocus();
		}
	}

	public void addListener(JPanel panel){
		panel.addKeyListener(new KeyListener() {
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
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				InputUtility.setMouseLeftDown(false);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				InputUtility.setMouseLeftDown(true);
				InputUtility.setMouseLeftTriggered(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		panel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				InputUtility.setMouseX(e.getX());
				InputUtility.setMouseY(e.getY());
				if(ConfigurableOption.debugGraphic)
					System.out.println("MouseX:"+e.getX()+" "+"MouseY:"+e.getY());
			}
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		panel.setFocusable(true);
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
