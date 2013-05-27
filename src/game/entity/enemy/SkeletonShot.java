package game.entity.enemy;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.rendering.TextureManager;

public class SkeletonShot extends EnemyShot {

	private int imageIndex = 18;
	private int imageIndex1 = 18;
	private int imageIndex2 = 19;
	
	private long animTime = 0;
	
	public SkeletonShot(float x, float y, Direction direction) {
		super(x, y, direction, 1.0, 0.5);
		this.setSpeed(7);
		this.setRange(10);
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {

		if (this.getDirection() == Direction.RIGHT) {
			TextureManager.getInstance().getSprites().get(imageIndex)
					.draw(x + (interpolation * this.getSpeed()), y, false);
		} else {
			TextureManager.getInstance().getSprites().get(imageIndex)
					.draw(x - (interpolation * this.getSpeed()), y, true);
		}

	}
	
	public void tickLogic(long delta) {
		animTime += delta;
		
		if(animTime > 70) {
			animTime = 0;
			if(imageIndex == imageIndex1) {
				imageIndex = imageIndex2;
			} else {
				imageIndex = imageIndex1;
			}
		}
		
	}
	
	@Override
	public void touchingEntity(Entity entity) {
		if((entity instanceof Player))
			EntityManager.getInstance().unregisterEntity(this);
	}

}
