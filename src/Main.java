

import javax.swing.*;

import Entity.GameLogic;
import render.GameScreen;
import ui.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameScreen gameScreen = new GameScreen();
		MainGame mainGame = new MainGame();
		GameLogic gameLogic = new GameLogic();
		
		JFrame GameWindow = new GameWindow(gameScreen, mainGame, gameLogic);
		
	}

}
