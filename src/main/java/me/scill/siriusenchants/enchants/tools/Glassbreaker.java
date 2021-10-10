package me.scill.siriusenchants.enchants.tools;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("unused")
public class Glassbreaker extends CustomEnchant {

	public Glassbreaker() {
		super("Glassbreaker");
		setGear(Gear.Pickaxe);
	}

	@Override
	public void onInteract(PlayerInteractEvent event, int level) {
		if (event.getAction() != Action.LEFT_CLICK_BLOCK || event.getClickedBlock() == null)
			return;

		if (event.getClickedBlock().getType().name().contains("GLASS"))
			event.getClickedBlock().breakNaturally(event.getPlayer().getItemInHand());

		// todo doesnt gimme glass
	}
}
