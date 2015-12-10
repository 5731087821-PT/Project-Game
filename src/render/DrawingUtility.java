package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import res.*;

public class DrawingUtility {

	protected static final Font standardFont /* fill code */ = new Font("Tahoma",Font.BOLD,30);
	protected static final Font smallFont /* fill code */ = new Font("Tahoma",Font.PLAIN,9);
	
	private static BufferedImage getImage(String directory){
		/* fill code */
		BufferedImage temp; 
		try{
			temp = ImageIO.read(DrawingUtility.class.getClassLoader().getResource(directory));
		}catch(IOException e){
			temp = null;
			System.out.println("oh no "+directory);
		}
		return temp;
	}
	
	protected static final BufferedImage bg = getImage("res/img/bg.jpg");
	protected static final BufferedImage gun = getImage("res/img/gun.png");
	protected static final BufferedImage gun_inf = getImage("res/img/gun_inf.png");
	protected static final BufferedImage shootAnim = getImage("res/img/shootAnim.png");
	
	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	
	public static BufferedImage getShootanim() {
		return shootAnim;
	}
	
	public static void drawShootableObject(Graphics2D g2,int x,int y,int radius,String name,boolean isPointerOver){
		/* fill code */
		Color thisColor = Color.GRAY;
		switch(name){
			case "simple" : thisColor = Color.BLUE; break;
			case "splitter" : thisColor = Color.RED; break;
			case "small" : thisColor = Color.YELLOW; break;
		}
		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);		
		g2.setColor(thisColor);
		g2.fillOval(x-radius, y-radius, radius*2, radius*2);
		
		if(isPointerOver){
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x-radius, y-radius, radius*2, radius*2);
			g2.setComposite(opaque);
		}
	}
	
	public static void drawItemGun(Graphics2D g2,int x,int y,int radius,String name,boolean isPointerOver){
		/* fill code */
		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);
		g2.setColor(Color.GRAY);
		g2.fillOval(x-radius, y-radius, radius*2, radius*2);
		
		BufferedImage imageTemp ;
		
		if(name.equalsIgnoreCase("gun")){
			/* fill code */
			imageTemp = gun; 
		}else{
			imageTemp = gun_inf;
		}
		
		/* fill code */
		g2.drawImage(imageTemp,null,x-15,y-15);
		
		if(isPointerOver){
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x-radius, y-radius, radius*2, radius*2);
			g2.setComposite(opaque);
		}
		
	}
	
	public static void drawItemBullet(Graphics2D g2,int x,int y,int radius,boolean isPointerOver){
		/* fill code */
		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);
		g2.setColor(Color.GRAY);
		g2.fillOval(x-radius, y-radius, radius*2, radius*2);

		g2.setColor(Color.BLACK);
		g2.fillOval(x-20, y-10, 40, 20);
		g2.fillRect(x-20, y-10, 20, 20);
		
	}
	
	public static void drawIconGun(Graphics2D g2,int bulletQuantity,int maxBullet,boolean isInfiniteBullet){
		if(gun == null || (isInfiniteBullet && gun_inf == null)) return;
		g2.setFont(DrawingUtility.smallFont);
		if(isInfiniteBullet){
			g2.drawImage(gun_inf,null,0,0);
		}else{
			g2.drawImage(gun,null,0,0);
			g2.drawString(bulletQuantity+"/"+maxBullet,15,30);
		}
	}
	
	public static void drawStatusBar(Graphics2D g2, int remainingSecond,int score,Gun gun,boolean pause){
		/* fill code */
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, ConfigurableOption.screenWidth, 40);
		
		
		g2.setFont(standardFont);
		g2.setColor(Color.WHITE);
		g2.drawString("TIME : "+remainingSecond, 5, 35);
		
		g2.drawString("SCORE : "+score, ConfigurableOption.screenWidth/2 + 40, 35);
		
		/* fill code */
		
		if(pause){
			g2.setColor(Color.WHITE);
			g2.drawString("PAUSE", ConfigurableOption.screenWidth/2-45, ConfigurableOption.screenHeight/2-19);
		}
		
		g2.translate(ConfigurableOption.screenWidth/2-15, 5);
		if(gun != null){
			gun.render(g2);
		}
	}
	
	public static GameAnimation createShootingAnimationAt(int x,int y){
		GameAnimation anim = new GameAnimation(DrawingUtility.shootAnim,7,1);
		anim.centerAnimationAt(x,y);
		anim.play();
		return anim;
	}
}
