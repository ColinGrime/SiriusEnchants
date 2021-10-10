package me.scill.siriusenchants.commands;

import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.data.PluginConfig;
import me.scill.siriusenchants.utils.CommonUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class SiriusCommand extends AbstractCommand {

	private final boolean DEBUG = false;
	private final SiriusEnchants plugin;

	public SiriusCommand(SiriusEnchants plugin) {
		super("sirius.use");
		this.plugin = plugin;
	}

	@Override
	protected boolean onCommand(CommandSender sender, String[] args) {
		if (args.length == 0)
			return false;

		// Reloads the entire plugin
		else if (args[0].equalsIgnoreCase("reload")) {
			PluginConfig.loadPluginConfig(plugin);
			plugin.loadEnchantsManager();
			plugin.getEnchantsConfig().loadEnchantsConfig();
			CommonUtil.sendMessage(sender, "&aSiriusEnchants has been reloaded!");
		}

		if (DEBUG) {
			// Clears debuffs on selected player.
			if (args[0].equalsIgnoreCase("clear")) {
				if (args.length > 1) {
					if (Bukkit.getPlayer(args[1]) == null) {
						CommonUtil.sendMessage(sender, "&cThe player " + args[1] + " does not exist");
						return true;
					}

					Player player = Bukkit.getPlayer(args[1]);
					player.setMaxHealth(20);
					player.setHealth(20);
					player.setFoodLevel(20);

					for (PotionEffect potionEffect : player.getActivePotionEffects())
						player.removePotionEffect(potionEffect.getType());
				} else
					CommonUtil.sendMessage(sender, "&cUsage: /sirius clear [player]");
			}

			// Displays item data on held item.
			else if (args[0].equalsIgnoreCase("data"))
				CommonUtil.sendMessage(sender, "&c[Debug] Item data: " + ((Player) sender).getItemInHand());

			// Displays custom enchants on held item.
			else if (args[0].equalsIgnoreCase("enchants")) {
				ItemStack item = ((Player) sender).getItemInHand();
				CommonUtil.sendMessage(sender, "&c[Debug] Item enchants: " + plugin.getEnchantsManager().getCustomEnchants(item));
			}
		}

		return true;
	}

	@Override
	protected String getCommandHelpMessage() {
		return null;
	}
}