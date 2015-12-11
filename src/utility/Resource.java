package utility;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import render.AnimationManager;
import render.ImageData;
import render.ImageReader;

public class Resource {
	private static HashMap<String,AnimationManager> rs = new HashMap<>();
	private static HashMap<String,AudioClip> audio = new HashMap<>();

	public static final Font standardFont = new Font("Tahoma",Font.BOLD,30);
	
	public AnimationManager read(String url){
		return new AnimationManager(ImageReader.get(url));
	}
	public AudioClip AudioRead(String url){
		return Applet.newAudioClip(Resource.class.getClassLoader().getResource(url));
	}
	
	public Resource(){
		audio.put("birdSound", AudioRead("res/sound/bird.wav"));
		audio.put("thebeat", AudioRead("res/sound/thebeat.wav"));
		audio.put("doorbell", AudioRead("res/sound/doorbell2.wav"));
		audio.put("gamebgm", AudioRead("res/sound/Intense Battle Music.wav"));
		audio.put("zombiedeath", AudioRead("res/sound/zombiedeath.wav"));
		
		rs.put("batman", read("pic/character/batman.gif"));
		rs.put("boy", read("pic/character/boy.gif"));
		
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
