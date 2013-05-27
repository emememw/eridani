package game.entity.object;

import org.lwjgl.input.Keyboard;

import engine.entity.Entity;
import engine.input.InputManager;
import engine.rendering.Renderable;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.map.MapManager;
import game.map.MapTransitionDirection;
import game.rendering.TextureManager;
import game.ui.SpeechBubbleArrowUp;
import game.ui.SpeechBubbleKey;

public class Door extends BasicObject {

	private Renderable speechBubble;
	
	public Door(float x, float y) {
		super(x, y, 64, 64, 1, 1);
		this.setzRenderIndex(-5);
	}
	
	public void tick(long delta) {
		speechBubble = null;
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(42).draw(x, y, false);
		
		if(speechBubble != null) {
			speechBubble.render(x+15, y-this.getHeight()-5, interpolation, delta);
		}
		
	}
	
	
	@Override
	public void touchedByEntity(Entity entity) {
		
		if(entity instanceof Player) {
			if(speechBubble == null) {
				if(EntityManager.getInstance().getPlayer().hasKey()) {
					speechBubble = new SpeechBubbleArrowUp();
				} else {
					speechBubble = new SpeechBubbleKey();
				}
			}
			
			if(InputManager.getInstance().isKeyPressed(Keyboard.KEY_UP) && EntityManager.getInstance().getPlayer().hasKey()) {
				EntityManager.getInstance().getPlayer().setHasKey(false);
				MapManager.getInstance().nextRoom(MapTransitionDirection.BOSS);
				
			}
			
		}
		
	}

}
