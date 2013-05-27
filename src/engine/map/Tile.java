package engine.map;

import java.util.LinkedList;
import java.util.List;

import engine.entity.Entity;
import engine.logic.Tickable;
import engine.rendering.Image2D;
import engine.rendering.Renderable;

public class Tile implements Renderable, Tickable {

	private List<Image2D> images = new LinkedList<Image2D>();
	private boolean accessible;
	private boolean touchable;
	
	public Tile(boolean accessible, Image2D ... images) {
		if(images != null) {
			for(Image2D image : images) {
				this.images.add(image);
			}
		}
		
		this.accessible = accessible;
	}

	

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		for(Image2D image : images) {
			image.draw(x, y, false);
		}
	}
	
	
	

	public boolean isTouchable() {
		return touchable;
	}

	public void setTouchable(boolean touchable) {
		this.touchable = touchable;
	}

	public void touchedByEntity(Entity entity) {
		
	}

	@Override
	public void tick(long delta) {
		
	}



	public List<Image2D> getImages() {
		return images;
	}

	public void addImage(Image2D image) {
		images.add(image);
	}

	
	
	
	
}
