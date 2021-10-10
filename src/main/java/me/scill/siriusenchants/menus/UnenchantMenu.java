package me.scill.siriusenchants.menus;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.menus.setup.Panel;
import me.scill.siriusenchants.utils.CommonUtil;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class UnenchantMenu extends Panel {

	public UnenchantMenu(SiriusEnchants plugin, Player viewer) {
		super(plugin, Bukkit.createInventory(null, 9, "Unenchant"), viewer);
	}

	@Override
	protected boolean setupInventory(String[] args) {
		ItemStack item = getViewer().getItemInHand();
		if (item.getType() == Material.ENCHANTED_BOOK) {
			CommonUtil.sendMessage(getViewer(), "&cYou cannot unenchant a custom enchantment book!");
			return false;
		}

		// Gets the custom enchants from the held item.
		Map<CustomEnchant, Integer> customEnchants = getPlugin().getEnchantsManager().getCustomEnchants(item);
		if (customEnchants.isEmpty()) {
			CommonUtil.sendMessage(getViewer(), "&cThis item does not have any custom enchants!");
			return false;
		}

		int index = 0;
		for (Map.Entry<CustomEnchant, Integer> customEnchantEntry : customEnchants.entrySet()) {

			// Gets the custom enchant, level, and its ItemStack form.
			CustomEnchant customEnchant = customEnchantEntry.getKey();
			int level = customEnchantEntry.getValue();
			ItemStack enchantItem = customEnchant.getItem(level);

			// Sets each slot in the inventory with the custom enchants from the held item.
			setItem(index++, enchantItem, (p, clickType) -> {
				if (clickType != ClickType.LEFT)
					return;

				// Updates the item with the removed custom enchant.
				getViewer().setItemInHand(getPlugin().getEnchantsManager().removeCustomEnchant(item, customEnchant));
				CommonUtil.sendMessage(getViewer(), "&aThe enchant " + enchantItem.getItemMeta().getDisplayName() + " has been removed!");

				// The player has a chance to get the custom enchant back!
				if (RandomUtil.chance(10)) {
					String lucky = "&aYou got lucky! %enchant% didn't break when dismantled!";
					customEnchant.givePlayerItem(level, p, lucky);
				}

				closeInventory(getViewer());
			});
		}

		return true;
	}
}