package Main;

import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import LogicGame.Logic;
import LogicGame.NorthScreenLogic;
import LogicGame.SouthScreenLogic;
import render.RenderableHolder;
import resource.Resource;
import sun.java2d.pipe.DrawImage;
import ui.*;
import utility.ConfigurableOption;
import utility.Debugger;
import utility.InputUtility;
import utility.TimeToCounter;;

public class ScreenManager{
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int GAMESCREEN = 3;
	public static final int ATTACKSCREEN = 4;
	public static final int GAMEOVERSCREEN = 5;
	public static final int WINNINGSCREEN = 6;
	public static final int PAUSESCREEN = 7;
	private static final boolean FADEIN = true;
	private static final boolean FADEOUT = false;
	
	public static Object locker1 = new Object();
	public static Object locker2 = new Object();
	public static boolean chagingScreen;

	private static AudioClip bgm;
	
	private static boolean initialize;
	
	private static NorthScreen northScreen;
	private static SouthScreen southScreen;
	private static NorthScreenLogic northScreenLogic;
	private static SouthScreenLogic southScreenLogic;
	private static IntroScreen introScreen;
	private static PauseScreen pauseScreen;
	private static GameOverScreen gameOverScreen;
	
	private static JFrame MainFrame;
	private static JPanel panelInsideFrame;
	
	private static ArrayList<JComponent> currentScreen = new ArrayList<>();
	private static ArrayList<Logic> currentLogic = new ArrayList<>();
	
	public static void resetScreen(){
		ConfigurableOption.PAUSE = false;
		
		RenderableHolder.getInstance().clear();
		
		northScreen = new NorthScreen();
		southScreen = new SouthScreen();
		pauseScreen = new PauseScreen();
		gameOverScreen = new GameOverScreen();
		
		northScreenLogic = new NorthScreenLogic();
		southScreenLogic = new SouthScreenLogic();
		northScreenLogic.setSouthScreenLogic(southScreenLogic);
		southScreenLogic.setNorthScreenLogic(northScreenLogic);
	}
	
	public ScreenManager(){
		initialize = true;
		chagingScreen = false;
		new Resource();		
		introScreen = new IntroScreen();
		MainFrame = new JFrame();
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.setIconImage(Resource.getImage("batman-icon"));
		MainFrame.setTitle("Zombie Escape");
		panelInsideFrame = new JPanel();
		panelInsideFrame.setLayout(new BoxLayout(panelInsideFrame, BoxLayout.Y_AXIS));
		MainFrame.add(panelInsideFrame);
		
		addListener(panelInsideFrame);
		
		
		if(ConfigurableOption.STARTSCREEN != INTROSCREEN)
			resetScreen();

		bgm = Resource.getAudio("gamebgm");
		changeScreen(ConfigurableOption.STARTSCREEN);
		
		initialize = false;
		MainFrame.pack();
		MainFrame.setResizable(false);
		MainFrame.setVisible(true);
		while(true){
			try{
				Thread.sleep(ConfigurableOption.sleepTime);
			}catch (InterruptedException e){}

			for(JComponent component:currentScreen){
				component.repaint();
			}
			
			for(Logic component:currentLogic){
				component.logicUpdate();
				if(chagingScreen) {
					chagingScreen = false;
					break;
				}
			}
			
			InputUtility.postUpdate();
			panelInsideFrame.requestFocus();
		}
	}
	
	public static void changeScreen(int screen){
//		if(!initialize)
//			fadeScreen(panelInsideFrame, FADEOUT);
		
		bgm.stop();
		panelInsideFrame.removeAll();
		currentScreen.clear();
		currentLogic.clear();
		System.out.print("Change screen to ");
		switch (screen) {
			case INTROSCREEN:{
				System.out.println("Intro Screen");
				currentScreen.add(introScreen);
				bgm = Resource.getAudio("introbgm");
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
			}case PAUSESCREEN:{
				System.out.println("Pause Screen");
				currentScreen.add(pauseScreen);
				bgm = Resource.getAudio("pausebgm");
				bgm.play();
				break;
			}case GAMEOVERSCREEN:{
				System.out.println("GameOver Screen");
				currentScreen.add(gameOverScreen);
				bgm = Resource.getAudio("gameoverbgm");
				bgm.play();
				break;
			}case WINNINGSCREEN:{
				System.out.println("Winning Screen");
				currentScreen.add(new WinningScreen());
				bgm = Resource.getAudio("winningbgm");
				bgm.play();
				break;
			}default:{
				throw new RuntimeException("Error Screen not found : "+screen);
			}
		}
		for(JComponent comp : currentScreen){
			panelInsideFrame.add(comp);
		}
		MainFrame.pack();
		MainFrame.setResizable(false);
		MainFrame.setVisible(true);
		chagingScreen = true;
	}
	
	public static void fadeScreen(JPanel panelInsideFrame,boolean option){
	    float alpha;
	    if(option)
	    	alpha = 0.0f;
	    else
	    	alpha = 1.0f;
	    
//	    BufferedImage screenImage = new BufferedImage(panelInsideFrame.getWidth(), panelInsideFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
//	    Graphics2D g2d = (Graphics2D) screenImage.getGraphics();
//	    g2d.drawImage(Resource.getImage("BMDP"), null, 0, 0);
	    
//	    JComponent panel0 = new JPanel();
//	    panel0.setPreferredSize(new Dimension(screenImage.getWidth(), screenImage.getHeight()));
//
//	    try {
//			ImageIO.write(screenImage, "JPG", new File("foo.jpg"));
//		} catch (IOException e1) {e1.printStackTrace();}
//	    

//		while(true){
//			try{
//				Thread.sleep(ConfigurableOption.sleepTime);
//			}catch (InterruptedException e){}
//
//			for(JComponent component:currentScreen){
//				component.repaint();
//				System.out.println(component.getClass());
//			}
//			
//			for(Logic component:currentLogic){
//				component.logicUpdate();
//				if(chagingScreen) {
//					chagingScreen = false;
//					break;
//				}
//			}
//			
//			InputUtility.postUpdate();
//			panelInsideFrame.requestFocus();
//		}

	    Graphics g = panelInsideFrame.getGraphics();
	    Graphics2D g2d = (Graphics2D) g;
		while(true){
		    //set the opacity
		    g2d.setComposite(AlphaComposite.getInstance(
		            AlphaComposite.SRC_OVER, alpha));
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		    //do the drawing here
		    g2d.setColor(Color.BLACK);
		    g2d.fillRect(0, 0, 100, 100);

		    //increase the opacity and repaint
		    if(option){
			    alpha += 0.01f;
			    if (alpha >= 1.0f) {
			        alpha = 1.0f;
			        break;
			    }
		    }else{
			    alpha -= 0.01f;
			    if (alpha <= 0.0f) {
			        alpha = 0.0f;
			        break;
			    }
		    }
		    
			panelInsideFrame.paint(g2d);
//			panelInsideFrame.repaint();

		    //sleep for a bit
		    try {
		        Thread.sleep(100);
		    } catch (InterruptedException e) {

		        e.printStackTrace();
		    }
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
}
