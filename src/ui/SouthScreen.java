package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JComponent;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import render.AnimationManager;
import render.IRenderable;
import render.RenderHelper;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.Resource;

@SuppressWarnings("serial")
public class SouthScreen extends JComponent {
	private int width, height;
	private BufferedImage img;
	private AnimationManager bgAnimation;
	
	public SouthScreen(){
		super();
		this.width = ConfigurableOption.screenWidth;
		this.height = ConfigurableOption.southScreenHeight;
		
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setVisible(true);
		
		bgAnimation = Resource.get("batman-intro");
//		bgAnimation = Resource.get("batman-walking");
		bgAnimation.loop();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		img = bgAnimation.getCurrentBufferedImage();
//		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		Graphics2D gg = (Graphics2D) img.getGraphics();
//		gg.setColor(Color.WHITE);
//		gg.fillRect(0, 0, width-10, height-10);
//		g.drawImage(img, 0, 0, width, height, null);
//		RenderHelper.draw(g2d, img,  0, 0,  width, height, RenderHelper.CENTER|RenderHelper.TOP);
//		RenderHelper.draw(g2d, img, 0, 0, 0, 0, RenderHelper.LEFT | RenderHelper.TOP);
//		RenderHelper.draw(g2d, img, width/2, height/2, 0, 0, RenderHelper.LEFT | RenderHelper.TOP);
		RenderHelper.draw(g2d, img, width/2, height, 0, height, RenderHelper.CENTER | RenderHelper.BOTTOM);
		bgAnimation.update();
		
		ArrayList<IRenderable> entity = (ArrayList<IRenderable>) RenderableHolder.getInstance().getSouthRenderableList();
		for(int i = 0 ; i<entity.size();i++){
			if(!entity.get(i).isVisible()) continue;
			entity.get(i).draw(g2d);
		}
	}
}
