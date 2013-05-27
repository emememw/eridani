package game.map.tiles;

import engine.rendering.Image2D;
import game.rendering.TextureManager;

public class Vine extends BasicTile {

	public Vine() {
		super(true, null);
		
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		TextureManager.getInstance().getTiles().get(3).draw(x, y, false);
		TextureManager.getInstance().getTiles().get(7).draw(x, y, false);
		
	}

}
