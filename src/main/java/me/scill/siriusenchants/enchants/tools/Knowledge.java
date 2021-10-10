package me.scill.siriusenchants.enchants.tools;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.block.BlockBreakEvent;

@SuppressWarnings("unused")
public class Knowledge extends CustomEnchant {

	public Knowledge() {
		super("Knowledge", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Pickaxe);
	}

	@Override
	public void onMine(BlockBreakEvent event, int level) {
		switch (level) {
			case 1:
				event.setExpToDrop((int) (event.getExpToDrop() * 1.25));
				break;
			case 2:
				event.setExpToDrop((int) (event.getExpToDrop() * 1.50));
				break;
			case 3:
				event.setExpToDrop((int) (event.getExpToDrop() * 1.75));
				break;
			case 4:
				event.setExpToDrop((int) (event.getExpToDrop() * 2.00));
				break;
		}
	}
}
