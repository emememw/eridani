package game.particle;

import game.rendering.TextureManager;

import java.util.Random;

public class ElevatorParticle extends Particle {

	public ElevatorParticle(float x, float y) {
		super(x, y);
		this.setAngle(new Random().nextInt(6)+2);
		this.setSpeed(0.5f);
		this.setLifeTime(300);
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		TextureManager.getInstance().getSprites().get(35).draw(x, y, 24, 24, false);
		
	}

}
