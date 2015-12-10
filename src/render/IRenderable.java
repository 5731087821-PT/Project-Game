package render;

import java.awt.Graphics2D;

public interface IRenderable {
	public void draw(Graphics2D g2d);
	public boolean isVisible();
	public void update();
	public int getX();
	public int getY();
	public int getZ();
}