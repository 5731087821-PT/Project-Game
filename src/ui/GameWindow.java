package ui;

import java.awt.*;

import javax.swing.*;

public class GameWindow extends JFrame{
	private int width,height;
	private JPanel miniGame,mainGame;
	
	public GameWindow(MiniGame miniGame, JPanel mainGame){
		this.width = 720;
		this.height = 480;
		this.miniGame = miniGame;
		this.mainGame = mainGame;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(miniGame, BorderLayout.NORTH);
		this.add(mainGame, BorderLayout.CENTER);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}
