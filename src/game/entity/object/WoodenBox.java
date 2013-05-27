package game.entity.object;

import engine.entity.Entity;
import engine.logic.Direction;
import game.entity.basic.BasicEntity;
import game.entity.basic.EntityManager;
import game.entity.basic.Shot;
import game.entity.player.Player;
import game.map.MapManager;
import game.particle.Particle;
import game.particle.ParticleManager;
import game.rendering.TextureManager;


public class WoodenBox extends BasicObject {

	
	public WoodenBox(float x, float y) {
		super(x, y, 64, 64, 1, 1);
		this.setAccessible(false);
		this.hp = 2;
		this.currentHp = this.hp;
	}

	protected void tickLogic(long delta) {
		
		
	}
	
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(17).draw(x, y, false);
	}
	
	public void touchedByEntity(Entity entity) {
		
		if(entity instanceof Player) {
			
			Player player = (Player) entity;
			if(player.getY() >= this.getY()) {
				if(player.isFalling()) {
					this.moveEntityHorizontal(player.getCurrentDirection(), 4);
				}  else {
					this.moveEntityHorizontal(player.getCurrentDirection(), 2);
				}
			}
			
			
		} else if(entity instanceof Shot) {
			this.currentHp--;
			if(this.currentHp <= 0) {
				EntityManager.getInstance().unregisterEntity(this);
				for(int i = 0; i < 30; i++) {
					ParticleManager.getInstance().registerParticle(new Particle(this.getX(), this.getY()));
				}
			}
		}
		
	}
	
}
