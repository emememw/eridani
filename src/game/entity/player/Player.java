package game.entity.player;

import engine.entity.Entity;
import engine.input.InputManager;
import engine.logic.Direction;
import game.entity.basic.BasicEntity;
import game.entity.basic.EntityManager;
import game.entity.enemy.BasicEnemy;
import game.entity.enemy.EnemyShot;
import game.entity.npc.Healer;
import game.entity.player.equip.Axe;
import game.entity.player.equip.BasicEquipItem;
import game.entity.player.equip.EquipType;
import game.entity.player.equip.Sword;
import game.entity.player.loot.BasicLootItem;
import game.entity.player.loot.HeartContainer;
import game.map.MapManager;
import game.map.MapTransitionDirection;
import game.rendering.TextureManager;
import game.ui.textbox.HealerTextbox;
import game.ui.textbox.ItemAcquisitionTextbox;
import game.ui.textbox.TextboxManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;

public class Player extends BasicEntity {

	private float speed = 8;
	private Direction currentDirection = Direction.RIGHT;
	private int imageIndex1 = 11;
	private int imageIndex2 = 12;
	private int imageIndex = imageIndex1;
	private long walkTime = 0;
	
	private long shotTime = 0;
	
	
	private long invulnerableTime = 0;
	private int tickCount = 0;
	
	private boolean hasKey = false;
	
	private int coins = 0;
	
	private Map<EquipType, List<BasicEquipItem>> inventory = new HashMap<EquipType, List<BasicEquipItem>>();
	
	
	public Player(float x, float y) {
		super(x, y, 64, 64, 0.45, 1);
		this.hp = 6;
		this.currentHp = hp;
		
		this.addItemToInventory(new Sword());
		this.addItemToInventory(new Axe());
		
	}

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		float drawX = x;
		float drawY = y;
		boolean flip = false;

		if (this.currentDirection == Direction.LEFT) {
			flip = true;
		}

		/*if (currentHorizontalSpeed != 0) {
			drawX = x + (interpolation * currentHorizontalSpeed);

		}
		if (currentVerticalSpeed != 0) {
			drawY = y + (interpolation * currentVerticalSpeed);

		}*/
		if((invulnerableTime > 0 && tickCount % 10 == 2) || invulnerableTime == 0) { 
		TextureManager.getInstance().getSprites().get(imageIndex)
				.draw(drawX, drawY, getWidth(), getHeight(), flip);
		}
	}

	@Override
	protected void tickLogic(long delta) {
		tickCount++;

		if(invulnerableTime > 0) {
			invulnerableTime -= delta;
			if(invulnerableTime < 0) {
				invulnerableTime = 0;
			}
		}
		
		if(shotTime > 0) {
			shotTime -= delta;
			if(shotTime < 0) {
				shotTime = 0;
			}
		}
		
		if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_LCONTROL)
				&& !falling) {
			this.speed = 15;
		} else if (!falling) {
			this.speed =8;
		}

		if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_RIGHT)) {
			this.moveEntityHorizontal(Direction.RIGHT, this.speed);
			
			this.currentDirection = Direction.RIGHT;
		} else if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_LEFT)) {
			this.moveEntityHorizontal(Direction.LEFT, this.speed);
			this.currentDirection = Direction.LEFT;
		}

		if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_C) && shotTime == 0) {
			int xOffset = this.getWidth()/4;
			if(currentDirection == Direction.LEFT) {
				xOffset = -this.getWidth()/4;
			}
			EntityManager.getInstance().registerEntity(
					new PlayerShot(this.getX()+xOffset, this.getY(), currentDirection));
			InputManager.getInstance().resetKey(Keyboard.KEY_C);
			//shotTime = 300;
		}

		if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_X)
				&& !falling) {
			terminalVelocity = -jumpVelocity;
			InputManager.getInstance().resetKey(Keyboard.KEY_X);
		}
		
		if(this.getX() < 0) {
			MapManager.getInstance().nextRoom(MapTransitionDirection.LEFT);
		} else if(this.getX() > (MapManager.getInstance().getMap().length-1)*MapManager.getInstance().getTileSize()) {
			MapManager.getInstance().nextRoom(MapTransitionDirection.RIGHT);
		}
		
		if (this.currentHorizontalSpeed != 0 && !falling) {
			walkTime += delta;

			if (walkTime >= 170) {
				if (imageIndex == imageIndex1) {
					imageIndex = imageIndex2;
				} else if (imageIndex == imageIndex2) {
					imageIndex = imageIndex1;
				}
				walkTime = 0;
			}
		} else if(falling) {
			imageIndex = imageIndex2;
		} else {
			imageIndex = imageIndex1;
		}
	}
	
	@Override
	public void touchedByEntity(Entity entity) {
		if (invulnerableTime == 0 && (entity instanceof BasicEnemy || entity instanceof EnemyShot) && !(entity instanceof PlayerShot)) {
			
			this.currentHp--;
			invulnerableTime = 1000;
			if(currentHp <= 0) {
				//EffectManager.getInstance().registerEffect(new Fade(1280, 720, false));
				System.out.println("dead!");
			} 
		}
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	public void removeCoints(int coins) {
		this.coins -= coins;
	}

	public void addItemToInventory(BasicEquipItem basicEquipItem) {
		List<BasicEquipItem> inventoryPart = inventory.get(basicEquipItem.getEquipType());
		
		if( inventoryPart == null) {
			inventoryPart = new LinkedList<BasicEquipItem>();
			inventory.put(basicEquipItem.getEquipType(), inventoryPart);
		}
		
		inventoryPart.add(basicEquipItem);
		
	}
	
	public List<BasicEquipItem> getInventoryByEquipType(EquipType equipType) {
		return inventory.get(equipType);
	}
	
	public void equipItem(BasicLootItem basicLootItem) {
		basicLootItem.onEquip();
	}
	
	

}
