package game.map.tiles;

import engine.map.Tile;
import engine.rendering.Image2D;
import game.rendering.TextureManager;

public class CaveBackground extends BasicTile{

	public CaveBackground() {
		super(true, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		TextureManager.getInstance().getTiles().get(3).draw(x, y, false);
		
		
	}


}
