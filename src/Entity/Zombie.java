package Entity;

import java.awt.*;

import render.IRenderable;
import render.RenderableHolder;

public class Zombie implements IRenderable{
	protected int x;
	protected int y;
	protected boolean moving; 
	protected boolean destroyed;
	private int deadCounter;

	public Zombie() {
		this.x = -40;
		this.y = 120;
		this.moving = true;
	}

	public void update() {
		if(moving){
			x++;
		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(!destroyed){
			g2d.setColor(Color.DARK_GRAY);
			g2d.fillRect(x, y, 40, 40);
		}else{
			if(deadCounter == 0){
				deadCounter = 150;
			}else{
				deadCounter--;
			}
			
			if(deadCounter%25<12){
				g2d.setColor(Color.DARK_GRAY);
			}else{
				g2d.setColor(new Color(169,169,169,0));
			}
			g2d.fillRect(x, y, 40, 40);
			
			if(deadCounter == 0){
				RenderableHolder.getInstance().getRenderableList().remove(this);
			}
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
