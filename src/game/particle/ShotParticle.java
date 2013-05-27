package game.particle;

import game.rendering.TextureManager;

import java.util.Random;

public class ShotParticle extends Particle {

	public ShotParticle(float x, float y) {
		super(x, y);
		this.setSpeed(5);
		this.setLifeTime(200);
		
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		if(this.getAngle() < 45) {
			TextureManager.getInstance().getSprites().get(33).draw(x, y, 32, 32, true);
		} else {
			TextureManager.getInstance().getSprites().get(33).draw(x, y, 32, 32, false);
		}
		
		
	}

}
