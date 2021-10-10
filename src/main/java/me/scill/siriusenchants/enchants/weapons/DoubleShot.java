package me.scill.siriusenchants.enchants.weapons;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class DoubleShot extends CustomEnchant {

	public DoubleShot() {
		super("Doubleshot", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Bow);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		switch (level) {
			case 1:
				if (RandomUtil.chance(10))
					event.setDamage(event.getDamage() * 2);
				break;
			case 2:
				if (RandomUtil.chance(20))
					event.setDamage(event.getDamage() * 2);
				break;
			case 3:
				if (RandomUtil.chance(30))
					event.setDamage(event.getDamage() * 2);
				break;
		}
	}
}
