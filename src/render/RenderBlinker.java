package render;

import java.awt.Graphics2D;

public abstract class RenderBlinker {
	protected boolean blink;
	protected Graphics2D g2d;
	public RenderBlinker(Graphics2D g2d,boolean blink){
		this.g2d = g2d;
		this.blink = blink;
		
	}
	public abstract void normal();
	public abstract void blinking();

}
