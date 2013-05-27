package game.ui;

import engine.rendering.Renderable;
import game.rendering.TextureManager;

public class SpeechBubbleKey implements Renderable {

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(39)
		.draw(x, y, 64, 64, false);
		
	}

}
