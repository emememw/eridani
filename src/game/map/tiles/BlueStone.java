package game.map.tiles;

import engine.rendering.Image2D;
import game.rendering.TextureManager;

public class BlueStone extends BasicTile {

	public BlueStone() {
		super(false, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		TextureManager.getInstance().getTiles().get(10).draw(x, y, false);
		
		
	}

}
