package me.scill.siriusenchants.commands;

import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.menus.EnchantMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommand extends AbstractCommand {

	private final SiriusEnchants plugin;

	public AdminCommand(SiriusEnchants plugin) {
		super("ceadmin.use");
		this.plugin = plugin;
	}

	@Override
	protected boolean onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player))
			return true;

		Player player = (Player) sender;
		new EnchantMenu(plugin, player).openInventory(player, "true");

		return true;
	}

	@Override
	protected String getCommandHelpMessage() {
		return null;
	}
}