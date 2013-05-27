package game.entity.object;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.BasicEntity;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.map.MapManager;
import game.rendering.TextureManager;

public class DoorKey extends BasicObject {

	private float speed = 3;

	public DoorKey(float x, float y) {
		super(x, y, 64, 64, 1, 1);
		this.setzRenderIndex(-1);

	}

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(40).draw(x, y, false);
	}

	@Override
	public void touchedByEntity(Entity entity) {
		if (entity instanceof Player) {
			EntityManager.getInstance().getPlayer().setHasKey(true);
			EntityManager.getInstance().unregisterEntity(this);
			
		}
	}

	@Override
	public void tickLogic(long delta) {
		if (this.getY() == EntityManager.getInstance().getPlayer().getY()) {
			int xDistanceToPlayer = Double.valueOf(
					(EntityManager.getInstance().getPlayer().getX() - this
							.getX()) / MapManager.getInstance().getTileSize())
					.intValue();

			if (xDistanceToPlayer < 0 && xDistanceToPlayer > -2) {
				this.moveEntityHorizontal(Direction.LEFT, speed);
			} else if (xDistanceToPlayer > 0 && xDistanceToPlayer < 2) {
				this.moveEntityHorizontal(Direction.RIGHT, speed);
			} else if(xDistanceToPlayer == 0) {
				this.setX(this.getX()+speed);
			}
		}

	}

}
