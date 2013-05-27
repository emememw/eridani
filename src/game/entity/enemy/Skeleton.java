package game.entity.enemy;

import java.util.Random;

import org.newdawn.slick.Color;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.BasicEntity;
import game.entity.basic.EntityManager;
import game.entity.object.Coin;
import game.entity.object.WoodenBox;
import game.entity.player.PlayerShot;
import game.map.MapManager;
import game.particle.Particle;
import game.particle.ParticleManager;
import game.rendering.TextureManager;

public class Skeleton extends BasicEnemy {

	private long walkTime = 0;
	private int imageIndex = 15;
	private int imageIndex1 = 15;
	private int imageIndex2 = 16;
	private Direction currentDirection = Direction.RIGHT;
	private float speed = 2.5f;

	private long moveTime = 0;
	private long nextCheck = new Random().nextInt(2000) + 500;
	private boolean stop = false;

	private long shotTime = 0;

	private long hitTime = 0;

	private int tickCount = 0;

	private boolean lockedOn = false;

	public Skeleton(float x, float y) {
		super(x, y, 64, 64, 1, 1);
		this.hp = 7;
		this.currentHp = hp;

	}

	@Override
	protected void tickLogic(long delta) {
		tickCount++;
		hitTime -= delta;

		if (moveTime >= nextCheck && !lockedOn) {
			stop = false;
			Direction direction = Direction.RIGHT;
			int rnd = new Random().nextInt(100);
			if (rnd < 10) {
				stop = true;
			} else if (rnd < 60) {
				direction = Direction.LEFT;
			}
			currentDirection = direction;
			moveTime = 0;
			nextCheck = new Random().nextInt(2000) + 500;

		} else if(lockedOn ) {
			if(EntityManager.getInstance().getPlayer().getX() > this.getX()) {
				currentDirection = Direction.RIGHT;
			} else {
				currentDirection = Direction.LEFT;
			}
		} else {
			moveTime += delta;
		}

		boolean result = false;
		if (!stop) {
			result = this.moveEntityHorizontal(currentDirection, speed);
			if (!result && !falling) {
				this.terminalVelocity = -jumpVelocity;
			}
		}
		

		if (shotTime == 0) {

			

			boolean canShoot = false;
			if (EntityManager.getInstance().getPlayer().getX() > this.getX()
					&& currentDirection == Direction.RIGHT) {
				canShoot = true;
			} else if (EntityManager.getInstance().getPlayer().getX() < this
					.getX() && currentDirection == Direction.LEFT) {
				currentDirection = Direction.LEFT;
				canShoot = true;
			}

			if (canShoot) {
				lockedOn = true;
				int xOffset = this.getWidth()/4;
				if (currentDirection == Direction.LEFT) {
					xOffset = -this.getWidth()/4;
				}
				EntityManager.getInstance().registerEntity(
						new SkeletonShot(this.getX() + xOffset, this.getY(),
								currentDirection));
				shotTime = new Random().nextInt(1000) + 2000;
			}

		}  else {
			shotTime -= delta;
			if (shotTime < 0) {
				shotTime = 0;
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
					.draw(x, y, false);
		} else {
			TextureManager.getInstance().getSprites().get(imageIndex)
					.draw(x, y, true);
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
			PlayerShot shot = (PlayerShot) entity;
			if (((PlayerShot) entity).getDirection() == Direction.RIGHT) {
				this.currentDirection = Direction.LEFT;
			} else {
				this.currentDirection = Direction.RIGHT;
			}
			this.lockedOn = true;

		} else if (entity instanceof WoodenBox) {
			this.currentHp = 0;
		}
		
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
