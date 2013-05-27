package game.map;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import engine.logic.Coord;
import engine.logic.Direction;
import engine.map.Tile;
import game.entity.basic.EntityManager;
import game.entity.enemy.Skeleton;
import game.entity.enemy.Slime;
import game.entity.enemy.Snake;
import game.entity.enemy.SnakeBoss;
import game.entity.npc.Healer;
import game.entity.object.Chest;
import game.entity.object.Door;
import game.entity.object.DoorKey;
import game.entity.object.Elevator;
import game.entity.object.ElevatorDirection;
import game.entity.object.Ladder;
import game.map.tiles.BasicTile;
import game.map.tiles.BlueStone;
import game.map.tiles.BlueStoneBackground;
import game.map.tiles.CaveBackground;
import game.map.tiles.Dirt;
import game.map.tiles.GrassyDirt;
import game.map.tiles.Vine;
import game.particle.ParticleManager;

public class MapManager {

	private static final MapManager INSTANCE = new MapManager();

	public static MapManager getInstance() {
		return MapManager.INSTANCE;
	}

	private int tileSize = 64;

	/*
	 * private Tile[][] map; private Tile[][] registeredMap;
	 * 
	 * 
	 * private List<Coord> spawns = new LinkedList<Coord>();
	 * 
	 * 
	 * 
	 * private int level = 1;
	 * 
	 * public void initMaps() {
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public void loadNextMap() {
	 * 
	 * if(level < 4) { generateMap(); level++; } else { generateSnakeBossRoom();
	 * level = 0; }
	 * 
	 * }
	 * 
	 * private MapManager() {
	 * 
	 * // generateMap(); }
	 * 
	 * private void generateMap() {
	 * 
	 * EntityManager.getInstance().getEntities().clear();
	 * ParticleManager.getInstance().getParticles().clear(); registeredMap = new
	 * Tile[50][13]; for (int x = 0; x < registeredMap.length; x++) { for (int y
	 * = 0; y < registeredMap[x].length; y++) {
	 * 
	 * registeredMap[x][y] = new Tile(null, true); if (y >=
	 * registeredMap[x].length - 4 || x == registeredMap.length - 1) {
	 * registeredMap[x][y] = new Tile(TextureManager.getInstance()
	 * .getTiles().get(2), false); } else if (x == 0 || y == 0) {
	 * registeredMap[x][y] = new Tile(TextureManager.getInstance()
	 * .getTiles().get(2), false); }
	 * 
	 * if (y == registeredMap[x].length - 5 && x != registeredMap.length -3) {
	 * if (new Random().nextInt(100) < 10) { registeredMap[x][y] = new
	 * Tile(TextureManager.getInstance() .getTiles().get(2), false); } } if
	 * (!registeredMap[x][y].isAccessible()) { checkFloorTile(x, y); }
	 * 
	 * if(y == registeredMap[x].length -5 && x == registeredMap.length-3) {
	 * registeredMap[x][y] = new Door(); } } }
	 * 
	 * // spawns
	 * 
	 * EntityManager.getInstance().getPlayer().setX(100);
	 * EntityManager.getInstance().getPlayer().setY(100);
	 * 
	 * 
	 * for (int i = 0; i < 5; i++) { int x = new Random().nextInt(2500)+1500;
	 * if(new Random().nextInt(100) < 20) {
	 * EntityManager.getInstance().registerEntity(new Skeleton(x, 100)); } else
	 * {
	 * 
	 * EntityManager.getInstance().registerEntity(new Snake(x, 100));
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * for (int i = 0; i < 15; i++) {
	 * EntityManager.getInstance().registerEntity( new WoodenBox(new
	 * Random().nextInt(3000) + 200, new Random().nextInt(200) + 240)); } // }
	 * 
	 * public void generateDemoRoom() {
	 * EntityManager.getInstance().getEntities().clear();
	 * ParticleManager.getInstance().getParticles().clear(); registeredMap = new
	 * Tile[20][13]; for (int x = 0; x < registeredMap.length; x++) { for (int y
	 * = 0; y < registeredMap[x].length; y++) {
	 * 
	 * registeredMap[x][y] = new Tile(null, true); if (y >=
	 * registeredMap[x].length - 5 || x == registeredMap.length - 1) {
	 * registeredMap[x][y] = new Tile(TextureManager.getInstance()
	 * .getTiles().get(2), false); } else if (x == 0 || y == 0) {
	 * registeredMap[x][y] = new Tile(TextureManager.getInstance()
	 * .getTiles().get(2), false); }
	 * 
	 * 
	 * 
	 * if (!registeredMap[x][y].isAccessible()) { checkFloorTile(x, y); } } }
	 * 
	 * EntityManager.getInstance().getPlayer().setX(100);
	 * EntityManager.getInstance().getPlayer().setY(100);
	 * EntityManager.getInstance().registerEntity(new SnakeChampion(300, 100));
	 * }
	 * 
	 * public void generateSnakeBossRoom() {
	 * EntityManager.getInstance().getEntities().clear();
	 * ParticleManager.getInstance().getParticles().clear(); registeredMap = new
	 * Tile[20][13]; for (int x = 0; x < registeredMap.length; x++) { for (int y
	 * = 0; y < registeredMap[x].length; y++) {
	 * 
	 * registeredMap[x][y] = new Tile(null, true); if (y >=
	 * registeredMap[x].length - 5 || x == registeredMap.length - 1) {
	 * registeredMap[x][y] = new Tile(TextureManager.getInstance()
	 * .getTiles().get(2), false); } else if (x == 0 || y == 0) {
	 * registeredMap[x][y] = new Tile(TextureManager.getInstance()
	 * .getTiles().get(2), false); }
	 * 
	 * // if(x == 2 && y == registeredMap[x].length - 5) { registeredMap[x][y] =
	 * new Tile(null, true); } // if(x > 1 && x < registeredMap.length-2 && y ==
	 * registeredMap[x].length-4) { registeredMap[x][y] = new Tile(null, true);
	 * } // if(x == registeredMap.length-3 && y == registeredMap[x].length - 5)
	 * { registeredMap[x][y] = new Tile(null, true); } //
	 * 
	 * if (!registeredMap[x][y].isAccessible()) { checkFloorTile(x, y); } } }
	 * 
	 * EntityManager.getInstance().getPlayer().setX(100);
	 * EntityManager.getInstance().getPlayer().setY(100);
	 * EntityManager.getInstance().registerEntity(new SnakeBoss(300, 100)); }
	 * 
	 * public List<Coord> getSpawns() { return spawns; }
	 * 
	 * public Tile[][] getMap() { return map; }
	 * 
	 * public int getTileSize() { return tileSize; }
	 * 
	 * public Tile[][] getRegisteredMap() { return registeredMap; }
	 * 
	 * public void checkRegisteredMaps() { if(registeredMap != null) { this.map
	 * = registeredMap; registeredMap = null; } }
	 * 
	 * public int getLevel() { return level; }
	 */

	private MapRoom[][] rooms;

	private int currentMapXIndex = -1;
	private int currentMapYIndex = -1;

	private int nextMapXIndex = -1;
	private int nextMapYIndex = -1;

	private MapTransitionDirection currentMapTransitionDirection;
	private boolean loadBossRoom = false;
	private MapRoom currentMapRoom;

	public void initMap() {

		generateRooms();

		// player spawn
		List<Coord> possiblePlayerSpawns = new LinkedList<Coord>();
		for (int x = 0; x < rooms.length; x++) {
			for (int y = 0; y < rooms[x].length; y++) {
				if (rooms[x][y] != null) {
					possiblePlayerSpawns.add(new Coord(x, y));
				}
			}
		}
		Collections.shuffle(possiblePlayerSpawns);
		Coord playerSpawn = possiblePlayerSpawns.get(0);

		
		
		loadNextRoom(playerSpawn.getX(), playerSpawn.getY());
		
	}

	public void nextRoom(MapTransitionDirection mapTransitionDirection) {

		this.currentMapTransitionDirection = mapTransitionDirection;

		nextMapXIndex = currentMapXIndex;
		nextMapYIndex = currentMapYIndex;

		if (mapTransitionDirection == MapTransitionDirection.RIGHT) {
			nextMapXIndex++;
		} else if (mapTransitionDirection == MapTransitionDirection.LEFT) {
			nextMapXIndex--;
		} else if (mapTransitionDirection == MapTransitionDirection.UP) {
			nextMapYIndex--;
		} else if (mapTransitionDirection == MapTransitionDirection.DOWN) {
			nextMapYIndex++;
		} else if (mapTransitionDirection == MapTransitionDirection.BOSS) {
			loadBossRoom = true;
			nextMapXIndex = 0;
			nextMapYIndex = 0;
		}

		System.out.println("next room will be : " + nextMapXIndex + " "
				+ nextMapYIndex);
	}

	private void handleRoomVisibility(int xIndex, int yIndex) {

		MapRoom room = rooms[xIndex][yIndex];
		room.setVisible(true);
		room.setVisited(true);

		if (room.isConnectionRight()) {
			rooms[xIndex + 1][yIndex].setVisible(true);
		}
		if (room.isConnectionLeft()) {
			rooms[xIndex - 1][yIndex].setVisible(true);
		}
		if (room.isConnectionDown()) {
			rooms[xIndex][yIndex + 1].setVisible(true);
		}
		if (room.isConnectionUp()) {
			rooms[xIndex][yIndex - 1].setVisible(true);
		}

	}

	private void loadNextRoom(int xIndex, int yIndex) {

		// resetElevator
		if (currentMapXIndex != -1 && currentMapYIndex != -1) {
			Elevator previousElevator = rooms[currentMapXIndex][currentMapYIndex]
					.getElevator();
			if (previousElevator != null) {
				previousElevator.resetToInitialPosition();
			}
		}

		currentMapXIndex = xIndex;
		currentMapYIndex = yIndex;

		MapRoom currentRoom = rooms[currentMapXIndex][currentMapYIndex];
		handleRoomVisibility(xIndex, yIndex);

		ParticleManager.getInstance().getParticles().clear();
		EntityManager.getInstance().setEntities(currentRoom.getEntities());

		// EntityManager.getInstance().registerAll(currentRoom.getEntities());

		if (currentMapTransitionDirection == MapTransitionDirection.UP) {
			Elevator elevator = currentRoom.getElevator();
			elevator.setY(currentRoom.getTiles()[0].length * tileSize);
			elevator.setDestinationY((currentRoom.getTiles()[0].length - 4)
					* tileSize + 3);
			EntityManager.getInstance().getPlayer().setX(elevator.getX());
			EntityManager.getInstance().getPlayer()
					.setY(elevator.getY() - elevator.getHeight());
			System.out.println("setting player y to " + elevator.getY() + " - "
					+ elevator.getHeight());

		} else if (currentMapTransitionDirection == MapTransitionDirection.DOWN) {
			Elevator elevator = currentRoom.getElevator();
			elevator.setY(2 * tileSize);
			elevator.setDestinationY((currentRoom.getTiles()[0].length - 4)
					* tileSize - 3);
			EntityManager.getInstance().getPlayer().setX(elevator.getX());
			EntityManager.getInstance().getPlayer()
					.setY(elevator.getY() - elevator.getHeight());
		} else if (currentMapTransitionDirection == MapTransitionDirection.RIGHT) {
			EntityManager.getInstance().getPlayer().setX(0);
			EntityManager.getInstance().getPlayer()
					.setY((currentRoom.getTiles()[0].length - 5) * tileSize);
		} else if (currentMapTransitionDirection == MapTransitionDirection.LEFT) {
			EntityManager.getInstance().getPlayer()
					.setX((currentRoom.getTiles().length - 1) * tileSize);
			EntityManager.getInstance().getPlayer()
					.setY((currentRoom.getTiles()[0].length - 5) * tileSize);
		}

		else {
			EntityManager.getInstance().getPlayer().setX(70);
			EntityManager.getInstance().getPlayer().setY(200);
		}

		this.currentMapRoom = currentRoom;

	}

	private void beautifyFloorTile(int x, int y, BasicTile[][] map) {
		if (y - 1 >= 0 && map[x][y - 1] != null && map[x][y - 1].isAccessible()) {
			map[x][y].setTop(true);

		}
		if (y < map[x].length - 1 && !map[x][y].isAccessible()
				&& map[x][y + 1] != null && map[x][y + 1].isAccessible()) {
			map[x][y].setBottom(true);
		}

		if (y > 0
				&& (map[x][y - 1].isBottom() || map[x][y - 1] instanceof Vine)) {
			if (new Random().nextInt(100) < 30) {
				map[x][y] = new Vine();
			}
		}

	}

	private MapRoom generateTiles(MapRoom mapRoom, int roomMapXIndex,
			int roomMapYIndex) {

		int width = new Random().nextInt(20) + 30;
		int height = 13;

		int elevatorX = new Random().nextInt(width - 2) + 1;

		BasicTile[][] map = new BasicTile[width][height];
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {

				map[x][y] = new CaveBackground();
				if (y >= map[x].length - 3 || x == map.length - 1) {
					if (new Random().nextInt(100) < 10) {
						map[x][y] = new GrassyDirt();
					} else {
						map[x][y] = new Dirt();
					}
				} else if (x == 0 || y == 0) {
					map[x][y] = new Dirt();
				}

				

				/*if(y == map[x].length-4 && new Random().nextInt(100) < 20) {
					map[x][y] = new Dirt();
				}
				if(y < map[x].length-4 && x > 0 && !map[x-1][y+1].isAccessible() && new Random().nextInt(100) < 10 && y > 3 && x < map.length/2) {
					map[x][y] = new Dirt();
				}
				if(x > 2 && !map[x-1][y].isAccessible()) {
					map[x][y] = new Dirt();
				}*/
				
				
				
				

				// Elevator Path Down
				if (x == elevatorX && y > 0
						&& roomMapYIndex < rooms[0].length - 1
						&& mapRoom.isConnectionDown()) {
					map[x][y] = new CaveBackground();

				} else if (x == elevatorX && y < 2 && roomMapYIndex > 0
						&& mapRoom.isConnectionUp()) {
					map[x][y] = new CaveBackground();
				}
				if (y <= map[x].length - 4
						&& x == elevatorX
						&& (mapRoom.isConnectionDown() || mapRoom
								.isConnectionUp())) {

					ElevatorDirection elevatorDirection = null;
					if (mapRoom.isConnectionDown() && mapRoom.isConnectionUp()) {
						elevatorDirection = ElevatorDirection.UPDOWN;
					} else if (mapRoom.isConnectionDown()) {
						elevatorDirection = ElevatorDirection.DOWN;
					} else if (mapRoom.isConnectionUp()) {
						elevatorDirection = ElevatorDirection.UP;
					}
					
					if(y == map[x].length-4) {
					mapRoom.getEntities().add(
							new Elevator(x * tileSize, y * tileSize + 1,
									elevatorDirection));
					}
					map[x][y] = new CaveBackground();
				}

				// Left Door
				if (x == 0 &&  roomMapXIndex > 0 && mapRoom.isConnectionLeft() && y > 3 && y < map[x].length-4) {
					map[x][y] = new CaveBackground();
				}
				// Right Door
				if (x == map.length - 1 
						&& roomMapXIndex < rooms.length - 1
						&& mapRoom.isConnectionRight() && y < map[x].length-4) {
					map[x][y] = new CaveBackground();
				}

			}
		}

		
		
		//tidy floor
		if(mapRoom.isConnectionRight()) {
		for (int x = map.length-1; x >= 0; x--) {
			for (int y = 0; y < map[x].length; y++) {
				
				if(x == map.length -1 && y > 3 && y < map[x].length-4) {
					map[x][y] = new CaveBackground();
				} else if(x == map.length -2 && y > 3 && y < map[x].length-5) {
					map[x][y] = new CaveBackground();
				} else if(x == map.length -3 && y > 3 && y < map[x].length-6) {
					map[x][y] = new CaveBackground();
				} else if(x == map.length -4 && y > 3 && y < map[x].length-7) {
					map[x][y] = new CaveBackground();
				} else if(x == map.length -5 && y > 3 && y < map[x].length-8) {
					map[x][y] = new CaveBackground();
				}
				
			}
		}
		}
		
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				beautifyFloorTile(x, y, map);
			}
		}
		
		

		mapRoom.setTiles(map);
		return mapRoom;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	private Tile[][] getEmptyMap() {
		Tile[][] result = new Tile[1][1];
		for (int x = 0; x < result.length; x++) {
			for (int y = 0; y < result.length; y++) {
				result[x][y] = new Tile(true, null);
			}
		}
		return result;
	}

	public Tile[][] getMap() {
		Tile[][] result = getEmptyMap();
		if (currentMapXIndex > -1 && currentMapYIndex > -1) {
			result = rooms[currentMapXIndex][currentMapYIndex].getTiles();
		}
		return result;
	}

	public void loadNextRoomIfNecessary() {
		if (loadBossRoom) {
			loadSnakeBossLevel();
			loadBossRoom = false;
		} else if (nextMapXIndex != -1 && nextMapYIndex != -1) {
			loadNextRoom(nextMapXIndex, nextMapYIndex);
			nextMapXIndex = -1;
			nextMapYIndex = -1;
		}
	}

	public MapRoom[][] getRooms() {
		return rooms;
	}

	public int getCurrentMapXIndex() {
		return currentMapXIndex;
	}

	public int getCurrentMapYIndex() {
		return currentMapYIndex;
	}

	private List<MapRoom> getAllMapRooms() {
		List<MapRoom> allRooms = new LinkedList<MapRoom>();
		for (int x = 0; x < rooms.length; x++) {
			for (int y = 0; y < rooms[x].length; y++) {
				if (rooms[x][y] != null) {
					allRooms.add(rooms[x][y]);
				}
			}
		}
		return allRooms;
	}

	private MapRoom getRandomRoom(List<MapRoom> allRooms, MapRoom... roomFilter) {
		Random random = new Random();
		MapRoom randomRoom = null;
		while (randomRoom == null) {
			int randomIndex = random.nextInt(allRooms.size());
			boolean valid = true;
			if (roomFilter != null) {
				for (MapRoom room : roomFilter) {
					if (room == allRooms.get(randomIndex)) {
						valid = false;
						break;
					}
				}
			}
			if (valid) {
				randomRoom = allRooms.get(randomIndex);
			}
		}
		return randomRoom;
	}

	public void generateRooms() {

		Random random = new Random();

		int gridWidth = 7;
		int gridHeight = 3;

		rooms = generateRoomGrid(gridWidth, gridHeight);

		List<MapRoom> allRooms = getAllMapRooms();
		MapRoom roomWithDoor = getRandomRoom(allRooms, null);
		roomWithDoor.setHasDoor(true);
		MapRoom roomWithKey = getRandomRoom(allRooms, roomWithDoor);
		roomWithKey.setHasKey(true);
		MapRoom roomWithChest = getRandomRoom(allRooms, roomWithDoor,
				roomWithKey);
		MapRoom roomWithHealer = getRandomRoom(allRooms, roomWithDoor,
				roomWithKey, roomWithChest);
		roomWithHealer.setHasHealer(true);

		// generate tiles
		for (int x = 0; x < rooms.length; x++) {
			for (int y = 0; y < rooms[x].length; y++) {
				if (rooms[x][y] != null) {
					if (rooms[x][y] == roomWithHealer) {
						generateHealerRoomTiles(rooms[x][y], x, y);
					} else {
						generateTiles(rooms[x][y], x, y);
					}
				}
			}
		}

		// generate enemies
		for (int xRoom = 0; xRoom < rooms.length; xRoom++) {
			for (int yRoom = 0; yRoom < rooms[xRoom].length; yRoom++) {
				if (rooms[xRoom][yRoom] != null) {
					MapRoom room = rooms[xRoom][yRoom];

					int maximumEnemiesPerRoom = new Random().nextInt(5) + 1;

					List<Coord> possibleSpawns = new LinkedList<Coord>();
					for (int x = Double.valueOf(room.getTiles().length * 0.25)
							.intValue(); x < room.getTiles().length
							- Double.valueOf(room.getTiles().length * 0.25)
									.intValue(); x++) {
						for (int y = 0; y < room.getTiles()[x].length; y++) {
							if (y > 0 && !room.getTiles()[x][y].isAccessible()
									&& room.getTiles()[x][y - 1].isAccessible()) {

								if (room.getElevator() == null
										|| (x < Double.valueOf(room
												.getElevator().getX()
												/ tileSize) - 4 || x > Double
												.valueOf(room.getElevator()
														.getX() / tileSize) + 4)) {
									possibleSpawns.add(new Coord(x, y));
								}

							}
						}
					}

					Collections.shuffle(possibleSpawns);

					if (room == roomWithDoor) {
						room.getEntities().add(
								new Door(possibleSpawns.get(0).getX()
										* tileSize, (possibleSpawns.get(0)
										.getY() - 1) * tileSize));
					}
					if (room == roomWithKey) {
						room.getEntities().add(
								new DoorKey(possibleSpawns.get(1).getX()
										* tileSize, (possibleSpawns.get(1)
										.getY() - 1) * tileSize));
					}
					if (room == roomWithChest) {
						room.getEntities().add(
								new Chest(possibleSpawns.get(2).getX()
										* tileSize, (possibleSpawns.get(2)
										.getY() - 1) * tileSize));
					}

					if (room == roomWithHealer) {

						//spawn healer
						Coord possibleSpawn = possibleSpawns.get(0);
						room.getEntities().add(
								new Healer(possibleSpawn.getX()
										* tileSize, (possibleSpawn
										.getY() - 1) * tileSize));
					} else {

						for (int i = 0; i < possibleSpawns.size()
								&& i < maximumEnemiesPerRoom; i++) {
							Coord possibleSpawn = possibleSpawns.get(i);
							int spawnPercent = new Random().nextInt(100);
							if (spawnPercent < 10) {
								room.getEntities().add(
										new Skeleton(possibleSpawn.getX()
												* tileSize, (possibleSpawn
												.getY() - 1) * tileSize));
							} else if (spawnPercent < 50) {
								room.getEntities().add(
										new Slime(possibleSpawn.getX()
												* tileSize, (possibleSpawn
												.getY() - 1) * tileSize));
							} else {
								room.getEntities().add(
										new Snake(possibleSpawn.getX()
												* tileSize, (possibleSpawn
												.getY() - 1) * tileSize));

							}
						}
					}
				}
			}
		}

	}

	private void connectRooms(MapRoomConnection mapRoomConnection) {

		if (mapRoomConnection.getDirection() == Direction.RIGHT) {
			mapRoomConnection.getMapRoom1().setConnectionRight(true);
			mapRoomConnection.getMapRoom2().setConnectionLeft(true);
		} else if (mapRoomConnection.getDirection() == Direction.LEFT) {
			mapRoomConnection.getMapRoom1().setConnectionLeft(true);
			mapRoomConnection.getMapRoom2().setConnectionRight(true);
		} else if (mapRoomConnection.getDirection() == Direction.DOWN) {
			mapRoomConnection.getMapRoom1().setConnectionDown(true);
			mapRoomConnection.getMapRoom2().setConnectionUp(true);
		} else if (mapRoomConnection.getDirection() == Direction.UP) {
			mapRoomConnection.getMapRoom1().setConnectionUp(true);
			mapRoomConnection.getMapRoom2().setConnectionDown(true);
		}

	}

	private MapRoom[][] generateRoomGrid(int width, int height) {

		Random random = new Random();

		int minRooms = Double.valueOf(width * height * 0.6).intValue();
		int maxRooms = random.nextInt((width) * (height)) - 1;
		if (maxRooms < minRooms) {
			maxRooms = minRooms;
		}

		MapRoom[][] grid = new MapRoom[width][height];

		int roomsAmount = 0;
		int currentX = random.nextInt(width);
		int currentY = random.nextInt(height);
		while (roomsAmount < maxRooms) {
			List<Coord> possibleCoords = new LinkedList<Coord>();
			if (currentX > 0) {
				possibleCoords.add(new Coord(currentX - 1, currentY));
			}
			if (currentX < width - 1) {
				possibleCoords.add(new Coord(currentX + 1, currentY));
			}
			if (currentY > 0) {
				possibleCoords.add(new Coord(currentX, currentY - 1));
			}
			if (currentY < height - 1) {
				possibleCoords.add(new Coord(currentX, currentY + 1));
			}

			Coord nextCoord = possibleCoords.get(random.nextInt(possibleCoords
					.size()));

			if (grid[nextCoord.getX()][nextCoord.getY()] == null) {
				MapRoom newRoom = new MapRoom();
				grid[nextCoord.getX()][nextCoord.getY()] = newRoom;

				Direction direction = null;
				if (currentX < nextCoord.getX()) {
					direction = Direction.RIGHT;
				} else if (currentX > nextCoord.getX()) {
					direction = Direction.LEFT;
				}
				if (currentY < nextCoord.getY()) {
					direction = Direction.DOWN;
				} else if (currentY > nextCoord.getY()) {
					direction = Direction.UP;
				}

				if (grid[currentX][currentY] != null) {
					connectRooms(new MapRoomConnection(
							grid[currentX][currentY], newRoom, direction));
				}

				roomsAmount++;
			}

			currentX = nextCoord.getX();
			currentY = nextCoord.getY();

		}

		return grid;

	}

	public MapRoom getCurrentRoom() {
		return currentMapRoom;
	}

	private void generateHealerRoomTiles(MapRoom mapRoom, int roomMapXIndex,
			int roomMapYIndex) {
		int width = 20;
		int height = 13;

		int elevatorX = new Random().nextInt(width - 2) + 1;

		BasicTile[][] map = new BasicTile[width][height];
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {

				map[x][y] = new BlueStoneBackground();
				if (x == 0 || y == 0 || x == map.length - 1
						|| y >= map[x].length - 3) {
					map[x][y] = new BlueStone();
				}

				// Elevator Path Down
				if (x == elevatorX && y > 0
						&& roomMapYIndex < rooms[0].length - 1
						&& mapRoom.isConnectionDown()) {
					map[x][y] = new BlueStoneBackground();

				} else if (x == elevatorX && y < 2 && roomMapYIndex > 0
						&& mapRoom.isConnectionUp()) {
					map[x][y] = new BlueStoneBackground();
				}
				if (y == map[x].length - 4
						&& x == elevatorX
						&& (mapRoom.isConnectionDown() || mapRoom
								.isConnectionUp())) {

					ElevatorDirection elevatorDirection = null;
					if (mapRoom.isConnectionDown() && mapRoom.isConnectionUp()) {
						elevatorDirection = ElevatorDirection.UPDOWN;
					} else if (mapRoom.isConnectionDown()) {
						elevatorDirection = ElevatorDirection.DOWN;
					} else if (mapRoom.isConnectionUp()) {
						elevatorDirection = ElevatorDirection.UP;
					}

					mapRoom.getEntities().add(
							new Elevator(x * tileSize, y * tileSize + 1,
									elevatorDirection));
					map[x][y] = new BlueStoneBackground();
				}

				// Left Door
				if (x == 0 && y > map[x].length - 8 && y < map[x].length - 4
						&& roomMapXIndex > 0 && mapRoom.isConnectionLeft()) {
					map[x][y] = new BlueStoneBackground();
				}
				// Right Door
				if (x == map.length - 1 && y > map[x].length - 8
						&& y < map[x].length - 4
						&& roomMapXIndex < rooms.length - 1
						&& mapRoom.isConnectionRight()) {
					map[x][y] = new BlueStoneBackground();
				}

			}
		}

		mapRoom.setTiles(map);
	}

	private void loadSnakeBossLevel() {

		EntityManager.getInstance().getEntities().clear();
		ParticleManager.getInstance().getParticles().clear();

		EntityManager.getInstance().getPlayer().setX(70);
		EntityManager.getInstance().getPlayer().setY(70);

		MapRoom[][] bossLevel = new MapRoom[1][1];

		MapRoom bossRoom = new MapRoom();
		bossLevel[0][0] = bossRoom;
		bossRoom.setVisible(true);
		bossRoom.setVisited(true);

		BasicTile[][] tiles = new BasicTile[20][13];

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				if (x == 0 || y == 0 || x == tiles.length - 1
						|| y > tiles[x].length - 4) {
					tiles[x][y] = new Dirt();
				} else {
					tiles[x][y] = new CaveBackground();
				}
				
				if(x == 2 && y >= tiles[x].length-4) {
					tiles[x][y] = new GrassyDirt();
				}
				
			}
		}

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				if (!tiles[x][y].isAccessible()) {
					beautifyFloorTile(x, y, tiles);
				}
			}
		}

		bossRoom.getEntities().add(new SnakeBoss(370, 170));
		EntityManager.getInstance().setEntities(bossRoom.getEntities());
		

		bossRoom.setTiles(tiles);
		currentMapXIndex = 0;
		currentMapYIndex = 0;
		this.currentMapRoom = bossRoom;
		rooms = bossLevel;

	}

}
