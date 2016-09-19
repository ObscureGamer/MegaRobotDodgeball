package entities.creatures;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.staticentities.DodgeBall;
import entities.staticentities.PlayerBall;
import game.Handler;
import gfx.Animation;
import gfx.Assets;

public class Player extends Creature {
	
	private Animation animdown;
	private Animation animup;
	private Animation animleft;
	private Animation animright;
	private String direction;
	private int has_ball;
	
	public static final int DEFAULT_HEALTH = 9;
	protected int health;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	bounds.x = 11 * DEFAULT_CREATURE_HEIGHT / Assets.getHeight();
	bounds.y = 18 * DEFAULT_CREATURE_WIDTH / Assets.getWidth();
	bounds.width = 10 * DEFAULT_CREATURE_WIDTH / Assets.getWidth() -1;
	bounds.height = 14 * DEFAULT_CREATURE_HEIGHT / Assets.getHeight() -1;
	health = DEFAULT_HEALTH;
	
	animdown = new Animation(500, Assets.player_down);
	animup = new Animation(500, Assets.player_up);
	animleft = new Animation(500, Assets.player_left);
	animright = new Animation(500, Assets.player_right);
	direction = "down";
	
	has_ball = 0;
	}

	public void tick() {
		animleft.tick();
		animright.tick();
		animup.tick();
		animdown.tick();
		isDead();
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		checkShoot();
	}
	
	private void checkShoot(){
		if (handler.getMouseManager().isLeftPressed() == true){
			if (has_ball > 0){
				double mx = handler.getMouseManager().getMouseX() - x;
				double my = handler.getMouseManager().getMouseY() - y;
				double angle = Math.atan2(my, mx);
				handler.getWorld().getEntityManager().addEntity(new PlayerBall(handler, x, y, angle));
				has_ball --;
			}
		}		
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		if(xMove != 0 || yMove != 0) {
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		}else{
			g.drawImage(getStandingDirection(direction), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		}
		//g.setColor(Color.red);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	private BufferedImage getStandingDirection(String dir){
		if (dir == "up"){
			return Assets.player_stand_up;
		}else if (dir == "down"){
			return Assets.player_stand_down;
		}else if (dir == "right"){
			return Assets.player_stand_right;
		}else{
			return Assets.player_stand_left;
		}
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if (xMove<0){
			direction = "left";
			return animleft.getCurrentFrame();
		}else if(xMove>0){
			direction = "right";
			return animright.getCurrentFrame();
		}else if(yMove<0){
			direction = "up";
			return animup.getCurrentFrame();
		}else{
			direction = "down";
			return animdown.getCurrentFrame();
		}
	}

	public void takeDamage(int i) {
		health = health - i;
	}
	
	public void isDead(){
		if (health <= 0)
			System.exit(0);
	}
	
	public void gainBall(){
		has_ball++;
	}

}