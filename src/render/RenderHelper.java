package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RenderHelper {
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;
	public static final int TOP = 0;
	public static final int MIDDLE = 4;
	public static final int BOTTOM = 8;
	public static final int REPEAT = 16;
	
	public static void draw(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position) {
		
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
		
		g.drawImage(img, x, y, width, height, null);
	}
//	public static void drawRepeatHorizontal(Graphics2D g, BufferedImage img, int x, int y, int width, int height,int preferedWidth , int position) {
//		draw(g,img,x,y,width,height,position);
//		
//		for(int i=0;i<preferedWidth/width;i++){
//			draw(g,img,x,y,width,height,position);
//		}
//	}
}
