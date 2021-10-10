package me.scill.siriusenchants.listeners;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.utils.CommonUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class EnchantAttachListener implements Listener {

	private final SiriusEnchants plugin;

	public EnchantAttachListener(SiriusEnchants plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player) || event.getCursor() == null || event.getCurrentItem() == null)
			return;

		ItemStack ench = event.getCursor();
		ItemStack item = event.getCurrentItem();

		// Make sure item is valid & cursor is a valid enchanted book.
		if (item.getType() == Material.AIR || item.getType() == Material.ENCHANTED_BOOK || ench.getType() != Material.ENCHANTED_BOOK)
			return;

		// Retrieve custom enchants on the book.
		Map<CustomEnchant, Integer> customEnchants = plugin.getEnchantsManager().getCustomEnchants(ench);
		if (customEnchants.isEmpty())
			return;

		// Retrieves newly enchanted item.
		for (Map.Entry<CustomEnchant, Integer> customEnchant : customEnchants.entrySet())
			item = plugin.getEnchantsManager().addCustomEnchant(item, customEnchant.getKey(), customEnchant.getValue());

		// If successful, remove custom enchant book & get the enchanted item.
		if (item != null) {
			event.setCurrentItem(item);
			event.setCursor(null);
			event.setCancelled(true);
			((Player) event.getWhoClicked()).updateInventory();
		}

		// Unsuccessful
		else
			CommonUtil.sendMessage(event.getWhoClicked(), "&cThis item cannot be enchanted by the custom enchant.");
	}
}
