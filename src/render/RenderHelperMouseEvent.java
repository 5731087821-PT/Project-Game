package render;

import resource.Resource;

public abstract class RenderHelperMouseEvent {
	public abstract void mouseClicked();
	public abstract void mousePressed();
	public abstract void mouseReleased();
	public abstract void mouseEntered();
	public abstract void mouseExited();
	public void mouseEnteredTrigger(){
		Resource.getAudio("punch").play();
	}
	public void mouseExitedTrigger(){
		
	}
//	public void mouseClicked(){
//		
//	}
//	public void mousePressed(){
//		
//	}
//	public void mouseReleased(){
//		
//	}
//	public void mouseEntered(){
//		
//	}
//	public void mouseExited(){
//		
//	}
}
