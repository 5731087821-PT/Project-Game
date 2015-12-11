package render;

import java.awt.image.BufferedImage;

public class ImageData {
	private int offsetX,offsetY;
	private BufferedImage img;
	
	public ImageData(BufferedImage img){
		this.img = img;
		this.offsetX = this.offsetY = 0;
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
	

}
