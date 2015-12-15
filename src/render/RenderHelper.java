package render;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import utility.InputUtility;

public class RenderHelper {
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;
	public static final int TOP = 0;
	public static final int MIDDLE = 4;
	public static final int BOTTOM = 8;
	public static final int REPEAT = 16;
	
	public static void draw(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position) {
		draw(g,img,x,y,width,height,position,null);
	}

	public static void draw(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position,RenderHelperMouseEvent event) {

		
		if(width==0&&height==0){
			width = img.getWidth();
			height = img.getHeight();
		}else if(height==0){
			height = (img.getHeight()*width)/img.getWidth();
		}else if(width==0){
			width = (img.getWidth()*height)/img.getHeight();
		}
		
		if((position & CENTER) != 0) {
			x -= width / 2;
		} else if((position & RIGHT) != 0) {
			x -= width;
		}
		if((position & MIDDLE) != 0) {
			y -= height / 2;
		} else if((position & BOTTOM) != 0) {
			y -= height;
		}
		
		if(g != null){
			addAntiAlising(g);
			g.drawImage(img, x, y, width, height, null);
		}
	
		if(event != null)
			checkEvent(event,x,y,width,height);
	}
	private static void checkEvent(RenderHelperMouseEvent event, int x, int y, int width, int height) {
		if(isMouseEntered(x, y, width, height)){
			event.mouseEntered();
			if(InputUtility.isMouseLeftClicked())
				event.mouseClicked();
			else if(InputUtility.isMouseLeftDown())
				event.mousePressed();
			else 
				event.mouseReleased();
		}else
			event.mouseExited();
	}
	private static boolean isMouseEntered(int x,int y,int width,int height){
		if(utility.InputUtility.getMouseX()-x>0 && x - utility.InputUtility.getMouseX()+width > 0 ){
			if(utility.InputUtility.getMouseY()-y>0 && y - utility.InputUtility.getMouseY()+height > 0 ){
				return true;
			}
		}
		return false;
	}

	public static void addAntiAlising(Graphics2D g2d){
		g2d.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(
        		RenderingHints.KEY_RENDERING, 
        		RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(
			    RenderingHints.KEY_TEXT_ANTIALIASING,
			    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}
	public static void removeAntiAliasing(Graphics2D g2d){
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.setRenderingHint(
			    RenderingHints.KEY_TEXT_ANTIALIASING,
			    RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}
}
