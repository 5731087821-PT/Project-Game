package render;

import java.awt.image.BufferedImage;

public class AnimationManager {
	private boolean isPlay;
	private boolean isLoop;
	private boolean isFinish;
	private int frame;
	private ImageData[] img;
	private int width, height;
	private int speed;
	private int charWidth;
	private int setX,setY;

	public AnimationManager(ImageData[] img,int setX,int setY,int charWidth) {
		frame = 0;
		speed = 0;
		isPlay = isFinish = false;
		this.img = img;
		for (int i = 0; i < img.length; i++) {
			width = Math.max(width, img[i].getWidth());
			height = Math.max(height, img[i].getHeight());
		}
		
		this.setX = setX;
		this.setY = setY;
		this.charWidth = charWidth;
		
	}
	
	public int getCharWidth(){
		return charWidth;
	}

	public int getSetX() {
		return setX;
	}

	public int getSetY() {
		return setY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void play() {
		isPlay = true;
		isLoop = isFinish = false;
		frame = 0;
	}

	public void loop() {
		isPlay = false;
		isLoop = true;
		isFinish = false;
		frame = 0;
	}

	public void stop() {
		isPlay = false;
		isLoop = false;
		isFinish = false;
	}
	
	public boolean isFinish() {
		return isFinish;
	}
	
	public void setFinish(boolean finish) {
		this.isFinish = finish;
	}

	public synchronized void update() {
		if(++speed < 4) return ;
		speed = 0;
		if (isLoop || isPlay) {
			frame++;
			if (frame >= img.length) {
				isPlay = false;
				isFinish = true;
				frame = 0;
			}
		}
	}
	
	public ImageData[] getAllImage() {
		return img;
	}
	
	public ImageData getCurrentImageData() {
		return img[frame];
	}

	public BufferedImage getCurrentBufferedImage() {
		return img[frame].getImg();
	}
	
	public BufferedImage getCurrentBufferedImage(int frameNo) {
		return img[frameNo].getImg();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
