

import javax.swing.*;

import ui.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiniGame miniGame = new MiniGame();
		JPanel mainGame = new JPanel();
		
		JFrame GameWindow = new GameWindow(miniGame, mainGame);
		
	}

}
