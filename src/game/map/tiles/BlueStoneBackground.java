package game.map.tiles;

import game.rendering.TextureManager;

public class BlueStoneBackground extends BasicTile {

	public BlueStoneBackground() {
		super(true, null);
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		TextureManager.getInstance().getTiles().get(11).draw(x, y, false);
		
		
	}
	
}
