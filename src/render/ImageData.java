package render;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageData {
	private int offsetX,offsetY;
	private int delay;
	private BufferedImage img;
	
	public ImageData(BufferedImage img){
		this.img = img;
		this.offsetX = this.offsetY = 0;
		this.delay = 100; //default delay animation
	}
	public void flipImage(){
        BufferedImage flipped = new BufferedImage(
                img.getWidth(),
                img.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        AffineTransform tran = AffineTransform.getTranslateInstance(img.getWidth(), 0);
        AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
        tran.concatenate(flip);

        Graphics2D g = flipped.createGraphics();
        g.setTransform(tran);
        g.drawImage(img, 0, 0, null);
        g.dispose();
        this.img = flipped;
	}
	public void setOffset(int x,int y){
		this.offsetX = x;
		this.offsetY = y;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public BufferedImage getImg() {
		return img;
	}
	public int getWidth(){
		return img.getWidth();
	}
	public int getHeight(){
		return img.getHeight();
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
		if(delay<=0)
			delay = 100; //default delay animation
	}
	

}
