package game.ui;

import engine.rendering.FontManager;
import game.entity.basic.EntityManager;
import game.rendering.TextureManager;

import org.newdawn.slick.Color;

public class CoinCounter {

	public static void renderCoinCounter() {
		FontManager.getInstance().renderString(String.valueOf(EntityManager.getInstance().getPlayer().getCoins()), 64, 78, 1f,
				TextureManager.getInstance().getFont(), Color.white);
	}

}
