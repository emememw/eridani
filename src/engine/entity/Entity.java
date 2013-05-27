package engine.entity;

import engine.logic.Direction;
import engine.logic.Tickable;
import engine.rendering.Renderable;

public abstract class Entity implements Renderable, Tickable {

	private float x;
	private float y;
	private int width;
	private int height;
	private boolean accessible = true;
	
	private double horizontalHitboxPercentage;
	private double verticalHitboxPercentage;
	
	private int zRenderIndex = 1;

	public Entity(float x, float y, int width, int height, double horizontalHitboxPercentage, double verticalHitboxPercentage) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.horizontalHitboxPercentage = horizontalHitboxPercentage;
		this.verticalHitboxPercentage = verticalHitboxPercentage;
	}
	
	public boolean intersects(Entity entity) {
		return intersects(entity.getHitboxX(), entity.getY(), entity.getHitboxWidth(), entity.getHeight());
	}
	
	public boolean intersects(float x1, float y1, int width1, int height1, float x2, float y2, int width2, int height2) {
		
		
		int tw = width1;
		int th = height1;
		int rw = width2;
		int rh = height2;
		if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
			return false;
		}
		float tx = x1;
		float ty = y1;
		float rx = x2;
		float ry = y2;
		rw += rx;
		rh += ry;
		tw += tx;
		th += ty;
		return ((rw < rx || rw > tx) && (rh < ry || rh > ty)
				&& (tw < tx || tw > rx) && (th < ty || th > ry));

	}

	public boolean intersects(float x2, float y2, int width2, int height2) {
		
		
		int tw = getHitboxWidth();
		int th = this.height;
		int rw = width2;
		int rh = height2;
		if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
			return false;
		}
		float tx = this.getHitboxX();
		float ty = this.y;
		float rx = x2;
		float ry = y2;
		rw += rx;
		rh += ry;
		tw += tx;
		th += ty;
		return ((rw < rx || rw > tx) && (rh < ry || rh > ty)
				&& (tw < tx || tw > rx) && (th < ty || th > ry));

	}
	
	public void move(Direction direction, float speed) {
		
		float newX = this.x;
		float newY = this.y;
		
		if(direction == Direction.RIGHT) {
			newX += speed;
		} else if(direction == Direction.LEFT) {
			newX -= speed;
		} else if(direction == Direction.DOWN) {
			newY += speed;
		} else if(direction == Direction.UP) {
			newY -= speed;
		}
		
		this.x = newX;
		this.y = newY;
		
	}
	
	public int getHitboxWidth() {
		return Double.valueOf(this.width * this.horizontalHitboxPercentage).intValue();
	}
	
	public float getHitboxX() {
		//return this.x + (this.getHitboxWidth())/2;
		return calculateHitboxX(this.x);
	}
	
	public float calculateHitboxX(float xValue) {
		return  xValue + (this.width/2) - (this.getHitboxWidth()/2);
	}
	
	public int getHitboxHeight() {
		return Double.valueOf(this.height * this.verticalHitboxPercentage).intValue();
	}
	
	public float getHitboxY() {
		//return this.x + (this.getHitboxWidth())/2;
		return calculateHitboxY(this.y);
	}
	
	public float calculateHitboxY(float yValue) {
		return  yValue + (this.height/2) - (this.getHitboxHeight()/2);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void touchedByEntity(Entity entity) {
		
	}
	
	public void touchingEntity(Entity entity) {
		
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public int getzRenderIndex() {
		return zRenderIndex;
	}

	public void setzRenderIndex(int zRenderIndex) {
		this.zRenderIndex = zRenderIndex;
	}

	
	
	
}
