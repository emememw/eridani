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

public class Slime extends BasicEnemy {

	private long hitTime = 0;
	private Direction currentDirection = Direction.RIGHT;
	private long tickCount = 0;
	private int imageIndex = 47;
	private int imageIndex1 = 47;
	private int imageIndex2 = 48;
	private long walkTime = 0;
	private int nextMoveTickModule = new Random().nextInt(50) + 80;

	private int speed = 5;
	
	public Slime(float x, float y) {
		super(x, y, 64, 64, 0.7, 1);
		this.hp = 4;
		this.jumpVelocity = 19;
		this.currentHp = this.hp;
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

		

		
		if (!falling && tickCount % nextMoveTickModule == 0) {
			this.terminalVelocity = -jumpVelocity;
			nextMoveTickModule = new Random().nextInt(50) + 80;
			if( EntityManager.getInstance().getPlayer().getX() > this.getX() || this.getX()-this.speed < 0 ) {
				currentDirection = Direction.RIGHT;
			} else if( EntityManager.getInstance().getPlayer().getX() < this.getX() || this.getX() + this.speed > (MapManager.getInstance().getMap().length-1)*MapManager.getInstance().getTileSize()) {
				currentDirection = Direction.LEFT;
			}
			
		} else if(falling) {
			boolean result = this.moveEntityHorizontal(this.currentDirection, speed);
			if(!result && EntityManager.getInstance().getPlayer().getX() > this.getX() || this.getX()-this.speed < 0 ) {
				currentDirection = Direction.RIGHT;
			} else if(!result && EntityManager.getInstance().getPlayer().getX() < this.getX() || this.getX() + this.speed > (MapManager.getInstance().getMap().length-1)*MapManager.getInstance().getTileSize()) {
				currentDirection = Direction.LEFT;
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
	public void touchedByEntity(Entity entity) {
		if (entity instanceof PlayerShot) {

			this.currentHp--;
			hitTime = 1000;

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

}
