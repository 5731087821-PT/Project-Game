package utility;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

import render.RenderableHolder;

public class Resource {
	
	public static final Font standardFont = new Font("Tahoma",Font.BOLD,30);
	private static HashMap<String,AnimationManager> rs = new HashMap<>();
	private static HashMap<String,AudioClip> audio = new HashMap<>();
	public Resource(){
		try {
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			appleSprite = ImageIO.read(loader.getResource("res/Apple.png"));
		} catch (Exception e) {
			appleSprite = null;
		}
		
		audio.put("coin",Applet.newAudioClip(Resource.class.getClassLoader().getResource("res/coin.wav")));
		
	}
	public static void playSound(String identifier){
		if(identifier=="shoot"){
			acShoot.play();
		}else{
			acCollect.play();
		}
	}
}