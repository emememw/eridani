package game.entity.object;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.map.MapManager;
import game.rendering.TextureManager;

public class Coin extends BasicObject {

	private long tickCount = 0;
	private int animIndex1 = 50;
	private int animIndex2 = 51;
	private int animIndex = animIndex1;
	
	public Coin(float x, float y) {
		super(x, y, 64, 64, 1, 1);
		
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(animIndex).draw(x, y, false);
	}

	@Override
	public void touchedByEntity(Entity entity) {
		if (entity instanceof Player) {
			EntityManager.getInstance().unregisterEntity(this);
			EntityManager.getInstance().getPlayer().addCoins(1);
		}
	}
	
	@Override
	public void tickLogic(long delta) {
		tickCount++;
		if(tickCount % 8 == 0) {
			if(animIndex == animIndex1) {
				animIndex = animIndex2;
			} else {
				animIndex = animIndex1;
			}
		}
	}
	

}
