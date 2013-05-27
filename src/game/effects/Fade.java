package game.effects;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

import engine.effects.Effect;

public class Fade extends Effect {

	private int width;
	private int height;
	private float alpha = 0.1f;
	private boolean out = true;
	private boolean fadeBack = false;
	
	public Fade(int width, int height, boolean fadeBack) {
		this.width = width;
		this.height = height;
		this.fadeBack = fadeBack;
	}
	
	@Override
	public void tick(long delta) {
		
		if(out) {
			alpha += 0.1f;
		} else if(fadeBack) {
			alpha -= 0.1f;
		}
		
		if(alpha >= 1f) {
			out = false;
		} else if(alpha <= 0f) {
			EffectManager.getInstance().unregisterEffect(this);
		}
		
		
	}

	@Override
	public void render(float x, float y, float interpolation, long delta) {
		TextureImpl.bindNone();
		new Color(0,0,0,alpha).bind();
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(0,0);
	    GL11.glVertex2f(0+width,0);
	    GL11.glVertex2f(0+width,0+height);
	    GL11.glVertex2f(0,0+height);
	    GL11.glEnd();
	    Color.white.bind();
	}
	
}
