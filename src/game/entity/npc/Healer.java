package game.entity.npc;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import engine.entity.Entity;
import engine.input.InputManager;
import engine.logic.Direction;
import engine.rendering.Renderable;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.entity.player.loot.HeartContainer;
import game.map.MapManager;
import game.rendering.TextureManager;
import game.ui.SpeechBubbleArrowUp;
import game.ui.textbox.HealerTextbox;
import game.ui.textbox.TextboxManager;

public class Healer extends BasicNpc {

	private Direction currentDirection = Direction.RIGHT;
	private int imageIndex = 56;
	private int imageIndex1 = 56;
	private int imageIndex2 = 57;
	private long walkTime = 0;
	private int nextMoveTick = 50;
	private long tickCount = 0;
	private Direction moveDirection = null;
	private boolean showBubble;
	private Renderable speechBubble = new SpeechBubbleArrowUp();

	public Healer(float x, float y) {
		super(x, y, 64, 64, 1, 1);

	}

	@Override
	protected void tickLogic(long delta) {
		tickCount++;
		showBubble = false;
		
		if(tickCount % nextMoveTick == 0) {
			if(new Random().nextInt(100) < 30) {
			if(new Random().nextBoolean()) {
				moveDirection = Direction.RIGHT;
			} else {
				moveDirection = Direction.LEFT;
			}
			} else {
				moveDirection = null;
			}
		}
		
		if(this.moveDirection != null) {
			this.moveEntityHorizontal(moveDirection, 2);
			currentDirection = moveDirection;
		}

		
		
		if (this.currentHorizontalSpeed != 0 && !falling) {
			walkTime += delta;

			if (walkTime >= 170) {
				if (imageIndex == imageIndex1) {
					imageIndex = imageIndex2;
				} else if (imageIndex == imageIndex2) {
					imageIndex = imageIndex1;
				}
				walkTime = 0;
			}
		} else if (falling) {
			imageIndex = imageIndex2;
		} else {
			imageIndex = imageIndex1;
		}

	}
	
	@Override
	public void touchedByEntity(Entity entity) {
		if (entity instanceof Player) {
			showBubble = true;
			if(InputManager.getInstance().isKeyPressed(Keyboard.KEY_UP)) {
				TextboxManager.getInstance().showTextbox(new HealerTextbox());
			}
		}
	}
	

	@Override
	public void render(float x, float y, float interpolation, long delta) {

		if (currentDirection == Direction.RIGHT) {
			TextureManager.getInstance().getSprites().get(imageIndex)
					.draw(x, y, this.getWidth(), this.getHeight(), false);
		} else {
			TextureManager.getInstance().getSprites().get(imageIndex)
					.draw(x, y, this.getWidth(), this.getHeight(), true);
		}
		
		if(showBubble) {
			speechBubble.render(x+15, y-this.getHeight(), interpolation, delta);
		}

	}

}
