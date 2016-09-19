package states;

import java.awt.Graphics;

import game.Handler;
import worlds.World;

public class GameState extends State {
	
	private World world;

	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
		handler.getGameCamera().move(0, 0);
	}
	
	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {	
		world.render(g);
	}

}
