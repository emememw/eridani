package game.entity.object;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import engine.entity.Entity;
import engine.input.InputManager;
import engine.logic.Direction;
import engine.rendering.Renderable;
import game.entity.basic.EntityManager;
import game.entity.player.Player;
import game.map.MapManager;
import game.map.MapTransitionDirection;
import game.particle.ElevatorParticle;
import game.particle.ParticleManager;
import game.rendering.TextureManager;
import game.ui.SpeechBubbleArrowDown;
import game.ui.SpeechBubbleArrowUp;
import game.ui.SpeechBubbleArrowUpDown;


public class Elevator extends BasicObject {

	int destinationY = -1;
	private Direction movingDirection;
	
	private float initialX;
	private float initialY;
	
	private Renderable speechBubble = null;
	
	private ElevatorDirection possibleElevatorDirection;
	
	public Elevator(float x, float y, ElevatorDirection possibleElevatorDirection) {
		super(x, y, 64, 64, 1, 1);
		this.initialX = x;
		this.initialY = y;
		this.setAccessible(false);
		this.hp = 2;
		this.currentHp = this.hp;
		this.possibleElevatorDirection = possibleElevatorDirection;
	}

	public void tick(long delta) {
		
		speechBubble = null;
		
		 if(destinationY > -1 && this.getY() > destinationY && movingDirection == Direction.UP) {
			boolean result = this.moveEntityVertical(-6);
			EntityManager.getInstance().getPlayer().moveEntityVertical(-6);
			EntityManager.getInstance().getPlayer().setX(this.getX());
			if(!result) {
				movingDirection = null;
			}

		} else if(destinationY > -1 && this.getY() < destinationY && movingDirection == Direction.DOWN) {
			boolean result = this.moveEntityVertical(6);
			EntityManager.getInstance().getPlayer().moveEntityVertical(6);
			EntityManager.getInstance().getPlayer().setX(this.getX());
			if(!result) {
				movingDirection = null;
			}
		}
		 else {
			destinationY = -1;
			this.setCurrentVerticalSpeed(0);
			movingDirection = null;
		}
		 
		 if(movingDirection != null) {
			 speechBubble = null;
		 }
		
		 
		//spawn elevator particles
		for(int i = 0; i < 1; i++) {
			ParticleManager.getInstance().registerParticle(new ElevatorParticle(this.getX()+new Random().nextInt(this.getWidth()/2)+5, this.getY()+this.getHeight()/2));
		}
		
		
	}
	
	public void render(float x, float y, float interpolation, long delta) {
		TextureManager.getInstance().getSprites().get(34).draw(x, y + (interpolation * currentVerticalSpeed), false);
		
		if(speechBubble != null) {
			speechBubble.render(x+15, y-this.getHeight()*2, interpolation, delta);
		}
	}
	
	public void touchedByEntity(Entity entity) {
		if(entity instanceof Player) {
			
			Player player = (Player) entity;
			if(player.getY() <= this.getY()) {
				if(!player.isFalling()) {
					
					if(this.movingDirection == null && speechBubble == null && EntityManager.getInstance().getPlayer().getY() < this.getY()) {
						if(this.possibleElevatorDirection == ElevatorDirection.UPDOWN) {
							this.speechBubble = new SpeechBubbleArrowUpDown();
						}else if(this.possibleElevatorDirection == ElevatorDirection.UP) {
							this.speechBubble = new SpeechBubbleArrowUp();
						} else if(this.possibleElevatorDirection == ElevatorDirection.DOWN) {
							this.speechBubble = new SpeechBubbleArrowDown();
						}
					}
					
					if(EntityManager.getInstance().getPlayer().getY() < this.getY() && InputManager.getInstance().isKeyPressed(Keyboard.KEY_UP) && (this.possibleElevatorDirection == ElevatorDirection.UP || this.possibleElevatorDirection == ElevatorDirection.UPDOWN)) {
						setDestinationY(0);
					} else if(EntityManager.getInstance().getPlayer().getY() < this.getY() && InputManager.getInstance().isKeyPressed(Keyboard.KEY_DOWN) && (this.possibleElevatorDirection == ElevatorDirection.DOWN || this.possibleElevatorDirection == ElevatorDirection.UPDOWN)) {
						setDestinationY(MapManager.getInstance().getMap()[0].length*MapManager.getInstance().getTileSize());
					}
					
					if(EntityManager.getInstance().getPlayer().getY() < 0 && movingDirection == Direction.UP) {
						System.out.println(this+" up");
						MapManager.getInstance().nextRoom(MapTransitionDirection.UP);
					} else if(EntityManager.getInstance().getPlayer().getY() >= (MapManager.getInstance().getMap()[0].length-2)*MapManager.getInstance().getTileSize() && movingDirection == Direction.DOWN) {
						MapManager.getInstance().nextRoom(MapTransitionDirection.DOWN);
						System.out.println(this+" down");
					} 
				}  
			}
			
			
		} 
		
	}

	public int getDestinationY() {
		return destinationY;
	}

	public void setDestinationY(int destinationY) {
		this.destinationY = destinationY;
		if(this.getY() > destinationY) {
			movingDirection = Direction.UP;
		} else if(this.getY() < destinationY) {
			movingDirection = Direction.DOWN;
		}
	}

	public void resetToInitialPosition() {
		this.setX(this.initialX);
		this.setY(this.initialY);
		destinationY = -1;
		movingDirection = null;
	}
	
	
	
}
