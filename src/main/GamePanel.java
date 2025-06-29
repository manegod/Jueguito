package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// screen settings
	final int originalTileSize = 16; //16x16 default size
	//we're gonna scale it bc otherwise its gonna look really small
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48*48
	
	//how many tiles are we gonna display?
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;//768 pixel
	public final int screenHeight = tileSize * maxScreenRow; // 576
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 80; //to change refresh rate
	
	
	TileManager tileM =new TileManager(this);
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //in-game time
	public Player player = new Player(this, keyH);
	

	
	public GamePanel() {
		
		this.setPreferredSize (new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.pink);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}

	
	public void startGameThread () {
		gameThread = new Thread (this);
		gameThread.start ();
	}

	
	//for initializing gameThread
	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS ;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta--;
			}
			
		}
		
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
		
	}

}
