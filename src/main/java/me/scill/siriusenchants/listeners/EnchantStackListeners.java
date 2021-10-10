package me.scill.siriusenchants.listeners;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.SiriusEnchants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class EnchantStackListeners implements Listener {

	private final SiriusEnchants plugin;

	public EnchantStackListeners(SiriusEnchants plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		// The inventory is probably custom.
		if (event.getInventory().getHolder() == null)
			return;

		ItemStack cursor = event.getCursor();
		ItemStack currentItem = event.getCurrentItem();

		boolean isCursorAnEnchant = cursor != null && cursor.getType() == Material.ENCHANTED_BOOK;
		boolean isCurrentItemAnEnchant = currentItem != null && currentItem.getType() == Material.ENCHANTED_BOOK;

		// No enchanted books
		if (!isCursorAnEnchant && !isCurrentItemAnEnchant)
			return;

		// Cursor is the only enchant, and has only a stack of 1.
		if (isCursorAnEnchant && cursor.getAmount() == 1 && !isCurrentItemAnEnchant)
			return;

		// Current item is the only enchant, and has only a stack of 1
		if (isCurrentItemAnEnchant && currentItem.getAmount() == 1 && !isCursorAnEnchant)
			return;

		// Both are enchanted books, but they only have a stack of 1.
		if (isCursorAnEnchant && cursor.getAmount() == 1 && isCurrentItemAnEnchant && currentItem.getAmount() == 1)
			return;

		Player player = (Player) event.getWhoClicked();
		InventoryAction action = event.getAction();

		// Sets the custom enchant amount to 1 before dropping.
		if (action == InventoryAction.DROP_ONE_CURSOR || action == InventoryAction.DROP_ALL_CURSOR) {
			if (cursor != null) {
				cursor.setAmount(1);
				Bukkit.getScheduler().runTask(plugin, player::updateInventory);
			}
		}

		// Sets the custom enchant amount to 1 before dropping.
		else if (action == InventoryAction.DROP_ONE_SLOT || action == InventoryAction.DROP_ALL_SLOT) {
			if (currentItem != null) {
				currentItem.setAmount(1);
				Bukkit.getScheduler().runTask(plugin, player::updateInventory);
			}
		}

		// Custom enchants are stacked (2x, 3x), so manually swap them.
		else if (action.name().startsWith("PICKUP") || action.name().startsWith("PLACE")) {
			event.setCurrentItem(cursor);
			event.setCursor(currentItem);

			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		if (event.getOldCursor().getType() != Material.ENCHANTED_BOOK)
			return;

		// todo Fix this later
		if (event.getOldCursor().getAmount() > 1)
			event.setCancelled(true);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getType() != Material.ENCHANTED_BOOK)
			return;

		Player player = event.getPlayer();
		if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR)
			return;

		// Sets the custom enchant amount to 1 before dropping.
		ItemStack item = event.getItemDrop().getItemStack();
		item.setAmount(1);

		event.getItemDrop().setItemStack(item);
		player.setItemInHand(null);
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		if (event.getItem().getItemStack().getType() != Material.ENCHANTED_BOOK)
			return;

		ItemMeta enchantedMeta = event.getItem().getItemStack().getItemMeta();

		// A tick after pickup, check player inventory for upgraded enchanted.
		Bukkit.getScheduler().runTask(plugin, () -> {
			for (ItemStack item : event.getPlayer().getInventory().getContents()) {
				if (item == null || !item.getItemMeta().equals(enchantedMeta))
					continue;

				// Set the stack amount to indicate enchant level.
				Map<CustomEnchant, Integer> customEnchants = plugin.getEnchantsManager().getCustomEnchants(item);
				for (int level : customEnchants.values())
					item.setAmount(level);
			}
		});
	}

	@EventHandler
	public void onInventoryMoveItem(InventoryMoveItemEvent event) {
		if (event.getItem().getType() != Material.ENCHANTED_BOOK)
			return;

		// Removes any leftover enchant stacks from source hopper.
		removeCustomEnchants(event.getSource(), event.getItem());
	}

	@EventHandler
	public void onBlockDispenser(BlockDispenseEvent event) {
		if (event.getItem().getType() != Material.ENCHANTED_BOOK)
			return;

		InventoryHolder inventoryHolder = (InventoryHolder) event.getBlock().getState();

		// Due to how dispensers work, check next tick for any leftover enchant stacks & remove them.
		Bukkit.getScheduler().runTask(plugin, () -> removeCustomEnchants(inventoryHolder.getInventory(), event.getItem()));
	}

	/**
	 * Removes ItemStack from inventory, ensuring that stacked items get removed.
	 *
	 * @param inventory Inventory you want checked
	 * @param customEnchant ItemStack of the custom enchant
	 */
	private void removeCustomEnchants(Inventory inventory, ItemStack customEnchant) {
		for (int i=0; i<inventory.getSize(); i++) {
			if (inventory.getItem(i) != null && inventory.getItem(i).getItemMeta().equals(customEnchant.getItemMeta())) {
				inventory.setItem(i, new ItemStack(Material.AIR));
				break;
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!(event.getBlock().getState() instanceof InventoryHolder))
			return;

		InventoryHolder inventoryHolder = (InventoryHolder) event.getBlock().getState();
		Inventory inventory = inventoryHolder.getInventory();

		// Sets the custom enchant amount to 1 before dropping.
		for (ItemStack item : inventory) {
			if (item != null && item.getType() == Material.ENCHANTED_BOOK)
				item.setAmount(1);
		}
	}

	// todo full inv on menus
	// It seems as though Scill has attempted to dupe a custom enchant!
}
