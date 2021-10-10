package me.scill.siriusenchants.enchants.tools;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("unused")
public class Harvester extends CustomEnchant {

	public Harvester() {
		super("Harvester", 2);
		setGear(Gear.Hoe);
	}

	@Override
	public void onInteract(PlayerInteractEvent event, int level) {

	}
}
