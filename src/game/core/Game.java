package game.core;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import engine.core.MigaGame;
import engine.entity.Entity;
import engine.input.InputManager;
import engine.rendering.FontManager;
import game.effects.EffectManager;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.map.MapManager;
import game.particle.Particle;
import game.particle.ParticleManager;
import game.rendering.TextureManager;
import game.ui.CoinBar;
import game.ui.CoinCounter;
import game.ui.DoorKeyBar;
import game.ui.HealthBar;
import game.ui.Inventory;
import game.ui.MiniMap;
import game.ui.textbox.TextboxManager;

public class Game extends MigaGame {

	private int resolutionWidth;
	private int resolutionHeight;
	
	private float drawX;
	private float drawY;
	private Particle particle = null;
	private float playerDrawX;
	private float playerDrawY;
	private Entity entity;
	private int xMax;
	private int yMax;
	private int xMin;
	private int yMin;
	private Player player;
	private float groundY;
	private float groundX;
	private boolean leftBorder;
	private boolean rightBorder;
	
	//
	private Entity tickEntity;

	public Game(int resolutionWidth, int resolutionHeight) {
		super(resolutionWidth, resolutionHeight, false);
		this.resolutionWidth = resolutionWidth;
		this.resolutionHeight = resolutionHeight;
		TextureManager.getInstance().init(resolutionHeight);

		// MapManager.getInstance().generateSnakeBossRoom();
		MapManager.getInstance().initMap();
		// MapManager.getInstance().generateDemoRoom();

	}

	@Override
	protected void render(float interpolation, long delta) {

		if(GlobalStates.getInstance().isGamePaused()) {
			interpolation = 0;
		}
		
		player = EntityManager.getInstance().getPlayer();

		groundY = 7 * MapManager.getInstance().getTileSize();
		groundX = player.getX()
				+ (player.getCurrentHorizontalSpeed() * interpolation);

		leftBorder = false;
		rightBorder = false;
		xMin = (int) player.getX() / MapManager.getInstance().getTileSize()
				- (this.resolutionWidth / 2)
				/ MapManager.getInstance().getTileSize() - 1;
		xMax = 0;
		if (xMin < 0) {

			if (xMin < -1) {
				groundX = resolutionWidth / 2;
				leftBorder = true;
				xMax = resolutionWidth / MapManager.getInstance().getTileSize();
			}
			xMin = 0;
		}

		if (!leftBorder) {

			xMax = (int) player.getX() / MapManager.getInstance().getTileSize()
					+ (this.resolutionWidth / 2)
					/ MapManager.getInstance().getTileSize() + 1;
			if (xMax > MapManager.getInstance().getMap().length) {
				if (xMax > MapManager.getInstance().getMap().length) {
					rightBorder = true;
					groundX = MapManager.getInstance().getMap().length
							* MapManager.getInstance().getTileSize()
							- resolutionWidth / 2;
					xMin = MapManager.getInstance().getMap().length
							- resolutionWidth
							/ MapManager.getInstance().getTileSize();
				}
				xMax = MapManager.getInstance().getMap().length;

			}
		}

		yMin = (int) player.getY() / MapManager.getInstance().getTileSize()
				- (this.resolutionHeight)
				/ MapManager.getInstance().getTileSize();
		if (yMin < 0) {
			yMin = 0;
		}
		yMax = (int) groundY / MapManager.getInstance().getTileSize()
				+ (this.resolutionHeight)
				/ MapManager.getInstance().getTileSize();
		if (yMax > MapManager.getInstance().getMap()[0].length) {
			yMax = MapManager.getInstance().getMap()[0].length;
		}

		// TILES
		TextureManager.getInstance().getTiles().get(0).begin();

		for (int x = xMin; x < xMax
				&& x < MapManager.getInstance().getMap().length; x++) {
			for (int y = yMin; y < yMax
					&& y < MapManager.getInstance().getMap()[x].length; y++) {
				drawX = x * MapManager.getInstance().getTileSize()
						+ this.resolutionWidth / 2
						// - (player.getX() + (player
						// .getCurrentHorizontalSpeed() * interpolation));
						- groundX;
				drawY = y
						* MapManager.getInstance().getTileSize()
						+ ((this.resolutionHeight / 8 + this.resolutionHeight / 2))
						- (groundY);

				MapManager.getInstance().getMap()[x][y].render(drawX, drawY,
						interpolation, delta);

			}
		}
		TextureManager.getInstance().getTiles().get(0).end();

		TextboxManager.getInstance().renderTextboxBackground();
		
		// SPRITES
		TextureManager.getInstance().getSprites().get(0).begin();

		// OTHER SPRITES
		// Entities
		for (int i = 0; i < EntityManager.getInstance().getEntities().size(); i++) {
			entity = EntityManager.getInstance().getEntities().get(i);
			if (entity.getX() >= xMin * MapManager.getInstance().getTileSize()
					&& entity.getX() < xMax
							* MapManager.getInstance().getTileSize()
					&& entity.getY() > yMin
							* MapManager.getInstance().getTileSize()
					&& entity.getY() < yMax
							* MapManager.getInstance().getTileSize()) {
				drawX = entity.getX() + this.resolutionWidth / 2
				// - (player.getX() + (player.getCurrentHorizontalSpeed() *
				// interpolation));
						- groundX;
				drawY = entity.getY()
						+ ((this.resolutionHeight / 8 + this.resolutionHeight / 2))
						- (groundY);

				entity.render(drawX, drawY, interpolation, delta);
			}
		}

		// PLAYER
		playerDrawX = this.resolutionWidth / 2;
		if (leftBorder) {
			playerDrawX = player.getX();
		} else if (rightBorder) {
			playerDrawX = player.getX()
					- (MapManager.getInstance().getMap().length
							* MapManager.getInstance().getTileSize() - resolutionWidth);
		}

		player.render(playerDrawX, player.getY()
				+ (this.resolutionHeight / 8 + this.resolutionHeight / 2)
				- groundY, interpolation, delta);

		// Particles
		for (int i = 0; i < ParticleManager.getInstance().getParticles().size(); i++) {
			 particle = ParticleManager.getInstance().getParticles()
					.get(i);

			if (particle.getX() > xMin * MapManager.getInstance().getTileSize()
					&& particle.getX() < xMax
							* MapManager.getInstance().getTileSize()
					&& particle.getY() > yMin
							* MapManager.getInstance().getTileSize()
					&& particle.getY() < yMax
							* MapManager.getInstance().getTileSize()) {
				drawX = particle.getX() + this.resolutionWidth / 2
				// - (player.getX() + (player.getCurrentHorizontalSpeed() *
				// interpolation));
						- groundX;
				drawY = particle.getY()
						+ ((this.resolutionHeight / 8 + this.resolutionHeight / 2))
						- (groundY);

				particle.render(drawX, drawY, interpolation, delta);
			}
		}

		// UI
		HealthBar.getInstance().renderHealthBar();
		// ItemBar.renderWeaponBar();
		CoinBar.renderCoinBar();
		MiniMap.getInstance().renderMiniMap();
		DoorKeyBar.renderDoorKeyBar();
		
		TextboxManager.getInstance().renderTextboxIcon();
		TextureManager.getInstance().getSprites().get(0).end();
		
		// UI Text
		CoinCounter.renderCoinCounter();
		
		if(Inventory.getInstance().isVisible()) {
			Inventory.getInstance().renderInventory();
		}
		
		TextboxManager.getInstance().renderTextboxText();

		// show Hitboxes
		// this.drawRect(player.getHitboxX(), player.getY(),
		// player.getHitboxWidth(), player.getHeight(), Color.yellow);

		// FONT
		
		  /*FontManager.getInstance().renderString( "fps " + this.currentFps +
		  " ticks " + this.currentTps, 10, 10, 0.6f,
		 TextureManager.getInstance().getFont(), Color.white);*/
		 
		// this.drawRect(100, 100, 100, 200, new Color(255,0,0,0.5f));

		// new FadeOut(this.resolutionWidth, this.resolutionHeight).render(0, 0,
		// interpolation, delta);
		/*for (int i = 0; i < EffectManager.getInstance().getEffects().size(); i++) {
			EffectManager.getInstance().getEffects().get(i)
					.render(0, 0, interpolation, delta);
		}*/
	}

	@Override
	protected void tick(long delta) {
		MapManager.getInstance().loadNextRoomIfNecessary();
		InputManager.getInstance().tick();

		if (InputManager.getInstance().isKeyPressed(Keyboard.KEY_I)) {
			InputManager.getInstance().resetKey(Keyboard.KEY_I);
			Inventory.getInstance().toggleVisibility();
		}
		
		if(Inventory.getInstance().isVisible()) {
			Inventory.getInstance().tick();
		}
		
		TextboxManager.getInstance().tick();

		if (!GlobalStates.getInstance().isGamePaused()) {
			for (int i = 0; i < EntityManager.getInstance().getEntities()
					.size(); i++) {
				tickEntity = EntityManager.getInstance().getEntities()
						.get(i);
				if (tickEntity.getX() < EntityManager.getInstance().getPlayer()
						.getX()
						+ resolutionWidth
						&& tickEntity.getX() > EntityManager.getInstance()
								.getPlayer().getX()
								- resolutionWidth) {
					EntityManager.getInstance().getEntities().get(i)
							.tick(delta);
				}
			}

			EntityManager.getInstance().getPlayer().tick(delta);

			for (int x = 0; x < MapManager.getInstance().getMap().length; x++) {
				for (int y = 0; y < MapManager.getInstance().getMap()[x].length; y++) {
					MapManager.getInstance().getMap()[x][y].tick(delta);
				}
			}

			for (int i = 0; i < ParticleManager.getInstance().getParticles()
					.size(); i++) {
				ParticleManager.getInstance().getParticles().get(i).tick(delta);
			}

			for (int i = 0; i < EffectManager.getInstance().getEffects().size(); i++) {
				EffectManager.getInstance().getEffects().get(i).tick(delta);
			}
		}
		
	}

	public static void main(String[] args) {
		new Game(1280, 720).start();
	}

}
