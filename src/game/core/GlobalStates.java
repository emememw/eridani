package game.core;

public class GlobalStates {

	private final static GlobalStates INSTANCE = new GlobalStates();
	
	public static GlobalStates getInstance() {
		return GlobalStates.INSTANCE;
	}
	
	private GlobalStates() {}
	
	private boolean gamePaused;

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
	
	public void toggleGamePaused() {
		this.gamePaused = !this.gamePaused;
	}

	
	
}
