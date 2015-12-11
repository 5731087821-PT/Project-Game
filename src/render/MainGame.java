package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import utility.ConfigurableOption;

public class MainGame extends JComponent {
	private int width, height;
	
	public MainGame(){
		super();
		this.width = ConfigurableOption.screenWidth;
		this.height = ConfigurableOption.mainGameHeight;
		
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setVisible(true);
		setFocusable(true);
		requestFocus();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		
		ArrayList<IRenderable> entity = (ArrayList<IRenderable>) RenderableHolder.getInstance().getSouthRenderableList();
		for(int i = 0 ; i<entity.size();i++){
			entity.get(i).draw(g2d);
		}
	}
}
