package render;

import java.awt.image.BufferedImage;

import utility.ConfigurableOption;
import utility.TimeToCounter;

public class AnimationManager {
	public static final int DONOTTHING = 0;
	public static final int FlipToUsual = 1;
	public static final int FlipToUnUsual = 2;
	public static final int FLIP = 4;
	public static final int RotateRight = 8;
	public static final int RotateLeft = 16;
	public static final int BufferOPTIMIZED = 32;
	
	
	private boolean isPlay;
	private boolean isLoop;
	private boolean isFinish;
	private int frame;
	private ImageData[] img;
	private int width, height;
	private int delayTime,delayCounter;
	private int charWidth,charHeight;

	private int setX,setY;
	private int flipInfo;

	public AnimationManager(ImageData[] img,int setXPerc,int setYPerc,int charWidthPerc,int charHeightPerc,int special) {
		frame = 0;
		delayCounter = delayTime = 0;
		isPlay = isFinish = false;
		this.img = img;
		this.flipInfo = FlipToUsual;
		for (int i = 0; i < img.length; i++) {
			width = Math.max(width, img[i].getWidth());
			height = Math.max(height, img[i].getHeight());
		}
		this.setX = (setXPerc*width)/100;
		this.setY = (setYPerc*height)/100;
		this.charWidth = (charWidthPerc*width)/100;
		this.charHeight = (charHeightPerc*height)/100;
		
		if(setXPerc==0||setYPerc==0||charWidthPerc==0){
			this.setX = width;
			this.setY = height;
			this.charWidth = width;
		}

		if((special & FLIP) != 0){
			this.setX = width - setX;
			flipImage();
		}
	}

	public void flip(int flipMode) {
		if((flipMode & FlipToUsual) != 0){
			flipToUsual();
		}else if((flipMode & FlipToUnUsual) != 0){
			flipToUnUsual();
		}else if((flipMode & FLIP) != 0){
			flip();
		}
	}
	public void flip() {
		if((flipInfo & FlipToUsual) != 0){
			flipToUnUsual();
		}else{
			flipToUsual();
		}
	}
	public void flipToUnUsual(){
		if((flipInfo & FlipToUsual) != 0){
			flipImage();
			flipInfo += FlipToUnUsual;
			flipInfo -= FlipToUsual;
		}
	}
	public void flipToUsual(){
		if((flipInfo & FlipToUnUsual) != 0){
			flipImage();
			flipInfo += FlipToUsual;
			flipInfo -= FlipToUnUsual;
		}
	}

	public void flipImage(){
		for (int i = 0; i < img.length; i++) {
			img[i].flipImage();
		}
		setX = width-setX;
	}
	
	public int getCharWidth(){
		return charWidth;
	}
	
	public int getCharHeight() {
		return charHeight;
	}
	
	public int getCharWidth(int userCharHeight){
		return (charWidth*userCharHeight)/charHeight;
	}
	
	public int getCharHeight(int userCharWidth) {
		return (charHeight*userCharWidth)/charWidth;
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
		delayTime = TimeToCounter.getCounter(img[frame].getDelay());
		
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
