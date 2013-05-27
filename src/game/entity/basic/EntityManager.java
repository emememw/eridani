package game.entity.basic;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import engine.entity.Entity;
import engine.entity.EntityComparator;
import game.entity.player.Player;
import game.map.MapManager;

public class EntityManager {
	
	private static final EntityManager INSTANCE = new EntityManager();
	
	public static EntityManager getInstance() {
		return EntityManager.INSTANCE;
	}
	
	private EntityManager() {}
	
	private List<Entity> entities = new LinkedList<Entity>();
	private Player player;
	
	public void registerEntity(Entity entity) {
		entities.add(entity);
		Collections.sort(entities, new EntityComparator());
	}
	
	public void registerAll(Collection<Entity> entityCollection) {
		for(Entity entity : entityCollection) {
			registerEntity(entity);
		}
	}
	
	public void unregisterEntity(Entity entity) {
		MapManager.getInstance().getCurrentRoom().unregisterEntity(entity);
	}
	
	public List<Entity> getEntities() {
		return this.entities;
	}

	public Player getPlayer() {
		if(player == null) {
			player = new Player(-100,-100);
		}
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	

}
