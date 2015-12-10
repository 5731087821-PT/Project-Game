package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Resource {
	public static BufferedImage character;
	public static AudioClip coinSound;
	
	public Resource(){
		try {
			ClassLoader loader = Resource.class.getClassLoader();
			character = ImageIO.read(loader.getResource("/*resource*/"));
			coinSound = Applet.newAudioClip((loader.getResource("/*resource*/")).toURI().toURL());;
		} catch (Exception e) {
			character = null;
			coinSound = null;
		}
	}
}