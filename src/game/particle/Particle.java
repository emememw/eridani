package game.particle;

import java.util.Random;


import engine.logic.Tickable;
import engine.rendering.Renderable;
import game.rendering.TextureManager;

public class Particle implements Renderable, Tickable{

	private float x;
	private float y;
	private float speed;
	private long lifeTime;
	private int angle;
	
	public Particle(float x, float y) {
		this.x = x;
		this.y = y;
		this.angle = new Random().nextInt(360)+1;
		this.speed = new Random().nextInt(4)+6;
		this.lifeTime = 100;
	}
	
	@Override
	public void render(float x, float y, float interpolation, long delta) {
		
		TextureManager.getInstance().getSprites().get(26).draw(x, y, 32, 32, false);
		
	}


	@Override
	public void tick(long delta) {
		
		x += speed * Math.cos(angle);
		y += speed * Math.sin(angle);
		
		if(lifeTime > 0) {
			lifeTime -= delta;
		} else {
			ParticleManager.getInstance().unregisterParticle(this);
		}
		
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public long getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(long lifeTime) {
		this.lifeTime = lifeTime;
	}
	
	
	
	
	
}
