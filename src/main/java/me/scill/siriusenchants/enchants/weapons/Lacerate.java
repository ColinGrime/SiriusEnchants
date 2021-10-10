package me.scill.siriusenchants.enchants.weapons;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class Lacerate extends CustomEnchant {

	public Lacerate() {
		super("Lacerate", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Hoe);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		if (!(event.getEntity() instanceof LivingEntity))
			return;

		LivingEntity attackedEntity = (LivingEntity) event.getEntity();

		switch (level) {
			case 1:
				attackedEntity.setHealth(attackedEntity.getHealth() - 1);
				event.setCancelled(true);
				break;
			case 2:
				attackedEntity.setHealth(attackedEntity.getHealth() - 2);
				event.setCancelled(true);
				break;
			case 3:
				attackedEntity.setHealth(attackedEntity.getHealth() - 3);
				event.setCancelled(true);
				break;
		}
	}
}
