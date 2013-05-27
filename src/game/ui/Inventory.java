package game.ui;

import engine.core.MigaGame;
import engine.input.InputManager;
import engine.rendering.FontManager;
import game.core.GlobalStates;
import game.entity.basic.EntityManager;
import game.entity.player.equip.BasicEquipItem;
import game.entity.player.equip.EquipType;
import game.rendering.TextureManager;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class Inventory {

	private static final Inventory INSTANCE = new Inventory();

	public static Inventory getInstance() {
		return Inventory.INSTANCE;
	}

	private Inventory() {
	}

	private boolean visible;
	private int equipTypeCursorIndex;
	private EquipType selectedEquipType = null;
	private int itemCursorIndex;

	private List<BasicEquipItem> itemsWithinEquipType;

	public void tick() {

		if (selectedEquipType == null) {
			if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_DOWN)) {
				if (equipTypeCursorIndex + 1 < EquipType.values().length) {
					equipTypeCursorIndex++;
				} else {
					equipTypeCursorIndex = 0;
				}
				InputManager.getInstance().resetKey(Keyboard.KEY_DOWN);
			} else if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_UP)) {
				if (equipTypeCursorIndex - 1 >= 0) {
					equipTypeCursorIndex--;
				} else {
					equipTypeCursorIndex = EquipType.values().length - 1;
				}
				InputManager.getInstance().resetKey(Keyboard.KEY_UP);
			} else if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_X)) {
				selectedEquipType = EquipType.values()[equipTypeCursorIndex];
				itemsWithinEquipType = EntityManager.getInstance().getPlayer()
						.getInventoryByEquipType(selectedEquipType);
				InputManager.getInstance().resetKey(Keyboard.KEY_X);
			}

			if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_C)) {
				this.setVisible(false);
				InputManager.getInstance().resetKey(Keyboard.KEY_C);
				GlobalStates.getInstance().setGamePaused(false);
			}

		} else {
			if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_C)) {
				selectedEquipType = null;
				itemsWithinEquipType = null;
				InputManager.getInstance().resetKey(Keyboard.KEY_C);
			}

			if (itemsWithinEquipType != null) {
				if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_DOWN)) {
					if (itemCursorIndex + 1 < itemsWithinEquipType.size()) {
						itemCursorIndex++;
					} else {
						itemCursorIndex = 0;
					}
					InputManager.getInstance().resetKey(Keyboard.KEY_DOWN);
				} else if (InputManager.getInstance().isKeyPressed(
						Keyboard.KEY_UP)) {
					if (itemCursorIndex - 1 >= 0) {
						itemCursorIndex--;
					} else {
						itemCursorIndex = itemsWithinEquipType.size() - 1;
					}
					InputManager.getInstance().resetKey(Keyboard.KEY_UP);
				}
			}

		}

	}

	public void renderInventory() {

		float x = 64;
		float y = 300;

		MigaGame.drawRect(x - 30, y - 10, 265, 120, Color.black);

		for (int i = 0; i < EquipType.values().length; i++) {
			FontManager.getInstance().renderString(
					EquipType.values()[i].getName(), x, y + (20 * i), 0.5f,
					TextureManager.getInstance().getFont(), Color.white);
		}

		FontManager.getInstance().renderString(">", x - 15,
				y + (20 * equipTypeCursorIndex), 0.5f,
				TextureManager.getInstance().getFont(), Color.white);

		if (selectedEquipType != null) {
			MigaGame.drawRect(x + 240, y - 10, 265, 220, Color.black);

			if (itemsWithinEquipType != null) {
				for (int i = 0; i < itemsWithinEquipType.size(); i++) {

					FontManager.getInstance()
							.renderString(
									itemsWithinEquipType.get(i).getName(),
									x + 270, y + (20 * i), 0.5f,
									TextureManager.getInstance().getFont(),
									Color.white);

				}
			}

			FontManager.getInstance().renderString(">", x + 255,
					y + (20 * itemCursorIndex), 0.5f,
					TextureManager.getInstance().getFont(), Color.white);
		}

	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void toggleVisibility() {
		this.visible = !this.visible;
		if (this.visible) {
			GlobalStates.getInstance().setGamePaused(true);
		} else {
			GlobalStates.getInstance().setGamePaused(false);
		}
	}

}
