package game.entity.enemy;

import java.util.Random;

import org.newdawn.slick.Color;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.BasicEntity;
import game.entity.basic.EntityManager;
import game.entity.basic.Shot;
import game.map.MapManager;
import game.particle.Particle;
import game.particle.ParticleManager;
import game.rendering.TextureManager;

public class SnakeBoss extends BasicEnemy {

	private Direction currentDirection = Direction.RIGHT;
	private long walkTime = 0;

	private int imageIndex = 20;
	private int imageIndex1 = 20;
	private int imageIndex2 = 21;

	private int tickCount = 0;
	private long hitTime = 0;
	private int speed = 6;

	private int state = 0;
	
	private long waitTime = 0;

	public SnakeBoss(float x, float y) {
		super(x, y, 256, 256, 0.9, 1);
		this.hp = 40;
		this.currentHp = hp;
		// TODO Auto-generated constructor stub
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
		
		if(waitTime > 0) {
			waitTime -= delta;
			if(waitTime < 0) {
				waitTime = 0;
			}
		}

		if(state == 0) {
			
			boolean result =  this.moveEntityHorizontal(currentDirection, speed);
			if(!result) {
				if(currentDirection == Direction.RIGHT) {
					currentDirection = Direction.LEFT;
				} else {
					currentDirection = Direction.RIGHT;
				}
			}
			
		} else 	if(state == 1) {
			currentDirection = Direction.RIGHT;
			boolean result =  this.moveEntityHorizontal(currentDirection, speed);
			if(!result) {
				currentDirection = Direction.LEFT;
				state = 3;
			}
		}
		
		if(state == 3) {
			int xFirst = new Random().nextInt(400)+70;
			for(int i = 0; i < 4; i++) {
				EntityManager.getInstance().getEntities().add(new Snake(xFirst + (i*80),70));
			}
			state = 4;
			waitTime = 10000;
		}
		
		if(state == 4 && waitTime == 0) {
			state = 0;
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
		if(state == 2 || state == 3 || state == 4) {
			Color.decode("#222222").bind();
			colorBind = true;
		} else if (hitTime > 0 && tickCount % 10 > 0) {
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
		if (entity instanceof Shot) {

			
			if(state == 0) {
				this.currentHp--;
				if(currentHp < this.hp && currentHp % 10 == 0) {
					state = 1;
				}
			}
			
			//
			hitTime = 1000;
			//speed++;

			if (currentHp <= 0) {
				EntityManager.getInstance().unregisterEntity(this);
				for (int i = 0; i < 30; i++) {
					ParticleManager.getInstance().registerParticle(
							new Particle(this.getX(), this.getY()));
					// MapManager.getInstance().getMap()[MapManager.getInstance().getMap().length-2][MapManager.getInstance().getMap()[0].length-6]
					// = new Door();
				}

			}
		}
	}

}
