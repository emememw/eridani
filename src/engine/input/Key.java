package engine.input;

public class Key {

	private int keyCode;
	private boolean down;
	
	public Key(int keyCode) {
		this.keyCode = keyCode;
		this.down = false;
	}
	
	public int getKeyCode() {
		return keyCode;
	}
	
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	
	
	
	
	
}
