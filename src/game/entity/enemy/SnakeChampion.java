package game.entity.enemy;


public class SnakeChampion extends Snake {

	public SnakeChampion(float x, float y) {
		super(x, y);
		this.setWidth(Double.valueOf(this.getWidth() + this.getWidth()*0.5).intValue());
		this.setHeight(Double.valueOf(this.getHeight() + this.getHeight() * 0.5).intValue());
		this.setHp(this.getHp()*3);
		this.setCurrentHp(this.getHp());
		this.setSpeed(5);
	}

}
