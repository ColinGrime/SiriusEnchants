package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

@SuppressWarnings("unused")
public class Nourish extends CustomEnchant {

	public Nourish() {
		super("Nourish");
		setGear(Gear.Boots);
	}

	@Override
	public void onMove(PlayerMoveEvent event, int level) {
		Player player = event.getPlayer();
		if (player.getFoodLevel() < 20 && RandomUtil.chance(10))
			player.setFoodLevel(player.getFoodLevel() + 1);
	}
}
