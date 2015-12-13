package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import com.sun.javafx.tk.RenderJob;

import render.AnimationManager;
import render.IRenderable;
import render.RenderHelper;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.Resource;

@SuppressWarnings("serial")
public class NorthScreen extends JComponent {
	private int width, height, statusHeight;
	private BufferedImage img;
	private AnimationManager bgAnimation;
	public NorthScreen(){
		super();
		this.width = ConfigurableOption.screenWidth;
		this.height = ConfigurableOption.northScreenHeight;
		this.statusHeight = ConfigurableOption.statusHeight;
		
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setVisible(true);
		
		bgAnimation = Resource.get("city");
		bgAnimation.loop();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		
		img = bgAnimation.getCurrentBufferedImage();
		RenderHelper.draw(g2d, img, width/2, height, 0, height-statusHeight-20, RenderHelper.BOTTOM | RenderHelper.CENTER);
		bgAnimation.update();
		
		ArrayList<IRenderable> entity = (ArrayList<IRenderable>) RenderableHolder.getInstance().getNorthRenderableList();
		for(int i = 0 ; i<entity.size();i++){
			if(!entity.get(i).isVisible()) continue;
			if(entity.get(i).isDestroyed()){
				entity.remove(i);
				continue;
			}
			entity.get(i).draw(g2d);
		}
	}
}
