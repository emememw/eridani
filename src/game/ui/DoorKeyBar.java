package game.ui;

import game.entity.basic.EntityManager;
import game.rendering.TextureManager;

public class DoorKeyBar {

	public static void renderDoorKeyBar() {
		if(EntityManager.getInstance().getPlayer().hasKey()) {
			TextureManager.getInstance().getSprites().get(53).draw(2, 130, false);
		}
		
	}
	
}
