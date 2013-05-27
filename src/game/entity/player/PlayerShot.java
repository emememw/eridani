package game.entity.player;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.EntityManager;
import game.entity.basic.Shot;
import game.entity.enemy.BasicEnemy;
import game.entity.object.BasicObject;

public class PlayerShot extends Shot {

	public PlayerShot(float x, float y, Direction direction) {
		super(x, y, direction, 1.0, 0.5);
		this.setRange(2);
	}

	@Override
	public void touchingEntity(Entity entity) {
		if(entity instanceof BasicEnemy || (entity instanceof BasicObject && !entity.isAccessible()))
			EntityManager.getInstance().unregisterEntity(this);
	}
	
}
