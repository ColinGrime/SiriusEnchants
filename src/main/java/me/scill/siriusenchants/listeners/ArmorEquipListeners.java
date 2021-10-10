package me.scill.siriusenchants.listeners;

import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.enums.Armor;
import me.scill.siriusenchants.events.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipListeners implements Listener {

	private final boolean DEBUG = false;
	private final SiriusEnchants plugin;

	public ArmorEquipListeners(SiriusEnchants plugin) {
		this.plugin = plugin;
	}

	/**
	 * ArmorEquipEvent is run when armor is equipped through player inventory.
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!(event.getInventory() instanceof CraftingInventory))
			return;

		Player player = (Player) event.getWhoClicked();

		ItemStack cursor = event.getCursor();
		ItemStack currentItem = event.getCurrentItem();

		ItemStack newArmor = null;
		ItemStack oldArmor = null;

		// Debug messages
		if (DEBUG) {
			Bukkit.getLogger().info("[Debug] Click action: " + event.getAction());
			Bukkit.getLogger().info("[Debug] Cursor: " + cursor);
			Bukkit.getLogger().info("[Debug] Current item: " + currentItem);
			Bukkit.getLogger().info("[Debug] Hotbar button: " + event.getHotbarButton() + "\n\n\n");
		}

		// Handles taking armor off.
		if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
			newArmor = cursor;
			oldArmor = currentItem;
		}

		// Handles shift+click to put on armor.
		if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			boolean canEquip = true;

			for (ItemStack armor : player.getInventory().getArmorContents()) {
				if (Armor.getArmor(armor) == Armor.getArmor(currentItem)) {
					canEquip = false;
					break;
				}
			}

			if (canEquip) {
				newArmor = currentItem;
			}
		}

		// Handles keypadding to put on/off armor.
		if (event.getAction() == InventoryAction.HOTBAR_SWAP) {
			ItemStack hotbarItem = player.getInventory().getItem(event.getHotbarButton());

			if (Armor.getArmor(hotbarItem) != null)
				newArmor = hotbarItem;
		}

		// More debug!
		if (DEBUG) {
			Bukkit.getLogger().info("[Debug] New armor: " + newArmor);
			Bukkit.getLogger().info("[Debug] Old armor: " + oldArmor + "\n\n\n");
		}

		// Calls event.
		ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(player, newArmor, oldArmor);
		Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
	}


	/**
	 * ArmorEquipEvent is run when armor is equipped from dragging it on.
	 */
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		if (!(event.getInventory() instanceof CraftingInventory))
			return;

		Player player = (Player) event.getWhoClicked();

		// Armor slots are 5-8 here
		for (int i=5; i<=8; i++) {
			if (i == event.getRawSlots().stream().findFirst().orElse(-1)) {
				ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(player, event.getOldCursor(), event.getCursor());
				Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
				break;
			}
		}
	}

	/**
	 * ArmorEquipEvent is run when armor is equipped through rightclicking the hand.
	 */
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		Armor armor = Armor.getArmor(item);

		// Item is a valid armor piece & player doesn't have that armor type equipped yet.
		if (armor != null && player.getInventory().getArmorContents()[armor.getIndex()] != null) {
			ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(player, item, null);
			Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
		}
	}

	/**
	 * ArmorEquipEvent is run when armor is broken.
	 */
	@EventHandler
	public void onPlayerItemBreak(PlayerItemBreakEvent event) {
		if (Armor.getArmor(event.getBrokenItem()) == null)
			return;

		ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(event.getPlayer(), null, event.getBrokenItem());
		Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
	}

	/**
	 * ArmorEquipEvent is run when player is dead.
	 */
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		for (ItemStack armor : event.getEntity().getInventory().getArmorContents()) {
			if (armor != null) {
				ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(event.getEntity(), null, armor);
				Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
			}
		}
	}

	/**
	 * ArmorEquipEvent is run when armor is equipped through dispenser.
	 */
	@EventHandler
	public void onBlockDispense(BlockDispenseEvent event) {
		Armor armor = Armor.getArmor(event.getItem());
		if (armor == null)
			return;

		Location location = event.getBlock().getLocation();
		Player player = null;

		// Gets the player that would equip the armor piece.
		for (Entity entity : location.getWorld().getNearbyEntities(location, 1, 1, 1)) {
			if (entity instanceof Player) {
				player = (Player) entity;
				break;
			}
		}

		// Player doesn't have the armor type equipped yet.
		if (player == null || player.getInventory().getArmorContents()[armor.getIndex()] == null)
			return;

		Player tempPlayer = player;

		Bukkit.getScheduler().runTask(plugin, () -> {
			// Armor was equipped through the dispenser.
			if (event.getItem().equals(tempPlayer.getInventory().getArmorContents()[armor.getIndex()])) {
				ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(tempPlayer, event.getItem(), null);
				Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
			}
		});
	}
}
