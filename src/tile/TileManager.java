package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	
	
	public TileManager(GamePanel gp) {
		
		this.gp=gp;
		
		tile = new Tile[10];
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		
		loadMap();
		
	}
	
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bridge.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/mud.png"));
			
		}  
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col =0;
			int row = 0;
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}	
					
				}
			br.close();
			}
		
		catch(Exception e) {
			
		}
	}
	
	
	public void draw(Graphics2D g2) {
		
		//not efficient tile drawing system
//		g2.drawImage(tile[0].image , 0 , 0 ,gp.tileSize,gp.tileSize, null );
//		g2.drawImage(tile[0].image , 48 , 0 ,gp.tileSize,gp.tileSize, null );
//		g2.drawImage(tile[0].image , 96 , 0 ,gp.tileSize,gp.tileSize, null );
		
		int worldCol = 0;
		int worldRow = 0;
//		int x = 0;
//		int y = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			//grid posn to pixel posn in world map
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			
			//pixel posn in world map to pixel posn in screen map... gp.player.screenX
			//offsets the posn from the top left corner
			//because the pixel is calculated from top left corner
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > (gp.player.worldX - gp.player.screenX) && 
			   worldX - gp.tileSize < (gp.player.worldX + gp.player.screenX) && 
			   worldY + gp.tileSize > (gp.player.worldY - gp.player.screenY) &&
			   worldY - gp.tileSize < (gp.player.worldY + gp.player.screenY)) {
			g2.drawImage(tile[tileNum].image, screenX ,screenY ,gp.tileSize,gp.tileSize,null);
			}
			
			worldCol ++;
			// x +=gp.tileSize;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				//x = 0;
				worldRow ++;
				//y += gp.tileSize;
				
			}
			
			
		}
		
		
		
	}
}
