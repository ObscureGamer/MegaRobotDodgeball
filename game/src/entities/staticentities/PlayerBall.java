package entities.staticentities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.Entity;
import entities.EntityManager;
import entities.creatures.Jock;
import entities.creatures.Player;
import game.Handler;
import gfx.Assets;
import tiles.Tile;
import worlds.World;

public class PlayerBall extends StaticEntity {
	
	int speed;
	double angle;
	
	double nx;
	double ny;
	
	public PlayerBall(Handler handler, float x, float y, double angle) {
		super(handler, x, y, Tile.TILE_WIDTH / 2, Tile.TILE_HEIGHT / 2);
		this.angle = angle;
		speed = 4;
		
		nx = (speed * Math.cos(angle));
		ny = (speed * Math.sin(angle));
		
	}

	@Override
	public void tick() {
		x+=nx;
		y+=ny;
		isOffscreen();
		checkProjectileCollisions(0f, 0f);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player_ball, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public void checkProjectileCollisions(float xOffset, float yOffset){
		for(Jock e : handler.getWorld().getEntityManager().getJock()){
			if(e.equals(this)){
				continue;
			}else if(e.getCollisionBounds(0f, 0f).intersects(getProjectileCollisionBounds(xOffset, yOffset))){
				handler.getWorld().killEnemy();
				handler.getWorld().getEntityManager().removeEntity(e);
				handler.getWorld().getEntityManager().removeEntity(this);
			}
		}
	}
	
	public Rectangle getProjectileCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset - 5), (int) (y +bounds.y - yOffset - 5), bounds.width + 10, bounds.height + 10);
	}
	
	private void isOffscreen(){
		if (x<0 || y<0 || x>handler.getWorld().getWidth() * 64 || y>handler.getWorld().getHeight() * 64)
			handler.getWorld().getEntityManager().removeEntity(this);
	}
}
