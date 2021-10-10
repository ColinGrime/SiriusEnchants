package me.scill.siriusenchants.enchants.disabled;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class Secure extends CustomEnchant {

	public Secure() {
		super("Secure");
		setRarity(Rarity.Rare);
		setGear(Gear.Leggings);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {

	}
}
