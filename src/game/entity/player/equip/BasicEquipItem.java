package game.entity.player.equip;

public class BasicEquipItem {

	private EquipType equipType;
	private String name;
	
	private int dmgBonus = 0;
	private int speedBonus = 0;
	private int rangeBonus = 0;
	private int defBonus = 0;
	
	public BasicEquipItem(String name, EquipType equipType, int dmgBonus, int speedBonus, int rangeBonus, int defBonus) {
		this.name = name;
		this.equipType = equipType;
		this.dmgBonus = dmgBonus;
		this.speedBonus = speedBonus;
		this.rangeBonus = rangeBonus;
		this.defBonus = defBonus;
	}

	public EquipType getEquipType() {
		return equipType;
	}

	public void setEquipType(EquipType equipType) {
		this.equipType = equipType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDmgBonus() {
		return dmgBonus;
	}

	public void setDmgBonus(int dmgBonus) {
		this.dmgBonus = dmgBonus;
	}

	public int getSpeedBonus() {
		return speedBonus;
	}

	public void setSpeedBonus(int speedBonus) {
		this.speedBonus = speedBonus;
	}

	public int getRangeBonus() {
		return rangeBonus;
	}

	public void setRangeBonus(int rangeBonus) {
		this.rangeBonus = rangeBonus;
	}

	public int getDefBonus() {
		return defBonus;
	}

	public void setDefBonus(int defBonus) {
		this.defBonus = defBonus;
	}
	
	
	
	
}
