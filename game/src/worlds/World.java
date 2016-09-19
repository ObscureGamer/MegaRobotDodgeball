package worlds;

import java.awt.Graphics;

import audio.Sound;
import entities.EntityManager;
import entities.creatures.Jock;
import entities.creatures.Player;
import entities.staticentities.Tree;
import entities.staticentities.UnclaimedBall;
import game.Handler;
import tiles.Tile;
import utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private boolean ismusicplaying = false;
	
	int pickshoot;
	long starttime;
	private boolean isRunning;
	private long startTime;
	private long elapsedTime;
	
	private EntityManager entityManager;
	
	private int enemies_left;
	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new Jock(handler, 64 * 21, 64 * 4, 128, 128, this));
		entityManager.addEntity(new Jock(handler, 64 * 21, 64 * 9, 128, 128, this));
		entityManager.addEntity(new Jock(handler, 64 * 21, 64 * 14, 128, 128, this));
		
		entityManager.addEntity(new UnclaimedBall(handler, 64 * 2, 64 * 2, 32, 32));
		entityManager.addEntity(new UnclaimedBall(handler, 64 * 2, 64 * 9, 32, 32));
		entityManager.addEntity(new UnclaimedBall(handler, 64 * 2, 64 * 16, 32, 32));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		isRunning = false;
		startTime = 0;
		
		enemies_left=3;
	}
	
	public void tick(){
		entityManager.tick();
		if (ismusicplaying == false){
			Sound.sound1.loop();
			ismusicplaying = true;
		}
		
		start();
		timer();
		
		if (enemies_left == 0)
			System.exit(0);
	}
	
	public void timer(){
		if(getElapsedTimeMillis() / 30000 >= 1){
			handler.getWorld().getEntityManager().addEntity(new UnclaimedBall(handler, 64 * 2, 64 * 2, 32, 32));
			handler.getWorld().getEntityManager().addEntity(new UnclaimedBall(handler, 64 * 2, 64 * 9, 32, 32));
			handler.getWorld().getEntityManager().addEntity(new UnclaimedBall(handler, 64 * 2, 64 * 16, 32, 32));
			reset();
		}
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.grassTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);		
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	
    public void start() {
        if (!isRunning) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }
    
    public void reset() {
        elapsedTime = 0;
        if (isRunning) {
            startTime = System.nanoTime();
        }
    }
    
    public long getElapsedTimeNanos() {
        if (isRunning) {
            return System.nanoTime() - startTime;
        }
        return elapsedTime;
    }
	
    public long getElapsedTimeMillis() {
        return getElapsedTimeNanos() / 1000000L;
    }
    
    public void killEnemy(){
    	enemies_left--;
    }

}
