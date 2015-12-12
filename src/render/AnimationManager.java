package render;

import java.awt.image.BufferedImage;

import utility.ConfigurableOption;

public class AnimationManager {
	public static final int DONOTTHING = 0;
	public static final int FLIP = 1;
	public static final int BufferSpecial = 2;
	
	
	private boolean isPlay;
	private boolean isLoop;
	private boolean isFinish;
	private int frame;
	private ImageData[] img;
	private int width, height;
	private int delayTime,delayCounter;
	private int charWidth;
	private int setX,setY;
	private boolean flip;

	public AnimationManager(ImageData[] img,int setX,int setY,int charWidth,int special) {
		frame = 0;
		delayCounter = delayTime = 0;
		isPlay = isFinish = false;
		this.setX = setX;
		this.setY = setY;
		this.charWidth = charWidth;
		this.img = img;
		for (int i = 0; i < img.length; i++) {
			width = Math.max(width, img[i].getWidth());
			height = Math.max(height, img[i].getHeight());
		}
		if(setX==0||setY==0||charWidth==0){
			this.setX = width;
			this.setY = height;
			this.charWidth = width;
		}
		if((special & FLIP) != 0)
			flip();
		
	}
	
	public boolean getFlip() {
		return flip;
	}

	public void setFlip(boolean flip) {
		if(this.flip ^ flip){
			flip();
			this.flip = flip;
		}
	}

	public void flip(){
		for (int i = 0; i < img.length; i++) {
			img[i].flipImage();
		}
		setX = width-setX;
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
		delayTime = (img[frame].getDelay()-10)/ConfigurableOption.sleepTime;
		
		if(delayCounter++ < delayTime) return ;
		delayCounter = 0;
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
