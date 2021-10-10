package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("unused")
public class SecondChance extends CustomEnchant {

	public SecondChance() {
		super("Secondchance", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Boots);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		Player attackedPlayer = (Player) event.getEntity();
		if (attackedPlayer.getHealth() > event.getDamage())
			return;
		// todo message
		switch (level) {
			case 1:
				if (RandomUtil.chance(10)) {
					attackedPlayer.setHealth(attackedPlayer.getMaxHealth() * 0.5);
					event.setCancelled(true);
				}
				break;
			case 2:
				if (RandomUtil.chance(20)) {
					attackedPlayer.setHealth(attackedPlayer.getMaxHealth() * 0.75);
					event.setCancelled(true);
				}
				break;
			case 3:
				if (RandomUtil.chance(30)) {
					attackedPlayer.setHealth(attackedPlayer.getMaxHealth());
					event.setCancelled(true);
				}
				break;
		}
	}
}
