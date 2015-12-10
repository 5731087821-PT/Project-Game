package render;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameScreen extends JComponent {
	
	public GameScreen(){
		super();
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(720, 160));
		setLayout(null);
		setVisible(true);
		setFocusable(true);
		requestFocus();
		

	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, 720, 160);
		
		ArrayList<IRenderable> entity = (ArrayList<IRenderable>) RenderableHolder.getInstance().getRenderableList();
		for(int i = 0 ; i<entity.size();i++){
			entity.get(i).draw(g2d);
		}
	}
}
