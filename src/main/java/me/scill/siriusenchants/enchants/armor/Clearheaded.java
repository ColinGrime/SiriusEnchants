package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enchants.weapons.Dizzy;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;

@SuppressWarnings("unused")
public class Clearheaded extends CustomEnchant {

	public Clearheaded() {
		super("Clearheaded");
		setRarity(Rarity.Rare);
		setGear(Gear.Helmet);
	}

	@Override
	public void onPotionApply(PotionApplyByEnchantEvent event) {
		if (event.getCustomEnchant() instanceof Dizzy)
			event.setCancelled(true);
	}
}
