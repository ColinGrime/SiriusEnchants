package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;

@SuppressWarnings("unused")
public class Thaw extends CustomEnchant {

	public Thaw() {
		super("Thaw");
		setRarity(Rarity.Rare);
		setGear(Gear.Leggings);
	}

	@Override
	public void onPotionApply(PotionApplyByEnchantEvent event) {
		if (event.getCustomEnchant() instanceof Amber)
			event.setCancelled(true);
	}
}
