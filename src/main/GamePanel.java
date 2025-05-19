package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

@usage(usager = "This is an extension of JPanel" , classnumber = 2 )
public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	//Screen Settings
	final int originalTileSize = 24; //16x16 tile
	//need to scale this size since our screen has much higher resolution than the old monitors
	final int scale = 2; //
	
	public final int tileSize = originalTileSize * scale; //48 * 48
	//size displayed on the game screen
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	//FPS
	int FPS = 60;
	
	Color z = new Color(163, 122 , 88);
	
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public Player player = new Player(this, keyH);
	public Player player1 = new Player(this,keyH);
	
	TileManager tileM = new TileManager(this);
	
	
//	int playerX =100;
//	int playerY =100;
//	int playerSpeed = 4;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(z);
		this.setDoubleBuffered(true); //better rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread(){
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
//	
//	//game loop using sleep method
//	@Override
//	public void run() {
//
//		double drawInterval = 1000000000/FPS; //1000000000 nanoseconds/60 
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//	
//		while(gameThread != null) {
//			
//			//System.out.println("Game loop is running");
//			
//			//long currentTime = System.nanoTime();
//			//System.out.println("Current Time" + currentTime);
//			
//			//1. UPDATE : update info such as character position
//			update();
//			//2. DRAW : draw the screen with the updated information
//			repaint();
//			
//			
//			
//			try {
//				
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime +=drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	
	
	
	
	
	//delta/accumulator method
	public void run() {
		
		double drawInterval = 1000000000/FPS; //1000000000 nanoseconds/60 
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0 ;
		long drawCount = 0;
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta+= (currentTime - lastTime) / drawInterval;
			timer += currentTime - lastTime;
			
			lastTime = currentTime;
			
			if(delta>=1) {
			update();
			repaint();
			delta--;
			drawCount++;
			}
			
			
			
			if(timer >= 1000000000) {
				System.out.println("FPS:"+ drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	
	
	
	
	public void update() {
		
//		if(keyH.upPressed == true) {
//			playerY-=playerSpeed;
//			
//		}
//		else if(keyH.downPressed == true) {
//			playerY+=playerSpeed;
//		}
//		else if(keyH.leftPressed == true) {
//			playerX-=playerSpeed;
//		}
//		else if(keyH.rightPressed == true) {
//			playerX+=playerSpeed;
//		}
		player.update();
	
	
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//tiles are drawn first so draw method of tile is called first. It's like a layer.
		tileM.draw(g2);
		
		player.draw(g2);
		
		
		
//		
//		g2.setColor(Color.white);
//		g2.fillRect(playerX,playerY,tileSize,tileSize);
		
		g2.dispose();
	}

}


