package me.scill.siriusenchants.enchants.disabled;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class Multishot extends CustomEnchant {

	public Multishot() {
		super("Multishot", 2);
		setRarity(Rarity.Rare);
		setGear(Gear.Bow);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		// fire bow event
	}
}
