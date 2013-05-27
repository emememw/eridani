package game.particle;


import java.util.LinkedList;
import java.util.List;

public class ParticleManager {

	private static final ParticleManager INSTANCE = new ParticleManager();
	public static ParticleManager getInstance() {
		return ParticleManager.INSTANCE;
	}
	
	private ParticleManager() {}
	
	private List<Particle> particles = new LinkedList<Particle>();
	
	public void registerParticle(Particle particle) {
		particles.add(particle);
	}
	
	public void unregisterParticle(Particle particle) {
		particles.remove(particle);
	}

	public List<Particle> getParticles() {
		return particles;
	}
	
	
	
}
