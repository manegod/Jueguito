package main;

import javax.swing.JFrame;

public class Main{
	
	
	public static void main (String[] args){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Manes first window uwu");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); //to see it	
		window.setLocationRelativeTo(null); //set location to the center of the screen
		window.setVisible(true);
		
		gamePanel.startGameThread();
		}
	}
