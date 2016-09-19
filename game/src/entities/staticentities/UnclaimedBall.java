package entities.staticentities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import gfx.Assets;

public class UnclaimedBall extends StaticEntity{

	public UnclaimedBall(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	@Override
	public void tick() {
		playerGrabbed(0f, 0f);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player_ball, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void playerGrabbed(float xOffset, float yOffset){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getProjectileCollisionBounds(xOffset, yOffset))){
			handler.getWorld().getEntityManager().removeEntity(this);
			handler.getWorld().getEntityManager().getPlayer().gainBall();
		}
	}
	
	public Rectangle getProjectileCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset - 5), (int) (y +bounds.y - yOffset - 5), bounds.width + 10, bounds.height + 10);
	}
}
