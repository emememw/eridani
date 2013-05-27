package engine.rendering;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

public class FontManager {

	private final static FontManager INSTANCE = new FontManager();
	
	public static FontManager getInstance() {
		return FontManager.INSTANCE;
	}
	
	private FontManager() {}
	
	public void renderString(String text, float x, float y, float scale, List<Image2D> fontsheet, Color color) {
		text = text.toLowerCase();
		TextureImpl.bindNone();
		color.bind();
		char[] symbols = text.toCharArray();
		fontsheet.get(0).begin();
		for(int i = 0; i < symbols.length; i++) {
			Font font = getFontBySymbol(String.valueOf(symbols[i]));
			Image2D image = fontsheet.get(font.getFontImageIndex());
			image.draw(x + (i * image.getWidth())*scale, y, scale, false);
		}
		fontsheet.get(0).end();
		Color.white.bind();
		
	}
	
	private Font getFontBySymbol(String symbol) {
		Font result = null;
		for(Font font : Font.values()) {
			if(font.getSymbol().equals(symbol)) {
				result = font;
				break;
			}
		}
		return result;
	}
	
}
