package game.entity.enemy;

import game.entity.basic.BasicEntity;

public abstract class BasicEnemy extends BasicEntity{

	public BasicEnemy(float x, float y, int width, int height,
			double horizontalPercentage, double verticalHitboxPercentage) {
		super(x, y, width, height, horizontalPercentage, verticalHitboxPercentage);
	}

}
