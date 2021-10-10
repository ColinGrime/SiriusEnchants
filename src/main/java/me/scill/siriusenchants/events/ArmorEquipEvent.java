package me.scill.siriusenchants.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final ItemStack newArmor, oldArmor;

	public ArmorEquipEvent(Player player, ItemStack newArmor, ItemStack oldArmor) {
		this.player = player;
		this.newArmor = newArmor;
		this.oldArmor = oldArmor;
	}

	public Player getPlayer() {
		return player;
	}

	public ItemStack getNewArmor() {
		return newArmor;
	}

	public ItemStack getOldArmor() {
		return oldArmor;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
