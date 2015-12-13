package render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utility.ConfigurableOption;

public class RenderAnimationHelper {
	public static void draw(Graphics2D g2d,AnimationManager animation,int x,int y,int userCharWidth,int userCharHeight){
		BufferedImage img ;
		if(ConfigurableOption.debugGraphic){
			img = new BufferedImage(animation.getWidth(),animation.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = (Graphics2D) img.getGraphics();
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, animation.getWidth(),animation.getHeight());
			g.setColor(Color.CYAN);
			g.fillRect(animation.getSetX()-animation.getCharWidth()/2, animation.getSetY()-animation.getCharHeight(), animation.getCharWidth(), animation.getCharHeight());
			g.drawImage(animation.getCurrentBufferedImage(), null, 0, 0);
		}else{
			img = animation.getCurrentBufferedImage();
		}
		int width, height ;
		int drawX, drawy ;
		
		if(userCharHeight==0){
			width = (userCharWidth*animation.getWidth())/animation.getCharWidth();
			height = (animation.getHeight()*width)/animation.getWidth();
		}else{
			height = (userCharHeight*animation.getHeight())/animation.getCharHeight();
			width = (animation.getWidth()*height)/animation.getHeight();
		}
		
		drawX = x-(animation.getSetX()*width/animation.getWidth());
		drawy = y-(animation.getSetY()*width/animation.getWidth());
		
		g2d.drawImage(
				img, 
				drawX, drawy, 
				width, height, 
				null
			);
		
	}

}
