package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Main.ScreenManager;
import render.AnimationManager;
import render.RenderHelper;
import utility.ConfigurableOption;
import utility.Resource;

@SuppressWarnings("serial")
public class IntroScreen extends JComponent{
	
	private static final boolean CONTINUECHOICE = true;
	private static final boolean NEWPLAYCHOICE = false;
	private JLabel playButton;
	private JLabel continueButton;
	private JLabel exitButton;
	private AnimationManager introBG;
	private AnimationManager buttonAnimation;
	private BufferedImage img;
	private int width,height;

	public IntroScreen(){
		super();
		buttonAnimation = Resource.get("button");
		introBG = Resource.get("batman-intro");
		introBG.loop();
		width = introBG.getWidth();
		height = ConfigurableOption.screenHeight;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width, height));
		setVisible(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(false);
		playButton = new JLabel(new ImageIcon(buttonAnimation.getCurrentBufferedImage(0).getScaledInstance(50,80,Image.SCALE_DEFAULT)));
		playButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
//		continueButton = new JButton("CONTINUE");
//		continueButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ScreenManager.changeScreen(ScreenManager.GAMESCREEN);
//			}
//		});
//		
//		exitButton = new JButton("EXIT");
//		exitButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
		add(playButton);
//		add(continueButton);
//		add(exitButton);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		img = introBG.getCurrentBufferedImage();
		RenderHelper.draw( (Graphics2D)g, img, width/2, 0, 0, height, RenderHelper.TOP|RenderHelper.CENTER);
		introBG.update();
	}
	
	private void showDialog(boolean mode){
		
	}

}
