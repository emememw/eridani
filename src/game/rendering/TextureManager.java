package game.rendering;

import java.util.LinkedList;
import java.util.List;

import engine.rendering.Image2D;

public class TextureManager extends engine.rendering.TextureManager {

	private static final TextureManager INSTANCE = new TextureManager();
	
	public static TextureManager getInstance() {
		return TextureManager.INSTANCE;
	}
	
	private TextureManager() {}
	
	private List<Image2D> sprites = new LinkedList<Image2D>();
	private List<Image2D> font = new LinkedList<Image2D>();
	private List<Image2D> tiles = new LinkedList<Image2D>();
	
	public void init(int resolutionHeight) {
		this.sprites = this.loadTextureAndParseImages("sprites.png", 16, 4);
		this.font = this.loadTextureAndParseImages("fontsheet.png", 14, 2);
		this.tiles = this.loadTextureAndParseImages("tilesheet.png", 16, 4);
	}

	public List<Image2D> getSprites() {
		return sprites;
	}
	
	public List<Image2D> getFont()  {
		return this.font; 
	}

	public List<Image2D> getTiles() {
		return tiles;
	}
	
	
}
