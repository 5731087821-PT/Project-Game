package ui;

import java.awt.*;

import javax.swing.*;

import LogicGame.GameScreenLogic;
import LogicGame.MainGameLogic;
import render.GameScreen;
import render.MainGame;
import utility.ConfigurableOption;;

public class GameWindow extends JFrame{
	private int width,height;
	private GameScreen gameScreen;
	private MainGame mainGame;
	private JPanel southPanel;
	private GameScreenLogic gameScreenLogic;
	private MainGameLogic mainGameLogic;
	
	public GameWindow(GameScreen gameScreen, MainGame mainGame, GameScreenLogic gameScreenLogic, MainGameLogic mainGameLogic, JPanel southPanel){
		this.width = ConfigurableOption.screenWidth;
		this.height = ConfigurableOption.screenHeight;
		this.gameScreen = gameScreen;
		this.mainGame = mainGame;
		this.southPanel = southPanel;
		this.gameScreenLogic = gameScreenLogic;
		this.mainGameLogic = mainGameLogic;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(gameScreen, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.CENTER);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
		while(true){
			try{
				Thread.sleep(10);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			mainGame.repaint();
			gameScreen.repaint();
			gameScreenLogic.logicUpdate();
			mainGameLogic.logicUpdate();
			
			mainGame.requestFocus();
		}
	}
}
