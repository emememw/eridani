package game.effects;

import java.util.LinkedList;
import java.util.List;

import engine.effects.Effect;

public class EffectManager {

	private static final EffectManager INSTANCE = new EffectManager();
	
	public static EffectManager getInstance() {
		return EffectManager.INSTANCE;
	}
	
	private EffectManager(){}
	
	private List<Effect> effects = new LinkedList<Effect>();
	
	public void registerEffect(Effect effect) {
		effects.add(effect);
	}
	
	public void unregisterEffect(Effect effect){
		effects.remove(effect);
	}

	public List<Effect> getEffects() {
		return effects;
	}
	
	
	
}
