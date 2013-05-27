package game.entity.enemy;

import engine.logic.Direction;
import game.entity.basic.Shot;

public class EnemyShot extends Shot {

	public EnemyShot(float x, float y, Direction direction, double horizontalHitboxPercentage, double verticalHitboxPercentage ) {
		super(x, y, direction, horizontalHitboxPercentage, verticalHitboxPercentage);
	}

}
