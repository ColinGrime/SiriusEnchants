package me.scill.siriusenchants.listeners.enchants;

import me.scill.siriusenchants.EnchantsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponListeners implements Listener {

	private final EnchantsManager manager;

	public WeaponListeners(EnchantsManager manager) {
		this.manager = manager;
	}

	/**
	 * Enchants are activated when players attack entities.
	 */
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			ItemStack item = ((Player) event.getDamager()).getItemInHand();
			manager.getCustomEnchants(item).forEach((key, value) -> key.onDamage(event, value));
		}
	}
}
