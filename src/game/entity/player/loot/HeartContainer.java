package game.entity.player.loot;

import game.entity.basic.EntityManager;
import game.ui.textbox.ItemAcquisitionTextbox;
import game.ui.textbox.TextboxManager;

public class HeartContainer extends BasicLootItem {

	public HeartContainer() {
		super("HeartContainer", "+ 1 Heart");
	}
	
	@Override
	public void onEquip() {
		TextboxManager.getInstance().showTextbox(new ItemAcquisitionTextbox(this));
		EntityManager.getInstance().getPlayer().setHp(EntityManager.getInstance().getPlayer().getHp()+2);
	}

}
