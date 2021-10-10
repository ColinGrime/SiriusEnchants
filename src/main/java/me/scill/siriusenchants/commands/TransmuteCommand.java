package me.scill.siriusenchants.commands;

import org.bukkit.command.CommandSender;

public class TransmuteCommand extends AbstractCommand {

	public TransmuteCommand() {
		super("transmute.use");
	}

	@Override
	protected boolean onCommand(CommandSender sender, String[] args) {
		return false;
	}

	@Override
	protected String getCommandHelpMessage() {
		return null;
	}
}
