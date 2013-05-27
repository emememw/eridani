package game.entity.object;

import org.lwjgl.input.Keyboard;

import engine.entity.Entity;
import engine.input.InputManager;
import engine.rendering.Renderable;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.entity.player.loot.HeartContainer;
import game.rendering.TextureManager;
import game.ui.SpeechBubbleArrowUp;

public class Chest extends BasicObject {

	private Renderable speechBubble = new SpeechBubbleArrowUp();
	private boolean showBubble = false;
	private boolean opened = false;
	
	public Chest(float x, float y) {
		super(x, y, 64, 64, 1, 1);
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		if(!opened) {
			TextureManager.getInstance().getSprites().get(54).draw(x, y, false);
			if(showBubble) {
				speechBubble.render(x+15, y-this.getHeight(), interpolation, delta);
			}
		} else {
			TextureManager.getInstance().getSprites().get(55).draw(x, y, false);
			
		}
		
	}
	
	@Override
	public void touchedByEntity(Entity entity) {
		if (!opened && entity instanceof Player) {
			showBubble = true;
			if(InputManager.getInstance().isKeyPressed(Keyboard.KEY_UP)) {
				this.opened = true;
				EntityManager.getInstance().getPlayer().equipItem(new HeartContainer());
			}
		}
	}
	
	@Override
	public void tickLogic(long delta) {
		showBubble = false;
	}
	

}
