package me.scill.siriusenchants.enchants.weapons;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class MonsterHunter extends CustomEnchant {

	public MonsterHunter() {
		super("Monsterhunter", 2);
		setRarity(Rarity.Rare);
		setGear(Gear.Weapons);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		if (!(event.getEntity() instanceof Monster))
			return;

		switch (level) {
			case 1:
				event.setDamage(event.getDamage() * 1.5);
				break;
			case 2:
				event.setDamage(event.getDamage() * 2.0);
				break;
		}
	}
}
