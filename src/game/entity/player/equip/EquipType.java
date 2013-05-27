package game.entity.player.equip;

public enum EquipType {
	PRIMARY_WEAPON("Primary Weapon"), SECONDARY_WEAPON("Secondary Weapon"), ARMOR("Armor"), TRINKET_1("Trinket 1"), TRINKET_2("Trinket 2");
	
	private String name;
	
	private EquipType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
	
}
