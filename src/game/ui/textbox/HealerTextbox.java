package game.ui.textbox;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import engine.core.MigaGame;
import engine.input.InputManager;
import engine.rendering.FontManager;
import game.entity.basic.EntityManager;
import game.rendering.TextureManager;

public class HealerTextbox extends BasicTextbox {

	private int selectedOption = 0;
	private boolean showNotEnoughCoins = false;
	private boolean purchased = false;

	public void renderBackground() {
		MigaGame.drawRect(429, 119, 452, 202, Color.white);
		MigaGame.drawRect(430, 120, 450, 200, Color.black);
	}

	public void renderText() {
		if (!showNotEnoughCoins && !purchased) {
			FontManager.getInstance().renderString("Hello Adventurer, ", 460,
					150, 0.5f, TextureManager.getInstance().getFont(),
					Color.white);
			FontManager.getInstance().renderString("i can heal you for 15 ",
					460, 175, 0.5f, TextureManager.getInstance().getFont(),
					Color.white);
			FontManager.getInstance().renderString("yes, please ", 475, 250,
					0.5f, TextureManager.getInstance().getFont(), Color.white);
			FontManager.getInstance().renderString("no! ", 720, 250, 0.5f,
					TextureManager.getInstance().getFont(), Color.white);
			if (selectedOption == 0) {
				FontManager.getInstance().renderString(">", 460, 250, 0.5f,
						TextureManager.getInstance().getFont(), Color.white);
			} else {
				FontManager.getInstance().renderString(">", 705, 250, 0.5f,
						TextureManager.getInstance().getFont(), Color.white);
			}
		} else if (showNotEnoughCoins && !purchased) {
			FontManager.getInstance().renderString("Sorry, ", 460, 150, 0.5f,
					TextureManager.getInstance().getFont(), Color.white);
			FontManager.getInstance().renderString("you dont have enough ",
					460, 175, 0.5f, TextureManager.getInstance().getFont(),
					Color.white);
			FontManager.getInstance().renderString(">", 460, 250,
					0.5f, TextureManager.getInstance().getFont(), Color.white);
			FontManager.getInstance().renderString("ok ", 475, 250,
					0.5f, TextureManager.getInstance().getFont(), Color.white);

		} else {
			FontManager.getInstance().renderString("... ", 460, 150, 0.5f,
					TextureManager.getInstance().getFont(), Color.white);
			FontManager.getInstance().renderString("i hope you feel better now",
					460, 175, 0.5f, TextureManager.getInstance().getFont(),
					Color.white);
			FontManager.getInstance().renderString(">", 460, 250,
					0.5f, TextureManager.getInstance().getFont(), Color.white);
			FontManager.getInstance().renderString("leave", 475, 250,
					0.5f, TextureManager.getInstance().getFont(), Color.white);
		}
	}

	public void renderIcons() {
		if (!showNotEnoughCoins && !purchased) {
			TextureManager.getInstance().getSprites().get(52)
					.draw(760, 165, 32, 32, false);
		} else if(!purchased)  {
			TextureManager.getInstance().getSprites().get(52)
					.draw(760, 165, 32, 32, false);
		}

	}

	public void tick() {

		if (!showNotEnoughCoins && !purchased) {
			if (selectedOption != 1
					&& InputManager.getInstance().isKeyPressed(
							Keyboard.KEY_RIGHT)) {
				selectedOption = 1;
				InputManager.getInstance().resetKey(Keyboard.KEY_RIGHT);
			} else if (selectedOption != 0
					&& InputManager.getInstance().isKeyPressed(
							Keyboard.KEY_LEFT)) {
				selectedOption = 0;
				InputManager.getInstance().resetKey(Keyboard.KEY_LEFT);
			} else if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_X)) {
				if (selectedOption == 1) {
					TextboxManager.getInstance().hideTextbox();
				} else {
					if (EntityManager.getInstance().getPlayer().getCoins() >= 15) {
						
						EntityManager.getInstance().getPlayer().removeCoints(15);
						EntityManager.getInstance().getPlayer().setCurrentHp(EntityManager.getInstance().getPlayer().getHp());
						purchased = true;
						
					} else {
						showNotEnoughCoins = true;
					}
				}
				InputManager.getInstance().resetKey(Keyboard.KEY_X);
			}
		} else {
			if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_X)) {
				TextboxManager.getInstance().hideTextbox();
				InputManager.getInstance().resetKey(Keyboard.KEY_X);
			}
		}
	}

}
