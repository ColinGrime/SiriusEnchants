package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;

@SuppressWarnings("unused")
public class TrueHearted extends CustomEnchant {

	public TrueHearted() {
		super("Truehearted");
		setRarity(Rarity.Mythical);
		setGear(Gear.Chestplate);
	}

	@Override
	public void onPotionApply(PotionApplyByEnchantEvent event) {
		if (event.getCustomEnchant() instanceof TrueHearted)
			event.setCancelled(true);
	}
}
