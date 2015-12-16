package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import entity.Player;
import render.AnimationManager;
import render.IRenderable;
import render.RenderHelper;
import render.RenderableHolder;
import resource.Resource;
import utility.ConfigurableOption;

@SuppressWarnings("serial")
public class NorthScreen extends JComponent {
	private int width, height, statusHeight;
	private BufferedImage img,coinsImage;
	private AnimationManager bgAnimation;
	private ArrayList<IRenderable> entity ;
	public NorthScreen(){
		super();
		this.width = ConfigurableOption.screenWidth;
		this.height = ConfigurableOption.northScreenHeight;
		this.statusHeight = ConfigurableOption.statusHeight;
		
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setVisible(true);
		
		entity = (ArrayList<IRenderable>) RenderableHolder.getInstance().getNorthRenderableList();
		
		coinsImage = Resource.getImage("coins-many");
		bgAnimation = Resource.get("town-creepy");
		bgAnimation.loop();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderHelper.addAntiAlising(g2d);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		img = bgAnimation.getCurrentBufferedImage();
		RenderHelper.draw(
				g2d, img, 
				width/2, height, 
//				0, height-statusHeight-20, 
				width, 0, 
				RenderHelper.BOTTOM | RenderHelper.CENTER);
		bgAnimation.update();

		RenderHelper.draw(
				g2d, coinsImage, 
				width/2, height+11, 
				(int) (width*0.23), 0, 
				RenderHelper.BOTTOM | RenderHelper.CENTER);
		
		for(int i = 0 ; i<entity.size();i++){
			if(!entity.get(i).isVisible()) 
				continue;
			if(entity.get(i).isDestroyed()){
				entity.remove(i);
				continue;
			}
			entity.get(i).draw(g2d);
			
			if(ConfigurableOption.stageNow != ConfigurableOption.ENDSTAGE)
				entity.get(i).updateAnimation();
			else if(entity.get(i) instanceof Player)
				entity.get(i).updateAnimation();
		}
	}
}
