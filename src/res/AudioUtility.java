package res;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;

import sun.audio.AudioPlayer;

public class AudioUtility {
		
	private static AudioClip acShoot;
	private static AudioClip acCollect;
	static{
		acShoot = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/se/shoot.wav"));
		acCollect = Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource("res/se/collect.wav"));
	}
	
	public static void playSound(String identifier){
		if(identifier=="shoot"){
			acShoot.play();
		}else{
			acCollect.play();
		}
	}
}
