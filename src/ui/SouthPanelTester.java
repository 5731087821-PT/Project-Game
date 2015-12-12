package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import LogicGame.NorthScreenLogic;
import entity.Gateway;
import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;

public class SouthPanelTester extends JPanel{
	private JButton nextStage;
	private JButton mistake;
	private SouthScreen southScreen;
	private int count;
	
	public SouthPanelTester(SouthScreen SouthScreen){
		setLayout(new BorderLayout());
		
		this.count = 0;
		nextStage = new JButton("Next Stage");
		mistake = new JButton("Mistake");
		this.southScreen = SouthScreen;
		
		this.add(nextStage, BorderLayout.WEST);
		this.add(mistake, BorderLayout.EAST);
		this.add(SouthScreen, BorderLayout.CENTER);
		
		nextStage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(IRenderable renderable : RenderableHolder.getInstance().getNorthRenderableList()){
					if(renderable instanceof Gateway){
						if( !((Gateway) renderable).isGateClose() ){
							continue;
						}else if( ((Gateway) renderable).getX() == 260 && count == 0 ){
							((Gateway) renderable).setGateClose(false);
							count = 1;
							ConfigurableOption.stageNow = 1;
							break;
						}else if( ((Gateway) renderable).getX() == 450 && count == 1 ){
							((Gateway) renderable).setGateClose(false);
							ConfigurableOption.stageNow = 2;
							break;
						}
					}
				}
			}
		});
		
		mistake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NorthScreenLogic.spawnZombie = true;
			}
		});
	}
}
