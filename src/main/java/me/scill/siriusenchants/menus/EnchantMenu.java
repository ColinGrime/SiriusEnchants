package me.scill.siriusenchants.menus;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.menus.setup.Panel;
import me.scill.siriusenchants.utils.CommonUtil;
import me.scill.siriusenchants.utils.RandomUtil;
import me.scill.siriusenchants.utils.XpUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class EnchantMenu extends Panel {

	private final EnchantPreview enchantsPreview;

	public EnchantMenu(SiriusEnchants plugin, Player viewer) {
		super(plugin, Bukkit.createInventory(null, 9 * 3, "Custom Enchants"), viewer);
		this.enchantsPreview = new EnchantPreview(plugin, viewer);
	}

	@Override
	protected boolean setupInventory(String[] args) {
		// Admin allows you to free-buy enchants.
		boolean isAdmin = args != null && args.length > 0 && args[0].equalsIgnoreCase("true");

		setItem(11, Material.GLASS, (p, clickType) -> registerRarityEnchants(p, clickType, Rarity.Common, 1000, isAdmin));
		setItem(13, Material.GLASS, (p, clickType) -> registerRarityEnchants(p, clickType, Rarity.Rare, 5000, isAdmin));
		setItem(15, Material.GLASS, (p, clickType) -> registerRarityEnchants(p, clickType, Rarity.Mythical, 10000, isAdmin));

		return true;
	}

	private void registerRarityEnchants(Player player, ClickType clickType, Rarity rarity, int xpCost, boolean isAdmin) {
		List<CustomEnchant> customEnchants = rarity.getCustomEnchants();

		// Attempts to purchase a custom enchant.
		if (clickType == ClickType.LEFT) {
			// Free-buy
			if (isAdmin)
				giveRandomEnchant(player, customEnchants);

			// Checks if the player has enough experience.
			else if (XpUtil.getExp(player) >= xpCost) {
				XpUtil.changeExp(player, xpCost * -1);
				giveRandomEnchant(player, customEnchants);
			}

			// Insufficient experience.
			else
				CommonUtil.sendMessage(player, "&cYou are missing " + (xpCost - XpUtil.getExp(player)) + " XP!");
		}

		// Opens up the preview menu.
		else if (clickType == ClickType.RIGHT) {
			closeInventory(player);
			enchantsPreview.openInventory(player, rarity.name(), String.valueOf(isAdmin));
		}
	}

	/**
	 * Gives the player a random custom enchant.
	 *
	 * @param player Player receiving the custom enchant
	 * @param customEnchants valid list of custom enchants
	 */
	private void giveRandomEnchant(Player player, List<CustomEnchant> customEnchants) {
		CustomEnchant customEnchant = customEnchants.get(RandomUtil.random(customEnchants.size()));
		customEnchant.givePlayerItem(RandomUtil.random(customEnchant.getMaxLevel()) + 1, player);
	}
}
