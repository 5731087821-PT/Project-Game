

import javax.swing.*;

import entity.GameScreenLogic;
import render.GameScreen;
import render.MainGame;
import render.SouthPanel;
import ui.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameScreen gameScreen = new GameScreen();
		MainGame mainGame = new MainGame();
		SouthPanel southPanel = new SouthPanel(mainGame);
		GameScreenLogic gameLogic = new GameScreenLogic();
		
		JFrame GameWindow = new GameWindow(gameScreen, mainGame, southPanel, gameLogic);
		
	}

}
