package me.scill.siriusenchants.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CommonUtil {

	private CommonUtil() {}

	/**
	 * If the message contains any valid
	 * color codes, they will be applied.
	 *
	 * @param message any string
	 * @return colored version of the string
	 */
	public static String color(String message) {
		if (message == null)
			return "";
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	/**
	 * If the list of messages contains any
	 * valid color codes, they will be applied.
	 *
	 * @param messages any list of strings
	 * @return colored version of the list
	 */
	public static List<String> color(List<String> messages) {
		if (messages == null || messages.isEmpty())
			return new ArrayList<>();
		return messages.stream().map(CommonUtil::color).collect(Collectors.toList());
	}

	/**
	 * If the list of messages contains any
	 * valid color codes, they will be applied.
	 *
	 * @param messages any list of strings
	 * @return colored version of the list
	 */
	public static List<String> color(String...messages) {
		return color(Arrays.asList(messages));
	}

	/**
	 * Displays a colored message to the sender.
	 *
	 * @param sender any sender
	 * @param message String you want to send to the sender
	 */
	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(color(message));
	}

	/**
	 * Creates a PotionEffect object.
	 *
	 * @param potionType any potion type
	 * @param seconds number in seconds
	 * @param level level, starting from 1
	 * @return PotionEffect object
	 */
	public static PotionEffect createPotionEffect(PotionEffectType potionType, int seconds, int level) {
		return new PotionEffect(potionType, seconds * 20, level - 1);
	}
}