package game.ui.textbox;

import engine.core.MigaGame;
import engine.input.InputManager;
import engine.rendering.FontManager;
import game.entity.player.loot.BasicLootItem;
import game.rendering.TextureManager;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class ItemAcquisitionTextbox extends BasicTextbox {

	private BasicLootItem item;
	
	public ItemAcquisitionTextbox(BasicLootItem item) {
		this.item = item;
	}
	
	public void renderBackground() {
		MigaGame.drawRect(429, 119, 452, 202, Color.white);
		MigaGame.drawRect(430, 120, 450, 200, Color.black);
	}

	public void renderText() {
		FontManager.getInstance().renderString("item acquired!", 460, 150, 1f,
				TextureManager.getInstance().getFont(), Color.white);
		FontManager.getInstance().renderString(item.getName(), 530, 200, 0.7f,
				TextureManager.getInstance().getFont(), Color.white);
		FontManager.getInstance().renderString(item.getDescription(), 530, 240, 0.5f,
				TextureManager.getInstance().getFont(), Color.white);
		
		FontManager.getInstance().renderString("> ok", 460, 290, 0.5f,
				TextureManager.getInstance().getFont(), Color.white);
	}

	public void renderIcons() {
		
		TextureManager.getInstance().getSprites().get(22).draw(470, 180, 64, 64, false);

	}
	
	public void tick() {
		
		if(InputManager.getInstance().isKeyPressed(Keyboard.KEY_X)) {
			TextboxManager.getInstance().hideTextbox();
			InputManager.getInstance().resetKey(Keyboard.KEY_X);
		}
		
	}
	
}
