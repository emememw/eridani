package game.map.tiles;

import engine.map.Tile;
import engine.rendering.Image2D;
import game.map.MapManager;
import game.rendering.TextureManager;

public class Dirt extends BasicTile {

	public Dirt() {
		super(false, null);
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		
		if(this.isTop()) {
			TextureManager.getInstance().getTiles().get(1).draw(x, y, false);
			TextureManager.getInstance().getTiles().get(4).draw(x, y-MapManager.getInstance().getTileSize(), false);
		} else if(this.isBottom()) {
			TextureManager.getInstance().getTiles().get(3).draw(x, y, false);
			TextureManager.getInstance().getTiles().get(2).draw(x, y, false);
		} else {
			
			TextureManager.getInstance().getTiles().get(1).draw(x, y, false);
		}
		
	}

}
