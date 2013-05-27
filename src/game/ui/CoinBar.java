package game.ui;

import engine.rendering.FontManager;
import game.rendering.TextureManager;

import org.newdawn.slick.Color;

public class CoinBar {

	public static void renderCoinBar() {
		TextureManager.getInstance().getSprites().get(52).draw(0, 60, 64, 64, false);
	}
	
}
