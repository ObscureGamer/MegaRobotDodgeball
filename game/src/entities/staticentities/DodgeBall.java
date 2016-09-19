package entities.staticentities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.Entity;
import entities.EntityManager;
import entities.creatures.Player;
import game.Handler;
import gfx.Assets;
import tiles.Tile;
import worlds.World;

public class DodgeBall extends StaticEntity {
	
	int speed;
	double angle;
	
	double nx;
	double ny;
	
	public DodgeBall(Handler handler, float x, float y, double angle) {
		super(handler, x, y, Tile.TILE_WIDTH / 2, Tile.TILE_HEIGHT / 2);
		this.angle = angle;
		speed = 4;
		
		nx = (speed * Math.cos(angle));
		ny = (speed * Math.sin(angle));
		
	}

	@Override
	public void tick() {
		isPlayerHit(handler.getGameCamera().getxOffset(), handler.getGameCamera().getyOffset());
		x+=nx;
		y+=ny;
		isOffscreen();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.dodge_ball, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public boolean isPlayerHit(float xOffset, float yOffset){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getProjectileCollisionBounds(xOffset, yOffset))){
			handler.getWorld().getEntityManager().getPlayer().takeDamage(3);
			handler.getWorld().getEntityManager().removeEntity(this);
			return true;
		}
		return false;
	}
	
	public Rectangle getProjectileCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset - 5), (int) (y +bounds.y - yOffset - 5), bounds.width + 10, bounds.height + 10);
	}
	
	private void isOffscreen(){
		if (x<0 || y<0 || x>handler.getWorld().getWidth() * 64 || y>handler.getWorld().getHeight() * 64)
			handler.getWorld().getEntityManager().removeEntity(this);
	}
	
}
