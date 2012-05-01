package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MenuGUI {
	
	JPanel verticalStrip;
	public MenuGUI() {
		final JFrame menuFrame = new JFrame();
		
		menuFrame.setTitle("Texas Scramble - Main Menu");
		menuFrame.setSize(1022,768);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setResizable(true);
		menuFrame.setLocationRelativeTo(null);
		
		Dimension top = new Dimension(0,200);
		Dimension padding = new Dimension(0,15);
		Box verticalStrip = Box.createVerticalBox();
		
		menuFrame.add(verticalStrip);
		
		ImageIcon i1 = new ImageIcon(this.getClass().getResource("../res/images/main_menu/play.png"));
		ImageIcon i2 = new ImageIcon(this.getClass().getResource("../res/images/main_menu/quit.png"));
		ImageIcon i3 = new ImageIcon(this.getClass().getResource("../res/images/main_menu/credits.png"));
		
		JButton singlePlayerButton = new JButton(i1);
		JButton quitButton = new JButton(i2);
		JButton creditsButton = new JButton(i3);
		
		singlePlayerButton.setBorder(null);
		quitButton.setBorder(null);
		creditsButton.setBorder(null);

		verticalStrip.add(Box.createRigidArea(top));
		verticalStrip.add(singlePlayerButton);
		singlePlayerButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		verticalStrip.add(Box.createRigidArea(padding)); // below
		verticalStrip.add(quitButton);
		quitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		verticalStrip.add(Box.createRigidArea(padding)); // below
		verticalStrip.add(creditsButton);
		creditsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		verticalStrip.setBackground(Color.BLACK);
		menuFrame.setVisible(true);
		verticalStrip.setVisible(true);
		menuFrame.revalidate();
	
		singlePlayerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new GameGUI();
				menuFrame.dispose();
			}
		});
		
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		creditsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args){
		new MenuGUI();
	}
}
