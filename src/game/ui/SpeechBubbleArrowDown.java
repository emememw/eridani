package game.ui;

import engine.rendering.Renderable;
import game.rendering.TextureManager;

public class SpeechBubbleArrowDown implements Renderable {

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(38)
		.draw(x, y, 64, 64, false);
		
	}

}
