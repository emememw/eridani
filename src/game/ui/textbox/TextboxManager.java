package game.ui.textbox;

import game.core.GlobalStates;

public class TextboxManager {

	private static final TextboxManager INSTANCE = new TextboxManager();
	
	public static TextboxManager getInstance() {
		return TextboxManager.INSTANCE;
	}
	
	private TextboxManager() {}
	
	private BasicTextbox textbox;
	
	public void renderTextboxBackground() {
		if(textbox != null) {
			textbox.renderBackground();
		}
	}
	
	public void renderTextboxText() {
		if(textbox != null) {
			textbox.renderText();
		}
	}
	
	public void renderTextboxIcon() {
		if(textbox != null) {
			textbox.renderIcons();
		}
	}
	
	public void tick() {
		if(textbox != null) {
			textbox.tick();
		}
	}
	
	public void showTextbox(BasicTextbox textbox) {
		this.textbox = textbox;
		GlobalStates.getInstance().setGamePaused(true);
	}
	
	public void hideTextbox() {
		this.textbox = null;
		GlobalStates.getInstance().setGamePaused(false);
	}
	
}
