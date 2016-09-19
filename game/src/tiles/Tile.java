package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile wall = new Wall(1);
	
	public static final int TILE_WIDTH = 64,
							TILE_HEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int ID;
	
	public Tile(BufferedImage texture, int ID){
		this.texture=texture;
		this.ID=ID;
		
		tiles[ID] = this;
	}

	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getID() {
		return ID;
	}
	
}
