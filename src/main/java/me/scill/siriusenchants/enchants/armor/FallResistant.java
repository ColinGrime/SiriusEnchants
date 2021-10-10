package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.entity.EntityDamageEvent;

@SuppressWarnings("unused")
public class FallResistant extends CustomEnchant {

	public FallResistant() {
		super("Fallresistant");
		setRarity(Rarity.Common);
		setGear(Gear.Boots);
	}

	@Override
	public void onDamage(EntityDamageEvent event, int level) {
		if (event.getCause() == EntityDamageEvent.DamageCause.FALL)
			event.setCancelled(true);
	}
}
