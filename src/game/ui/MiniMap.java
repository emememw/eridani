package game.ui;

import game.map.MapManager;
import game.map.MapRoom;
import game.rendering.TextureManager;

public class MiniMap {

	private static final MiniMap INSTANCE = new MiniMap();
	public static MiniMap getInstance() {
		return MiniMap.INSTANCE;
	}
	
	private MiniMap() {}
	
	private MapRoom room = null;
	private int mapCellSize = 32;
	
	public void renderMiniMap() {
		
		for(int x = 0; x < MapManager.getInstance().getRooms().length; x++) {
			for(int y = 0; y < MapManager.getInstance().getRooms()[x].length; y++) {
				if(MapManager.getInstance().getRooms()[x][y] != null) {
					if(MapManager.getInstance().getRooms()[x][y].isVisited()) {
						TextureManager.getInstance().getSprites().get(27).draw(900 + (mapCellSize*x), 20 + (mapCellSize*y), mapCellSize, mapCellSize, false);
						room = MapManager.getInstance().getRooms()[x][y];
						if(room.isConnectionUp()) {
							TextureManager.getInstance().getSprites().get(30).draw(900 + (mapCellSize*x), 20 + (mapCellSize*y), mapCellSize, mapCellSize, false);
						}
						if(room.isConnectionDown()) {
							TextureManager.getInstance().getSprites().get(31).draw(900 + (mapCellSize*x), 20 + (mapCellSize*y), mapCellSize, mapCellSize, false);
						}
						if(room.isConnectionLeft()) {
							TextureManager.getInstance().getSprites().get(32).draw(900 + (mapCellSize*x), 20 + (mapCellSize*y), mapCellSize, mapCellSize, false);
						}
						if(room.isConnectionRight()) {
							TextureManager.getInstance().getSprites().get(32).draw(900 + (mapCellSize*x), 20 + (mapCellSize*y), mapCellSize, mapCellSize, true);
						}
						
						if(MapManager.getInstance().getRooms()[x][y].isDoorRoom()) {
							TextureManager.getInstance().getSprites().get(41).draw(900 + (mapCellSize*x)+mapCellSize/4, 20 + (mapCellSize*y)+mapCellSize/4, mapCellSize/2, mapCellSize/2, false);
						}
						
						if(MapManager.getInstance().getRooms()[x][y].isHealerRoom()) {
							TextureManager.getInstance().getSprites().get(22).draw(900 + (mapCellSize*x)+mapCellSize/4, 20 + (mapCellSize*y)+mapCellSize/4, mapCellSize/2, mapCellSize/2, false);
						}
						
					} else if(MapManager.getInstance().getRooms()[x][y].isVisible()) {
						TextureManager.getInstance().getSprites().get(29).draw(900 + (mapCellSize*x), 20 + (mapCellSize*y), mapCellSize, mapCellSize, false);
					}
					
					
					if(x == MapManager.getInstance().getCurrentMapXIndex() && y == MapManager.getInstance().getCurrentMapYIndex()) {
						TextureManager.getInstance().getSprites().get(28).draw(900 + (mapCellSize*x), 20 + (mapCellSize*y), mapCellSize, mapCellSize, false);
					}
				}
			}
		}
		
		
	}
	
}
