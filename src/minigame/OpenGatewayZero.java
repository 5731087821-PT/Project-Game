package minigame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.glass.events.KeyEvent;
import LogicGame.NorthScreenLogic;
import entity.Gateway;
import entity.PlayerStatus;
import entity.RunningBall;
import entity.SpacebarGap;
import render.IRenderable;
import render.RenderHelper;
import render.RenderableHolder;
import resource.Resource;
import utility.ConfigurableOption;
import utility.InputUtility;
import utility.TimeToCounter;

public class OpenGatewayZero implements IRenderable {
	protected int answerCounter;
	protected int amountAlphabet;
	protected int n;
	protected int radianCounter;
	protected double radian;
	protected double radianAlpha;
	protected boolean destroyed;
	protected ArrayList<Integer> passwords;
	
	
	public OpenGatewayZero() {
		this.answerCounter = 0;
		this.amountAlphabet = 10;
		this.radianAlpha = (2*Math.PI)/amountAlphabet;
		this.radian = radianAlpha/2;
		this.radianCounter = 0;
		this.passwords = new ArrayList<Integer>();
		
		for(int i=0;i<amountAlphabet;i++){
			passwords.add(i);
		}
		Collections.shuffle(passwords);
		
	}

	@Override
	public void draw(Graphics2D g2d) {      
        //things you draw after here will not be rotated
		AffineTransform at = new AffineTransform();
		at.rotate(radian, Resource.getImage("coin").getWidth() / 2, Resource.getImage("coin").getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage tempImage = new BufferedImage(Resource.getImage("coin").getWidth(), Resource.getImage("coin").getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = (Graphics2D) tempImage.getGraphics();
		gg.drawImage(Resource.getImage("coin"), op, 0, 0);
		RenderHelper.draw(g2d, tempImage, 100, 100, 100, 0, RenderHelper.LEFT|RenderHelper.TOP);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	public void enterSpacebar() {
		answerCounter++;

		if (answerCounter >= 3) {
			for (IRenderable renderable : RenderableHolder.getInstance().getNorthRenderableList()) {
				if (renderable instanceof Gateway && ((Gateway) renderable).getX() == ConfigurableOption.xGateway1) {
					((Gateway) renderable).setGateClose(false);
					ConfigurableOption.stageNow++;
					break;
				}
			}
		}
	}

	@Override
	public void update() {
		radianCounter++;

		if(InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)){
			if(n == passwords.get(answerCounter)){
				Resource.getAudio("gotitem").play();
				enterSpacebar();
			}else{
				Resource.getAudio("bump").play();
				zombieAppear();
			}
		}
		
		if(radianCounter >= TimeToCounter.getCounter(10)){
			radianCounter = 0;
			radian+=Math.PI/180;
			radian%=2*Math.PI;
			n = (int)(radian/(2*Math.PI/amountAlphabet));
		}
	}
	
	public void zombieAppear(){
		NorthScreenLogic.spawnZombie = true;

	}	

	public ArrayList<Integer> getPassword(){
		return passwords;

	}
	
	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public void setDestroying(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public void updateAnimation() {
		
	}

}
