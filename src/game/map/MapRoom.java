package game.map;


import engine.entity.Entity;
import game.entity.enemy.Snake;
import game.entity.object.Elevator;
import game.map.tiles.BasicTile;

import java.util.LinkedList;
import java.util.List;

public class MapRoom {


	private BasicTile[][] tiles;
	private final List<Entity> entities = new LinkedList<Entity>();
	
	private boolean connectionUp, connectionDown, connectionLeft, connectionRight;
	
	private boolean visited;
	private boolean visible;
	
	private boolean hasDoor;
	private boolean hasKey;
	private boolean hasHealer;
	
	public MapRoom() {
	}
	
	
	public BasicTile[][] getTiles() {
		return tiles;
	}

	public void setTiles(BasicTile[][] tiles) {
		this.tiles = tiles;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void unregisterEntity(Entity entity) {
		if(entities.contains(entity)) {
			entities.remove(entity);
		}
	}
	
	
	public Elevator getElevator() {
		Elevator result = null;
		for(Entity entity : entities) {
			if(entity instanceof Elevator) {
				result = (Elevator) entity;
				break;
			}
		}
		return result;
	}

	public boolean isConnectionUp() {
		return connectionUp;
	}

	public void setConnectionUp(boolean connectionUp) {
		this.connectionUp = connectionUp;
	}

	public boolean isConnectionDown() {
		return connectionDown;
	}

	public void setConnectionDown(boolean connectionDown) {
		this.connectionDown = connectionDown;
	}

	public boolean isConnectionLeft() {
		return connectionLeft;
	}

	public void setConnectionLeft(boolean connectionLeft) {
		this.connectionLeft = connectionLeft;
	}

	public boolean isConnectionRight() {
		return connectionRight;
	}

	public void setConnectionRight(boolean connectionRight) {
		this.connectionRight = connectionRight;
	}
	
	public String getDebugRoomConnections() {
		String result = "";
		if(connectionDown) {
			result+= " down";
		}
		if(connectionLeft) {
			result += " left";
		}
		if(connectionRight) {
			result += " right";
		}
		if(connectionUp) {
			result += " up";
		}
		return result;
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited(boolean visited) {
		this.visited = visited;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isDoorKeyRoom() {
		return hasKey;
	}
	
	public boolean isDoorRoom() {
		return hasDoor;
	}
	
	public boolean isHealerRoom() {
		return hasHealer;
	}


	public void setHasDoor(boolean hasDoor) {
		this.hasDoor = hasDoor;
	}


	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}


	public void setHasHealer(boolean hasHealer) {
		this.hasHealer = hasHealer;
	}

	public Entity getEntityAtCoord(int x, int y) {
		Entity result = null;
		for(Entity entity : entities) {
			if(entity.getX() == x && entity.getY() == y) {
				result = entity;
				break;
			}
		}
		return result;
	}



	
	
	
	
	
	
	
	
	
}
