package me.scill.siriusenchants.enums;

import me.scill.siriusenchants.CustomEnchant;

import java.util.ArrayList;
import java.util.List;

public enum Rarity {

	Common,
	Rare,
	Mythical;

	private final List<CustomEnchant> customEnchants = new ArrayList<>();

	public void addEnchant(CustomEnchant enchant) {
		customEnchants.add(enchant);
	}

	public List<CustomEnchant> getCustomEnchants() {
		return customEnchants;
	}
}
