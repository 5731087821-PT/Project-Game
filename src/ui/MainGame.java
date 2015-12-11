package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import entity.Gateway;
import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class MainGame extends JPanel{
	private JButton nextStage;
	private int count;
	
	public MainGame(){
		this.count = 0;
		nextStage = new JButton("Next Stage");
		this.add(nextStage);
		
		nextStage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(IRenderable renderable : RenderableHolder.getInstance().getRenderableList()){
					if(renderable instanceof Gateway){
						if( !((Gateway) renderable).isGateClose() ){
							continue;
						}else if( ((Gateway) renderable).getX() == 260 && count == 0 ){
							((Gateway) renderable).setGateClose(false);
							count = 1;
							break;
						}else if( ((Gateway) renderable).getX() == 450 && count == 1 ){
							((Gateway) renderable).setGateClose(false);
							break;
						}
					}
				}
			}
		});
	}
}
