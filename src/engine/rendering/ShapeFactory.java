package engine.rendering;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

public class ShapeFactory {

	private static final ShapeFactory INSTANCE = new ShapeFactory();
	public static ShapeFactory getInstance() {
		return ShapeFactory.INSTANCE;
	}
	
	private ShapeFactory() {}
	
	public void drawRectangle(float x, float y, float width, float height, Color color) {
		TextureImpl.bindNone();
		color.bind();
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(x,y);
	    GL11.glVertex2f(x+width,y);
	    GL11.glVertex2f(x+width,y+height);
	    GL11.glVertex2f(x,y+height);
	    GL11.glEnd();
	    Color.white.bind();
	}
	
}
