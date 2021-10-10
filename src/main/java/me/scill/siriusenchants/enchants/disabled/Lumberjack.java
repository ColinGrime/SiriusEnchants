package me.scill.siriusenchants.enchants.disabled;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.block.BlockBreakEvent;

@SuppressWarnings("unused")
public class Lumberjack extends CustomEnchant {

	public Lumberjack() {
		super("Lucky", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Pickaxe);
	}

	@Override
	public void onMine(BlockBreakEvent event, int level) {

	}
}
