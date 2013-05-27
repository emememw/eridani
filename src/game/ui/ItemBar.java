package game.ui;

import game.rendering.TextureManager;


public class ItemBar {

	public static void renderWeaponBar() {
		
		TextureManager.getInstance().getSprites().get(25).draw(350, 20, 96, 96, false);
		TextureManager.getInstance().getSprites().get(8).draw(374, 40, 48, 48, false);
		
		TextureManager.getInstance().getSprites().get(25).draw(441, 20, 96, 96, false);
	}
	
}
