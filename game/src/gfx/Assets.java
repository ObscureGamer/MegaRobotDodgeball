package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage wall, grass;
	public static BufferedImage tree, dodge_ball, player_ball;
	public static BufferedImage jock_image;
	public static BufferedImage player_stand_up, player_stand_down, player_stand_left, player_stand_right;

	
	public static BufferedImage[] player_down, player_up, player_right, player_left;
	public static BufferedImage[] menu_image;
	
	private static final int width = 32, height = 32;	
	
	public static void init(){
		SpriteSheet tilesheet=new SpriteSheet(ImageLoader.loadImage("/textures/TileSheet.png"));
		SpriteSheet creaturesheet = new SpriteSheet(ImageLoader.loadImage("/textures/CreatureSheet.png"));
		SpriteSheet staticsheet = new SpriteSheet(ImageLoader.loadImage("/textures/StaticSheet.png"));
		SpriteSheet uisheet = new SpriteSheet(ImageLoader.loadImage("/textures/UISheet.png"));
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		menu_image = new BufferedImage[2];
		
		player_down[0] = creaturesheet.crop(width * 0, height * 0, width, height);
		player_down[1] =creaturesheet.crop(width * 2, height * 0, width, height);
		player_up[0] =creaturesheet.crop(width * 0, height * 1, width, height);
		player_up[1] =creaturesheet.crop(width * 2, height * 1, width, height);
		player_right[0] =creaturesheet.crop(width * 0, height * 2, width, height);
		player_right[1] =creaturesheet.crop(width * 1, height * 2, width, height);
		player_right[2] =creaturesheet.crop(width * 2, height * 2, width, height);
		player_right[3] =creaturesheet.crop(width * 1, height * 2, width, height);
		player_left[0] =creaturesheet.crop(width * 0, height * 3, width, height);
		player_left[1] =creaturesheet.crop(width * 1, height * 3, width, height);
		player_left[2] =creaturesheet.crop(width * 2, height * 3, width, height);
		player_left[3] =creaturesheet.crop(width * 1, height * 3, width, height);
		player_stand_down = creaturesheet.crop(width * 1, height * 0, width, height);
		player_stand_up = creaturesheet.crop(width * 1, height * 1, width, height);
		player_stand_right = creaturesheet.crop(width * 1, height * 2, width, height);
		player_stand_left = creaturesheet.crop(width * 1, height * 3, width, height);
		jock_image = creaturesheet.crop(width * 3, height * 0, width, height);

		tree = staticsheet.crop(width * 0, height * 0, width, height);
		dodge_ball = staticsheet.crop(width * 1, height * 0, width, height);
		player_ball = staticsheet.crop(width * 2, height * 0, width, height);
		
		grass = tilesheet.crop(width * 0, height * 0, width, height);
		wall = tilesheet.crop(width * 1, height * 0, width, height);
		
		menu_image[0] = uisheet.crop(width * 0, height * 0, 64, height);
		menu_image[1] = uisheet.crop(width * 2, height * 0, 64, height);
		
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
	
}
