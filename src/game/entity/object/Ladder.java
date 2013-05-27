package game.entity.object;

import engine.entity.Entity;
import engine.input.InputManager;
import game.entity.basic.BasicEntity;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.rendering.TextureManager;

import org.lwjgl.input.Keyboard;

public class Ladder extends BasicObject {

	public Ladder(float x, float y) {
		super(x, y, 64, 64, 1, 1);
		
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(58).draw(x, y, false);
		
		
	}
	
	@Override
	public void tick(long delta) {
		
	}
	
	@Override
	public void touchedByEntity(Entity entity) {
		
		if(entity instanceof BasicEntity && entity.getY() > this.getY()) {
			BasicEntity basicEntity = (BasicEntity)entity;
			basicEntity.setFalling(false);
			basicEntity.setTerminalVelocity(-1);
			basicEntity.setY(this.getY()-basicEntity.getHeight()+1);
		}
		
		if(entity instanceof Player) {
			if(InputManager.getInstance().isKeyPressed(Keyboard.KEY_UP)) {
				EntityManager.getInstance().getPlayer().moveEntityVertical(-5);
				
			}
		}
	}
	
	

}
