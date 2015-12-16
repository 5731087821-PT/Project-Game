package resource;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JOptionPane;

import render.AnimationManager;
import render.ImageReader;

public class Resource {
	private static HashMap<String,AnimationManager> rs = new HashMap<>();
	private static HashMap<String,AudioClip> audio = new HashMap<>();

	public static final Font standardFont = new Font("Tahoma",Font.BOLD,30);
	public static BufferedImage[] playButton= new BufferedImage[2];
	public static BufferedImage[] rankButton= new BufferedImage[2];
	public static BufferedImage[] continueButton= new BufferedImage[2];
	public static BufferedImage[] exitButton= new BufferedImage[2];
	public static BufferedImage[] restartButton= new BufferedImage[2];
	public static Cursor CURSOR_DEFAULT;
	public static  Cursor CURSOR_HAND;
	
	public AnimationManager read(String url,int setX,int setY,int setCharWidth,int setCharHeight,int mode) throws FethResourceException{
		return new AnimationManager(ImageReader.get(url,mode),setX,setY,setCharWidth,setCharHeight,mode);
	}
	public AnimationManager read(String url,int setX,int setY,int setCharWidth,int setCharHeight) throws FethResourceException{
		return new AnimationManager(
				ImageReader.get(url)
				,setX
				,setY
				,setCharWidth
				,setCharHeight
				,AnimationManager.DONOTTHING
			);
	}
	public AnimationManager read(String url,int mode) throws FethResourceException{
		return new AnimationManager(ImageReader.get(url,mode),0,0,0,0,mode);
	}
	public AnimationManager read(String url) throws FethResourceException{
		return new AnimationManager(ImageReader.get(url),0,0,0,0,AnimationManager.DONOTTHING);
	}
	public AudioClip audioRead(String url) throws FethResourceException{
		AudioClip audio = null ;
		try{
			audio = Applet.newAudioClip(
					Resource.class.getClassLoader().getResource(url));
		}catch(Exception e){
			throw new FethResourceException(FethResourceException.AUDIO, url);
		}
		return audio;
	}
	
	public Resource(){
		try {
			audio.put("dooropen", audioRead("res/sound/dooropen.wav"));
			audio.put("zombiedeath", audioRead("res/sound/zombiedeath.wav"));
			audio.put("gotitem", audioRead("res/sound/gotitem.wav"));
			audio.put("groan", audioRead("res/sound/groan.wav"));
			audio.put("zombie", audioRead("res/sound/zombie.wav"));
			audio.put("bump", audioRead("res/sound/bump.wav"));
			audio.put("coin-impact", audioRead("res/sound/coin-impact.wav"));
			audio.put("collect", audioRead("res/sound/collect.wav"));
			audio.put("dead", audioRead("res/sound/dead.wav"));
			audio.put("error", audioRead("res/sound/error.wav"));
			audio.put("punch", audioRead("res/sound/punch.wav"));

			audio.put("startgame", audioRead("res/sound/01. Plants vs. Zombies Original Soundtrack - Crazy Dave's Greeting.wav"));
			audio.put("gamebgm", audioRead("res/sound/05. Plants vs. Zombies Original Soundtrack - Loonboon.wav"));
			audio.put("introbgm", audioRead("res/sound/18. Plants vs. Zombies Original Soundtrack - Crazy Dave IN-GAME.wav"));
			audio.put("pausebgm", audioRead("res/sound/11. Plants vs. Zombies Original Soundtrack - Rigor Mormist.wav"));
			audio.put("gameoverbgm", audioRead("res/sound/27. Plants vs. Zombies Original Soundtrack - Cerebrawl IN-GAME.wav"));
			audio.put("winningbgm", audioRead("res/sound/16. Plants vs. Zombies Original Soundtrack - Zombotany (Unreleased Track).wav"));
			
			rs.put("batman-walking", read("res/character/batman-walking.gif",45,90,23,68));
			rs.put("batman-standing", read("res/character/batman-standing.gif",45,90,23,68));
			rs.put("boy", read("res/character/boy.gif",45,90,35,80));
			
			rs.put("zombie-ballon", read("res/character/zombie-ballon.gif",48,150,90,120,AnimationManager.FLIP));
			rs.put("zombie-helmet", read("res/character/zombie-helmet.gif",40,100,71,90,AnimationManager.FLIP));
			rs.put("zombie-imps", read("res/character/zombie-imps.gif",50,100,85,150,AnimationManager.FLIP));
			rs.put("zombie-moonwalk", read("res/character/zombie-moonwalk.gif",45,100,90,100,AnimationManager.FLIP));
			rs.put("zombie-running", read("res/character/zombie-running.gif",50,96,50,140,AnimationManager.FLIP));
			
			rs.put("batman-intro", read("res/bg/batman-intro.gif",AnimationManager.BufferOPTIMIZED));
			rs.put("batman-pic", read("res/bg/batman-pic.png"));
			rs.put("BMDP", read("res/bg/BMDP.gif",AnimationManager.BufferOPTIMIZED));
			rs.put("babadook", read("res/bg/babadook.gif",AnimationManager.BufferOPTIMIZED));
			rs.put("town", read("res/bg/town.jpg"));
			rs.put("city", read("res/bg/city.png"));
			rs.put("town-creepy", read("res/bg/town-creepy.png"));
			
			rs.put("coins-stack", read("res/minigame/getcoin/coins-stack.png"));
			rs.put("coins-many", read("res/minigame/getcoin/coins-many.png"));
			rs.put("coins", read("res/minigame/getcoin/coin-dollarsign.png"));
			rs.put("wireFrame", read("res/minigame/wirecut/Defuser1.png"));
			rs.put("wirecutter", read("res/minigame/wirecut/wirecutter.png"));
			rs.put("checkmark", read("res/minigame/passcode/check-mark.png"));
			rs.put("passcodebg", read("res/minigame/passcode/bg.jpg"));
	
			rs.put("button1", read("res/etc/button1.6.png"));
			rs.put("button2", read("res/etc/button2.2.png"));
			rs.put("button-start", read("res/etc/button-start.2.png"));
			rs.put("button-restart", read("res/etc/button-restart.2.png"));
			rs.put("batman-icon", read("res/etc/batman-icon.png"));
//			rs.put("cursor-default", read("res/etc/cursor-default.png"));
//			rs.put("cursor-hand", read("res/etc/cursor-hand.png"));
			rs.put("coin", read("res/minigame/getcoin/Puffle_Rescue_Coin.png"));
			rs.put("alphabet", read("res/minigame/opengatezero/alphabet.png"));

		} catch (FethResourceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
//		CURSOR_DEFAULT = Toolkit.getDefaultToolkit().createCustomCursor(
//				Resource.getImage("cursor-default"), 
//				new Point(0,0), "custom default cursor");
//		CURSOR_HAND = Toolkit.getDefaultToolkit().createCustomCursor(
//				Resource.getImage("cursor-hand"), 
//				new Point(0,0), "custom hand cursor");
		
		CURSOR_DEFAULT = Cursor.getDefaultCursor();
		CURSOR_HAND = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		
		playButton[0] = getImage("button1",0);
		playButton[1] = getImage("button1",1);
		rankButton[0] = getImage("button1",4);
		rankButton[1] = getImage("button1",5);
		continueButton[0] = getImage("button2",0);
		continueButton[1] = getImage("button2",1);
		exitButton[0] = getImage("button1",2);
		exitButton[1] = getImage("button1",3);
		restartButton[0] = getImage("button-restart",0);
		restartButton[1] = getImage("button-restart",1);
		
	}
	
	public static AnimationManager get(String key) {
		if(rs.containsKey(key)) {
			return rs.get(key);
		}
		throw new GetResourceException(GetResourceException.ANIMATION, key);
	}
	
	public static BufferedImage getImage(String key) {
		if(rs.containsKey(key)) {
			return rs.get(key).getCurrentBufferedImage();
		}
		throw new RuntimeException("Character Key is incorrect : " + key);
	}
	
	public static BufferedImage getImage(String key,int index) {
		if(rs.containsKey(key)) {
			return rs.get(key).getCurrentBufferedImage(index);
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
