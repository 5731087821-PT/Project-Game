package ui;

import java.awt.*;

import javax.swing.*;

import Entity.GameLogic;
import render.GameScreen;

public class GameWindow extends JFrame{
	private int width,height;
	private GameScreen gameScreen;
	private JPanel mainGame;
	private GameLogic gameLogic;
	
	public GameWindow(GameScreen gameScreen, JPanel mainGame, GameLogic gameLogic){
		this.width = 720;
		this.height = 480;
		this.gameScreen = gameScreen;
		this.mainGame = mainGame;
		this.gameLogic = gameLogic;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(gameScreen, BorderLayout.NORTH);
		this.add(mainGame, BorderLayout.CENTER);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
		while(true){
			try{
				Thread.sleep(20);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			gameScreen.repaint();
			gameLogic.logicUpdate();
		}
	}
}
