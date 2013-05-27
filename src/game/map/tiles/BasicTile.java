package game.map.tiles;

import engine.map.Tile;
import engine.rendering.Image2D;

public class BasicTile extends Tile {

	private boolean top;
	private boolean bottom;
	
	public BasicTile(boolean accessible, Image2D[] images) {
		super(accessible, images);
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}
	
	
	
	

}
