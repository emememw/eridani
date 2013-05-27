package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

public class InputManager {

	private static final InputManager INSTANCE = new InputManager();
	public static InputManager getInstance() { 
		return InputManager.INSTANCE;
	}
	
	private InputManager() {
		Keyboard.enableRepeatEvents(false);
	}
	
	private Map<Integer, Key> keys = new HashMap<Integer, Key>();
	
		
	public void tick() {
		
		while( Keyboard.next() )
		{
		   int keyCode = Keyboard.getEventKey();
		   
		   if(keys.get(keyCode) == null) {
			   Key key = new Key(keyCode);
			   key.setDown(Keyboard.getEventKeyState());
			   keys.put(keyCode, key);
		   } else {
			   Key key = keys.get(keyCode);
			   key.setDown(Keyboard.getEventKeyState());
		   }
		   
		}
	}
	
	private Key getKeyByKeyCode(int keyCode) {
		return keys.get(keyCode);
	}
	
	public boolean isKeyPressed(int keyCode) {
		boolean result = false;
		Key key = getKeyByKeyCode(keyCode);
		if(key != null && key.isDown()) {
			result = true;
		}
		return result;
	}
	
	public void resetKey(int keyCode) {
		Key key = getKeyByKeyCode(keyCode);
		if(key != null) {
			key.setDown(false);
		}
	}

	
	
}
