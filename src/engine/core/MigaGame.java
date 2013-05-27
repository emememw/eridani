package engine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

public class MigaGame implements Runnable {

	// TODO: http://lwjgl.org/wiki/index.php?title=The_Quad_textured

	private int ticksPerSeconds = 60;
	private int skipTicks = 1000 / ticksPerSeconds;
	private int maxFrameskip = 5;
	private long nextGameTick = getTickCount();
	private int loops;
	private boolean gameIsRunning = true;

	protected int currentFps = 0;
	protected int currentTps = 0;

	private int resolutionWidth;
	private int resolutionHeight;

	private long startTimestamp;

	private long getTickCount() {
		return System.currentTimeMillis() - startTimestamp;
	}

	public MigaGame(int resolutionWidth, int resolutionHeight,
			boolean fullscreen) {
		this.resolutionWidth = resolutionWidth;
		this.resolutionHeight = resolutionHeight;
		initializeDisplay(fullscreen);
	}

	private void initializeDisplay(boolean fullscreen) {
		
		try {
			DisplayMode displayMode = null;
			DisplayMode[] modes = Display.getAvailableDisplayModes();

			for (int i = 0; i < modes.length; i++) {
				System.out.println(modes[i].isFullscreenCapable()+" -> "+modes[i].getWidth()+" -> "+modes[i].getHeight());
				if (modes[i].getWidth() == this.resolutionWidth
						&& modes[i].getHeight() == this.resolutionHeight
						&& modes[i].isFullscreenCapable()) {
					displayMode = modes[i];
				}
			}
			if (displayMode == null) {
				displayMode = new DisplayMode(this.resolutionWidth,
						this.resolutionHeight);
			}
			Display.setDisplayMode(displayMode);
			Display.setFullscreen(fullscreen);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// enable alpha blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, this.resolutionWidth, this.resolutionHeight);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, this.resolutionWidth, this.resolutionHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		

	}

	protected void tick(long delta) {

	}

	protected void render(float interpolation, long delta) {

	}

	public void start() {
		run();
	}

	private void display(float interpolation, long delta) {

		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// set the color of the quad (R,G,B,A)
		// GL11.glColor3f(0.5f, 0.5f, 1.0f);

		// draw quad
		// drawTestQuad(x + (interpolation * speed), 400, 32);

		// TextureManager.getInstance().getSpriteSheet().get(1).begin();
		// TextureManager.getInstance().getSpriteSheet().get(1).draw(x +
		// (interpolation * speed), 50);
		// TextureManager.getInstance().getSpriteSheet().get(1).end();
		this.render(interpolation, delta);

		Display.update();
	}

	public static void drawRect(float x, float y, int width, int height, Color color) {

		TextureImpl.bindNone();
		color.bind();
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(x,y);
	    GL11.glVertex2f(x+width,y);
	    GL11.glVertex2f(x+width,y+height);
	    GL11.glVertex2f(x,y+height);
	    GL11.glEnd();
	    Color.white.bind();

	}

	public void run() {
		long fpsTime = System.currentTimeMillis();
		long tickTime = System.currentTimeMillis();
		long renderTime = System.currentTimeMillis();
		int fpsCount = 0;
		int tpsCount = 0;
		float interpolation = 0;
		while (!Display.isCloseRequested()) {
			loops = 0;
			while (getTickCount() > nextGameTick && loops < maxFrameskip) {
				tick(System.currentTimeMillis() - tickTime);
				tickTime = System.currentTimeMillis();
				nextGameTick += skipTicks;
				loops++;
				tpsCount++;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			interpolation = (float) (getTickCount() + skipTicks - nextGameTick)
					/ skipTicks;
			display(interpolation, System.currentTimeMillis() - renderTime);
			renderTime = System.currentTimeMillis();
			fpsCount++;

			if (System.currentTimeMillis() - fpsTime >= 1000) {
				fpsTime = System.currentTimeMillis();
				this.currentFps = fpsCount;
				fpsCount = 0;
				this.currentTps = tpsCount;
				tpsCount = 0;
				System.out
						.println("Fps: " + currentFps + " Tps: " + currentTps);
			}

		}
		Display.destroy();
		System.exit(0);

	}

	public static void main(String[] args) {
		MigaGame migaGame = new MigaGame(800, 600, false);
		migaGame.start();

	}

}
