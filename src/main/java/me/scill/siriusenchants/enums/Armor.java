package me.scill.siriusenchants.enums;

import org.bukkit.inventory.ItemStack;

public enum Armor {

	Helmet(3),
	Chestplate(2),
	Leggings(1),
	Boots(0);

	private final int index;

	Armor(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	/**
	 * Retrieves Armor enum of the given ItemStack.
	 *
	 * @param item ItemStack
	 * @return Armor enum if the ItemStack is a valid armor piece, otherwise returns null.
	 */
	public static Armor getArmor(ItemStack item) {
		if (item == null)
			return null;

		for (Armor armor : Armor.values()) {
			if (item.getType().name().endsWith(armor.name().toUpperCase()))
				return armor;
		}

		return null;
	}
}
