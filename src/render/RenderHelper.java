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
	
	public static void draw(Graphics2D g, BufferedImage img, int x, int y, int width, int height, int position) {
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
		
		if(width==0&&height==0){
			g.drawImage(img, x, y, null);
		}else if(height==0){
			System.out.println((img.getHeight()*width)/img.getWidth());
			g.drawImage(img, x, y, width, (img.getHeight()*width)/img.getWidth(), null);
		}else{
			g.drawImage(img, x, y, width, height, null);
		}
	}
}
