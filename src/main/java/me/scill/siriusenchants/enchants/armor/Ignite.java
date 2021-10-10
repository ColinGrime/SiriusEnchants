package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class Ignite extends CustomEnchant {

	public Ignite() {
		super("Ignite", 2);
		setRarity(Rarity.Rare);
		setGear(Gear.Helmet);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		switch (level) {
			case 1:
				if (RandomUtil.chance(10))
					event.getEntity().setFireTicks(3 * 20);
				break;
			case 2:
				if (RandomUtil.chance(20))
					event.getEntity().setFireTicks(6 * 20);
				break;
		}
	}
}
