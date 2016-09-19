package game;

import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import worlds.World;

public class Handler {
	
	private Crux crux;
	private World world;
	
	public Handler(Crux crux){
		this.crux = crux;
	}
	
	public GameCamera getGameCamera(){
		return crux.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return crux.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return crux.getMouseManager();
	}
	
	public int getWidth(){
		return crux.getWidth();
	}
	
	public int getHeight(){
		return crux.getHeight();
	}

	public Crux getGame() {
		return crux;
	}

	public void setGame(Crux crux) {
		this.crux = crux;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}