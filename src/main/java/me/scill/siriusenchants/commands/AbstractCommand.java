package me.scill.siriusenchants.commands;

import me.scill.siriusenchants.utils.CommonUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand implements CommandExecutor {

	private final String permission;

	public AbstractCommand(String permission) {
		this.permission = permission;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!permission.equals("") && !sender.hasPermission(permission))
			sender.sendMessage(CommonUtil.color("&cYou lack the permissions for this command."));

		else if (!onCommand(sender, args))
			sender.sendMessage(getCommandHelpMessage());

		return true;
	}

	protected abstract boolean onCommand(CommandSender sender, String[] args);

	protected abstract String getCommandHelpMessage();
}