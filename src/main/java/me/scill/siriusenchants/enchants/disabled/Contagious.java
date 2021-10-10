package me.scill.siriusenchants.enchants.disabled;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.block.BlockBreakEvent;

@SuppressWarnings("unused")
public class Contagious extends CustomEnchant {

	public Contagious() {
		super("Lucky", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Pickaxe);
	}

	@Override
	public void onMine(BlockBreakEvent event, int level) {

	}
}
