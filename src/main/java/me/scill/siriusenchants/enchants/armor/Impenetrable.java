package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.event.entity.EntityDamageEvent;

@SuppressWarnings("unused")
public class Impenetrable extends CustomEnchant {

	public Impenetrable() {
		super("Impenetrable", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Chestplate);
	}

	@Override
	public void onDamage(EntityDamageEvent event, int level) {
		switch (level) {
			case 1:
				if (RandomUtil.chance(5))
					event.setCancelled(true);
				break;
			case 2:
				if (RandomUtil.chance(10))
					event.setCancelled(true);
				break;
			case 3:
				if (RandomUtil.chance(15))
					event.setCancelled(true);
				break;
		}
	}
}
