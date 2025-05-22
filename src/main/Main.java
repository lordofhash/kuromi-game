package main;

import javax.swing.JFrame;


@usage(usager = "Main class that creates and bounds all the elements" , classnumber = 1 )
public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//This lets the window properly close when the user clicks 
		//on x 
		window.setResizable(false);
		window.setTitle("自分自身を見つける                                Find Yourself: An adventure game                 { Developed by Shiven Vasan }");
		  
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
	}
}