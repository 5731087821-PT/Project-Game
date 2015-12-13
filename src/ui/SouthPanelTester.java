package ui;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import LogicGame.NorthScreenLogic;
import entity.Gateway;
import entity.Player;
import render.IRenderable;
import render.RenderableHolder;
import utility.ConfigurableOption;
import utility.Resource;

public class SouthPanelTester extends JPanel{
	private JButton nextStage;
	private JButton mistake;
	private SouthScreen southScreen;
	private int count,threadCounter;
	private boolean threadStart = false;
	
	public SouthPanelTester(SouthScreen SouthScreen){
		setLayout(new BorderLayout());
		
		this.count = 0;
		this.threadCounter = 0;
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
				
				threadCounter = 0;//Count up
				if(!threadStart){
					new Thread(new Runnable() {
						public void run() {
							threadStart = true;
							
							Player player = null;
							
							ArrayList<IRenderable> list = (ArrayList<IRenderable>) RenderableHolder.getInstance().getNorthRenderableList();
							for(IRenderable thisOne : list){
								if(thisOne instanceof Player){
									player = (Player) thisOne;
								}
							}

							
							while(true){
								try {
									Thread.sleep(utility.ConfigurableOption.sleepTime);
								} catch (InterruptedException e) {}
								
								if(threadCounter==0){
									AudioClip bgm = Resource.getAudio("zombiedeath");
									bgm.play();	
								}else if(threadCounter==5){
//									player.animationCurrent.setFlip(true);
								}else if(threadCounter==30){
//									player.animationCurrent.setFlip(false);
									threadStart = false;
									break;
								}
								
								threadCounter++;
							}
							
						}
					}).start();

				}

			}
		});
	}

}
