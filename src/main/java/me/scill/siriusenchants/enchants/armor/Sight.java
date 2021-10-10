package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enchants.weapons.Blackout;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;

@SuppressWarnings("unused")
public class Sight extends CustomEnchant {

	public Sight() {
		super("Sight");
		setRarity(Rarity.Rare);
		setGear(Gear.Helmet);
	}

	@Override
	public void onPotionApply(PotionApplyByEnchantEvent event) {
		if (event.getCustomEnchant() instanceof Blackout)
			event.setCancelled(true);
	}
}
