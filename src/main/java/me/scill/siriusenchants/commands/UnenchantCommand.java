package me.scill.siriusenchants.commands;

import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.menus.UnenchantMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnenchantCommand extends AbstractCommand {

	private final SiriusEnchants plugin;

	public UnenchantCommand(SiriusEnchants plugin) {
		super("unenchant.use");
		this.plugin = plugin;
	}

	@Override
	protected boolean onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player))
			return true;

		Player player = (Player) sender;
		new UnenchantMenu(plugin, player).openInventory(player);

		return true;
	}

	@Override
	protected String getCommandHelpMessage() {
		return null;
	}
}