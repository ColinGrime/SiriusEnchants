package me.scill.siriusenchants.enchants.disabled;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class Thief extends CustomEnchant {

	public Thief() {
		super("Thief", 3);
		setRarity(Rarity.Rare);
		setGear(Gear.CloseCombat);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		event.getDamager().setFireTicks(40);
	}
}
