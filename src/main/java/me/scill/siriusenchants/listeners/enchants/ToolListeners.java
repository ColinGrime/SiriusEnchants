package me.scill.siriusenchants.listeners.enchants;

import me.scill.siriusenchants.EnchantsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ToolListeners implements Listener {

	private final EnchantsManager manager;

	public ToolListeners(EnchantsManager manager) {
		this.manager = manager;
	}

	/**
	 * Enchants are activated when blocks are broken.
	 */
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		ItemStack item = event.getPlayer().getItemInHand();
		manager.getCustomEnchants(item).forEach((key, value) -> key.onMine(event, value));
	}

	/**
	 * Enchants are activated when items are damaged.
	 */
	@EventHandler
	public void onPlayerItemDamage(PlayerItemDamageEvent event) {
		manager.getCustomEnchants(event.getItem()).forEach((key, value) -> key.onItemDamage(event, value));
	}

	/**
	 * Enchants are activated when interactions with blocks are made.
	 */
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		ItemStack item = event.getPlayer().getInventory().getItemInHand();
		manager.getCustomEnchants(item).forEach((key, value) -> key.onInteract(event, value));
	}
}
