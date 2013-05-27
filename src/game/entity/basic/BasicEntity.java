package game.entity.basic;

import engine.entity.Entity;
import engine.logic.Coord;
import engine.logic.Direction;
import engine.logic.Tickable;
import engine.map.Tile;
import engine.rendering.Renderable;
import game.entity.player.Player;
import game.map.MapManager;

public abstract class BasicEntity extends Entity implements Renderable,
		Tickable {

	protected float currentHorizontalSpeed = 0;
	protected float currentVerticalSpeed = 0;

	protected float terminalVelocity = 0;
	protected boolean falling = true;

	protected int jumpVelocity = 17;

	protected int hp;
	protected int currentHp;

	public BasicEntity(float x, float y, int width, int height,
			double horizontalPercentage, double verticalHitboxPercentage) {
		super(x, y, width, height, horizontalPercentage,
				verticalHitboxPercentage);
	}

	@Override
	public void render(float x, float y, float interpolation, long delta) {

	}

	protected Coord checkCollision(int newX, int newY) {
		Coord result = null;
		for (int tileX = 0; tileX < MapManager.getInstance().getMap().length; tileX++) {
			for (int tileY = 0; tileY < MapManager.getInstance().getMap()[tileX].length; tileY++) {
				Tile tile = MapManager.getInstance().getMap()[tileX][tileY];
				if (!tile.isAccessible() || tile.isTouchable()) {
					if (this.intersects(newX, newY, this.getHitboxWidth(), this
							.getHitboxHeight(), tileX
							* MapManager.getInstance().getTileSize(), tileY
							* MapManager.getInstance().getTileSize(),
							MapManager.getInstance().getTileSize(), MapManager
									.getInstance().getTileSize())) {
						result = new Coord(tileX
								* MapManager.getInstance().getTileSize(), tileY
								* MapManager.getInstance().getTileSize());
						if (tile.isTouchable()) {
							tile.touchedByEntity(this);
							result = null;

						} else {
							break;
						}
					}
				}
			}
		}

		if (result == null) {
			for (int i = 0; i < EntityManager.getInstance().getEntities()
					.size(); i++) {
				if (EntityManager.getInstance().getEntities().get(i) != this
						&& !EntityManager.getInstance().getEntities().get(i)
								.isAccessible()) {
					Entity entity = EntityManager.getInstance().getEntities()
							.get(i);
					if (this.intersects(newX, newY, this.getHitboxWidth(),
							this.getHitboxHeight(), entity.getHitboxX(),
							entity.getHitboxY(), entity.getHitboxWidth(),
							entity.getHitboxHeight())) {
						result = new Coord(Float.valueOf(entity.getHitboxX())
								.intValue(), Float.valueOf(entity.getHitboxY())
								.intValue());
						entity.touchedByEntity(this);

					}
				}
			}
		}

		return result;
	}

	protected void checkEntityCollision() {

		for (int i = 0; i < EntityManager.getInstance().getEntities().size(); i++) {
			Entity entity = EntityManager.getInstance().getEntities().get(i);
			if (entity != this
					&& entity.intersects(this.getHitboxX(), this.getY(),
							this.getHitboxWidth(), this.getHitboxHeight(),
							entity.getHitboxX(), entity.getHitboxY(),
							entity.getHitboxWidth(), entity.getHitboxHeight())) {
				entity.touchedByEntity(this);
				this.touchingEntity(entity);
			}
		}
		if (!(this instanceof Player)) {
			Entity entity = EntityManager.getInstance().getPlayer();
			if (this.intersects(this.getHitboxX(), this.getY(),
					this.getHitboxWidth(), this.getHitboxHeight(),
					entity.getHitboxX(), entity.getHitboxY(),
					entity.getHitboxWidth(), entity.getHitboxHeight())) {
				entity.touchedByEntity(this);
				this.touchingEntity(entity);
			}
		}

	}

	public boolean moveEntityVertical(float speed) {

		boolean result = true;

		float newY = this.getY();

		newY += speed;
		//
		Coord verticalCollisionCoord = checkCollision((int) this.getHitboxX(),
				(int) calculateHitboxY(newY));
		if (verticalCollisionCoord != null) {
			if (speed > 0) {
				newY = verticalCollisionCoord.getY() - this.getHeight()
						+ (this.calculateHitboxY(newY) - newY);
			} else {
				newY = verticalCollisionCoord.getY()
						+ MapManager.getInstance().getTileSize()
						- (this.calculateHitboxY(newY) - newY) + 1;
			}
			result = false;
		}

		this.currentVerticalSpeed = newY - this.getY();
		this.setY(newY);

		return result;
	}

	public boolean moveEntityHorizontal(Direction direction, float speed) {

		boolean result = true;

		float newX = this.getX();

		if (direction == Direction.RIGHT) {
			newX += speed;
		} else if (direction == Direction.LEFT) {
			newX -= speed;
		}

		Coord horizontalCollisionCoord = checkCollision(
				(int) calculateHitboxX(newX), (int) this.getHitboxY());
		if (horizontalCollisionCoord != null) {
			if (direction == Direction.RIGHT) {
				newX = horizontalCollisionCoord.getX() - this.getWidth()
						+ (this.calculateHitboxX(newX) - newX) - 1;

			} else {
				newX = horizontalCollisionCoord.getX()
						+ MapManager.getInstance().getTileSize()
						- (this.calculateHitboxX(newX) - newX) + 1;
			}
			result = false;
		}
		this.currentHorizontalSpeed = newX - this.getX();
		this.setX(newX);

		return result;
	}

	protected void tickLogic(long delta) {

	}

	@Override
	public void tick(long delta) {

		this.currentHorizontalSpeed = 0;
		this.currentVerticalSpeed = 0;

		if (terminalVelocity != 0) {
			this.falling = this.moveEntityVertical(terminalVelocity);
			if (!this.falling && terminalVelocity < 0) {
				this.falling = true;
				terminalVelocity = 0;
			}
		}

		if (terminalVelocity < jumpVelocity && falling) {
			terminalVelocity++;
		} else if (!falling) {
			terminalVelocity = 1;
		}

		tickLogic(delta);

		checkEntityCollision();
		
		if(this.currentHp <= 0) {
			onDeath();
		}
	}
	
	protected void onDeath() {
		
	}

	public float getCurrentHorizontalSpeed() {
		return currentHorizontalSpeed;
	}

	public float getCurrentVerticalSpeed() {
		return currentVerticalSpeed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public void setCurrentHorizontalSpeed(float currentHorizontalSpeed) {
		this.currentHorizontalSpeed = currentHorizontalSpeed;
	}

	public void setCurrentVerticalSpeed(float currentVerticalSpeed) {
		this.currentVerticalSpeed = currentVerticalSpeed;
	}

	public float getTerminalVelocity() {
		return terminalVelocity;
	}

	public void setTerminalVelocity(float terminalVelocity) {
		this.terminalVelocity = terminalVelocity;
	}

	
	
}
