package entities.creatures;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import entities.Entity;
import entities.EntityManager;
import entities.staticentities.DodgeBall;
import entities.staticentities.Tree;
import game.Handler;
import gfx.Assets;
import worlds.World;

public class Jock extends Creature {
	
	Random dice = new Random();
	int pickshoot;
	long starttime;
	private boolean isRunning;
	private long startTime;
	private long elapsedTime;
	private World world;

	public Jock(Handler handler, float x, float y, int width, int height, World world) {
		super(handler, x, y, width, height);
		this.world = world;
		isRunning = false;
		startTime = 0;
		world.getEntityManager().addJock(this);
	}

	@Override
	public void tick() {
		start();
		if(getElapsedTimeMillis() / 1000 >= 1){
			shoot();
			reset();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.jock_image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	private void shoot(){
		pickshoot = dice.nextInt(3);
		if (pickshoot == 0){
			handler.getWorld().getEntityManager().addEntity(new DodgeBall( handler, 1300, y+40, Math.toRadians(180)));			
		}else if (pickshoot == 1){
			handler.getWorld().getEntityManager().addEntity(new DodgeBall( handler, 1300, y+40, Math.toRadians(195)));
			handler.getWorld().getEntityManager().addEntity(new DodgeBall( handler, 1300, y+40, Math.toRadians(165)));	
		}else{
			handler.getWorld().getEntityManager().addEntity(new DodgeBall( handler, 1300, y+40, Math.toRadians(210)));
			handler.getWorld().getEntityManager().addEntity(new DodgeBall( handler, 1300, y+40, Math.toRadians(150)));	
		}
		
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

}
