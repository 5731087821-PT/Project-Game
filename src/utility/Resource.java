package utility;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.util.HashMap;

import render.AnimationManager;
import render.ImageReader;

public class Resource {
	private static HashMap<String,AnimationManager> rs = new HashMap<>();
	private static HashMap<String,AudioClip> audio = new HashMap<>();

	public static final Font standardFont = new Font("Tahoma",Font.BOLD,30);
	
	public AnimationManager read(String url,int setX,int setY,int setCharWidth,int special){
		return new AnimationManager(ImageReader.get(url),setX,setY,setCharWidth,special);
	}
	public AnimationManager read(String url,int setX,int setY,int setCharWidth){
		return new AnimationManager(ImageReader.get(url),setX,setY,setCharWidth,AnimationManager.DONOTTHING);
	}
	public AnimationManager read(String url,int special){
		return new AnimationManager(ImageReader.get(url),0,0,0,special);
	}
	public AnimationManager read(String url){
		return new AnimationManager(ImageReader.get(url),0,0,0,AnimationManager.DONOTTHING);
	}
	public AudioClip AudioRead(String url){
		return Applet.newAudioClip(Resource.class.getClassLoader().getResource(url));
	}
	
	public Resource(){
		audio.put("birdSound", AudioRead("res/sound/bird.wav"));
		audio.put("thebeat", AudioRead("res/sound/thebeat.wav"));
		audio.put("doorbell", AudioRead("res/sound/doorbell2.wav"));
		audio.put("zombiedeath", AudioRead("res/sound/zombiedeath.wav"));
		audio.put("gamebgm", AudioRead("res/sound/05. Plants vs. Zombies Original Soundtrack - Loonboon.wav"));
		
		rs.put("batman-walking", read("res/character/batman-walking.gif",180,255,140));
		rs.put("batman-standing", read("res/character/batman-standing.gif",180,255,140));
		rs.put("boy", read("res/character/boy.gif",180,255,140));
		rs.put("batman-intro", read("res/bg/batman-intro.gif"));
		rs.put("BMDP", read("res/bg/BMDP.gif"));
		rs.put("zombie-walking", read("res/character/zombie-walking.gif",AnimationManager.FLIP));
		rs.put("zombie-imps", read("res/character/imps.gif"));
		rs.put("test", read("res/character/zombie-walking.6.png"));
		
	}
	
	public static AnimationManager get(String key) {
		if(rs.containsKey(key)) {
			return rs.get(key);
		}
		throw new RuntimeException("Character Key is incorrect : " + key);
	}
	
	public static AudioClip getAudio(String key) {
		if(audio.containsKey(key)) {
			return audio.get(key);
		}
		throw new RuntimeException("Audio Key is incorrect : " + key);
	}
}
