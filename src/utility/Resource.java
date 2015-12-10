package utility;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import render.RenderableHolder;

public class Resource {
	
	public static final Font standardFont = new Font("Tahoma",Font.BOLD,30);
	public static BufferedImage appleSprite;
	public static AudioClip coinSound;
	public static AudioClip acShoot;
	public static AudioClip acCollect;
	static{
		try {
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			appleSprite = ImageIO.read(loader.getResource("res/Apple.png"));
			coinSound = Applet.newAudioClip(Resource.class.getClassLoader().getResource("res/coin.wav"));
			acShoot = Applet.newAudioClip(Resource.class.getClassLoader().getResource("res/se/shoot.wav"));
			acCollect = Applet.newAudioClip(Resource.class.getClassLoader().getResource("res/se/collect.wav"));
		} catch (Exception e) {
			appleSprite = null;
			coinSound = null;
			acShoot = null;
			acCollect = null;
		}
	}
	public static void playSound(String identifier){
		if(identifier=="shoot"){
			acShoot.play();
		}else{
			acCollect.play();
		}
	}
}