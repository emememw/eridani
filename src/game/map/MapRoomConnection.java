package game.map;

import engine.logic.Direction;

public class MapRoomConnection {

	private MapRoom mapRoom1;
	private MapRoom mapRoom2;
	private Direction direction;
	
	public MapRoomConnection(MapRoom mapRoom1, MapRoom mapRoom2, Direction direction) {
		this.mapRoom1 = mapRoom1;
		this.mapRoom2 = mapRoom2;
		this.direction = direction;
	}

	public MapRoom getMapRoom1() {
		return mapRoom1;
	}

	public void setMapRoom1(MapRoom mapRoom1) {
		this.mapRoom1 = mapRoom1;
	}

	public MapRoom getMapRoom2() {
		return mapRoom2;
	}

	public void setMapRoom2(MapRoom mapRoom2) {
		this.mapRoom2 = mapRoom2;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
	
}
