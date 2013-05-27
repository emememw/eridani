package game.entity.enemy;

import java.util.Random;

import org.newdawn.slick.Color;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.EntityManager;
import game.entity.object.Coin;
import game.entity.object.WoodenBox;
import game.entity.player.PlayerShot;
import game.map.MapManager;
import game.particle.Particle;
import game.particle.ParticleManager;
import game.rendering.TextureManager;

public class Snake extends BasicEnemy {

	public long moveTime = 0;
	public boolean moveFlag = false;
	public int nextCheck = new Random().nextInt(2000) + 1;
	public boolean stopFlag = false;
	private Direction currentDirection = Direction.RIGHT;
	private long walkTime = 0;

	private int imageIndex = 20;
	private int imageIndex1 = 20;
	private int imageIndex2 = 21;

	private float speed = 3;

	private long hitTime = 0;
	private int tickCount = 0;
	
	private Direction randomDirection;

	private boolean jumpFlag = false;

	public Snake(float x, float y) {
		super(x, y, 64, 64, 0.7, 1);
		this.hp = 4;
		this.currentHp = this.hp;

	}

	private boolean playerInSight() {

		boolean result = false;

		float yDistance = this.getY()
				- EntityManager.getInstance().getPlayer().getY();
		if (yDistance < 0) {
			yDistance *= -1;
		}
		yDistance /= MapManager.getInstance().getTileSize();
		
		
		float xDistance = this.getX()
				- EntityManager.getInstance().getPlayer().getX();
		if (xDistance < 0) {
			xDistance *= -1;
		}
		xDistance /= MapManager.getInstance().getTileSize();

		if (yDistance <= 4 && xDistance <= 5) {
			result = true;
		} 

		return result;
	}

	@Override
	protected void tickLogic(long delta) {
		tickCount++;

		if (hitTime > 0) {
			hitTime -= delta;
			if (hitTime < 0) {
				hitTime = 0;
			}
		}

		if (moveTime >= nextCheck) {
			moveTime = 0;
			nextCheck = new Random().nextInt(2000) + 1;
			if (new Random().nextInt(100) < 20 && !falling) {
				jumpFlag = true;
			}
			if(new Random().nextBoolean()) {
				randomDirection = Direction.LEFT;
			} else {
				randomDirection = Direction.RIGHT;
			}

		} else {
			moveTime += delta;
		}
		
		if (playerInSight()) {
			this.speed = 4;
			if (EntityManager.getInstance().getPlayer().getX() > this.getX()) {
				currentDirection = Direction.RIGHT;

			} else {

				currentDirection = Direction.LEFT;
			}
			boolean result = this.moveEntityHorizontal(currentDirection, speed);
			if (!result || jumpFlag) {
				this.terminalVelocity = -jumpVelocity;
				jumpFlag = false;
			}
		} else {
			this.speed = 1;
			if(randomDirection != null) {
				boolean result = this.moveEntityHorizontal(randomDirection, speed);
				currentDirection = randomDirection;
				if(!result) {
					randomDirection = null;
				}
			} else {
				if(new Random().nextBoolean()) {
					randomDirection = Direction.RIGHT;
				} else {
					randomDirection = Direction.LEFT;
				}
			}
		}

		if (this.currentHorizontalSpeed != 0 && !falling) {
			walkTime += delta;

			if (walkTime >= 170) {
				if (imageIndex == imageIndex1) {
					imageIndex = imageIndex2;
				} else if (imageIndex == imageIndex2) {
					imageIndex = imageIndex1;
				}
				walkTime = 0;
			}
		} else if (falling) {
			imageIndex = imageIndex2;
		} else {
			imageIndex = imageIndex1;
		}

	}

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		boolean colorBind = false;
		if (hitTime > 0 && tickCount % 10 > 0) {
			Color.decode("#ff0000").bind();
			colorBind = true;
		}

		if (currentDirection == Direction.RIGHT) {
			TextureManager.getInstance().getSprites().get(imageIndex)
					.draw(x, y, this.getWidth(), this.getHeight(), false);
		} else {
			TextureManager.getInstance().getSprites().get(imageIndex)
					.draw(x, y, this.getWidth(), this.getHeight(), true);
		}

		if (colorBind) {
			Color.white.bind();
		}
	}

	@Override
	public void touchedByEntity(Entity entity) {
		if (entity instanceof PlayerShot) {

			this.currentHp--;
			hitTime = 1000;

		} else if (entity instanceof WoodenBox) {
			this.currentHp = 0;
		}
		
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	@Override
	protected void onDeath() {
		EntityManager.getInstance().unregisterEntity(this);
		for (int i = 0; i < 30; i++) {
			ParticleManager.getInstance().registerParticle(
					new Particle(this.getX(), this.getY()));
		}
		//loot
		if(new Random().nextBoolean()) {
			MapManager.getInstance().getCurrentRoom().getEntities().add(new Coin(this.getX(), this.getY()));
		}
		
	}
		
	
	

}
