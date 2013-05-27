package game.map.tiles;

import game.map.MapManager;
import game.rendering.TextureManager;

public class GrassyDirt extends BasicTile{

	public GrassyDirt() {
		super(false, null);
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		TextureManager.getInstance().getTiles().get(1).draw(x, y, false);
		if(this.isTop()) {
			TextureManager.getInstance().getTiles().get(6).draw(x, y-MapManager.getInstance().getTileSize()+17, false);
		}
		
	}

}
