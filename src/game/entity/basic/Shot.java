package game.entity.basic;

import engine.entity.Entity;
import engine.logic.Direction;
import game.map.MapManager;
import game.particle.Particle;
import game.particle.ParticleManager;
import game.particle.ShotParticle;
import game.rendering.TextureManager;

public class Shot extends BasicEntity {

	private float speed = 15;
	private float spawnX = 0;
	private int range = 2;

	private Direction direction;

	public Shot(float x, float y, Direction direction, double horizontalHitboxPercentage, double verticalHitboxPercentage ) {
		super(x, y, 64, 64, horizontalHitboxPercentage, verticalHitboxPercentage);
		this.direction = direction;
		this.spawnX = this.getX();
	}
	
	private void spawnDestructionParticles() {
		for(int i = 0; i < 10; i++) {
			int xOffset = this.getWidth();
			if(this.getDirection() == Direction.LEFT) {
				xOffset = 0;
			}
			ParticleManager.getInstance().registerParticle(new ShotParticle(this.getX()+xOffset, this.getY()));
		}
	}

	@Override
	public void tick(long delta) {
		
		float traveledRange = this.getX() - this.spawnX;
		if(traveledRange < 0 ) {
			traveledRange *= -1;
		}
		
		this.tickLogic(delta);
		if(traveledRange > range * MapManager.getInstance().getTileSize()) {
			EntityManager.getInstance().unregisterEntity(this);
			spawnDestructionParticles();
		} else {
		
			boolean result = this.moveEntityHorizontal(direction, this.speed);
			
			
			
			if (!result) {
				EntityManager.getInstance().unregisterEntity(this);
				spawnDestructionParticles();
			} else {
				this.checkEntityCollision();
			}
		}
	}
	

	@Override
	public void render(float x, float y, float interpolation, long delta) {

		if (direction == Direction.RIGHT) {
			TextureManager.getInstance().getSprites().get(8)
					.draw(x + (interpolation * this.speed), y, false);
		} else {
			TextureManager.getInstance().getSprites().get(8)
					.draw(x - (interpolation * this.speed), y, true);
		}

	}
	
	@Override
	public void touchedByEntity(Entity entity) {
	}
	
	@Override
	public void touchingEntity(Entity entity) {
		if(!(entity instanceof Shot))
			EntityManager.getInstance().unregisterEntity(this);
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	
	
	

}
