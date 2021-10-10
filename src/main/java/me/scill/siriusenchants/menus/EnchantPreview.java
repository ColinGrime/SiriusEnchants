package me.scill.siriusenchants.menus;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.menus.setup.Panel;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class EnchantPreview extends Panel {

	protected EnchantPreview(SiriusEnchants plugin, Player viewer) {
		super(plugin, Bukkit.createInventory(null, 9 * 3, "Enchants Preview"), viewer);
	}

	@Override
	protected boolean setupInventory(String[] args) {
		if (args == null)
			return true;

		List<CustomEnchant> customEnchants = Rarity.valueOf(args[0]).getCustomEnchants();
		boolean isAdmin = args.length > 1 && args[1].equalsIgnoreCase("true");

		for (int i=0; i<customEnchants.size(); i++) {
			setItem(i, customEnchants.get(i).getItem(0));

			// Allows player to get any custom enchant they want from the preview menu.
			if (isAdmin) {
				CustomEnchant customEnchant = customEnchants.get(i);
				int maxLevel = customEnchant.getMaxLevel();
				setAction(i, (p, clickType) -> customEnchant.givePlayerItem(RandomUtil.random(maxLevel) + 1, p));
			}
		}

		return true;
	}
}
