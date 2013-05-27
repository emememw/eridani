package game.ui;

import engine.rendering.Renderable;
import game.rendering.TextureManager;

public class SpeechBubbleArrowUpDown implements Renderable {

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(37)
		.draw(x, y, 64, 64, false);
		
	}

}
