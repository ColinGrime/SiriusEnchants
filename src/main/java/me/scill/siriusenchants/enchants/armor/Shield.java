package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enchants.weapons.Venom;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;

@SuppressWarnings("unused")
public class Shield extends CustomEnchant {

	public Shield() {
		super("Shield");
		setRarity(Rarity.Rare);
		setGear(Gear.Chestplate);
	}

	@Override
	public void onPotionApply(PotionApplyByEnchantEvent event) {
		if (event.getCustomEnchant() instanceof Venom)
			event.setCancelled(true);
	}
}
