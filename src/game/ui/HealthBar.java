package game.ui;

import game.entity.basic.EntityManager;
import game.rendering.TextureManager;

public class HealthBar {

	private static final HealthBar INSTANCE = new HealthBar();
	public static HealthBar getInstance() {
		return HealthBar.INSTANCE;
	}
	
	int hp = EntityManager.getInstance().getPlayer().getHp();
	int currentHp = EntityManager.getInstance().getPlayer().getCurrentHp();
	int healthImageSize = 64;
	int healthBarXPos = 0;
	int healthBarYPos = 5;
	
	int healthAmount = hp / 2;
	int currentHealthAmount = 0;
	
	
	public void renderHealthBar() {
		
		hp = EntityManager.getInstance().getPlayer().getHp();
		currentHp = EntityManager.getInstance().getPlayer().getCurrentHp();
		
		healthAmount = hp / 2;
		
		int xPos = 0;
		for(int i = 0; i < healthAmount; i++) {
			xPos = healthImageSize * i + healthBarXPos;
			TextureManager.getInstance().getSprites().get(24).draw(xPos, healthBarYPos, healthImageSize, healthImageSize, false);
		}

		
		currentHealthAmount = currentHp / 2;
		
		
		for(int i = 0; i < currentHealthAmount; i++) {
			xPos = healthImageSize * i + healthBarXPos;
			TextureManager.getInstance().getSprites().get(22).draw(xPos, healthBarYPos, healthImageSize, healthImageSize, false);
		}
		
		if(currentHp % 2 != 0) {
			xPos = healthImageSize * currentHealthAmount + healthBarXPos;
			TextureManager.getInstance().getSprites().get(23).draw(xPos, healthBarYPos, healthImageSize, healthImageSize, false);
		}
		
		
	}
}
