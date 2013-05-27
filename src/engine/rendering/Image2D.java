package engine.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Image2D {
	private float normalizedWidth, normalizedHeight;
	private float width, height;
	private float centerX, centerY;
	private float textureOffsetX, textureOffsetY;
	private Texture texture;

	public Image2D(Texture texture) {
		this.texture = texture;
		this.normalizedWidth = texture.getWidth();
		this.normalizedHeight = texture.getHeight();
		this.width = texture.getImageWidth();
		this.height = texture.getImageHeight();
		this.centerX = width / 2f;
		this.centerY = height / 2f;
	}

	public Image2D copy() {
		Image2D img = new Image2D(texture);
		return img;
	}

	public Image2D getSubImage(float x, float y, float width, float height, float scaleFactor) {
		float tx = (x / this.width * normalizedWidth) + textureOffsetX;
		float ty = (y / this.height * normalizedHeight) + textureOffsetY;
		float tw = width / this.width * normalizedWidth;
		float th = height / this.height * normalizedHeight;

		Image2D img = copy();
		img.textureOffsetX = tx;
		img.textureOffsetY = ty;
		img.width = width * scaleFactor;
		img.height = height * scaleFactor;
		img.normalizedWidth = tw;
		img.normalizedHeight = th;
		img.centerX = width / 2f;
		img.centerY = height / 2f;
		return img;
	}

	public void begin() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		
		texture.bind();

		GL11.glBegin(GL11.GL_QUADS);

	}

	public void end() {
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	
	
	public void draw(float x, float y,  boolean flip) {
		this.draw(x,y,this.width, this.height,flip);
	}
	
	public void draw(float x, float y, float scale, boolean flip) {
		this.draw(x,y,this.width * scale, this.height * scale,flip);
	}
	
	public void draw(float x, float y, float drawWidth, float drawHeight, boolean flip) {
		if (!flip) {
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY); // TOP LEFT
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY + normalizedHeight); // BOTTOM
																					// LEFT
			GL11.glVertex2f(x, y + drawHeight);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY
					+ normalizedHeight); // BOTTOM RIGHT
			GL11.glVertex2f(x + drawWidth, y + drawHeight);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY); // TOP
																					// RIGHT
			GL11.glVertex2f(x + drawWidth, y);

		} else {

			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY); // TOP
																					// LEFT
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY
					+ normalizedHeight); // BOTTOM LEFT
			GL11.glVertex2f(x, y + drawHeight);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY + normalizedHeight); // BOTTOM
																					// RIGHT
			GL11.glVertex2f(x + 0.2f+ drawWidth, y + drawHeight);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY); // TOP RIGHT
			GL11.glVertex2f(x + drawWidth, y);

		}
	}
	
	/*public void draw(float x, float y, float scale, boolean flip) {
		if (!flip) {
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY); // TOP LEFT
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY + normalizedHeight); // BOTTOM
																					// LEFT
			GL11.glVertex2f(x, y + height * scale);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY
					+ normalizedHeight); // BOTTOM RIGHT
			GL11.glVertex2f(x + width * scale, y + height * scale);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY); // TOP
																					// RIGHT
			GL11.glVertex2f(x + width * scale, y);

		} else {

			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY); // TOP
																					// LEFT
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY
					+ normalizedHeight); // BOTTOM LEFT
			GL11.glVertex2f(x, y + height * scale);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY + normalizedHeight); // BOTTOM
																					// RIGHT
			GL11.glVertex2f(x + 0.2f+ width * scale, y + height * scale);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY); // TOP RIGHT
			GL11.glVertex2f(x + width * scale, y);

		}
	}*/

	/*public void draw(float x, float y, boolean flip) {
		if (!flip) {
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY); // TOP LEFT
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY + normalizedHeight); // BOTTOM
																					// LEFT
			GL11.glVertex2f(x, y + height);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY
					+ normalizedHeight); // BOTTOM RIGHT
			GL11.glVertex2f(x + width, y + height);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY); // TOP
																					// RIGHT
			GL11.glVertex2f(x + width, y);

		} else {

			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY); // TOP
																					// LEFT
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(textureOffsetX + normalizedWidth, textureOffsetY
					+ normalizedHeight); // BOTTOM LEFT
			GL11.glVertex2f(x, y + height);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY + normalizedHeight); // BOTTOM
																					// RIGHT
			GL11.glVertex2f(x + width+0.1f, y + height);
			GL11.glTexCoord2f(textureOffsetX, textureOffsetY); // TOP RIGHT
			GL11.glVertex2f(x + width, y);

		}
	}*/

	public float getNormalizedWidth() {
		return normalizedWidth;
	}

	public void setNormalizedWidth(float normalizedWidth) {
		this.normalizedWidth = normalizedWidth;
	}

	public float getNormalizedHeight() {
		return normalizedHeight;
	}

	public void setNormalizedHeight(float normalizedHeight) {
		this.normalizedHeight = normalizedHeight;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
