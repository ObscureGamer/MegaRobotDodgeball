package tiles;

import gfx.Assets;

public class Wall extends Tile {

	public Wall(int ID) {
		super(Assets.wall, ID);
	}

	public boolean isSolid(){
		return true;
	}
}
