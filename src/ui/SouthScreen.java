package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JComponent;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import render.AnimationManager;
import render.IRenderable;
import render.RenderAnimationHelper;
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
		
//		bgAnimation = Resource.get("zombie-ballon");
//		bgAnimation = Resource.get("batman-intro");
		bgAnimation = Resource.get("BMDP");
		bgAnimation.loop();
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				InputUtility.setMouseLeftDown(false);
				InputUtility.setMouseLeftTriggered(false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				InputUtility.setMouseLeftTriggered(true);
				InputUtility.setMouseLeftDown(true);
				InputUtility.setMouseX(e.getX());
				InputUtility.setMouseY(e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		img = bgAnimation.getCurrentBufferedImage();
		RenderHelper.draw(g2d, img, width/2, height, 0, height, RenderHelper.CENTER | RenderHelper.BOTTOM);
//		RenderAnimationHelper.draw(g2d, bgAnimation, width/2, height, 0,200);
		bgAnimation.update();
		
		ArrayList<IRenderable> entity = (ArrayList<IRenderable>) RenderableHolder.getInstance().getSouthRenderableList();
		for(int i = 0 ; i<entity.size();i++){
			if(!entity.get(i).isVisible()) continue;
			entity.get(i).draw(g2d);
		}
	}
}
